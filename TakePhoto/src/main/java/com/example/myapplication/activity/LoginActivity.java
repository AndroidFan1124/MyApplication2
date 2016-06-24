package com.example.myapplication.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.app.BaseMainActivity;
import com.example.myapplication.presenter.LoginPresenter;
import com.example.myapplication.util.CameraUtil;
import com.example.myapplication.view.ILoginView;

/**
 * Created by Administrator on 2016/6/3.
 */
public class LoginActivity extends BaseMainActivity implements View.OnClickListener , ILoginView{
    private Button btn_login;
    private Button btn_register;
    private TextView btn_forget;
    private EditText et_name;
    private EditText et_pass;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initEvents();
        initData();
    }

    private void initView() {
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_register = (Button)findViewById(R.id.btn_register);
        btn_forget = (TextView)findViewById(R.id.tv_forget);
        et_name = (EditText)findViewById(R.id.et_userName);
        et_pass = (EditText)findViewById(R.id.et_passWord);

    }
    private void initEvents() {
        btn_forget.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);

    }

    private void initData() {
        presenter = new LoginPresenter(this,getNetwork(),this);
    }

    @Override
    public String getName() {
        return et_name.getText().toString();
    }

    @Override
    public String getPass() {
        return et_pass.getText().toString();
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        switch(arg0.getId()){
            case R.id.btn_login:
                CameraUtil.getDefaultPath();
                presenter.login();
                break;
            case R.id.btn_register:
                Log.d("wmy","wmy---->onClick");
                presenter.toRegister();
                break;
            case R.id.tv_forget:
                presenter.toForget();
                break;
            default:
                break;
        }
    }

    @Override
    public void onReceive(Object obj) {
        super.onReceive(obj);
        Log.d("wmy","obj:"+obj.toString());
        presenter.parse(obj.toString());
    }


}
