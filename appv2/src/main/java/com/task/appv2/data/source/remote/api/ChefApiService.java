package com.task.appv2.data.source.remote.api;

import com.task.appv2.data.models.ApiResponse;
import com.task.appv2.data.models.LoginRequest;
import com.task.appv2.data.models.OrderProcessedRequest;
import com.task.appv2.data.models.OrderRequest;
import com.task.appv2.data.models.OrderData;
import com.task.appv2.data.models.UsrData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ChefApiService {

    @POST("User/GetUserDetails")
    Call<ApiResponse<UsrData>> getUserDetails(@Body LoginRequest request);

    @POST("Order/GetOrders")
    Call<ApiResponse<OrderData>> getOrders(@Body OrderRequest request);

    @POST("Order/SetOrderProcessed")
    Call<ApiResponse<OrderData>> setOrderProcessed(@Body OrderProcessedRequest request);

}
