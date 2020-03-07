package com.egoumall.manager.service.impl;

import com.egoumall.common.mapper.ItemParamMapper;
import com.egoumall.common.pojo.EasyUIDataGridResult;
import com.egoumall.common.pojo.ItemParam;
import com.egoumall.common.pojo.ItemParamExample;
import com.egoumall.common.utils.EgouResult;
import com.egoumall.common.utils.IDUtils;
import com.egoumall.manager.service.ItemParamService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: egoumall->ItemParamServiceImpl
 * @description: 商品分类规格Service实现
 * @author: cg
 * @create: 2020-01-28 18:10
 **/
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    ItemParamMapper itemParamMapper;

    @Override
    public EasyUIDataGridResult getItemParamList(Integer page, Integer rows) {
        //设置分页信息
        PageHelper.startPage(page, rows);
        //执行查询
        ItemParamExample example = new ItemParamExample();
        List<ItemParam> items = itemParamMapper.selectByExampleWithBLOBsHaveItemCatName(example);
        PageInfo<ItemParam> pageInfo = new PageInfo<>(items);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(items);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EgouResult checkItemCatId(long itemCatId) {
        ItemParamExample example = new ItemParamExample();
        ItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(itemCatId);
        List<ItemParam> itemParams = itemParamMapper.selectByExample(example);
        if (itemParams == null) {
            return EgouResult.ok();
        } else {
            return EgouResult.build(100, "该类目已经添加，请选择其他类目。", itemParams);
        }
    }

    @Override
    public EgouResult addItemParam(String paramData, long ItemCatId) {
        ItemParam param = new ItemParam();
        param.setItemCatId(ItemCatId);
        param.setParamData(paramData);
        param.setCreated(new Date());
        param.setUpdated(new Date());
        if (itemParamMapper.insert(param) != 0) {
            return EgouResult.ok();
        }
        return EgouResult.build(100, "插入失败，请联系管理员!", null);
    }

    @Override
    public EgouResult deleteItemParam(String ids) {
        List<Long> idList = IDUtils.getIdList(ids);
        ItemParamExample example = new ItemParamExample();
        ItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);
        itemParamMapper.deleteByExample(example);
        return EgouResult.ok();
    }
}
