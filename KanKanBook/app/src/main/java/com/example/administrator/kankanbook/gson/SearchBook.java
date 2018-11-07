package com.example.administrator.kankanbook.gson;

import com.google.gson.annotations.SerializedName;

public class SearchBook {
    @SerializedName("_id")
    public String bookId;
    @SerializedName("title")
    public String bookTitle;
    @SerializedName("author")
    public String bookAuthor;
    @SerializedName("shortIntro")
    public String bookShortIntro;//短文预览
    @SerializedName("cat")
    public String bookCat;//主分类
    @SerializedName("cover")
    public String bookPicUrl;
}
