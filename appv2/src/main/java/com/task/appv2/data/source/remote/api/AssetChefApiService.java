package com.task.appv2.data.source.remote.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.task.appv2.data.models.ApiResponse;
import com.task.appv2.data.models.LoginRequest;
import com.task.appv2.data.models.OrderData;
import com.task.appv2.data.models.OrderProcessedRequest;
import com.task.appv2.data.models.OrderRequest;
import com.task.appv2.data.models.UsrData;
import com.task.appv2.data.source.remote.FakeCall;
import com.task.appv2.utils.FileUtils;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;

public class AssetChefApiService implements ChefApiService {
    private final Context context;

    public AssetChefApiService(Context context) {
        this.context = context;
    }

    @Override
    public Call<ApiResponse<UsrData>> getUserDetails(LoginRequest request) {
        return null;
    }

    @Override
    public Call<ApiResponse<OrderData>> getOrders(OrderRequest request) {
        Log.d("AssetChefApiService", "start getOrders: ");
        try {
            String jsonData = FileUtils.loadJSONFromAsset(context, "data.json");

            Type listType = new TypeToken<ApiResponse<OrderData>>() {}.getType();

            Gson gson = new Gson();
            ApiResponse<OrderData> response = gson.fromJson(jsonData, listType);
            Log.d("AssetChefApiService", "getOrders: "+ response);
            return new FakeCall<>(response);
        }catch (Exception e) {
            e.printStackTrace();
            return new FakeCall<>(null);
        }

    }

    @Override
    public Call<ApiResponse<OrderData>> setOrderProcessed(OrderProcessedRequest request) {
        return null;
    }
}
