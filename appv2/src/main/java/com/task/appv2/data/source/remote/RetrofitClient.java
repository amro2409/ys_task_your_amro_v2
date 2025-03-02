package com.task.appv2.data.source.remote;

import com.task.appv2.data.source.remote.api.ChefApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public final String BaseURL = "http://mdev.yemensoft.net:8087/OnyxRmsService/api/OnyxChef/";

    private static RetrofitClient mInstance;
    private final ChefApiService apiService;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create(/*gson*/)).build();
        apiService = retrofit.create(ChefApiService.class);

    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) mInstance = new RetrofitClient();
        return mInstance;
    }

    public ChefApiService getApiService() {
        return apiService;
    }


}
