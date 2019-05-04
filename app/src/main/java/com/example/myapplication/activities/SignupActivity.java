package com.example.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.utils.PostCallBack;
import com.example.myapplication.utils.Utils;

import org.json.JSONObject;

import okhttp3.internal.Util;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void onClickSignup(View v ){
        EditText etID = findViewById(R.id.signUpID);
        EditText etPwd = findViewById(R.id.signUpPwd);
        String id = etID.getText().toString();
        String pwd = etPwd.getText().toString();

        try{
            JSONObject json = new JSONObject();
            json.put("command", "signup");
            json.put("ID", id);
            json.put("pwd", pwd);

            Utils.post(json, new PostCallBack() {
                @Override
                public void onResponse(JSONObject ret, String errMsg) {
                    try{
                        if (errMsg != null){
                            Utils.toast(SignupActivity.this, errMsg);
                            return;
                        }
                        if (ret.getBoolean("ret") == false){
                            Utils.toast(SignupActivity.this, ret.getString("message"));
                            return;
                        }
                        Utils.toast(SignupActivity.this, "가입 성공! 로그인해주세요");
                        finish();
                    }catch (Exception e){
                        System.out.println("onClickSignup onResponse : " + e);
                    }
                }
            });

        }catch (Exception e){
            System.out.println("onClickSignup : " + e);
        }
    }
}
