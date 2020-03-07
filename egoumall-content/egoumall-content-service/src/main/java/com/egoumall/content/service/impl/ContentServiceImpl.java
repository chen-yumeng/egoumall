package com.egoumall.content.service.impl;

import com.egoumall.common.mapper.ContentMapper;
import com.egoumall.common.pojo.Content;
import com.egoumall.common.pojo.ContentExample;
import com.egoumall.common.pojo.EasyUIDataGridResult;
import com.egoumall.common.utils.EgouResult;
import com.egoumall.common.utils.IDUtils;
import com.egoumall.content.service.ContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: egoumall->ContentServiceImpl
 * @description: 内容Service实现类
 * @author: cg
 * @create: 2020-01-30 17:06
 **/
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public EasyUIDataGridResult getContentList(long categoryId, Integer page, Integer rows) {
        //设置分页信息
        PageHelper.startPage(page, rows);
        //执行查询
        ContentExample example = new ContentExample();
        ContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<Content> contents = contentMapper.selectByExampleWithBLOBs(example);
        PageInfo<Content> pageInfo = new PageInfo<>(contents);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(contents);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EgouResult addContent(Content content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insertSelective(content);
        return EgouResult.ok();
    }

    @Override
    public EgouResult deleteContent(String ids) {
        List<Long> idList = IDUtils.getIdList(ids);
        ContentExample example = new ContentExample();
        ContentExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);
        contentMapper.deleteByExample(example);
        return EgouResult.ok();
    }

    @Override
    public EgouResult updateContent(Content content) {
        content.setUpdated(new Date());
        contentMapper.updateByPrimaryKeySelective(content);
        return EgouResult.ok();
    }

}
