package com.example.reminder_app;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface Api {





    @POST("register/")
    Call<ResponseBody> register(@Body RegisterRequest registerRequest);
    @POST("login/")
    Call<ResponseBody> login(@Body LoginRequest loginRequest);


}
