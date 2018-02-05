package com.yida.app.InstitutionForThrAged.api.remote;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author think
 * @name InstitutionForTheAged
 * @class name：com.yida.app.InstitutionForThrAged.api.remote
 * @class describe :
 * @time 2018-02-05 10:09
 */
public interface AppApi {

    @GET("/healthy/v1/{index}")
    Observable<Object> get(@Path("index") String indexs);

}
