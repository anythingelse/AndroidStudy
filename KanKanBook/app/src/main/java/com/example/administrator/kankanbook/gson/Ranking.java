package com.example.administrator.kankanbook.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Ranking {
    @SerializedName("books")
    public List<Books> books;
}
