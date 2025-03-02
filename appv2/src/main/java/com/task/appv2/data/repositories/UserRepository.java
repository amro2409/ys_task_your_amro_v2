package com.task.appv2.data.repositories;

import com.task.appv2.data.source.local.AppDatabase;
import com.task.appv2.data.source.local.entities.UserEntity;
import com.task.appv2.data.source.remote.api.ChefApiService;
import com.task.appv2.data.models.ApiResponse;
import com.task.appv2.data.models.LoginRequest;
import com.task.appv2.data.models.RemoteListener;

import retrofit2.Call;

public class UserRepository extends  BaseRepository{

    public UserRepository(AppDatabase appDatabase, ChefApiService apiService) {
        super(appDatabase, apiService);
    }

    public void loginUser(LoginRequest request, RemoteListener<ApiResponse<UserEntity>> listener) {
        Call<ApiResponse<UserEntity>> call = apiService.getUserDetails(request);
        call.enqueue(handleApiCallback("Login", listener));
    }

    public void logout() {
        appDatabase.userDao().delete();
    }

}
