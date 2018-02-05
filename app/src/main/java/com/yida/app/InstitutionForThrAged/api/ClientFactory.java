package com.yida.app.InstitutionForThrAged.api;

import com.yida.app.InstitutionForThrAged.AppConstant;
import com.yida.app.InstitutionForThrAged.AppContext;
import com.yida.app.InstitutionForThrAged.BuildConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by black on 2017/2/22.
 */

public enum ClientFactory {
    INSTANCE;

    private volatile OkHttpClient okHttpClient;
    private static final int TIMEOUT_READ = 15;
    private static final int TIMEOUT_CONNECTION = 15;

    private final OkHttpClient.Builder mBuilder;

    ClientFactory() {

        mBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            //日志
            mBuilder.addInterceptor(ClientHelper.getHttpLoggingInterceptor());
        }
        Cache cache = new Cache(new File(AppConstant.NET_DATA_PATH), 10 * 1024 * 1024);
        mBuilder.addNetworkInterceptor(ClientHelper.getAutoCacheInterceptor())
                .addInterceptor(ClientHelper.getAutoCacheInterceptor())
                .cache(cache)
                //连接失败重试
                .retryOnConnectionFailure(true)
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .build();
    }

    /**
     * https没有证书
     */
    private void onHttpsNoCertficates() {
        try {
            mBuilder.sslSocketFactory(ClientHelper.getSSLSocketFactory())
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * http有证书
     *
     * @param certficates
     * @param hosts
     */
    private void onHttpCertficates(int[] certficates, String[] hosts) {
        mBuilder.socketFactory(ClientHelper.getSSLSocketFactory(AppContext.context(), certficates));
        mBuilder.hostnameVerifier(ClientHelper.getHostnameVerifier(hosts));
    }


    public OkHttpClient getHttpClient() {
        okHttpClient = mBuilder.build();
        return okHttpClient;
    }


}
