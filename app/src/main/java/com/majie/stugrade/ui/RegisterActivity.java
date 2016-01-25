package com.majie.stugrade.ui;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.majie.stugrade.R;

public class RegisterActivity extends BaseActivity{
	
	private EditText accountEt;//学号
	private EditText pwdEt;//密码
	private EditText pwdAgainEt;//再次输入密码
	private EditText nameEt;//姓名
	private EditText majorEt;//专业
	
	private Button loginBtn;
	private Button cancelBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_register);
		setTitle("注册");

		accountEt = (EditText)findViewById(R.id.register_account);
		pwdEt = (EditText)findViewById(R.id.register_pwd);
		pwdAgainEt = (EditText)findViewById(R.id.register_pwd_again);
		nameEt = (EditText)findViewById(R.id.register_name);
		majorEt = (EditText)findViewById(R.id.register_major);

		loginBtn = (Button)findViewById(R.id.register_login);
		cancelBtn = (Button) findViewById(R.id.register_cancel);

        loginBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
                doRegister();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {

            @Override
			public void onClick(View v) {
				RegisterActivity.this.finish();
			}
		});
	}

	protected void doRegister() {
		
		if (TextUtils.isEmpty(accountEt.getText().toString().trim())) {
			Toast.makeText(RegisterActivity.this, "学号不可为空", Toast.LENGTH_SHORT).show();
			return;
		}
		
		//如果两次密码不相等 跳出
		if (!pwdEt.getText().toString().trim().equals(pwdAgainEt.getText().toString().trim())) {
			Toast.makeText(RegisterActivity.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if (TextUtils.isEmpty(nameEt.getText().toString().trim())) {
			Toast.makeText(RegisterActivity.this, "姓名不可为空", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if (TextUtils.isEmpty(majorEt.getText().toString().trim())) {
			Toast.makeText(RegisterActivity.this, "专业不可为空", Toast.LENGTH_SHORT).show();
			return;
		}
		
		Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
		intent.putExtra("userId", 0);
		startActivity(intent);
	}
}
