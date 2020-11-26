package com.hotel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StatusInterface {
    @GET("lab/getstatus.php")
    Call<List<Research>> getPosts(@Query("id") int id);
}
