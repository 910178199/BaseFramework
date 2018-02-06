package com.yida.app.InstitutionForThrAged;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.text.TextUtils;

import com.yida.app.InstitutionForThrAged.base.BaseApplication;
import com.yida.app.InstitutionForThrAged.utils.LogUtils;
import com.yida.app.InstitutionForThrAged.utils.OSUtil;
import com.yida.app.InstitutionForThrAged.utils.SafeUtils;
import com.yida.app.InstitutionForThrAged.utils.StringUtils;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by think on 2017/2/21.
 */

public class AppContext extends BaseApplication {

    private static final String KEY_APP_ID = "appid";
    private static final String KEY_APK_SIGNATURE = "app_signature";

    private static AppContext instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        LogUtils.onStrictMode();
    }

    public static AppContext getInstance() {
        return instance;
    }

    /**
     * 判断当前版本是否兼容
     */
    public boolean isMethodsCompat(int VisionCode) {
        int currentVisionCode = Build.VERSION.SDK_INT;
        return currentVisionCode >= VisionCode;
    }

    /**
     * 获取包相关信息
     *
     * @return
     */

    public PackageInfo getPackageInfo() {
        PackageInfo info = null;

        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (info == null) {
            info = new PackageInfo();
        }

        return info;
    }

    public static String getProperty(String key) {
        return getPreferences().getString(key, null);
    }

    public static void setProperty(String key, String value) {
        SharedPreferences.Editor edit = getPreferences().edit();
        edit.putString(key, value);
        apply(edit);
    }

    public static void removeProperty(String... keys) {
        for (String key : keys) {
            SharedPreferences.Editor editor = getPreferences().edit();
            editor.putString(key, null);
            apply(editor);
        }
    }


    public String getAppId() {
        String id = getProperty(KEY_APP_ID);
        if (StringUtils.isEmpty(id)) {
            id = UUID.randomUUID().toString();
            setProperty(KEY_APP_ID, id);
        }
        return id;
    }

    private int clearCacheFolder(File dir, long curTime) {
        int deletedFiles = 0;
        if (dir != null && dir.isDirectory()) {
            try {
                for (File child : dir.listFiles()) {
                    if (child.isDirectory()) {
                        deletedFiles += clearCacheFolder(child, curTime);
                    }
                    if (child.lastModified() < curTime) {
                        if (child.delete()) {
                            deletedFiles++;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletedFiles;
    }

    public void saveSignature(String signature) {
        SharedPreferences.Editor edit = getPreferences().edit();
        edit.putString(KEY_APK_SIGNATURE, signature);
        apply(edit);
    }

    public String getSignature() {
        String signature = getPreferences().getString(KEY_APK_SIGNATURE, "");
        if (TextUtils.isEmpty(signature)) {
            Signature packageSignature = OSUtil.getPackageSignature(this, getPackageName());

            try {
                signature = StringUtils.hexToString(SafeUtils.getMd5(packageSignature.toByteArray()));
            } catch (NoSuchAlgorithmException e) {
                LogUtils.i(e.getMessage());
            }
        }
        return signature;
    }

    public void setNoImageMode(boolean isChecked) {
        SharedPreferences.Editor edit = getPreferences().edit();
        edit.putBoolean(AppConstant.KEY_MODE_NO_IMAGE, isChecked);
        apply(edit);
    }

    public boolean isNoImageMode() {
        return getPreferences().getBoolean(AppConstant.KEY_MODE_NO_IMAGE, false);
    }

    public void setAutoCacheMode(boolean isChecked) {
        SharedPreferences.Editor edit = getPreferences().edit();
        edit.putBoolean(AppConstant.KEY_MODE_AUTO_CACHE, isChecked);
        apply(edit);
    }

    public boolean isAutoCacheMode() {
        return getPreferences().getBoolean(AppConstant.KEY_MODE_AUTO_CACHE, false);
    }

}
