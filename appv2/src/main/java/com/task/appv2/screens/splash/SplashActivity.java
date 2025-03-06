package com.task.appv2.screens.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import com.task.appv2.R;
import com.task.appv2.screens.order.MainActivity;
import com.task.appv2.screens.login.LoginActivity;
import com.task.appv2.screens.login.LoginViewModel;


public class SplashActivity extends AppCompatActivity {
    private boolean isLogged;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        LoginViewModel viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        viewModel.getUserData().observe(SplashActivity.this, userEntity -> {
            isLogged = userEntity != null && userEntity.getLoggedOn() !=null;
            Log.d("SplashActivity", "onCreate() called with:getUserData().observe isLogged:"+isLogged);
        });

       new CountDownTimer(1000, 4) {
           @Override
           public void onTick(long l) {

           }

           @Override
           public void onFinish() {
               if (isLogged){
                   startActivity(new Intent(SplashActivity.this, MainActivity.class));
                   finish();
               }else {
                   startActivity(new Intent(SplashActivity.this, LoginActivity.class));
               }
           }
       }.start();
    }
}