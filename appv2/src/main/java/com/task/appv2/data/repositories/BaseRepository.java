package com.task.appv2.data.repositories;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;
import androidx.sqlite.db.SimpleSQLiteQuery;

import com.task.appv2.data.models.CRUDCallback;
import com.task.appv2.data.source.local.AppDatabase;
import com.task.appv2.data.source.local.dao.BaseDao;
import com.task.appv2.data.source.remote.api.ChefApiService;
import com.task.appv2.data.models.ApiResponse;
import com.task.appv2.data.models.RemoteListener;
import com.task.appv2.utils.AppExecutor;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseRepository {
    protected final AppDatabase appDatabase;
    protected final ChefApiService apiService;
    protected final ExecutorService mExecutorService;


    public BaseRepository(AppDatabase appDatabase, ChefApiService apiService) {
        this.appDatabase = appDatabase;
        this.apiService = apiService;
        //--
        mExecutorService = Executors.newSingleThreadExecutor();

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
              try{  if (response.isSuccessful()) {

                        if (response.body() != null) {
                            printEvent(String.format("1-onResult-> result %s :%s", title, response.body().toString()));
                            callback.onSuccess(response.body());
                            return;
                        }
                        printError(String.format("2-onResponse-> " + title + ":Empty response body - %s", response.toString()));
                        callback.onError(getRespError(response.toString()));
                        return;
                    }
                    printError(String.format("3-Server Error -> %s: unsuccessful Response  - %s", title, getRespError(response.toString())));
                    callback.onError(String.format("3-Server Error -> %s unsuccessful Response - %s", title, getRespError(response.toString())));
                }catch (Exception e){
                  callback.onError(String.format("Local Error-> %s%s", title, e.toString()));
              }
            }

            @Override
            public void onFailure(@NotNull Call<T> call, @NotNull Throwable t) {
                printError(String.format("4-Network Error-> %s : request failed - %s", title, t.toString()));
                callback.onError(String.format("4-Network Error-> %s%s", title, t.toString()));
            }
        };
    }

    private void printEvent(String msg) {
        Log.d(TAG(), msg);
        Log.d(TAG(), msg);
    }

    private void printError(String msg) {
        Log.e("TAG", msg);
    }

    @NotNull
    private String getRespError(@NotNull String d) {
        return d.substring(0, d.lastIndexOf("url"));
    }

    protected <T> void showRequestBody(@NotNull Call<ApiResponse<T>> call) {
        // Get the request body
        RequestBody requestBody = call.request().body();
        // Convert the request body to a string
        String requestBodyString;
        if (requestBody != null) {
            Buffer buffer = new Buffer();
            try {
                requestBody.writeTo(buffer);
                requestBodyString = buffer.readUtf8();
                Log.d(TAG(), "request:" +requestBodyString.length() + ":"+ requestBodyString);
                //SharePerf.getInstance(mContext).addLog(requestBodyString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    String TAG(){
        return this.getClass().getSimpleName();
    }

    @SuppressLint("NewApi")
    private  void request(@NotNull Call<?> headerCall) {
        Log.w(TAG(), "request:" + headerCall.request().toString());
    }



//--------------------------------------------------

    protected PagedList.Config configPagedList() {
        return new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPrefetchDistance(2)
                .setPageSize(4)
                .build();
    }

}
