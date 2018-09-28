package com.example.administrator.jingcai.db;

import org.litepal.crud.DataSupport;

public class News extends DataSupport {
    private int id;
    private String title;
    private String date;
    private String authorName;
    private String url;
    private String picUrl;

    public String getAuthorName() {
        return authorName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public void setPicUrl(String picUrl) {

        this.picUrl = picUrl;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setDate(String date) {

        this.date = date;
    }

    public void setAuthorName(String authorName) {

        this.authorName = authorName;
    }

    public String getUrl() {

        return url;
    }

    public String getTitle() {

        return title;
    }

    public String getPicUrl() {

        return picUrl;
    }

    public int getId() {

        return id;
    }

    public String getDate() {

        return date;
    }
}
