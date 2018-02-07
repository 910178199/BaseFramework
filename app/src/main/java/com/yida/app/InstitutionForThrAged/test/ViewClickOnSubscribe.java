package com.yida.app.InstitutionForThrAged.test;

import android.view.View;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Cancellable;

/**
 * @author think
 * @name InstitutionForTheAged
 * @class name：com.yida.app.InstitutionForThrAged.test
 * @class describe :
 * @time 2018-02-07 10:04
 */
public class ViewClickOnSubscribe implements ObservableOnSubscribe<View> {

    View view;

    public ViewClickOnSubscribe(View view) {
        this.view = view;
    }

    @Override
    public void subscribe(final ObservableEmitter<View> e) throws Exception {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!e.isDisposed()) {
                  e.onNext(v);
                }
            }
        };
        view.setOnClickListener(onClickListener);
        e.setCancellable(new Cancellable() {
            @Override
            public void cancel() throws Exception {
                view.setOnClickListener(null);
            }
        });
    }
}
