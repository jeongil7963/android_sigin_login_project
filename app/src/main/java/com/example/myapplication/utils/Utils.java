package com.example.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.myapplication.activities.SignupActivity;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Utils extends AppCompatActivity {


    public static void toast(final Context context,
                                       final String msg) {
        if (context != null && msg != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    static private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    static private OkHttpClient client = new OkHttpClient();
    static private Call post(String url, String json, Callback callback) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }

    static public void post(JSONObject params, final PostCallBack cb){
        post("http://1234:8080", params.toString(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (cb != null)
                    cb.onResponse(null, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response){
                if(cb == null)
                    return;
                try{
                    if (response.isSuccessful()){
                        String responseStr = response.body().string();
                        cb.onResponse(new JSONObject(responseStr), null);
                    }
                    else{
                        cb.onResponse(null, response.message());
                    }
                }catch (Exception e){
                    cb.onResponse(null, e.getMessage());
                }
            }
        });
    }
}
