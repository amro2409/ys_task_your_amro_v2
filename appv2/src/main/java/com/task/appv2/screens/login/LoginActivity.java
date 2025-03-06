package com.task.appv2.screens.login;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.task.appv2.R;
import com.task.appv2.data.models.UsrData;
import com.task.appv2.data.models.ApiResponse;
import com.task.appv2.data.models.LoginRequest;
import com.task.appv2.data.models.RemoteListener;
import com.task.appv2.databinding.ActivityLoginBinding;
import com.task.appv2.screens.order.MainActivity;
import com.task.appv2.widgets.dialog.CustomDialog;

import java.util.Stack;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding loginBinding;
    private ProgressDialog progressDialog1;
    int MAX_IN = 6;
    Stack<Integer> pinIntegerStack = new Stack<>();
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);


        loginBinding.zeroBtn.setOnClickListener(view -> addStock(0));
        loginBinding.oneBtn.setOnClickListener(view -> addStock(1));
        loginBinding.toBtn.setOnClickListener(view -> addStock(2));
        loginBinding.threeBtn.setOnClickListener(view -> addStock(3));
        loginBinding.fourBtn.setOnClickListener(view -> addStock(4));
        loginBinding.fiveBtn.setOnClickListener(view -> addStock(5));
        loginBinding.sixBtn.setOnClickListener(view -> addStock(6));
        loginBinding.sevenBtn.setOnClickListener(view -> addStock(7));
        loginBinding.eightBtn.setOnClickListener(view -> addStock(8));
        loginBinding.ninBtn.setOnClickListener(view -> addStock(9));
        loginBinding.deleteBtn.setOnClickListener(view -> removeStack());

        loginBinding.tryDemoBtn.setOnClickListener(view -> sendLogin());
    }

    void addStock(int number) {
        int size = pinIntegerStack.size();

        if (size < MAX_IN) {
            pinIntegerStack.push(number);
        }
        size = pinIntegerStack.size();
        Log.d("addStock", "size:" + size);
        circleState(size, true);
    }

    @SuppressLint("NewApi")
    String convertStackToString() {
        StringBuilder builder = new StringBuilder();
        pinIntegerStack.forEach(builder::append);
        return builder.toString();
    }

    void removeStack() {
        int size = pinIntegerStack.size();
        if (size > 0 || !pinIntegerStack.empty()) {
            pinIntegerStack.pop();
        }
        circleState(size, false);
    }


    void circleState(int number, boolean isFull) {
        switch (number) {
            case 1:
                loginBinding.imageviewCircle1.setImageResource(isFull ? R.drawable.circle2 : R.drawable.circle);
                break;
            case 2:
                loginBinding.imageviewCircle2.setImageResource(isFull ? R.drawable.circle2 : R.drawable.circle);
                break;
            case 3:
                loginBinding.imageviewCircle3.setImageResource(isFull ? R.drawable.circle2 : R.drawable.circle);
                break;
            case 4:
                loginBinding.imageviewCircle4.setImageResource(isFull ? R.drawable.circle2 : R.drawable.circle);
                break;
            case 5:
                loginBinding.imageviewCircle5.setImageResource(isFull ? R.drawable.circle2 : R.drawable.circle);
                break;
            case 6:
                loginBinding.imageviewCircle6.setImageResource(isFull ? R.drawable.circle2 : R.drawable.circle);

        }
    }

    private void sendLogin() {
        String password = convertStackToString();
        Log.d("LoginActivity", "st:" + convertStackToString());

        if (password.length() > 5){
            LoginRequest.LoginValue loginValue = new LoginRequest.LoginValue("87", "1", password);
            LoginRequest loginRequest = new LoginRequest(loginValue);

            showProgress1();
            loginViewModel.login(loginRequest, new RemoteListener<ApiResponse<UsrData>>() {
                @Override
                public void onSuccess(ApiResponse<UsrData> tData) {

                    if (progressDialog1 != null) progressDialog1.dismiss();
                    CustomDialog.setDialogCallback(LoginActivity.this,
                            new CustomDialog.NoteAlertDialogFactory(() -> startActivity()),
                            tData.result.errNo,
                            tData.result.errMessage);
                    Log.d("LoginActivity", "st:" + pinIntegerStack);

                }

                @Override
                public void onError(String error) {
                    if (progressDialog1 != null && progressDialog1.isShowing())
                        progressDialog1.dismiss();
                    CustomDialog.setDialogCallback(LoginActivity.this,
                            new CustomDialog.ErrorAlertDialogFactory(() ->{}),
                            "",
                           error);
                    Log.d("LoginActivity", "st:" + pinIntegerStack);
                    Log.e("LoginActivity", "st:" + error);
                }
            });
        }
        else {
            Toast.makeText(LoginActivity.this, "Must input password.", Toast.LENGTH_SHORT).show();
        }
    }

    private void showProgress1() {
        progressDialog1 = new ProgressDialog(this);
        String[] msg = {"جاري التحقق من تسجيل الدخول ......", "جاري  التنزيل ......", "جاري تحديث ......"};
        progressDialog1.setMessage(msg[0]);
        progressDialog1.setMax(100);
        progressDialog1.setCancelable(false);
        progressDialog1.show();
    }

    private void startActivity() {
        Intent in = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(in);
        // overridePendingTransition(R.anim.animate_slide_up_enter,R.anim.animate_slide_up_enter);
        finish();
    }


}