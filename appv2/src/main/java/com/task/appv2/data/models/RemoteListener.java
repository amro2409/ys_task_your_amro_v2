package com.task.appv2.data.models;

public interface RemoteListener<T> {
    void onSuccess(T tData);
    default void onError(String error){}
    default void onProgress(float progress){}
    default void updateUI(T t){}
}
