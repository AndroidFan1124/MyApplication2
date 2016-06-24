package com.example.myapplication.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.activity.FirstActivity;
import com.example.myapplication.activity.ForgetPassActivity;
import com.example.myapplication.activity.RegisterActivity;
import com.example.myapplication.command.Command;
import com.example.myapplication.command.Invoker;
import com.example.myapplication.command.LoginCommand;
import com.example.myapplication.constant.Constant;
import com.example.myapplication.entity.User;
import com.example.myapplication.networker.NetworkWatched;
import com.example.myapplication.util.GsonUtil;
import com.example.myapplication.util.ToastUtil;
import com.example.myapplication.view.ILoginView;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/6/3.
 */
public class LoginPresenter {
    ILoginView view;
    String name = "";
    String password = "";
    NetworkWatched nw;
    Context mContext;
   public  LoginPresenter(ILoginView view, NetworkWatched nw, Context mContext){
       this.view = view;
       this.nw = nw;
       this.mContext = mContext;
   }

    public void login(){
        name = view.getName();
        password = view.getPass();
        User user = new User();
        if(!(name.equals("")&&password.equals(""))) {
            user.setName(name);
            user.setPassword(password);
            user.setCode(Constant.LOGIN_CODE);
            initRequest(user);
        }
        else
            ToastUtil.showShort("用户名或密码不能为空！");

    }
   public void  initRequest(Object obj){
       Invoker invoker = new Invoker();
       Command command = new LoginCommand();
       invoker.setCommand(command);
       invoker.execute(obj, nw);
    }


    public void parse(String json){
        if(json != null) {
            int code = GsonUtil.getCode(json);
            if (code == Constant.LOGIN_CODE) {
                Intent intent = new Intent(mContext, FirstActivity.class);
                Bundle bundle = new Bundle();
                User user = new Gson().fromJson(json, User.class);
                bundle.putSerializable("user", user);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            } else if (code == Constant.LOGIN_ERROR_CODE) {
                ToastUtil.showShort("用户名或密码错误！");
            }
        }
    }

    public void toRegister() {
        Log.d("wmy","wmy---->toregister");
        Intent intent = new Intent(mContext, RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }



    public void toForget() {
        Intent intent = new Intent(mContext, ForgetPassActivity.class);
        mContext.startActivity(intent);
    }
}
