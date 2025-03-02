package com.task.appv2.data.repositories;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import com.task.appv2.data.source.local.AppDatabase;
import com.task.appv2.data.source.remote.api.ChefApiService;
import com.task.appv2.data.models.ApiResponse;
import com.task.appv2.data.models.RemoteListener;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseRepository {
    private static final String TAG = BaseRepository.class.getSimpleName();
    protected final AppDatabase appDatabase;
    protected final ChefApiService apiService;

    public BaseRepository(AppDatabase appDatabase, ChefApiService apiService) {
        this.appDatabase = appDatabase;
        this.apiService = apiService;
    }

    /***
     * Handle the API request body and initiate network call.
     *
     * @return call API call to be executed
     */
    @NotNull
    public <T> Callback<T> handleApiCallback(String title, RemoteListener<T> callback) {
        return new Callback<T>() {
            @Override
            public void onResponse(@NotNull Call<T> call, @NonNull Response<T> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        printEvent(String.format("1-onSuccess-> result %s :%s", title, response.body().toString()));
                        callback.onSuccess(response.body());
                        return;
                    }
                    printError(String.format("2-onResponse-> " + title + ":Empty response body - %s", response.toString()));
                    callback.onError(getRespError(response.toString()));
                    return;
                }
                printError(String.format("3-Server Error -> %s: unsuccessful Response  - %s", title, getRespError(response.toString())));
                callback.onError(String.format("3-Server Error -> %s unsuccessful Response - %s", title, getRespError(response.toString())));
            }

            @Override
            public void onFailure(@NotNull Call<T> call, @NotNull Throwable t) {
                printError(String.format("4-Network Error-> %s : request failed - %s", title, t.toString()));
                callback.onError(String.format("4-Network Error-> %s%s", title, t.toString()));
            }
        };
    }

    private void printEvent(String msg) {
        Log.d(TAG, msg);
        Log.d(TAG, msg);
    }

    private void printError(String msg) {
        Log.e("TAG", msg);
    }

    @NotNull
    private String getRespError(@NotNull String d) {
        return d.substring(0, d.lastIndexOf("url"));
    }

    protected void showRequestBody(@NotNull Call<ApiResponse> call) {
        // Get the request body
        RequestBody requestBody = call.request().body();
        // Convert the request body to a string
        String requestBodyString;
        if (requestBody != null) {
            Buffer buffer = new Buffer();
            try {
                requestBody.writeTo(buffer);
                requestBodyString = buffer.readUtf8();
                Log.d(TAG, requestBodyString.length() + "request:" + requestBodyString);
                //SharePerf.getInstance(mContext).addLog(requestBodyString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("NewApi")
    private void request(@NotNull Call<?> headerCall) {
        Log.w(TAG, "request:" + headerCall.request().toString());
        // Log1.w(TAG,"host:"+ headerCall.request().url().host());
        // Log1.w(TAG,"encodedPath:"+ headerCall.request().url().encodedPath());
        //Log1.w(TAG,"redact:"+ headerCall.request().url().redact());
        //Log1.w(TAG,"headers:"+headerCall.request().headers().toString());
    }
}
