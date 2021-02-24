package com.github.jobsearch.api;

import feign.Feign;
import feign.gson.GsonDecoder;

public interface APIFunctions {
    /**
     * For building the call to Feign, we need to generate a Feing client,
     * hide that this is the client internally used, that way it makes the http library
     * replacement easier in the future
     *
     * @param api a type T Class to build our api
     * @param url the base URL where we will making requests
     * @param <T> API type we will build
     * @return a T instance to be used as API client
     */
    static <T> T buildAPI(Class<T> api, String url) {
        return Feign.builder()
                .decoder(new GsonDecoder())
                .target(api, url);
    }
}
