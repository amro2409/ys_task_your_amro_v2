package com.task.appv2.data.repositories.providers;

import android.util.Log;

import com.task.appv2.data.repositories.ChefRepository;
import com.task.appv2.data.source.local.AppDatabase;
import com.task.appv2.data.source.remote.api.ChefApiService;

public class ChefRepositoryProvider {

    private static ChefRepository INSTANCE;


    public static ChefRepository getInstance(AppDatabase appDatabase, ChefApiService apiService) {
        if (INSTANCE == null) {
            Log.d("ChefRepositoryProvider", "getInstance() called with: appDatabase = [" + appDatabase + "], apiService = [" + apiService + "]");
            INSTANCE = new ChefRepository(appDatabase, apiService);
        }
        return INSTANCE;
    }
}
