package com.example.administrator.kankanbook.gson;

import com.google.gson.annotations.SerializedName;

public class BookSourceData {
    @SerializedName("_id")
    public String sourceId;
    @SerializedName("source")
    public String sourceName;
}
