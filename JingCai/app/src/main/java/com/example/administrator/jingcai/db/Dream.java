package com.example.administrator.jingcai.db;

import org.litepal.crud.DataSupport;

public class Dream extends DataSupport {
    private int id;
    private String content;
    private String listContent;

    public void setListContent(String listContent) {
        this.listContent = listContent;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public String getListContent() {

        return listContent;
    }

    public int getId() {

        return id;
    }

    public String getContent() {

        return content;
    }
}
