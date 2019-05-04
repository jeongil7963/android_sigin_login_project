package com.example.myapplication.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public interface Callback {
    void onFailure(Call call, IOException e);


    void onResponse(Call call, Response response) throws IOException;
}
