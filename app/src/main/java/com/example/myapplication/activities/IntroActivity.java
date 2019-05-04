package com.example.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.db.SessionTable;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        SessionTable.inst().load(this);

        //session 정보 유무에 따른 액티비티 전환
        if (hasSession()) {
            //세션 있을 시
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            //세션 없을 시
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        }

        finish();
    }

    //화면이 보일떄 불리는 함수 onResume
    @Override
    protected void onResume() {
        super.onResume();
    }

    private boolean hasSession() {
        String utk = SessionTable.inst().getSession();
        return (utk != null);
    }
}
