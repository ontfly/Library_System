package com.systcloud.entity;

import java.io.Serializable;
import java.util.List;

//分页结果实体类
public class pageResult implements Serializable {
    private long total; //总数
    private List rows;  //返回的数据集合
    public pageResult(long total,List rows){
        super(); //子类的构造方法中必须显示的调用父类的构造方法
        this.total=total;
        this.rows=rows;
    }

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
