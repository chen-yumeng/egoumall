package com.egoumall.content.service;

import com.egoumall.common.pojo.EasyUITreeNode;
import com.egoumall.common.utils.EgouResult;

import java.util.List;

/**
 * @program: egoumall->ContentCategoryService
 * @description: 内容分类Service
 * @author: cg
 * @create: 2020-01-29 23:17
 **/

public interface ContentCategoryService {

    /**
     * 获取父id下分类列表
     * @param parentId 父节点id
     * @return
     */
    List<EasyUITreeNode> getContentCategoryList(long parentId);

    /**
     * 通过id删除该内容分类
     * @param id 内容分类id
     * @param isAll 是否删除所有
     * @return
     */
    EgouResult deleteContentCategoryById(long id, boolean isAll);

    /**
     * 通过id更新该内容分类的分类名称
     * @param id 内容分类id
     * @param name 分类名称
     * @return
     */
    EgouResult updateContentCategoryById(long id, String name);

    /**
     * 通过父内容分类id创建新的内容分类
     * @param parentId 父内容分类id
     * @param name 分类名称
     * @return
     */
    EgouResult addContentCategoryById(long parentId, String name);

}
