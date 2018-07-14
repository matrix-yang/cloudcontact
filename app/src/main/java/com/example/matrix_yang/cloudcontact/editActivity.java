package com.example.matrix_yang.cloudcontact;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import model.Friend;
import org.json.JSONException;
import util.HttpClientUtil;
import util.Util;

import java.util.ArrayList;

public class editActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent=getIntent();
        String s=intent.getStringExtra("info");
        Friend friend= Util.string2Friend(s);
        final long id= friend.getId();
        final EditText name = (EditText) findViewById(R.id.textView2);
        name.setText(friend.getName());
        final EditText phone = (EditText) findViewById(R.id.textView4);
        phone.setText(friend.getPhoneNum());
        final EditText sex = (EditText) findViewById(R.id.textView6);
        sex.setText(friend.getSex());
        final EditText stuid = (EditText) findViewById(R.id.textView8);
        stuid.setText(friend.getStuId());
        final EditText pwd = (EditText) findViewById(R.id.textView10);
        pwd.setText(friend.getPwd());

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpClientUtil.sendGet("http://192.168.31.228/Friend/saveOrUpdate?id="+id+"&name="+name.getText()+"&phoneNum="+phone.getText()+"&sex="+sex.getText()+"&stuId="+stuid.getText()+"&pwd="+pwd.getText());
                    }
                }).start();
                Toast.makeText(editActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
