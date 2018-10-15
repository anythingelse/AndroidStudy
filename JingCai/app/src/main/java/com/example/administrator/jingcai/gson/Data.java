package com.example.administrator.jingcai.gson;

import com.google.gson.annotations.SerializedName;

public class Data {
    public String title;
    public String date;
    public String url;
    @SerializedName("author_name")
    public String authorName;
    @SerializedName("thumbnail_pic_s")
    public String picUrl1;
    @SerializedName("thumbnail_pic_s02")
    public String picUrl2;
    @SerializedName("thumbnail_pic_s03")
    public String picUrl3;
}
