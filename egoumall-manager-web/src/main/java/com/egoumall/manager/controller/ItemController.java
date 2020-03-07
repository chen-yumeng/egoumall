package com.egoumall.manager.controller;

import com.egoumall.common.pojo.EasyUIDataGridResult;
import com.egoumall.common.utils.EgouResult;
import com.egoumall.common.pojo.Item;
import com.egoumall.manager.service.ItemCatService;
import com.egoumall.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: egoumall->ItemController
 * @description: 商品管理Controller
 * @author: cg
 * @create: 2020-01-22 14:56
 **/
@Controller
public class ItemController {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemCatService itemCatService;

    /**
     * 通过itemId查询商品
     * @param itemId 商品id
     * @return
     */
    @ResponseBody
    @RequestMapping("item/{itemId}")
    public EgouResult getItemById(@PathVariable Long itemId) {
        Item item = itemService.getItemById(itemId);
        return EgouResult.ok(item);
    }

    /**
     * 查询分类列表
     * @param page 页数
     * @param rows 行数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "item/list", method = RequestMethod.GET)
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        EasyUIDataGridResult itemList = itemService.getItemList(page, rows);
        return itemList;
    }

    /**
     * 添加商品
     * @param item Item的实体类
     * @param desc 商品描述
     * @return
     */
    @RequestMapping(value = "item/save", method = RequestMethod.POST)
    @ResponseBody
    public EgouResult addItem(Item item, String desc) {
        return itemService.addItem(item, desc);
    }

    /**
     * 通过id删除多个商品
     * @param ids 商品id
     * @return
     */
    @RequestMapping(value = "item/delete", method = RequestMethod.POST)
    @ResponseBody
    public EgouResult deleteItem(String ids) {
        return itemService.deleteItems(ids);
    }

    /**
     * 更新商品信息
     * @param item Item的实体类
     * @param desc 商品描述
     * @return
     */
    @RequestMapping(value = "item/update", method = RequestMethod.POST)
    @ResponseBody
    public EgouResult updateItem(Item item, String desc) {
        return itemService.updateItem(item, desc);
    }

    /**
     * 通过id获取商品描述
     * @param itemId 商品id
     * @return
     */
    @RequestMapping("item/query/desc/{itemId}")
    @ResponseBody
    public EgouResult getItemDescById(@PathVariable long itemId) {
        return itemService.getItemDescById(itemId);
    }

    /**
     * 通过id下架多个商品
     * @param ids 商品id
     * @return
     */
    @RequestMapping(value = "item/instock", method = RequestMethod.POST)
    @ResponseBody
    public EgouResult instockItem(String ids) {
        return itemService.changeItemsStatus(ids, 2);
    }

    /**
     * 通过id上架多个商品
     * @param ids 商品id
     * @return
     */
    @RequestMapping(value = "item/reshelf", method = RequestMethod.POST)
    @ResponseBody
    public EgouResult reshelfItem(String ids) {
        return itemService.changeItemsStatus(ids, 1);
    }

}
