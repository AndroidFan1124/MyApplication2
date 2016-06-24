package com.example.myapplication.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.myapplication.activity.LoginActivity;
import com.example.myapplication.command.Command;
import com.example.myapplication.command.Invoker;
import com.example.myapplication.command.RegisterCommand;
import com.example.myapplication.constant.Constant;
import com.example.myapplication.entity.User;
import com.example.myapplication.networker.NetworkWatched;
import com.example.myapplication.util.GsonUtil;
import com.example.myapplication.util.ToastUtil;
import com.example.myapplication.view.IRegisterView;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/6/15.
 */
public class RegisterPresenter {
    Context mContext;
    IRegisterView view;
    NetworkWatched nw;

    public RegisterPresenter(Context context, IRegisterView view, NetworkWatched network) {
        this.mContext = context;
        this.view = view;
        this.nw = network;

    }

    public void register() {
        String userName = view.getUserName();
        String pass = view.getPassWord();
        String rePass = view.getRePassword();
        String phone = view.getPhone();
        String email = view.getEmail();
        if (isNull(userName,pass,rePass,phone,email)){
            User user = new User();
            user.setCode(Constant.REGISTER_COED);
            user.setPassword(pass);
            user.setName(userName);
            user.setEmail(email);
            user.setTelnum(phone);
//            String jsonStr = GsonUtil.toJson(user);
//            Log.d("wmy","--->"+jsonStr);
            initRequest(user);
        }
    }

//    06-16 20:22:25.332 14288-14288/com.example.myapplication D/wmy: --->{"telnum":"13345679909","email":"133@qq.com","name":"133456","password":"123456","code":301}
//    06-16 20:22:25.341 14288-14288/com.example.myapplication D/wmy: --->{"telnum":"13345679909","email":"133@qq.com","name":"133456","password":"123456","code":301}
//    06-16 20:22:25.341 14288-14288/com.example.myapplication D/wmy: jsonStr--->loginCommand->
//    06-16 20:22:25.341 14288-14288/com.example.myapplication D/wmy: requestDispatch()

    public void  initRequest(Object obj){
        Invoker invoker = new Invoker();
        Command command = new RegisterCommand();
        invoker.setCommand(command);
        Log.d("wmy","--->"+obj.toString());
        invoker.execute(obj, nw);
    }

    private boolean isNull(String userName, String pass, String rePass, String phone, String email) {
        boolean flag = true;
        if(userName.equals("")||userName==null) {
            flag = false;
            ToastUtil.showShort("用户名不能为空！");
        }
        else if(pass.equals("")||pass==null) {
            flag = false;
            ToastUtil.showShort("密码不能为空！");
        }
        else if(rePass.equals("")||rePass==null){
            flag = false;
            ToastUtil.showShort("请输入确认密码！");
        }
        else if(!(pass.equals(rePass))) {
            flag = false;
            ToastUtil.showShort("两次输入密码不一致！");
        }
        else if(phone.equals("")||phone==null) {
            flag = false;
            ToastUtil.showShort("手机号不能为空！");
        }
        else if(email.equals("")||email==null) {
            flag = false;
            ToastUtil.showShort("邮箱不能为空！");
        }
        return flag;
    }

    public void parse(Object obj) {
        String json = obj.toString();
        int code = GsonUtil.getCode(json);
        if (code == Constant.REGISTER_COED) {
            User user = new Gson().fromJson(json,User.class);
            if(user.getMsg().equals("Success")){
                ToastUtil.showShort("注册成功");
                Intent intent = new Intent(mContext, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        } else if (code == Constant.REGISTER_ERROR_CODE) {
            ToastUtil.showShort("注册失败");
        }
    }
}
