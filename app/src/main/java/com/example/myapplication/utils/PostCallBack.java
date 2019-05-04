package com.example.myapplication.utils;

import org.json.JSONObject;

public interface PostCallBack {
    void onResponse(JSONObject ret, String errMsg);
}
