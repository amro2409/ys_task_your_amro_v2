package com.task.appv2.screens.order;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.navigationrail.NavigationRailView;
import com.task.appv2.R;
import com.task.appv2.screens.login.LoginActivity;
import com.task.appv2.screens.login.LoginViewModel;
import com.task.appv2.widgets.dialog.CustomDialog;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    LoginViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userViewModel = new ViewModelProvider(this).get(LoginViewModel.class);


        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }


        NavigationRailView navigationRailView = findViewById(R.id.navigation_rail);


        navigationRailView.setOnItemSelectedListener(item -> {
            try {
                if (item.getItemId() == R.id.nav_logout) {
                    logout();
                    return true;
                }
                navController.navigate(item.getItemId());
                return true;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return false;
            }
        });


//        navigationRailView.setNavigationItemSelectedListener(menuItem -> {
//            switch (menuItem.getItemId()) {
//                case R.id.nav_orders:
//                    navController.navigate(R.id.nav_orders);
//                    return true;
//                case R.id.nav_history:
//                    navController.navigate(R.id.nav_history);
//                    return true;
//                case R.id.nav_summary:
//                    navController.navigate(R.id.nav_summary);
//                    return true;
//                case R.id.nav_language:
//                    navController.navigate(R.id.nav_language);
//                    return true;
//                case R.id.nav_logout:
//                    // تعامل مع تسجيل الخروج
//                    return true;
//                default:
//                    return false;
//            }
//        });

    }


    private void logout() {
        CustomDialog.setDialogCallback(MainActivity.this,
                new CustomDialog.NoteWithCancelAlertDialogFactory(() -> {
                    userViewModel.logout();
                    //----
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }), "", "are you sure you ant to logout?.");
    }
}