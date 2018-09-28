package com.example.administrator.jingcai.db;

import org.litepal.crud.DataSupport;

public class Joke extends DataSupport {
    private int id;
    private String content;

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public int getId() {

        return id;
    }

    public String getContent() {

        return content;
    }
}
