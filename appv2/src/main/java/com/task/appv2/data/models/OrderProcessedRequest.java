package com.task.appv2.data.models;

import com.google.gson.annotations.SerializedName;

public class OrderProcessedRequest {

    @SerializedName("Value")
    public OrderProcessedValue orderProcessedValue;

    public static class OrderProcessedValue {
        public String P_ORDR_SRL;
        public String P_USR_NO;
        public String P_LANG_NO;
        public String UNT_NO;

    }

}
