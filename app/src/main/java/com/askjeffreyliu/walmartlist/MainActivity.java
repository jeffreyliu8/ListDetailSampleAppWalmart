package com.askjeffreyliu.walmartlist;


import android.media.Image;
import android.os.Bundle;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.askjeffreyliu.walmartlist.endpoint.GetProductEndPoint;
import com.askjeffreyliu.walmartlist.listener.OnLoadMoreListener;
import com.askjeffreyliu.walmartlist.model.Product;
import com.askjeffreyliu.walmartlist.model.ResponseObject;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;


    private DataAdapter mAdapter;
    private List<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Logger.addLogAdapter(new AndroidLogAdapter());


        ButterKnife.bind(this);

        loadData();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);

        // create an Object for Adapter
        mAdapter = new DataAdapter(products, mRecyclerView, this, new DataAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, final int position, Product product) {

            }
        });

        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(String mediaId) {

            }
        });
    }

    // load initial data
    private void loadData() {
        GetProductEndPoint usersEndpoint = new GetProductEndPoint(Constant.API_KEY);
        usersEndpoint.getProducts(1, 10).enqueue(new retrofit2.Callback<ResponseObject>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseObject> call, retrofit2.Response<ResponseObject> response) {
                if (response.isSuccessful()) {
                    Logger.d("load successfully");
                    ResponseObject responseObject = response.body();
                    if (responseObject != null) {
                        products = responseObject.getProducts();
                        for (int i = 0; i < products.size(); i++) {
                            Logger.d("product id " + products.get(i).getProductId());
                        }
                        mAdapter.addProducts(products);
                    }
                } else {
                    Logger.e(response.toString());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseObject> call, Throwable t) {
                Logger.e("load recent failed");
            }
        });
    }
}
