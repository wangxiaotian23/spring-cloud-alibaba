package com.imooc.contentcenter.dao;

import com.imooc.contentcenter.domain.Share;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ShareMapper extends Mapper<Share> {

    List<Share> selectByParam(@Param("title") String title);
}