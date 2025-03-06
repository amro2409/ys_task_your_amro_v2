package com.task.appv2.data.source.remote;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FakeCall<T> implements Call<T> {

    private final T responseData;

    public FakeCall(T responseData) {
        this.responseData = responseData;
    }

    @Override
    public Response<T> execute() {
        return Response.success(responseData);
    }

    @Override
    public void enqueue(@NotNull Callback<T> callback) {
        Log.d("FakeCall", "enqueue:   تنفيذ callback فورياً على الـ main thread أو thread آخر حسب الحاجة");
        callback.onResponse(this, Response.success(responseData));
    }

    @Override
    public boolean isExecuted() {
        return false;
    }

    @Override
    public void cancel() {
        Log.d("TAG", "cancel() called, لا يوجد شيء لإلغاءه هنا");
        //
    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @NotNull
    @Override
    public Call<T> clone() {
        return new FakeCall<>(responseData);
    }

    @Override
    public Request request() {
        return new Request.Builder().url("http://localhost/").build();
    }
}
