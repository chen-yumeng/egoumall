package com.egoumall.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @program: egoumall->EasyUIDataGridResult
 * @description: 后台商品EasyUI展示的商品数据返回集
 * @author: cg
 * @create: 2020-01-22 19:40
 **/

public class EasyUIDataGridResult implements Serializable {

    private long total;
    private List rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
