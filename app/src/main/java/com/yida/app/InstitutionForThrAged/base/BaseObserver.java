package com.yida.app.InstitutionForThrAged.base;

import android.content.Context;

import com.yida.app.InstitutionForThrAged.api.exception.ApiErrorHelper;

import io.reactivex.disposables.Disposable;

/**
 * Created by black on 2017/2/22.
 */

public class BaseObserver<T> implements io.reactivex.Observer<T> {

    private Context context;
    private ObserverOnNextListener listener;

    public BaseObserver() {
    }

    public BaseObserver(Context context, ObserverOnNextListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T value) {
        listener.onNext(value);
    }

    @Override
    public void onError(Throwable e) {
        ApiErrorHelper.handleCommonError(context, e);
    }

    @Override
    public void onComplete() {

    }
}
