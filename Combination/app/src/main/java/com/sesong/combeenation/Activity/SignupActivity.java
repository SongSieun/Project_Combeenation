package com.sesong.combeenation.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.gson.JsonObject;
import com.sesong.combeenation.R;
import com.sesong.combeenation.databinding.ActivitySignupBinding;
import com.sesong.combeenation.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivitySignupBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Id = binding.idEdit.getText().toString();
                String Password_e = binding.passEdit.getText().toString();
                Log.d("ID", Id);
                Log.d("PassWord", Password_e);
                signUp(Id, Password_e);
            }
        });
    }

    public void signUp(String username, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<JsonObject> response = retrofitService.signup(username, password);
        response.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("Login", "Pass ");
                Log.d("Response (body)", String.valueOf(response.body()));
                Log.d("Response (code)", String.valueOf(response.code()));
                Log.d("Response (message)", String.valueOf(response.message()));
                Log.d("Response (isSuccessful)", String.valueOf(response.isSuccessful()));
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
}