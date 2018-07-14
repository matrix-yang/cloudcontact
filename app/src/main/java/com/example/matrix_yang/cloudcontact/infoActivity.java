package com.example.matrix_yang.cloudcontact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
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
        //System.out.println("222222222222222222"+friend.toString());
        TextView name = (TextView) findViewById(R.id.textView2);
        name.setText(friend.getName());
        TextView phone = (TextView) findViewById(R.id.textView4);
        phone.setText(friend.getPhoneNum());
        TextView sex = (TextView) findViewById(R.id.textView6);
        sex.setText(friend.getSex());
        TextView stuid = (TextView) findViewById(R.id.textView8);
        stuid.setText(friend.getStuId());
    }
}
