package com.yida.app.InstitutionForThrAged.router;

/**
 * Created by think on 2017/2/24.
 */

import android.app.Activity;
import android.content.Intent;

import com.yida.app.InstitutionForThrAged.ui.MainActivity;

/**
 * 跳转类
 */
public class Router {

    public static void showMain(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

}
