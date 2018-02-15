package com.Telstra.sample;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.Telstra.sample.adapter.ContentAdapter;
import com.Telstra.sample.model.LiveData;
import com.Telstra.sample.repository.ContentRepository;
import com.Telstra.sample.databinding.ActivityMainBinding;
import com.Telstra.sample.dialog.NoNetworkConnection;
import com.Telstra.sample.repository.RetrofitDataStatus;
import com.Telstra.sample.util.Util;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, RetrofitDataStatus {

    ActivityMainBinding dataBinding;

    ContentAdapter contentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        dataBinding.swipeContainer.setOnRefreshListener(this);
        contentAdapter = new ContentAdapter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        dataBinding.recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        dataBinding.recyclerView.setLayoutManager(layoutManager);
        dataBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        dataBinding.recyclerView.setAdapter(contentAdapter);
        liveData();
    }

    @Override
    public void onRefresh() {
        liveData();
    }

    private void liveData() {
        dataBinding.swipeContainer.setRefreshing(false);
        if (Util.isNetworkAvailable(this)) {
            ContentRepository.getInstance().getProjectList(this);
        } else {
            new NoNetworkConnection(this, getString(R.string.alert_no_network_title), getString(R.string.alert_no_network_text)).show();
        }
    }

    private void setContentAdapter(LiveData liveData) {
        contentAdapter.updateList(Util.removeNull(liveData.rows));
    }

    @Override
    public void onSuccess(LiveData data) {
        if (data != null) {
            setContentAdapter(data);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null)
                actionBar.setTitle(data.title);
        }
    }

    @Override
    public void onFailure() {
        new NoNetworkConnection(this, getString(R.string.alert_no_network_title), getString(R.string.alert_no_network_text)).show();
    }

}