package com.example.administrator.jingcai.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    public String stat;
    @SerializedName("data")
    public List<Data> dataList;
}
