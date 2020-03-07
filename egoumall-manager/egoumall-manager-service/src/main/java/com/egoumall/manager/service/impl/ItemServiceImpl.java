package com.egoumall.manager.service.impl;

import com.egoumall.common.mapper.ItemDescMapper;
import com.egoumall.common.mapper.ItemMapper;
import com.egoumall.common.pojo.*;
import com.egoumall.common.utils.EgouResult;
import com.egoumall.common.utils.IDUtils;
import com.egoumall.manager.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @program: egoumall->ItemServiceImpl
 * @description: 商品服务实现类
 * @author: cg
 * @create: 2020-01-22 14:53
 **/
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public Item getItemById(Long itemId) {
        Item item = itemMapper.selectByPrimaryKeyWithItemCatName(itemId);
        if (item != null) {
            return item;
        }
        return null;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        //设置分页信息
        PageHelper.startPage(page, rows);
        //执行查询
        ItemExample example = new ItemExample();
        List<Item> items = itemMapper.selectByExampleWithItemCatName(example);
        PageInfo<Item> pageInfo = new PageInfo<>(items);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(items);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EgouResult addItem(Item item, String desc) {
        //生成商品id
        long itemId = IDUtils.genItemId();
        //补全item的属性
        item.setId(itemId);
        //商品状态，1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //向item表插入数据
        itemMapper.insert(item);
        //创建商品描述表的common.pojo对象
        ItemDesc itemDesc = new ItemDesc();
        //补全商品描述表的common.pojo的属性
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        //向ItemDesc表插入数据
        itemDescMapper.insert(itemDesc);
        //返回成功
        return EgouResult.ok();
    }

    @Override
    public EgouResult updateItem(Item item, String desc) {
        item.setUpdated(new Date());
        itemMapper.updateByPrimaryKeySelective(item);
        ItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(item.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setUpdated(new Date());
        ItemDescExample example = new ItemDescExample();
        ItemDescExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemDesc.getItemId());
        itemDescMapper.updateByExampleSelective(itemDesc, example);
        return EgouResult.ok();
    }

    @Override
    public EgouResult deleteItems(String ids) {
        List<Long> idList = IDUtils.getIdList(ids);
        ItemExample example = new ItemExample();
        ItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);
        itemMapper.deleteByExample(example);
        return EgouResult.ok();
    }

    @Override
    public EgouResult getItemDescById(long itemId) {
        ItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
        Map<String, Object> map = new HashMap<>();
        map.put("itemDesc", itemDesc.getItemDesc());
        return EgouResult.ok(map);
    }

    @Override
    public EgouResult changeItemsStatus(String ids, int status) {
        //Status商品状态，1-正常，2-下架，3-删除
        //判断传入的id一个还是多个
        if (ids.contains(",")) {
            //多个
            //切割
            String[] split = ids.split(",");
            List<Long> idList = new ArrayList<>();
            for (int i = 0; i < split.length; i++) {
                idList.add(Long.parseLong(split[i]));
            }
            ItemExample example = new ItemExample();
            ItemExample.Criteria criteria = example.createCriteria();
            //设置条件
            criteria.andIdIn(idList);
            List<Item> items = itemMapper.selectByExample(example);
            //遍历
            for (Item item : items) {
                //设置状态
                item.setStatus((byte) status);
                //更新商品
                itemMapper.updateByPrimaryKeySelective(item);
            }
        } else {
            //一个
            //通过id获取商品信息
            Item item = itemMapper.selectByPrimaryKey(Long.parseLong(ids));
            //修改状态
            item.setStatus((byte) status);
            //更新商品
            itemMapper.updateByPrimaryKey(item);
        }
        return EgouResult.ok();
    }

}
