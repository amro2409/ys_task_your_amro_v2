package com.task.appv2.data.source.remote.api;

import com.task.appv2.data.source.local.entities.OrderEntity;
import com.task.appv2.data.source.local.entities.UserEntity;
import com.task.appv2.data.models.LoginRequest;
import com.task.appv2.data.models.ApiResponse;
import com.task.appv2.data.models.OrderRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ChefApiService {

    @POST("User/GetUserDetails")
    Call<ApiResponse<UserEntity>> getUserDetails(@Body LoginRequest request);

    @POST("Order/GetOrders")
    Call<ApiResponse<OrderEntity>> getOrders(@Body OrderRequest request);

    //@POST("Order/SetOrderProcessed")
    //Call<OrderProcessedResponse> setOrderProcessed(@Body OrderProcessedRequest request);

}
