package com.Telstra.sample.repository;

import com.Telstra.sample.model.LiveData;

public interface RetrofitDataStatus {
     void onSuccess(LiveData data);

     void onFailure();
}
