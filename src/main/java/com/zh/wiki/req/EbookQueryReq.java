package com.zh.wiki.req;

//! 继承分页
public class EbookQueryReq extends PageReq {
    private Long id;

    private Long categoryId2;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId2() {
        return categoryId2;
    }

    public void setCategoryId2(Long categoryId2) {
        this.categoryId2 = categoryId2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //? toString 内容不一致，和其他模块toString内容不一致
    @Override
    public String toString() {
        return "EbookQueryReq{" +
                "id=" + id +
                ", categoryId2=" + categoryId2 +
                ", name='" + name + '\'' +
                "} " + super.toString();
    }
}
