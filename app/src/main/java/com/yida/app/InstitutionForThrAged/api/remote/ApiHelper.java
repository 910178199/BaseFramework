package com.yida.app.InstitutionForThrAged.api.remote;

import com.yida.app.InstitutionForThrAged.model.MovieBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author think
 * @name InstitutionForTheAged
 * @class name：com.yida.app.InstitutionForThrAged.api.remote
 * @class describe :
 * @time 2018-02-06 15:08
 */
public class ApiHelper {

    /**
     * 封装线程管理和订阅的过程
     */
    public static void ApiSubscribe(Observable observable, Observer observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 用于获取豆瓣电影Top250的数据
     *
     * @param observer 由调用者传过来的观察者对象
     * @param start    起始位置
     * @param count    获取长度
     */
    public static void getTopMovie(Observer<MovieBean> observer, int start, int count) {
        ApiSubscribe(ApiFactory.getAppApi().getTopMovie(start, count), observer);
    }

}
