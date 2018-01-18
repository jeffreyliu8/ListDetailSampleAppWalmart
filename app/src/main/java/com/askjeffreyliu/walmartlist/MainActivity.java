package com.askjeffreyliu.walmartlist;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.askjeffreyliu.walmartlist.listener.OnLoadMoreListener;
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
    private ProductsViewModel viewModel;
    private DataAdapter mAdapter;

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
            public void onClick(View view, final ImageView imageView, Product product) {
                DetailActivity.navigate(MainActivity.this, imageView, product);
            }
        });

        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                viewModel.loadPage();
            }
        });
    }

    private void setDataListener() {
        // Get the ViewModel.
        viewModel = ViewModelProviders.of(this).get(ProductsViewModel.class);

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.getLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                if (products != null && mAdapter != null) {
                    Log.d("jeff", "products size " + products.size());
                    mAdapter.setProducts(products);
                }
            }
        });
    }
}
