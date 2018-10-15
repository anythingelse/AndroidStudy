package com.example.administrator.kankanbook.gson;

import com.google.gson.annotations.SerializedName;

public class Books {
    @SerializedName("_id")
    public String bookId;
    @SerializedName("title")
    public String bookTitle;
    @SerializedName("author")
    public String bookAuthor;
    @SerializedName("shortIntro")
    public String bookShortIntro;//短文预览
    @SerializedName("majorCate")
    public String bookMajorCate;//主分类
    @SerializedName("minorCate")
    public String bookMinorCate;//子分类
}
