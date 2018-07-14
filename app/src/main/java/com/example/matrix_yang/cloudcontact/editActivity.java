package com.example.matrix_yang.cloudcontact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import model.Friend;
import util.Util;

public class editActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent=getIntent();
        String s=intent.getStringExtra("info");
        Friend friend= Util.string2Friend(s);
        //System.out.println("222222222222222222"+friend.toString());
        EditText name = (EditText) findViewById(R.id.textView2);
        name.setText(friend.getName());
        EditText phone = (EditText) findViewById(R.id.textView4);
        phone.setText(friend.getPhoneNum());
        EditText sex = (EditText) findViewById(R.id.textView6);
        sex.setText(friend.getSex());
        EditText stuid = (EditText) findViewById(R.id.textView8);
        stuid.setText(friend.getStuId());
        EditText pwd = (EditText) findViewById(R.id.textView10);
        pwd.setText(friend.getPwd());
    }
}
