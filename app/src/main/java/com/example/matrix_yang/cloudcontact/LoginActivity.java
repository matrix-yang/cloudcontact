package com.example.matrix_yang.cloudcontact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import util.EditTextClearTools;

public class LoginActivity extends AppCompatActivity {

    private EditText etUserName;
    private EditText etUserPassword;
    private Button btnLogin;
    private String userName;
    private String userPassword;

    private void init(){
        EditText userName = (EditText) findViewById(R.id.et_userName);
        EditText password = (EditText) findViewById(R.id.et_password);
        ImageView unameClear = (ImageView) findViewById(R.id.iv_unameClear);
        ImageView pwdClear = (ImageView) findViewById(R.id.iv_pwdClear);


        EditTextClearTools.addClearListener(userName,unameClear);
        EditTextClearTools.addClearListener(password,pwdClear);

        TextView reg= (TextView) findViewById(R.id.textView11);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this,editActivity.class);
                intent.putExtra("info","20,姓名,电话号码,性别,学号,密码");
                startActivity(intent);
            }
        });

        Button button= (Button) findViewById(R.id.btn_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
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
