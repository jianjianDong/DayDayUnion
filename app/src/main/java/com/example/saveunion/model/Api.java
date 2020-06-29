package com.example.saveunion.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("discovery/categories")
    Call<bean> getCategories();

}
