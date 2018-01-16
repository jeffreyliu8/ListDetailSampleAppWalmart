package com.askjeffreyliu.walmartlist.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.askjeffreyliu.walmartlist.livedata.ProductsLiveData;




public class ProductsViewModel extends ViewModel {
    private ProductsLiveData liveData;


    public ProductsViewModel() {
        liveData = new ProductsLiveData();
    }

    public ProductsLiveData getLiveData() {
        return liveData;
    }
}
