package com.egoumall.manager.controller;

import com.egoumall.common.pojo.EasyUITreeNode;
import com.egoumall.common.utils.EgouResult;
import com.egoumall.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: egoumall->ContentCategoryController
 * @description: 内容分类控制层
 * @author: cg
 * @create: 2020-01-29 23:41
 **/
@Controller
public class ContentCategoryController {

    @Autowired
    ContentCategoryService contentCategoryService;

    /**
     * 获取父id下分类列表
     * @param parentId 父节点id
     * @return
     */
    @RequestMapping("content/category/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCategoryList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        return contentCategoryService.getContentCategoryList(parentId);
    }

    /**
     * 通过父内容分类id创建新的内容分类
     * @param parentId 父内容分类id
     * @param name 分类名称
     * @return
     */
    @RequestMapping(value = "content/category/create", method = RequestMethod.POST)
    @ResponseBody
    public EgouResult addContentCategoryById(long parentId, String name) {
        return contentCategoryService.addContentCategoryById(parentId, name);
    }

    /**
     * 通过id更新该内容分类的分类名称
     * @param id 内容分类id
     * @param name 分类名称
     * @return
     */
    @RequestMapping(value = "content/category/update", method = RequestMethod.POST)
    @ResponseBody
    public EgouResult updateContentCategoryById(long id, String name) {
        return contentCategoryService.updateContentCategoryById(id, name);
    }

    /**
     * 通过id删除该内容分类
     * @param id 内容分类id
     * @param isAll 是否删除所有
     * @return
     */
    @RequestMapping(value = "content/category/delete", method = RequestMethod.POST)
    @ResponseBody
    public EgouResult deleteContentCategoryById(long id,boolean isAll) {
        return contentCategoryService.deleteContentCategoryById(id, isAll);
    }

}
