package com.majie.stugrade.ui;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.majie.stugrade.R;

public class LoginActivity extends BaseActivity{

	private EditText accountEt;
	private EditText pwdEt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);
		setTitle("登录");

		accountEt = (EditText)findViewById(R.id.login_account);
		pwdEt = (EditText)findViewById(R.id.login_pwd);

		findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				login();
			}
		});

		findViewById(R.id.register_btn).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
			}
		});
	}

	/**
	 * 登录操作
	 * 先判断是否为空，如果为空不进行登录。直接提示错误。
	 */
	private void login() {
		if (TextUtils.isEmpty(accountEt.getText().toString().trim()) || TextUtils.isEmpty(pwdEt.getText().toString().trim())) {
			Toast.makeText(LoginActivity.this, "检查账号密码", Toast.LENGTH_SHORT).show();
			return;
		}

		Intent intent = new Intent(LoginActivity.this,MainActivity.class);
		startActivity(intent);
		LoginActivity.this.finish();
	}
}
