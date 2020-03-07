package com.egoumall.common.pojo;

import java.io.Serializable;

/**
 * @program: egoumall->EasyUITreeNode
 * @description: EasyUI树形结构商品列表分类pojo
 * @author: cg
 * @create: 2020-01-22 21:11
 **/

public class EasyUITreeNode implements Serializable {

    private long id;
    private String text;
    private String state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
