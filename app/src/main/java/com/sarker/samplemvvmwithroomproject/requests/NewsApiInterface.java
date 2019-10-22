package com.sarker.samplemvvmwithroomproject.requests;

import com.sarker.samplemvvmwithroomproject.requests.responses.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiInterface {
    @GET("top-headlines")
    Call<NewsResponse> getNewsList(@Query("country") String newsSource,
                                   @Query("apiKey") String apiKey);
}
