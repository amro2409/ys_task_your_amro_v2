package com.task.appv2.data.repositories;


import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.task.appv2.data.models.ApiResponse;
import com.task.appv2.data.models.OrderData;
import com.task.appv2.data.models.OrderProcessedRequest;
import com.task.appv2.data.models.OrderRequest;
import com.task.appv2.data.models.RemoteListener;
import com.task.appv2.data.source.local.AppDatabase;
import com.task.appv2.data.source.local.entities.OrderMasterEntity;
import com.task.appv2.data.source.local.entities.UserEntity;
import com.task.appv2.data.source.local.entities.pojo.OrderWithDetails;
import com.task.appv2.data.source.remote.api.AssetChefApiService;
import com.task.appv2.data.source.remote.api.ChefApiService;
import com.task.appv2.utils.AppExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import retrofit2.Call;


public class ChefRepository extends BaseRepository {
    //retry each 30-s
    private static final long REFRESH_INTERVAL = 30L;//seconds
    private static final long INIT_DALEY = 5L;//second
    private boolean isWork;


    public ChefRepository(AppDatabase appDatabase, ChefApiService apiService) {
        super(appDatabase, apiService);
    }


    public void startRefreshTask(final RemoteListener<ApiResponse<OrderData>> callback, boolean isRepeat) {
        if (isRepeat) {
            final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.scheduleAtFixedRate(() -> updateOrders(callback), INIT_DALEY, REFRESH_INTERVAL, TimeUnit.SECONDS);
        }else {
            AppExecutor.getInstance().diskIO().execute(() -> updateOrders(callback));
        }
        if (!isWork) setWork(true);
    }

    /***
     * get from local- list data using pagedList
     */
    @NonNull
    public LiveData<PagedList<OrderWithDetails>> loadPagingOrders(String limit) {
        return new LivePagedListBuilder<>(
                appDatabase.orderDao().getOrderWithDetailsLiveData(),
                configPagedList()).setFetchExecutor(Executors.newSingleThreadExecutor())
                .build();
    }


    public void updateOrders(RemoteListener<ApiResponse<OrderData>> listener) {
        UserEntity user = appDatabase.userDao().getUserData1();

        OrderRequest.OrderRequestValue value =
                new OrderRequest.OrderRequestValue(user.getTerminalNo(), "1");
        OrderRequest orderRequest = new OrderRequest(value);

        getOrders(orderRequest, listener);
    }


    /***
     * remote fetch data
     */
    private void getOrders(OrderRequest request, RemoteListener<ApiResponse<OrderData>> listener) {

        final Call<ApiResponse<OrderData>> call = apiService.getOrders(request);
        call.enqueue(handleApiCallback("getOrders", new RemoteListener<ApiResponse<OrderData>>() {
            @SuppressLint("NewApi")
            @Override
            public void onSuccess(ApiResponse<OrderData> tData) {
                Log.d(TAG(), "onSuccess: "+tData.data);
                Log.d(TAG(), "resutl: "+tData.result);
                if (tData.result.errNo.equals("0")) {
                    AppExecutor.getInstance().diskIO().execute(() -> {
                        for (OrderMasterEntity masterEntity : tData.data.getOrderMasterList()) {
                            // order
                            appDatabase.orderDao().insertOrderMaster(masterEntity);
                            // details
                            appDatabase.orderDao().insertOrderDetails(
                                    masterEntity.getOrdrDtl().stream().peek(orderDetailEntity ->
                                            orderDetailEntity.setOrderSrlFk(masterEntity.getOrderSrl()))
                                            .collect(Collectors.toList()));
                        }
                    });
                    if (listener != null) {
                        listener.onSuccess(tData);
                    }
                } else {
                    if (listener != null) {
                        listener.onError(tData.result.toString());
                    }
                }
            }

            @Override
            public void onError(String error) {
                if (listener != null) {
                    listener.onError(error);
                }
            }
        }));
        showRequestBody(call);
    }

    /**
     * SetOrderProcessed
     */
    public void updateOrderStatus(OrderProcessedRequest request, RemoteListener<ApiResponse<OrderData>> listener) {

    }

    public boolean isWork() {
        return isWork;
    }

    public void setWork(boolean work) {
        isWork = work;
    }
}
