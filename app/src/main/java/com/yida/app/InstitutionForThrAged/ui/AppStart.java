package com.yida.app.InstitutionForThrAged.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.yida.app.InstitutionForThrAged.R;
import com.yida.app.InstitutionForThrAged.router.Router;
import com.yida.app.InstitutionForThrAged.utils.SystemBarTintManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by black on 2017/2/22.
 */

public class AppStart extends Activity {

    @BindView(R.id.app_start_img)
    ImageView appStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_start);
        ButterKnife.bind(this);

        if (appStart != null && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.transparent);
        }

        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        scaleAnimation.setDuration(3000);

        animationSet.addAnimation(scaleAnimation);

        animationSet.setFillAfter(true);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Router.showMain(AppStart.this);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        appStart.startAnimation(animationSet);
    }

    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
