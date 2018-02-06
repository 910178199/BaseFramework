package com.yida.app.InstitutionForThrAged.api.remote;

import com.yida.app.InstitutionForThrAged.model.MovieBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author think
 * @name InstitutionForTheAged
 * @class name：com.yida.app.InstitutionForThrAged.api.remote
 * @class describe :
 * @time 2018-02-05 10:09
 */
public interface AppApi {

    @GET("top250")
    Observable<MovieBean> getTopMovie(@Query("start") int start, @Query("count") int count);

}
