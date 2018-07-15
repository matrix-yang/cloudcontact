package com.example.matrix_yang.cloudcontact;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.*;
import model.Friend;
import org.json.JSONException;
import util.EditTextClearTools;
import util.HttpClientUtil;
import util.Util;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    public static String FLAG="false";
    private EditText etUserName;
    private EditText etUserPassword;
    private Button btnLogin;
    private String userName;
    private String userPassword;

    private void init() {
        final EditText userName = (EditText) findViewById(R.id.et_userName);
        final EditText password = (EditText) findViewById(R.id.et_password);
        ImageView unameClear = (ImageView) findViewById(R.id.iv_unameClear);
        ImageView pwdClear = (ImageView) findViewById(R.id.iv_pwdClear);


        EditTextClearTools.addClearListener(userName, unameClear);
        EditTextClearTools.addClearListener(password, pwdClear);

        TextView reg = (TextView) findViewById(R.id.textView11);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, editActivity.class);
                intent.putExtra("info", "20,姓名,电话号码,性别,学号,密码");
                startActivity(intent);
            }
        });

        Button button = (Button) findViewById(R.id.btn_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Editable un = userName.getText();
                final Editable pwd = password.getText();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        FLAG = HttpClientUtil.sendGet("http://"+Util.SERVERIP+"/Friend/verify?phoneNum=" + un + "&pwd=" + pwd);
                    }
                }).start();
                if (FLAG.equals("true")){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
}
