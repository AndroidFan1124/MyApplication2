package com.example.myapplication.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.app.BaseActivity;
import com.example.myapplication.presenter.RegisterPresenter;
import com.example.myapplication.view.IRegisterView;

/**
 * Created by Administrator on 2016/6/15.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener, IRegisterView {
    private EditText et_username;
    private EditText et_pass;
    private EditText et_repass;
    private EditText et_phone;
    private Button btn_register;
    private EditText et_email;
    private RegisterPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initEvents();
        initData();
    }

    private void initView() {
        et_username = (EditText) findViewById(R.id.et_username);
        et_pass = (EditText) findViewById(R.id.et_password);
        et_repass = (EditText) findViewById(R.id.et_repassword);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_email = (EditText) findViewById(R.id.et_email);
        btn_register = (Button) findViewById(R.id.btn_register);
    }

    private void initEvents() {
        btn_register.setOnClickListener(this);
    }

    private void initData() {
        presenter = new RegisterPresenter(this, this, getNetwork());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                presenter.register();
                break;
            default:
                break;
        }
    }

    @Override
    public String getUserName() {
        Log.d("wmy", "et_username->" + et_username);
        Log.d("wmy", "值-->" + et_username.getText().toString());
        return et_username.getText().toString();
    }

    @Override
    public String getPassWord() {
        return et_pass.getText().toString();
    }

    @Override
    public String getRePassword() {
        return et_repass.getText().toString();
    }

    @Override
    public String getPhone() {
        return et_phone.getText().toString();
    }

    @Override
    public String getEmail() {
        return et_email.getText().toString();
    }


    @Override
    public void onReceive(Object obj) {
        super.onReceive(obj);
        Log.d("wmy", "RegisterActivity-->onReceiver-->" + obj.toString());
        if (obj != null) {
            presenter.parse(obj);
        }
    }


}
