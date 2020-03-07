package com.egoumall.manager.service;

import com.egoumall.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 * @program: egoumall->ItemCatService
 * @description: 商品分类Service
 * @author: cg
 * @create: 2020-01-22 21:17
 **/

public interface ItemCatService {

    /**
     * 获取父id下分类列表
     * @param parentId 父节点id
     * @return
     */
    List<EasyUITreeNode> getItemCatList(long parentId);

}
