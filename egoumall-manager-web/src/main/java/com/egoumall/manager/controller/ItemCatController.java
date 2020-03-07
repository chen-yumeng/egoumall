package com.egoumall.manager.controller;

import com.egoumall.common.pojo.EasyUITreeNode;
import com.egoumall.manager.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: egoumall->ItemCatController
 * @description: 商品分类管理Controller
 * @author: cg
 * @create: 2020-01-22 21:32
 **/
@Controller
public class ItemCatController {

    @Autowired
    ItemCatService itemCatService;

    /**
     * 获取分类列表
     * @param parentId 父节点id
     * @return
     */
    @RequestMapping(value = "item/itemCat/list", method = RequestMethod.POST)
    @ResponseBody
    public List<EasyUITreeNode> getItemCatList(@RequestParam(value = "id",defaultValue = "0") Long parentId) {
        List<EasyUITreeNode> itemCatList = itemCatService.getItemCatList(parentId);
        return itemCatList;
    }

}
