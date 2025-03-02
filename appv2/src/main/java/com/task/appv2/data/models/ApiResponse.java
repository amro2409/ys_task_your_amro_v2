package com.task.appv2.data.models;

import com.google.gson.annotations.SerializedName;

public class ApiResponse<T> {
    @SerializedName("Data")
    public T data;
    @SerializedName("Result")
    public Result result;

    public static class Result {
        @SerializedName("ErrNo")
        public String errNo;
        @SerializedName("ErrMsg")
        public String errMessage;
    }
}
