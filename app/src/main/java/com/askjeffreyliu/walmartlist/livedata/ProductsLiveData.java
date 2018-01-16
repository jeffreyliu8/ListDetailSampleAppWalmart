package com.askjeffreyliu.walmartlist.livedata;

import android.arch.lifecycle.MutableLiveData;
import android.os.Handler;


import com.askjeffreyliu.walmartlist.Constant;
import com.askjeffreyliu.walmartlist.endpoint.GetProductEndPoint;
import com.askjeffreyliu.walmartlist.model.Product;
import com.askjeffreyliu.walmartlist.model.ResponseObject;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;


public class ProductsLiveData extends MutableLiveData<List<Product>> {
    private List<Product> products;
    private int nextPageToBeFetched = 0;
    private GetProductEndPoint usersEndpoint = new GetProductEndPoint(Constant.API_KEY);


    public ProductsLiveData() {
        loadMore(false);
    }

    public void loadMore(boolean removeProgressBar) {
        usersEndpoint.getProducts(nextPageToBeFetched, Constant.PAGE_SIZE).enqueue(new retrofit2.Callback<ResponseObject>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseObject> call, retrofit2.Response<ResponseObject> response) {
                if (response.isSuccessful()) {
                    ResponseObject responseObject = response.body();
                    if (responseObject != null) {
                        if (nextPageToBeFetched == responseObject.getPageNumber()) {
                            nextPageToBeFetched = responseObject.getPageNumber() + 1;

                            if (products == null) {
                                Logger.d("setting values count before null");
                                products = new ArrayList<>();
                            } else {
                                Logger.d("setting values count before" + products.size());
                            }
                            products.addAll(responseObject.getProducts());
                            Logger.d("setting values count " + products.size());
                            setValue(products);
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
        });
    }
}
