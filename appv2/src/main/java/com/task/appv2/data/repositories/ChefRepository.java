package com.task.appv2.data.repositories;


import com.task.appv2.data.source.local.AppDatabase;
import com.task.appv2.data.source.remote.api.ChefApiService;
import com.task.appv2.data.models.OrderProcessedRequest;
import com.task.appv2.data.models.OrderRequest;


public class ChefRepository  extends  BaseRepository{
    public ChefRepository(AppDatabase appDatabase, ChefApiService apiService) {
        super(appDatabase, apiService);
    }



    public void getOrders(OrderRequest request) {

    }

    public void updateOrderStatus(OrderProcessedRequest request) {

    }


}
