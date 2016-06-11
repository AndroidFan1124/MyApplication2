package com.example.myapplication.command;

import android.util.Log;

import com.example.myapplication.networker.NetworkWatched;
import com.example.myapplication.util.GsonUtil;

/**
 * Created by Administrator on 2016/6/3.
 */
public class LoginCommand implements Command {

    @Override
    public boolean execute(Object obj, NetworkWatched nw) {
        String jsonStr = GsonUtil.toJson(obj);
        Log.d("wmy", "jsonStr--->loginCommand->" + jsonStr);
        nw.requestDispatch(jsonStr);
        return true;
    }
}
