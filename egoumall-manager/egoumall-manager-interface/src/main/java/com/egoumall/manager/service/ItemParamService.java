package com.egoumall.manager.service;

import com.egoumall.common.pojo.EasyUIDataGridResult;
import com.egoumall.common.utils.EgouResult;

/**
 * @program: egoumall->ItemParamService
 * @description: 商品分类规格Service
 * @author: cg
 * @create: 2020-01-28 18:07
 **/

public interface ItemParamService {

    /**
     * 获取商品分类规格数据集
     * @param page 页数
     * @param rows 个数
     * @return
     */
    EasyUIDataGridResult getItemParamList(Integer page, Integer rows);

    /**
     * 通过分类的id判断是否添加过规格
     * @param itemCatId 分类id
     * @return
     */
    EgouResult checkItemCatId(long itemCatId);

    /**
     * 添加商品规格
     * @param paramData 商品分类规格内容
     * @param ItemCatId 商品分类id
     * @return
     */
    EgouResult addItemParam(String paramData, long ItemCatId);

    /**
     * 通过id删除商品分类规格
     * @param ids 商品分类id
     * @return
     */
    EgouResult deleteItemParam(String ids);

}
