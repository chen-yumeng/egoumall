package com.egoumall.manager.controller;

import com.egoumall.common.pojo.EasyUIDataGridResult;
import com.egoumall.common.utils.EgouResult;
import com.egoumall.manager.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: egoumall->ItemParamController
 * @description: ItemParam控制层
 * @author: cg
 * @create: 2020-01-28 18:02
 **/
@Controller
@RequestMapping("item/param/")
public class ItemParamController {

    @Autowired
    ItemParamService itemParamService;

    /**
     * 获取商品规格列表
     * @param page 页数
     * @param rows 个数
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public EasyUIDataGridResult getItemParamList(Integer page, Integer rows) {
        return itemParamService.getItemParamList(page, rows);
    }

    @RequestMapping("query/itemCatId/{itemCatId}")
    @ResponseBody
    public EgouResult checkItemCatId(@PathVariable long itemCatId) {
        return itemParamService.checkItemCatId(itemCatId);
    }

    @RequestMapping(value = "save/{itemCatId}", method = RequestMethod.POST)
    @ResponseBody
    public EgouResult saveItemParam(String paramData, @PathVariable long itemCatId) {
        return itemParamService.addItemParam(paramData, itemCatId);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public EgouResult deleteItemParam(String ids) {
        return itemParamService.deleteItemParam(ids);
    }

}
