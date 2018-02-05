package com.yida.app.InstitutionForThrAged.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.yida.app.InstitutionForThrAged.R;
import com.yida.app.InstitutionForThrAged.presenter.db.RealmHelper;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by think on 2017/2/20.
 */

public class BaseApplication extends Application {

    static Context mContext;
    private static String PREF_NAME = "app.pref_name";
    static Resources sResource;
    private static String sLastToast = "";
    private static long sLastToastTime;
    private static boolean sIsAtLeastGB;
    private RealmHelper realmHelper;

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            sIsAtLeastGB = true;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        sResource = mContext.getResources();
        init();
    }

    public static synchronized BaseApplication context() {
        return (BaseApplication) mContext;
    }


    protected void init() {
        Logger.init("机构养老APP");

    }

    public RealmHelper getRealmInstance() {
        if (realmHelper == null) {
            realmHelper = new RealmHelper(this);
            return realmHelper;
        }
        return realmHelper;
    }


    public static Resources resources() {
        return sResource;
    }

    public static SharedPreferences getPersistPreferences() {
        return context().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static SharedPreferences getPreferences() {
        SharedPreferences pre = context().getSharedPreferences(PREF_NAME,
                Context.MODE_MULTI_PROCESS);
        return pre;
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static void apply(SharedPreferences.Editor editor) {
        if (sIsAtLeastGB) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public static String join(Set<String> set, String delim) {
        StringBuilder sb = new StringBuilder();
        String loopDelim = "";

        for (String s : set) {
            sb.append(loopDelim);
            sb.append(s);

            loopDelim = delim;
        }
        return sb.toString();
    }

    public static Set<String> getStringSet(String key) {
        String regularEx = "#";
        SharedPreferences sp = getPreferences();
        String values = sp.getString(key, "");
        String[] strs = values.split(regularEx);
        Set<String> vs = new HashSet<String>();
        for (String str : strs) {
            vs.add(str);
        }
        return vs;
    }

    public static void putStringSet(String key, Set<String> values) {
        String regularEx = "#";
        String str = "";
        SharedPreferences sp = getPreferences();
        if (values != null && values.size() > 0) {
            Iterator<String> itr = values.iterator();
            while (itr.hasNext()) {
                str += itr.next();
                str += regularEx;
            }
            SharedPreferences.Editor et = sp.edit();
            et.putString(key, str);
            apply(et);
        }
    }

    public static int[] getDisplaySize() {
        return new int[]{getPreferences().getInt("screen_width", 480),
                getPreferences().getInt("screen_height", 854)};
    }

    public static void saveDisplaySize(Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay()
                .getMetrics(displaymetrics);
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt("screen_width", displaymetrics.widthPixels);
        editor.putInt("screen_height", displaymetrics.heightPixels);
        editor.putFloat("density", displaymetrics.density);
        editor.commit();
    }

    public static String string(int id) {
        return sResource.getString(id);
    }

    public static String string(int id, Object... args) {
        return sResource.getString(id, args);
    }

    public static void toast(int message) {
        toast(message, Toast.LENGTH_LONG, 0);
    }

    public static void toast(String message) {
        toast(message, Toast.LENGTH_LONG, 0, Gravity.FILL_HORIZONTAL
                | Gravity.TOP);
    }

    public static void toast(int message, int icon) {
        toast(message, Toast.LENGTH_LONG, icon);
    }

    public static void toast(String message, int icon) {
        toast(message, Toast.LENGTH_LONG, icon, Gravity.FILL_HORIZONTAL
                | Gravity.TOP);
    }

    public static void toastShort(int message) {
        toast(message, Toast.LENGTH_SHORT, 0);
    }

    public static void toastShort(String message) {
        toast(message, Toast.LENGTH_SHORT, 0, Gravity.FILL_HORIZONTAL
                | Gravity.TOP);
    }

    public static void toastShort(int message, Object... args) {
        toast(message, Toast.LENGTH_SHORT, 0, Gravity.FILL_HORIZONTAL
                | Gravity.TOP, args);
    }

    public static void toast(int message, int duration, int icon) {
        toast(message, duration, icon, Gravity.FILL_HORIZONTAL
                | Gravity.TOP);
    }

    public static void toast(int message, int duration, int icon,
                             int gravity) {
        toast(context().getString(message), duration, icon, gravity);
    }

    public static void toast(int message, int duration, int icon,
                             int gravity, Object... args) {
        toast(context().getString(message, args), duration, icon, gravity);
    }

    public static void toast(String message, int duration, int icon,
                             int gravity) {
        if (message != null && !message.equalsIgnoreCase("")) {
            long time = System.currentTimeMillis();
            if (!message.equalsIgnoreCase(sLastToast)
                    || Math.abs(time - sLastToastTime) > 2000) {

                View view = LayoutInflater.from(context()).inflate(
                        R.layout.view_toast, null);
                ((TextView) view.findViewById(R.id.title_tv)).setText(message);
                if (icon != 0) {
                    ((ImageView) view.findViewById(R.id.icon_iv))
                            .setImageResource(icon);
                    view.findViewById(R.id.icon_iv)
                            .setVisibility(View.VISIBLE);
                }
                Toast toast = new Toast(context());
                toast.setView(view);
                toast.setDuration(duration);
                toast.show();

                sLastToast = message;
                sLastToastTime = System.currentTimeMillis();
            }
        }
    }

}
