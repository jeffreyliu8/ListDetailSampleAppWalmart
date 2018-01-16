package com.askjeffreyliu.walmartlist.endpoint;

import com.askjeffreyliu.walmartlist.model.ResponseObject;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.http.GET;

import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jeff on 1/14/18.
 */

public class GetProductEndPoint extends BaseEndpoint {
    private interface GetProductService {

        @GET("walmartproducts/{apiKey}/{pageNumber}/{pageSize}")
        Call<ResponseObject> getProduct(
                @Path("apiKey") String apiKey,
                @Query("pageNumber") int pageNumber,
                @Query("pageSize") int pageSize);
    }

    private final GetProductService getProductService;

    public GetProductEndPoint(final String apiKey) {
        super(apiKey);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …
        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        getProductService = retrofit.create(GetProductService.class);
    }

    public Call<ResponseObject> getProducts(final int pageNumber, int pageSize) {
        return getProductService.getProduct(apiKey, pageNumber, pageSize);
    }
}
