package com.yida.app.InstitutionForThrAged.base.ui;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yida.app.InstitutionForThrAged.AppContext;
import com.yida.app.InstitutionForThrAged.AppManager;
import com.yida.app.InstitutionForThrAged.R;
import com.yida.app.InstitutionForThrAged.base.inter.IBaseView;
import com.yida.app.InstitutionForThrAged.view.dialog.CommonToast;
import com.yida.app.InstitutionForThrAged.view.dialog.DialogHelper;
import com.yida.app.InstitutionForThrAged.view.dialog.IDialog;
import com.yida.app.InstitutionForThrAged.view.dialog.WaitDialog;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by think on 2017/2/20.
 */

public abstract class BaseActivity extends RxAppCompatActivity
        implements IBaseView, View.OnClickListener, IDialog {

    private boolean mIsVisible;
    private WaitDialog mDialog;
    protected LayoutInflater mInflater;
    private Toolbar mToolbar;
    private TextView mActionTitle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppContext.saveDisplaySize(this);

        if (!hasActionBar()) {
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        /**
         * 添加activity到栈
         */
        AppManager.getAppManager().addActivity(this);

        onBeforeSetContentLayout();

        /**
         * 设置layout
         */
        if (getLayout() != 0) {
            setContentView(getLayout());
        }

        mToolbar = (Toolbar) findViewById(R.id.actionBar);
        mInflater = getLayoutInflater();

        if (hasActionBar()) {
            initToolbar(mToolbar);
        }

        //注解绑定控件
        ButterKnife.bind(this);

        init(savedInstanceState);
        initView();
        initData();
        mIsVisible = true;

        /**
         * Android 6.0（API 23）及更高的版本上，
         * 系统也开始提供了对应的 API 来实现浅色调背景的状态栏效果，
         * 可将状态栏图标和文字内容改为黑色样式
         */
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }


    protected void init(Bundle saveInstanceState) {

    }


    protected void initToolbar(Toolbar actionBar) {
        if (actionBar == null) {
            return;
        }
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        actionBar.setTitle("");

        if (!isDefaultActionBar()) {
            /**
             * 使用自定义toolbar
             */
            int layoutRes = getActionBarCustonView();
            if (layoutRes != 0) {
                View view = inflateView(layoutRes);
                Toolbar.LayoutParams params = new Toolbar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                        ActionBar.LayoutParams.MATCH_PARENT);
                actionBar.addView(view, params);
                mActionTitle = (TextView) view.findViewById(R.id.action_bar_title1);
                int titleRes = getActionBarTitle();
                if (titleRes != 0 && mActionTitle != null) {
                    mActionTitle.setText(titleRes);
                }
            }
        } else {
            mActionTitle = (TextView) actionBar.findViewById(R.id.action_bar_title1);
            int titleRes = getActionBarTitle();
            if (titleRes != 0 && mActionTitle != null) {
                mActionTitle.setText(titleRes);
            }
        }

        if (hasBackButton()) {
            actionBar.setNavigationIcon(R.mipmap.actionbar_back_icon_normal);
            actionBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            actionBar.setPadding(0, 0, 0, 0);
        }
    }


    protected boolean hasBackButton() {
        return false;
    }


    protected int getActionBarTitle() {
        return R.string.app_name;
    }

    protected View inflateView(int resId) {
        return mInflater.inflate(resId, null);
    }


    protected int getActionBarCustonView() {
        return 0;
    }


    protected boolean isDefaultActionBar() {
        return true;
    }


    protected abstract int getLayout();


    protected void onBeforeSetContentLayout() {
    }

    protected boolean hasActionBar() {
        return true;
    }


    public void setActionBarTitle(String title) {
        if (hasActionBar()) {
            if (mActionTitle != null) {
                mActionTitle.setText(title);
            } else if (mToolbar != null) {
                mToolbar.setTitle(title);
            }
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    public void setActionBarTitle(int resId) {
        if (resId != 0) {
            setActionBarTitle(getString(resId));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        mIsVisible = false;
        hideWaitDialog();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mIsVisible = true;
        super.onResume();
    }

    public void showToast(String message, int icon, int gravity) {
        CommonToast toast = new CommonToast(this);
        toast.setMessage(message);
        toast.setMessageIc(icon);
        toast.setLayoutGravity(gravity);
        toast.show();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void hideWaitDialog() {
        if (mIsVisible && mDialog != null) {
            try {
                mDialog.dismiss();
                mDialog = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public WaitDialog showWaitDialog() {
        return showWaitDialog(R.string.loading);
    }

    @Override
    public WaitDialog showWaitDialog(int resid) {

        return showWaitDialog(getString(resid));
    }

    @Override
    public WaitDialog showWaitDialog(String text) {
        if (mIsVisible) {
            if (mDialog == null) {
                mDialog = DialogHelper.getWaitDialog(this, text);
            }

            if (mDialog != null) {
                mDialog.setMessage(text);
                mDialog.show();
            }
            return mDialog;
        }

        return null;
    }

    protected int getScreenHeight() {
        return findViewById(android.R.id.content).getHeight();
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

    @Override
    public void useNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }
}
