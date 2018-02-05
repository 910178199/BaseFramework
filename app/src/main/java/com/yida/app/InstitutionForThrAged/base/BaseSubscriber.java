package com.yida.app.InstitutionForThrAged.base;

import android.content.Context;

import com.yida.app.InstitutionForThrAged.api.exception.ApiErrorCode;
import com.yida.app.InstitutionForThrAged.api.exception.ApiException;
import com.yida.app.InstitutionForThrAged.utils.OSUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by black on 2017/2/22.
 */

public class BaseSubscriber<T> implements Subscriber<T> {

    private Context context;

    public BaseSubscriber() {
    }

    public BaseSubscriber(Context context) {
        this.context = context;
    }


    @Override
    public void onSubscribe(Subscription s) {
        if (!OSUtil.hasInternet()) {
            this.onError(new ApiException(ApiErrorCode.ERROR_NO_INTERNET, "network interrupt"));
            return;
        }
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}
