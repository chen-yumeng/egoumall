package com.egoumall.content.service;

import com.egoumall.common.pojo.Content;
import com.egoumall.common.pojo.EasyUIDataGridResult;
import com.egoumall.common.utils.EgouResult;

/**
 * @program: egoumall->ContentService
 * @description: 内容Service
 * @author: cg
 * @create: 2020-01-30 17:03
 **/

public interface ContentService {

    /**
     * 获取内容数据集
     * @param categoryId 内容分类id
     * @param page 页数
     * @param rows 行数
     * @return
     */
    EasyUIDataGridResult getContentList(long categoryId, Integer page, Integer rows);

    /**
     * 添加内容
     * @param content 对应的实体类
     * @return
     */
    EgouResult addContent(Content content);

    /**
     * 通过id删除多个内容
     * @param ids
     * @return
     */
    EgouResult deleteContent(String ids);

    /**
     * 更新内容
     * @param content 对应的实体类
     * @return
     */
    EgouResult updateContent(Content content);
}
