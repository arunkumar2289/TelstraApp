package com.Telstra.sample.repository;


import android.text.TextUtils;
import android.util.Log;

import com.Telstra.sample.model.ImageData;
import com.Telstra.sample.model.LiveData;

import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContentRepository {
    private RetrofitService retrofitService;
    private static ContentRepository contentRepository;
    LiveData liveData;

    private ContentRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.HTTPS_API_CONTENT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitService = retrofit.create(RetrofitService.class);
    }

    public synchronized static ContentRepository getInstance() {
        if (contentRepository == null) {
            if (contentRepository == null) {
                contentRepository = new ContentRepository();
            }
        }
        return contentRepository;
    }

    public void getProjectList(final RetrofitDataStatus dataStatus) {

        retrofitService.getProjectList().enqueue(new Callback<LiveData>() {
            @Override
            public void onResponse(Call<LiveData> call, Response<LiveData> response) {
                dataStatus.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<LiveData> call, Throwable t) {
                dataStatus.onFailure();
            }
        });
    }

}
