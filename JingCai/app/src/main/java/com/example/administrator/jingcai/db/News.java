package com.example.administrator.jingcai.db;

import org.litepal.crud.DataSupport;

public class News extends DataSupport {
    private int id;
    private String title;
    private String date;
    private String authorName;
    private String url;
    private String picUrl1;
    private String picUrl2;
    private String picUrl3;

    public String getAuthorName() {
        return authorName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public void setPicUrl1(String picUrl) {

        this.picUrl1 = picUrl;
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

    public void setPicUrl3(String picUrl3) {
        this.picUrl3 = picUrl3;
    }

    public void setPicUrl2(String picUrl2) {

        this.picUrl2 = picUrl2;
    }

    public String getPicUrl3() {

        return picUrl3;
    }

    public String getPicUrl2() {

        return picUrl2;
    }

    public String getPicUrl1() {

        return picUrl1;
    }

    public int getId() {

        return id;
    }

    public String getDate() {

        return date;
    }
}
