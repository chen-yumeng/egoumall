package com.egoumall.common.mapper;

import com.egoumall.common.pojo.Content;
import com.egoumall.common.pojo.ContentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ContentMapper {
    long countByExample(@Param("example") ContentExample example);

    int deleteByExample(@Param("example") ContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Content record);

    int insertSelective(Content record);

    List<Content> selectByExampleWithBLOBs(@Param("example") ContentExample example);

    List<Content> selectByExample(@Param("example") ContentExample example);

    Content selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Content record, @Param("example") ContentExample example);

    int updateByExampleWithBLOBs(@Param("record") Content record, @Param("example") ContentExample example);

    int updateByExample(@Param("record") Content record, @Param("example") ContentExample example);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKeyWithBLOBs(Content record);

    int updateByPrimaryKey(Content record);
}