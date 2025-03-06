package com.task.appv2.data.models;

public interface CRUDCallback<T> {
    void onResult(T t);

    void onError(String error);
}
