package com.example.administrator.kankanbook.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookSearchs {
    @SerializedName("books")
    public List<SearchBook> searchBookList;
}
