package com.example.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.db.SessionTable;
import com.example.myapplication.utils.PostCallBack;
import com.example.myapplication.utils.Utils;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickLogout(View v) {
        SessionTable.inst().delSession(this);

        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);

        finish();
    }
}
