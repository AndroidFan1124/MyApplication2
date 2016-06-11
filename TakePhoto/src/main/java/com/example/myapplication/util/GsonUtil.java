package com.example.myapplication.util;

import com.example.myapplication.entity.User;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/6/4.
 */
public class GsonUtil {
    public static String toJson(Object object){
        String jsonStr = "";
        if(object instanceof User){
            User user = (User)object;
            jsonStr = new Gson().toJson(user);
        }
        return jsonStr;
    }
}
