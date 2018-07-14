package com.example.matrix_yang.cloudcontact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import model.Friend;
import util.Util;

public class infoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Intent intent=getIntent();
        String s=intent.getStringExtra("info");
        Friend friend= Util.string2Friend(s);
        System.out.println("222222222222222222"+friend.toString());
    }
}
