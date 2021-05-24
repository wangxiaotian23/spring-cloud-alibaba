package com.imooc.contentcenter.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.contentcenter.dao.MidUserShareMapper;
import com.imooc.contentcenter.dao.RocketmqTransactionLogMapper;
import com.imooc.contentcenter.dao.ShareMapper;
import com.imooc.contentcenter.domain.MidUserShare;
import com.imooc.contentcenter.domain.RocketmqTransactionLog;
import com.imooc.contentcenter.domain.Share;
import com.imooc.contentcenter.dto.*;
import com.imooc.contentcenter.feignclient.UserCenterFeignClient;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


/**
 * @Author lawhen
 * @Date 2020/12/28
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareService {

    private final RestTemplate restTemplate;

    private final ShareMapper shareMapper;

    private final UserCenterFeignClient userCenterFeignClient;

    //private final DiscoveryClient discoveryClient;

    private final RocketMQTemplate rocketMQTemplate;

    private final RocketmqTransactionLogMapper rocketmqTransactionLogMapper;

    private final Source source;

    private final MidUserShareMapper midUserShareMapper;

    public ShareDTO findById(Integer id) {
        Share share = shareMapper.selectByPrimaryKey(id);
        //获取用户中心的所有实例
        //List<ServiceInstance> instances = discoveryClient.getInstances("user-center");
        //String targetURL = instances.stream().map(instance->instance.getUri().toASCIIString()+"/users/{id}")
        //       .findFirst().orElseThrow(()->new IllegalArgumentException("当前没有实例"));
        //List<String> targetURLS = instances.stream().map(instance->instance.getUri().toASCIIString()+"/users/{id}")
        //        .collect(Collectors.toList());
        //UserDTO userDTO = restTemplate.getForObject(targetURL,UserDTO.class,share.getUserId());
        //UserDTO userDTO = restTemplate.getForObject("http://user-center/users/{id}",UserDTO.class,share.getUserId());
        UserDTO userDTO = userCenterFeignClient.findById(share.getUserId());
        ShareDTO shareDTO = new ShareDTO();
        BeanUtils.copyProperties(share, shareDTO);
        shareDTO.setWxNickname(userDTO.getWxNickname());
        return shareDTO;
    }

    public Share auditById(Integer id, AuditDTO auditDTO) {

        Share share = shareMapper.selectByPrimaryKey(id);
        if (share == null) {
            throw new IllegalArgumentException("参数非法！该分享不存在");
        }
        if (!Objects.equals("NOT_YET", share.getAuditStatus())) {
            throw new IllegalArgumentException("参数非法！该分享已审核或拒绝");
        }
        //如果是pass那么发消息给rocketmq，让用户中心去消费，并为发布人添加积分
        String transactionId = UUID.randomUUID().toString();
        if (AuditStatusEnum.PASS.equals(auditDTO.getAuditStatusEnum())) {
            //发送半消息,实现分布式事务
            rocketMQTemplate.sendMessageInTransaction(
                    "tx-add-bonus-group",
                    "add-bonus",
                    MessageBuilder
                            .withPayload(UserAddBonusMsgDTO.builder().userId(share.getUserId()).bonus(50).build())
                            .setHeader(RocketMQHeaders.TRANSACTION_ID, transactionId)
                            .setHeader("share_id", id)
                            .build(),
                    auditDTO
            );
        } else {
            auditByIdInDB(id, auditDTO);
        }
        //rocketMQTemplate.convertAndSend("bonus", UserAddBonusMsgDTO.builder().userId(share.getUserId()).bonus(50).build());

        return share;
    }



    public Share auditByIdStream(Integer id, AuditDTO auditDTO) {

        Share share = shareMapper.selectByPrimaryKey(id);
        if (share == null) {
            throw new IllegalArgumentException("参数非法！该分享不存在");
        }
        if (!Objects.equals("NOT_YET", share.getAuditStatus())) {
            throw new IllegalArgumentException("参数非法！该分享已审核或拒绝");
        }
        //如果是pass那么发消息给rocketmq，让用户中心去消费，并为发布人添加积分
        String transactionId = UUID.randomUUID().toString();

        if (AuditStatusEnum.PASS.equals(auditDTO.getAuditStatusEnum())) {
            //发送半消息,实现分布式事务
            this.source.output().send(MessageBuilder
                    .withPayload(UserAddBonusMsgDTO.builder().userId(share.getUserId()).bonus(50).build())
                    .setHeader(RocketMQHeaders.TRANSACTION_ID, transactionId)
                    .setHeader("share_id", id)
                    .setHeader("dto", JSON.toJSONString(auditDTO))
                    .build());
        } else {
            auditByIdInDB(id, auditDTO);
        }
        //rocketMQTemplate.convertAndSend("bonus", UserAddBonusMsgDTO.builder().userId(share.getUserId()).bonus(50).build());

        return share;
    }


    @Transactional(rollbackFor = Exception.class)
    public void auditByIdInDB(Integer id, AuditDTO auditDTO) {
        //审核资源
        shareMapper.updateByPrimaryKeySelective(Share.builder()
                .id(id)
                .auditStatus(auditDTO.getAuditStatusEnum().toString())
                .reason(auditDTO.getReason())
                .build());
    }

    @Transactional(rollbackFor = Exception.class)
    public void auditByIdWithEocketMqLog(Integer id, AuditDTO auditDTO, String transactionId) {
        this.auditByIdInDB(id, auditDTO);
        rocketmqTransactionLogMapper.insertSelective(
                RocketmqTransactionLog.builder()
                        .transactionId(transactionId)
                        .log("审核分享")
                        .build());
    }

    public PageInfo<Share> q(String title, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Share> shareList = this.shareMapper.selectByParam(title);
        return new PageInfo<Share>(shareList);
    }

    public Share exchangeById(Integer id, HttpServletRequest request) {
        //根据id查询share，校验是否存在
        Share share = this.shareMapper.selectByPrimaryKey(id);
        if (share==null){
            throw new IllegalArgumentException("该分享不存在!");
        }
        Integer userId = (Integer) request.getAttribute("id");
        MidUserShare midUserShare = this.midUserShareMapper.selectOne(
                MidUserShare.builder()
                        .userId(userId)
                        .shareId(share.getId())
                        .build()
        );
        if (midUserShare!=null){
            return share;
        }
        //根据当前登录的用户id查询积分是否足够

        UserDTO userDTO = this.userCenterFeignClient.findById(userId);
        if (share.getPrice()>userDTO.getBonus()){
            throw new IllegalArgumentException("用户积分不够用!");
        }
        //扣减积分
        userCenterFeignClient.addBonus(UserAddBonusDTO.builder().userId(userId).bonus(-share.getPrice()).build());
        //插入数据
        midUserShareMapper.insert(MidUserShare.builder()
                .shareId(share.getId())
                .userId(userId)
                .build());
        return share;
    }
}
