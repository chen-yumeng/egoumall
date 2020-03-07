package com.egoumall.manager.service.impl;

import com.egoumall.common.pojo.EasyUITreeNode;
import com.egoumall.common.mapper.ItemCatMapper;
import com.egoumall.common.pojo.ItemCat;
import com.egoumall.common.pojo.ItemCatExample;
import com.egoumall.manager.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: egoumall->ItemCatServiceImpl
 * @description: 商品分类ServiceImpl
 * @author: cg
 * @create: 2020-01-22 21:19
 **/
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public List<EasyUITreeNode> getItemCatList(long parentId) {
        ItemCatExample example = new ItemCatExample();
        ItemCatExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andParentIdEqualTo(parentId);
        //根据parentId查询子节点列表
        List<ItemCat> itemCats = itemCatMapper.selectByExample(example);
        //把列表转换成EasyUITreeNode列表
        List<EasyUITreeNode> resultList = new ArrayList<>();
        for (ItemCat cat : itemCats) {
            EasyUITreeNode node = new EasyUITreeNode();
            //设置属性
            node.setId(cat.getId());
            node.setText(cat.getName());
            node.setState(cat.getIsParent() ? "closed" : "open");
            resultList.add(node);
        }
        return resultList;
    }
}
