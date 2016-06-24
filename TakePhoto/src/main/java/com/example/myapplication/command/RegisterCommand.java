package com.example.myapplication.command;

import android.util.Log;

import com.example.myapplication.entity.User;
import com.example.myapplication.networker.NetworkWatched;
import com.example.myapplication.util.GsonUtil;

/**
 * Created by Administrator on 2016/6/15.
 */
public class RegisterCommand  implements Command{

    public RegisterCommand(){}

    @Override
    public boolean execute(Object obj, NetworkWatched nw) {
        if(obj instanceof User){
            User user = (User)obj;
            String jsonStr = GsonUtil.toJson(user);
            Log.d("wmy", "jsonStr--->regisetrCommand->" + jsonStr);
            nw.requestDispatch(jsonStr);
        }

        return true;
    }
}
