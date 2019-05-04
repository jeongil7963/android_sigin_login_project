package com.example.myapplication.db;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

public class SessionTable extends JSONObject {
    private static SessionTable instancne = new SessionTable();

    public static SessionTable inst() {return instancne; }

    public void load(Activity act){
        SharedPreferences sharedPreferences = act.getSharedPreferences("myapplication", MODE_PRIVATE);
        String json = sharedPreferences.getString("SessionTable", null);
        if(json == null)
            return;
        try{
            JSONObject load = new JSONObject(json);
            put("utk", load.getString("utk"));
        }catch (Exception e ){
            System.out.println("load : " + e);
        }
    }

    public void putSession(Activity act, String utk){
        try{
            put("utk",utk);

            SharedPreferences sharedPreferences = act.getSharedPreferences("myapplication", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("SessionTable", this.toString());
            editor.commit();
        }catch (Exception e){
            System.out.println("putSession : " + e);
        }
    }

    public String getSession(){
        try{
            String utk = getString("utk");
            return utk;
        }catch (Exception e){
            System.out.println("getSession : " + e);
        }
        return null;
    }

    public void delSession(Activity act){
        remove("utk");
        SharedPreferences sharedPreferences = act.getSharedPreferences("myapplication", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("SessionTable");
        editor.apply();
    }
}
