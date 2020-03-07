package com.egoumall.content.service.impl;

import com.egoumall.common.mapper.ContentCategoryMapper;
import com.egoumall.common.pojo.ContentCategory;
import com.egoumall.common.pojo.ContentCategoryExample;
import com.egoumall.common.pojo.EasyUITreeNode;
import com.egoumall.common.utils.EgouResult;
import com.egoumall.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: egoumall->ContentCategoryServiceImpl
 * @description: 内容分类Service实现类
 * @author: cg
 * @create: 2020-01-29 23:28
 **/
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private ContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getContentCategoryList(long parentId) {
        ContentCategoryExample example = new ContentCategoryExample();
        ContentCategoryExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andParentIdEqualTo(parentId);
        //根据parentId查询子节点列表
        List<ContentCategory> contentCategories = contentCategoryMapper.selectByExample(example);
        //把列表转换成EasyUITreeNode列表
        List<EasyUITreeNode> resultList = new ArrayList<>();
        for (ContentCategory contentCategory : contentCategories) {
            EasyUITreeNode node = new EasyUITreeNode();
            //设置属性
            node.setId(contentCategory.getId());
            node.setText(contentCategory.getName());
            node.setState(contentCategory.getIsParent() ? "closed" : "open");
            resultList.add(node);
        }
        return resultList;
    }

    @Override
    public EgouResult deleteContentCategoryById(long id, boolean isAll) {
        //是否删除该分类所有
        if (isAll) {
            deleteContentCategoryByParentId(id);
            return EgouResult.ok();
        } else {
            ContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
            //判断是否为父类目
            if (contentCategory.getIsParent()) {
                //是
                return EgouResult.build(100, "该目录还有子分类，无法删除！");
            } else {
                //不是
                contentCategoryMapper.deleteByPrimaryKey(id);
                return EgouResult.ok();
            }
        }
    }

    @Override
    public EgouResult updateContentCategoryById(long id, String name) {
        ContentCategory category = new ContentCategory();
        category.setId(id);
        category.setName(name);
        category.setUpdated(new Date());
        contentCategoryMapper.updateByPrimaryKeySelective(category);
        return EgouResult.ok();
    }

    @Override
    public EgouResult addContentCategoryById(long parentId, String name) {
        ContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
        //判断父分类内容的is_parent是否为flase
        if (!contentCategory.getIsParent()) {
            //不是
            contentCategory.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(contentCategory);
        }
        //添加
        ContentCategory category = new ContentCategory();
        category.setParentId(parentId);
        category.setName(name);
        //新添加的节点为叶子节点
        category.setIsParent(false);
        //默认排序为1
        category.setSortOrder(1);
        //1(正常),2(删除)
        category.setStatus(1);
        category.setCreated(new Date());
        category.setUpdated(new Date());
        contentCategoryMapper.insert(category);
        return EgouResult.ok(category);
    }

    private void deleteContentCategoryByParentId(long id) {
        ContentCategory parent = contentCategoryMapper.selectByPrimaryKey(id);
        if (parent.getIsParent()) {
            System.out.println("父节点："+parent.getName());
            ContentCategoryExample example = new ContentCategoryExample();
            ContentCategoryExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(id);
            List<ContentCategory> childList = contentCategoryMapper.selectByExample(example);
            for (ContentCategory category : childList) {
                deleteContentCategoryByParentId(category.getId());
            }
            contentCategoryMapper.deleteByPrimaryKey(id);
        } else {
            System.out.println("子节点："+parent.getName());
            contentCategoryMapper.deleteByPrimaryKey(id);
        }
    }

}
