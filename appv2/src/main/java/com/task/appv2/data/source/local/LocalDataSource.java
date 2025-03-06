package com.task.appv2.data.source.local;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.sqlite.db.SimpleSQLiteQuery;

import com.task.appv2.data.models.CRUDCallback;
import com.task.appv2.utils.AppExecutor;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;

public class LocalDataSource {
    protected final AppDatabase appDatabase;
    protected final ExecutorService mExecutorService;

    public LocalDataSource(AppDatabase appDatabase, ExecutorService mExecutorService) {
        this.appDatabase = appDatabase;
        this.mExecutorService = mExecutorService;
    }
    protected void execute(Runnable task) {
        mExecutorService.execute(task);
    }

    public final void getCount(String cols, String tableName, CRUDCallback<Integer> callback) {
        execute(() -> {
            final String query = String.format("SELECT COUNT(%s) FROM %s ", cols, tableName);
            int count = appDatabase.userDao().getCount(new SimpleSQLiteQuery(query));
            Log.d("TAG", String.format("getCount-> %s, COUNT: %d", tableName, count));
            AppExecutor.getInstance().mainThread().execute(() -> callback.onResult(count));
        });
    }

    @NotNull
    public final LiveData<Integer> getCount(String cols, String tableName) {
        MutableLiveData<Integer> liveData = new MutableLiveData<>();

        execute(() -> {
            final String query = String.format("SELECT COUNT(%s) FROM %s ", cols, tableName);
            int count = appDatabase.userDao().getCount(new SimpleSQLiteQuery(query));
            Log.d("TAG", String.format("getCount-> %s, COUNT: %d", tableName, count));
            liveData.postValue(count); // update LiveData
        });

        return liveData;
    }

}
