package com.askjeffreyliu.walmartlist;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;



import com.askjeffreyliu.walmartlist.listener.OnLoadMoreListener;
import com.askjeffreyliu.walmartlist.livedata.ProductsLiveData;
import com.askjeffreyliu.walmartlist.model.Product;

import com.askjeffreyliu.walmartlist.viewmodel.ProductsViewModel;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private ProductsLiveData productsLiveData;
    private DataAdapter mAdapter;
    private List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Logger.addLogAdapter(new AndroidLogAdapter());


        ButterKnife.bind(this);

        setDataListener();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);

        // create an Object for Adapter
        mAdapter = new DataAdapter(mRecyclerView, this, new DataAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, final int position, Product product) {

            }
        });

        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                productsLiveData.loadMore(true);
            }
        });
    }

    private void setDataListener() {
        // Get the ViewModel.
        ProductsViewModel viewModel = ViewModelProviders.of(this).get(ProductsViewModel.class);

        // Create the observer which updates the UI.
        final Observer<List<Product>> dataObserver = new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable final List<Product> newCompleteList) {
                if (newCompleteList != null && mAdapter != null) {
                    MainActivity.this.products = newCompleteList;
                    mAdapter.setProducts(newCompleteList);
                }
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.getLiveData().observe(this, dataObserver);
        productsLiveData = viewModel.getLiveData();
    }
}
