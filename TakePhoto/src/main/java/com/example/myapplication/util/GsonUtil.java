package com.example.myapplication.util;

import com.example.myapplication.entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by Administrator on 2016/6/4.
 */
public class GsonUtil {
    public static String toJson(Object object) {
        String jsonStr = "";
        if (object instanceof User) {
            User user = (User) object;
            jsonStr = new Gson().toJson(user);
        }
        return jsonStr;
    }

    public static int getCode(String json) {
        JsonElement je = new JsonParser().parse(json);
        JsonObject jo = je.getAsJsonObject();
        String code = jo.get("code") + "";
        if (code == null || "".equals(code)) {
            try {
                throw new Exception("code为空");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return Integer.parseInt(code);

    }
}
