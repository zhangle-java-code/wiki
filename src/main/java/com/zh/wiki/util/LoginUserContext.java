package com.zh.wiki.util;


import com.zh.wiki.resp.UserLoginResp;

import java.io.Serializable;

// ? Serializable
// Serializable是Java提供的通用数据保存、读取和传输的接口
public class LoginUserContext implements Serializable {

    private static ThreadLocal<UserLoginResp> user = new ThreadLocal<>();

    public static UserLoginResp getUser() {
        return user.get();
    }

    public static void setUser(UserLoginResp user) {
        LoginUserContext.user.set(user);
    }

}
