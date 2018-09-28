package com.example.administrator.jingcai.util;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    public static String handleDataResponse(String response){
        try {
            JSONArray jsonArray = new JSONArray(response);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String data = jsonObject.getString("data");
            return data;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
