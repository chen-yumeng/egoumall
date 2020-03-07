package com.egoumall.manager.controller;

import com.egoumall.common.pojo.Content;
import com.egoumall.common.pojo.EasyUIDataGridResult;
import com.egoumall.common.utils.EgouResult;
import com.egoumall.content.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: egoumall->ContentController
 * @description:
 * @author: cg
 * @create: 2020-01-30 16:58
 **/
@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * 获取内容数据集
     * @param categoryId 内容分类id
     * @param page 页数
     * @param rows 行数
     * @return
     */
    @RequestMapping("content/query/list")
    @ResponseBody
    public EasyUIDataGridResult getContentList(long categoryId, Integer page, Integer rows) {
        EasyUIDataGridResult contentList = contentService.getContentList(categoryId, page, rows);
        return contentList;
    }

    /**
     * 添加内容
     * @param content 对应的实体类
     * @return
     */
    @RequestMapping(value = "content/save", method = RequestMethod.POST)
    @ResponseBody
    public EgouResult addContent(Content content) {
        return contentService.addContent(content);
    }

    /**
     * 通过id删除多个内容
     * @param ids
     * @return
     */
    @RequestMapping(value = "content/delete", method = RequestMethod.POST)
    @ResponseBody
    public EgouResult deleteContent(String ids) {
        return contentService.deleteContent(ids);
    }

    /**
     * 更新内容
     * @param content 对应的实体类
     * @return
     */
    @RequestMapping(value = "content/update", method = RequestMethod.POST)
    @ResponseBody
    public EgouResult deleteContent(Content content) {
        return contentService.updateContent(content);
    }

}
