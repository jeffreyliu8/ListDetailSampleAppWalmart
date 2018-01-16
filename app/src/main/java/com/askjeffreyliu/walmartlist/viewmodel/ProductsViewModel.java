package com.askjeffreyliu.walmartlist.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.askjeffreyliu.walmartlist.livedata.ProductsLiveData;


public class ProductsViewModel extends ViewModel {
    private ProductsLiveData liveData = new ProductsLiveData();

    public ProductsViewModel() {
    }

    public ProductsLiveData getLiveData() {
        return liveData;
    }
}
