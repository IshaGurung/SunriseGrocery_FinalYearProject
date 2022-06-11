package com.example.sunrisegrocery.home.fragments.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sunrisegrocery.R;
import com.example.sunrisegrocery.api.ApiClient;
import com.example.sunrisegrocery.api.response.RegisterResponse;
import com.example.sunrisegrocery.utils.SharedPrefUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {
    LinearLayout changePwLL, forgetLL, cancelLL;
    String type = "show";

    EditText newPasswordET, confirmPasswordET;
    TextView changeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        newPasswordET = findViewById(R.id.newPwET);
        confirmPasswordET = findViewById(R.id.confirmPasswordET);
        changePwLL = findViewById(R.id.changePwLL);
        cancelLL = findViewById(R.id.cancelLL);

        cancelLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        changePwLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePassword();
            }

            private void updatePassword() {

//                if (validateAll()) {
                String key = SharedPrefUtils.getString(ChangePasswordActivity.this, getString(R.string.api_key));
                Call<RegisterResponse> changePassword = ApiClient.getClient().forgetPassword(key, newPasswordET.getText().toString());
                changePassword.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        if (response.isSuccessful()) {
                            if (!response.body().getError()) {
                                Toast.makeText(ChangePasswordActivity.this, "Password Successfully Changed", Toast.LENGTH_SHORT).show();
//                                    SharedPrefUtils.setString(ChangePWActivity.this, DataHolder.PASSWORD_KEY, newPasswordET.getText().toString());
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {

                    }
                });
            }
        });
    }
}