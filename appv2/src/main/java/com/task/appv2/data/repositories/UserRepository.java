package com.task.appv2.data.repositories;

import androidx.lifecycle.LiveData;

import com.task.appv2.data.models.ApiResponse;
import com.task.appv2.data.models.LoginRequest;
import com.task.appv2.data.models.RemoteListener;
import com.task.appv2.data.models.UsrData;
import com.task.appv2.data.source.local.AppDatabase;
import com.task.appv2.data.source.local.entities.UserEntity;
import com.task.appv2.data.source.remote.api.ChefApiService;
import com.task.appv2.utils.AppExecutor;

import retrofit2.Call;

public class UserRepository extends BaseRepository {

    public UserRepository(AppDatabase appDatabase, ChefApiService apiService) {
        super(appDatabase, apiService);
    }

    public LiveData<UserEntity> getUserData() {
        return appDatabase.userDao().getUserData();
    }

    public void loginUser(LoginRequest request, RemoteListener<ApiResponse<UsrData>> listener) {
        Call<ApiResponse<UsrData>> call = apiService.getUserDetails(request);
        call.enqueue(handleApiCallback("Login", new RemoteListener<ApiResponse<UsrData>>() {
            @Override
            public void onSuccess(ApiResponse<UsrData> tData) {
                if (tData.result.errNo.equals("0")) {
                    AppExecutor.getInstance().diskIO().execute(() -> {
                        appDatabase.userDao().delete();
                        appDatabase.userDao().insert(tData.data.getUserEntity());
                    });
                    listener.onSuccess(tData);
                }else
                listener.onError(tData.result.errNo+"-"+tData.result.errMessage);
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        }));
    }

    public void logout() {
        AppExecutor.getInstance().diskIO().execute(() -> appDatabase.userDao().delete());
    }

}
