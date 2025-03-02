package com.task.appv2.data.models;


import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("Value")
    LoginValue value ;

    public LoginRequest(LoginValue loginValue) {
      this.value = loginValue;
    }

    public static class LoginValue{
        String UNT_NO ;
        String P_LANG_NO ;
        String P_PASSWORD;

        public LoginValue(String UNT_NO, String p_LANG_NO, String p_PASSWORD) {
            this.UNT_NO = UNT_NO;
            this.P_LANG_NO = p_LANG_NO;
            this.P_PASSWORD = p_PASSWORD;
        }
    }

}

