package com.askjeffreyliu.walmartlist.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import android.util.Log;


import com.askjeffreyliu.walmartlist.Constant;
import com.askjeffreyliu.walmartlist.endpoint.GetProductEndPoint;

import com.askjeffreyliu.walmartlist.model.Product;
import com.askjeffreyliu.walmartlist.model.ResponseObject;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;


public class ProductsViewModel extends ViewModel implements Callback<ResponseObject> {

    private GetProductEndPoint usersEndpoint = new GetProductEndPoint(Constant.API_KEY);
    private MutableLiveData<List<Product>> mLiveData = new MutableLiveData<>();
    private int pageNumber = 0;
    private List<Product> products = new ArrayList<>();

    public ProductsViewModel() {
        loadPage();
    }

    public void loadPage() {
        usersEndpoint.getProducts(pageNumber, Constant.PAGE_SIZE).enqueue(this);
    }

    @Override
    public void onResponse(retrofit2.Call<ResponseObject> call, retrofit2.Response<ResponseObject> response) {
        if (response.isSuccessful()) {
            ResponseObject responseObject = response.body();
            if (responseObject != null) {
                if (pageNumber == responseObject.getPageNumber()) {
                    pageNumber++;

                    products.addAll(responseObject.getProducts());
                    Log.d("jeff", pageNumber + " setting values count " + products.size());
                    mLiveData.setValue(products);
                }
            }
        } else {
            Logger.e(response.toString());
        }
    }

    @Override
    public void onFailure(retrofit2.Call<ResponseObject> call, Throwable t) {
        Logger.e("load recent failed");
    }

    public LiveData<List<Product>> getLiveData() {
        return mLiveData;
    }
}
