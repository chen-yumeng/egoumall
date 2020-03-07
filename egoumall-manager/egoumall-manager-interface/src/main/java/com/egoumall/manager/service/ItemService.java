package com.egoumall.manager.service;

import com.egoumall.common.pojo.EasyUIDataGridResult;
import com.egoumall.common.pojo.Item;
import com.egoumall.common.utils.EgouResult;

/**
 * @program: egoumall->ItemService
 * @description: 商品Service
 * @author: cg
 * @create: 2020-01-22 14:52
 **/

public interface ItemService {

    /**
     * 通过id获取商品信息
     * @param itemId 商品id
     * @return
     */
    Item getItemById(Long itemId);

    /**
     * 获取商品数据集
     * @param page 页数
     * @param rows 个数
     * @return
     */
    EasyUIDataGridResult getItemList(int page, int rows);

    /**
     * 添加商品
     * @param item Item的common.pojo
     * @param desc 商品描述
     * @return
     */
    EgouResult addItem(Item item, String desc);

    /**
     * 更新商品
     * @param item Item的common.pojo
     * @param desc 商品描述
     * @return
     */
    EgouResult updateItem(Item item, String desc);

    /**
     * 通过id删除多个商品
     * @param ids
     * @return
     */
    EgouResult deleteItems(String ids);

    /**
     * 通过商品id获取商品描述
     * @param itemId 商品id
     * @return
     */
    EgouResult getItemDescById(long itemId);

    /**
     * 通过id改变多个商品的状态
     * @param ids 商品id
     * @param status 商品状态
     * @return
     */
    EgouResult changeItemsStatus(String ids,int status);
}
