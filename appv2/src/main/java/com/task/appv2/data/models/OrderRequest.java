package com.task.appv2.data.models;


import com.google.gson.annotations.SerializedName;

public class OrderRequest {

    @SerializedName("Value")
    OrderRequestValue Value;

    public OrderRequest(OrderRequestValue value) {
        Value = value;
    }

    public static class OrderRequestValue {
        String P_BRN_NO = "1"; // number branch that require fetch orders
        String P_ORDR_SRL = ""; // Leave empty to fetch all orders
        String P_PRCSSD_FLG = " "; // fetch order's not processed
        String P_TRMNL_NO = "4"; // Retrieved from GetUserDetails API
        String P_LANG_NO = "1";
        String UNT_NO = "87";

    }
}
