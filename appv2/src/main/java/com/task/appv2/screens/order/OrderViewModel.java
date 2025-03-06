package com.task.appv2.screens.order;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.PagedList;

import com.task.appv2.data.models.ApiResponse;
import com.task.appv2.data.models.OrderData;
import com.task.appv2.data.models.OrderRequest;
import com.task.appv2.data.models.RemoteListener;
import com.task.appv2.data.repositories.ChefRepository;
import com.task.appv2.data.source.local.AppDatabase;
import com.task.appv2.data.source.local.entities.OrderMasterEntity;
import com.task.appv2.data.source.local.entities.pojo.OrderWithDetails;
import com.task.appv2.data.source.remote.RetrofitClient;
import com.task.appv2.data.source.remote.api.AssetChefApiService;
import com.task.appv2.data.source.remote.api.ChefApiService;

public class OrderViewModel extends AndroidViewModel {
    private final ChefRepository repository;

    // Observer for counting how many order records exist in the database
    protected MutableLiveData<Integer> countOrdersObserver = new MutableLiveData<>(0);
    protected MutableLiveData<String> limit = new MutableLiveData<>("0");
    // A paged list of Orders filtered by the current limit query
    private final LiveData<PagedList<OrderWithDetails>> orders;

    public OrderViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getDatabase(application);
        //ChefApiService apiService = RetrofitClient.getInstance().getApiService();
        AssetChefApiService assetChefApiService = new AssetChefApiService(application);
        repository = new ChefRepository(database, assetChefApiService);
        //--------
        // Transform the limit query into a filtered Orders list
        orders = Transformations.switchMap(limit, repository::loadPagingOrders);
        //
        repository.startRefreshTask(null, false);
    }

    public LiveData<PagedList<OrderWithDetails>> getOrders(){
        return orders;
    }







}
