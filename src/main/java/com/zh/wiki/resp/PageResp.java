package com.zh.wiki.resp;

import java.util.List;

public class PageResp<T> {
    private long total;

    private List<T> list;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    //! 打印日志，序列化
    @Override
    public String toString() {
        return "PageResp{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}
