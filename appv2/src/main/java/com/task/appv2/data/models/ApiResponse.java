package com.task.appv2.data.models;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

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

        @NotNull
        @Override
        public String toString() {
            return "Result{" +
                    "errNo='" + errNo + '\'' +
                    ", errMessage='" + errMessage + '\'' +
                    '}';
        }
    }

    @NotNull
    @Override
    public String toString() {
        return "ApiResponse{" +
                "data=" + data +
                ", result=" + result +
                '}';
    }
}
