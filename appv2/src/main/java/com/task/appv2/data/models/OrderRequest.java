package com.task.appv2.data.models;


import com.google.gson.annotations.SerializedName;

public class OrderRequest {

    @SerializedName("Value")
    OrderRequestValue Value;

    public OrderRequest(OrderRequestValue value) {
        Value = value;
    }

    public static class OrderRequestValue {
        @SerializedName("P_BRN_NO")
        String pBrnNo = "1"; // number branch that require fetch orders
        @SerializedName("P_ORDR_SRL")
        String pOrderSrl = ""; // Leave empty to fetch all orders
        @SerializedName("P_PRCSSD_FLG")
        String pProcessedFlg = " "; // fetch order's not processed
        @SerializedName("P_TRMNL_NO")
        String pTerminalNo; //4 Retrieved from GetUserDetails API
        @SerializedName("P_LANG_NO")
        String pLangNo;// 1
        @SerializedName("UNT_NO")
        String untNo = "87";

        public OrderRequestValue(String pTerminalNo,String pLangNo) {
            this.pTerminalNo = pTerminalNo;
            this.pLangNo = pLangNo;
        }

        public String getpLangNo() {
            return pLangNo;
        }

        public String getpTerminalNo() {
            return pTerminalNo;
        }
    }

}
