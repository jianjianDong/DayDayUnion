package com.example.saveunion.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Api {

    @GET("discovery/categories")
    Call<bean> getCategories();

    @GET
    Call<HomePageContent> getHomePageContent(@Url String url);

}
