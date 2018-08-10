package com.example.student.androidappazat2;

import com.example.student.androidappazat2.models.ModelRecycler;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetInet {
    @GET("api/")
    Call<ModelRecycler> randomUsers(@Query("results") Integer results);
}
