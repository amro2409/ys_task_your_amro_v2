package com.task.appv2.data.repositories;


import android.annotation.SuppressLint;

import com.task.appv2.data.models.ApiResponse;
import com.task.appv2.data.models.OrderData;
import com.task.appv2.data.models.OrderProcessedRequest;
import com.task.appv2.data.models.OrderRequest;
import com.task.appv2.data.models.RemoteListener;
import com.task.appv2.data.source.local.AppDatabase;
import com.task.appv2.data.source.local.entities.OrderMasterEntity;
import com.task.appv2.data.source.remote.api.ChefApiService;
import com.task.appv2.utils.AppExecutor;

import java.util.stream.Collectors;

import retrofit2.Call;


public class ChefRepository extends BaseRepository {
    public ChefRepository(AppDatabase appDatabase, ChefApiService apiService) {
        super(appDatabase, apiService);
    }


    public void getOrders(OrderRequest request, RemoteListener<ApiResponse<OrderData>> listener) {
        final Call<ApiResponse<OrderData>> call = apiService.getOrders(request);
        call.enqueue(handleApiCallback("getOrders", new RemoteListener<ApiResponse<OrderData>>() {
            @SuppressLint("NewApi")
            @Override
            public void onSuccess(ApiResponse<OrderData> tData) {
                if (tData.result.errNo.equals("0")) {
                    AppExecutor.getInstance().diskIO().execute(() -> {
                        for (OrderMasterEntity masterEntity : tData.data.getOrderMasterList()) {

                            appDatabase.orderDao().insertOrderMaster(masterEntity);
                            //masterEntity.getOrdrDtl().forEach(orderDetailEntity -> orderDetailEntity.setOrderSrlFk(masterEntity.getOrderSrl()));
                            // details
                            appDatabase.orderDao().insertOrderDetails(
                                    masterEntity.getOrdrDtl().stream().peek(orderDetailEntity ->
                                            orderDetailEntity.setOrderSrlFk(masterEntity.getOrderSrl()))
                                            .collect(Collectors.toList()));
                        }
                    });
                    listener.onSuccess(tData);
                }else {
                    listener.onError(tData.result.toString());
                }
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        }));
    }

    public void updateOrderStatus(OrderProcessedRequest request, RemoteListener<ApiResponse<OrderData>> listener) {

    }


}
