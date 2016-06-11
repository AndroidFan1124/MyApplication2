package com.example.myapplication.presenter;

import com.example.myapplication.command.Command;
import com.example.myapplication.command.Invoker;
import com.example.myapplication.command.LoginCommand;
import com.example.myapplication.constant.Constant;
import com.example.myapplication.entity.User;
import com.example.myapplication.networker.NetworkWatched;
import com.example.myapplication.util.ToastUtil;
import com.example.myapplication.view.ILoginView;

/**
 * Created by Administrator on 2016/6/3.
 */
public class LoginPresenter {
    ILoginView view;
    String name = "";
    String password = "";
    NetworkWatched nw;
   public  LoginPresenter(ILoginView view,NetworkWatched nw){
       this.view = view;
       this.nw = nw;
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
}
