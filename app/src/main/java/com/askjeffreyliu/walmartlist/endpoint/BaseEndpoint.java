package com.askjeffreyliu.walmartlist.endpoint;

/**
 * Created by jeff on 1/14/18.
 */

public abstract class BaseEndpoint {
    protected static final String BASE_URL = "https://walmartlabs-test.appspot.com/_ah/api/walmart/v1/";

    protected final String apiKey;


    protected BaseEndpoint(final String apiKey) {
        this.apiKey = apiKey;
    }
}
