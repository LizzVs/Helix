package com.hotel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainInterface {
    @GET("getid.php")
    Call<List<Patients>> getPosts(@Query("email") String email);
}
