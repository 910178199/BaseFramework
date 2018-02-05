package com.yida.app.InstitutionForThrAged;

import android.os.Environment;

import java.io.File;

/**
 * Created by think on 2017/2/21.
 */

public class AppConstant {

    //DATA STORE
    public static final String DATA_PATH = AppContext.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String SDCARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "fastapp";
    public static final String LOG_PATH = SDCARD_PATH + File.separator + "log" + File.separator;
    public static final String NET_DATA_PATH = DATA_PATH + File.separator + "net_cache";
    public static final String UPDATE_FILE_PATH = SDCARD_PATH + File.separator + "update";


    //SP CONFIG
    public static final String KEY_MODE_NO_IMAGE = "no image";
    public static final String KEY_MODE_AUTO_CACHE = "auto cache";

}
