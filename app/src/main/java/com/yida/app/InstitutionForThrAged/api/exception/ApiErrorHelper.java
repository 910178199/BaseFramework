package com.yida.app.InstitutionForThrAged.api.exception;

import android.app.Activity;
import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.yida.app.InstitutionForThrAged.AppContext;
import com.yida.app.InstitutionForThrAged.R;
import com.yida.app.InstitutionForThrAged.base.BaseApplication;
import com.yida.app.InstitutionForThrAged.utils.LogUtils;

import java.io.IOException;

/**
 * Created by black on 2017/2/22.
 */

public class ApiErrorHelper  {

    private static final String TAG = ApiErrorHelper.class.getSimpleName();


    public static void handleCommonError(Context context,Throwable e){
        if (e instanceof HttpException || e instanceof IOException) {
            AppContext.toastShort(BaseApplication.resources().getString(R.string.error_no_internet));
            LogUtils.e(TAG, e.getMessage());
        } else if (e instanceof ApiException) {
            handleApiError(context, e);
        } else {
            LogUtils.e(TAG, e.getMessage());
        }
    }

    private static void handleApiError(Context context, Throwable e) {

        ApiException exception = (ApiException) e;
        switch (exception.getErrorCode()) {
            case ApiErrorCode.ERROR_CLIENT_AUTHORIZED:
                AppContext.toastShort(BaseApplication.resources().getString(R.string.error_invalid_client));
                break;
            case ApiErrorCode.ERROR_NO_INTERNET:
                AppContext.toastShort(BaseApplication.resources().getString(R.string.error_no_internet));
                break;
            case ApiErrorCode.ERROR_OTHER:
                AppContext.toast(BaseApplication.resources().getString(R.string.error_other));
                break;
            case ApiErrorCode.ERROR_PARAM_CHECK:
            case ApiErrorCode.ERROR_REQUEST_PARAM:
                LogUtils.e(TAG, "param request error:" + exception.getMessage());
                break;
            case ApiErrorCode.ERROR_USER_AUTHORIZED:
                reLogin(context);
                break;
            default:
                AppContext.toastShort(BaseApplication.resources().getString(R.string.error_unknown));
                break;
        }
    }

    private static void reLogin(Context context) {
        if (context != null && context instanceof Activity) {
            //need login

        }
    }


}
