package com.task.appv2.screens.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.task.appv2.data.models.ApiResponse;
import com.task.appv2.data.models.LoginRequest;
import com.task.appv2.data.models.RemoteListener;
import com.task.appv2.data.models.UsrData;
import com.task.appv2.data.repositories.UserRepository;
import com.task.appv2.data.source.local.AppDatabase;
import com.task.appv2.data.source.local.entities.UserEntity;
import com.task.appv2.data.source.remote.RetrofitClient;
import com.task.appv2.data.source.remote.api.ChefApiService;


public class LoginViewModel extends AndroidViewModel {
    private final UserRepository repository;
    private final LiveData<UserEntity> _userData;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getDatabase(application);
        ChefApiService apiService = RetrofitClient.getInstance().getApiService();
        repository = new UserRepository(database, apiService);
        //----
        _userData = repository.getUserData();
    }

    public LiveData<UserEntity> getUserData() {
        return _userData;
    }

    public void login(LoginRequest request, final RemoteListener<ApiResponse<UsrData>> listener) {
        repository.loginUser(request, listener);
    }

    public void logout()  {
        repository.logout();
    }

}
