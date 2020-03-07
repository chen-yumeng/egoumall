package com.egoumall.pagehelper;

import com.egoumall.common.mapper.ItemMapper;
import com.egoumall.common.pojo.Item;
import com.egoumall.common.pojo.ItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @program: egoumall->PageHelperTset
 * @description: 测试PageHelper
 * @author: cg
 * @create: 2020-01-22 18:25
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class PageHelperTset {

    @Autowired
    ItemMapper itemMapper;

    @Test
    public void testPageHelper() {
        PageHelper.startPage(1, 10);
        ItemExample example = new ItemExample();
        List<Item> list = itemMapper.selectByExample(example);
        PageInfo<Item> pageInfo = new PageInfo<>(list);
        List<Item> itemList = pageInfo.getList();
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.getPages());
        for (Item item : itemList) {
            System.out.println(item.getTitle());
        }
    }

}
