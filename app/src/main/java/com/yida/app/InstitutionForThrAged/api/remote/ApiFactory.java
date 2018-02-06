package com.yida.app.InstitutionForThrAged.api.remote;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.yida.app.InstitutionForThrAged.api.ClientFactory;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by black on 2017/2/22.
 */

public enum ApiFactory {
    INSTANCE;

    //    public static final String BASE_API = "http://47.92.83.246:8060/";
    public static final String BASE_API = "https://api.douban.com/v2/movie/";
    private static AppApi appApi;

    ApiFactory() {
    }


    public static AppApi getAppApi() {
        if (appApi == null) {
            synchronized (ApiFactory.class) {
                appApi = createApi(BASE_API, AppApi.class, GsonConverterFactory.create());
            }
        }
        return appApi;
    }


    private static <T> T createApi(String baseUrl, Class<T> clazz, Converter.Factory factory) {
        Retrofit.Builder retrofit = new Retrofit.Builder();
        retrofit.baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(factory);
        return retrofit.client(ClientFactory.INSTANCE.getHttpClient()).build().create(clazz);
    }


}
