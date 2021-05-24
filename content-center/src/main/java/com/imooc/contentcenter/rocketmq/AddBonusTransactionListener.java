package com.imooc.contentcenter.rocketmq;

import com.alibaba.fastjson.JSON;
import com.imooc.contentcenter.dao.RocketmqTransactionLogMapper;
import com.imooc.contentcenter.domain.RocketmqTransactionLog;
import com.imooc.contentcenter.dto.AuditDTO;
import com.imooc.contentcenter.service.ShareService;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.Objects;

/**
 * @Author lawhen
 * @Date 2021/1/5
 */
@RocketMQTransactionListener(txProducerGroup = "tx-add-bonus-group")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddBonusTransactionListener implements RocketMQLocalTransactionListener {

    private final ShareService shareService;
    private final RocketmqTransactionLogMapper rocketmqTransactionLogMapper;

    /**
     * 执行本地事务
     * @param message
     * @param o
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        MessageHeaders headers = message.getHeaders();
        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        Integer shareId = Integer.valueOf((String) Objects.requireNonNull(headers.get("share_id")));
        //有个小坑,需要转成json
        AuditDTO dto = JSON.parseObject((String)headers.get("dto"),AuditDTO.class);
        try {
            //shareService.auditByIdWithEocketMqLog(shareId, (AuditDTO) o,transactionId);
            shareService.auditByIdWithEocketMqLog(shareId, (AuditDTO) dto,transactionId);
            return RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 通过数据库检查本地事务是否完成
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        MessageHeaders headers = message.getHeaders();
        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        RocketmqTransactionLog rocketmqTransactionLog = rocketmqTransactionLogMapper.selectOne(RocketmqTransactionLog.builder().transactionId(transactionId).build());
        if (rocketmqTransactionLog != null) {
            rocketmqTransactionLogMapper.delete(rocketmqTransactionLog);
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
