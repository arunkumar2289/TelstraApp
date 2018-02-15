package com.Telstra.sample.repository;


import com.Telstra.sample.model.LiveData;

import retrofit2.Call;
import retrofit2.http.GET;

interface RetrofitService {

    String HTTPS_API_CONTENT_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";

    @GET("facts.json")
    Call<LiveData> getProjectList();
}
