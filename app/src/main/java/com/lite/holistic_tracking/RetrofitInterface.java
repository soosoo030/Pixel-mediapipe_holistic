package com.lite.holistic_tracking;


import com.google.gson.JsonElement;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("/user/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/user/signup")
    Call<Void> executeSignup(@Body HashMap<String, String> map);

    @POST("/user/check") //이메일 보내기 (인증번호용)
    Call<CheckResult> executeCheck (@Body HashMap<String, String> map);

    @GET("/dict/dictAll")
    Call<JsonElement> getDictAll();
}
