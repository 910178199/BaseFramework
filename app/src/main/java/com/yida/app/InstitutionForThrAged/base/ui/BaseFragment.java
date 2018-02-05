package com.yida.app.InstitutionForThrAged.base.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yida.app.InstitutionForThrAged.AppContext;
import com.yida.app.InstitutionForThrAged.R;
import com.yida.app.InstitutionForThrAged.base.inter.IBaseFragment;
import com.yida.app.InstitutionForThrAged.view.dialog.IDialog;
import com.yida.app.InstitutionForThrAged.view.dialog.WaitDialog;

/**
 * Created by think on 2017/2/20.
 */

public class BaseFragment extends com.trello.rxlifecycle2.components.support.RxFragment implements IBaseFragment, View.OnClickListener {

    /**
     * 正常
     */
    public static final int STATE_NONE = 0;
    /**
     * 刷新中
     */
    public static final int STATE_REFRESH = 1;
    /**
     * 加载更多
     */
    public static final int STATE_LOADMORE = 2;
    /**
     * 没有更多
     */
    public static final int STATE_NOMORE = 3;
    /**
     * 下拉中,但还未触发刷新
     */
    public static final int STATE_PRESSNONE = 4;

    public static int mState = STATE_NONE;

    protected LayoutInflater mInflater;

    public AppContext getApplication() {
        return (AppContext) getActivity().getApplication();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mInflater = inflater;
        View view = super.onCreateView(inflater, container, savedInstanceState);

        /**
         * Android 6.0（API 23）及更高的版本上，
         * 系统也开始提供了对应的 API 来实现浅色调背景的状态栏效果，
         * 可将状态栏图标和文字内容改为黑色样式
         */
        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected int getLayoutId() {
        return 0;
    }

    protected View getInflaterView(int resId) {
        return mInflater.inflate(resId, null);
    }

    public boolean onBackPressed() {
        return false;
    }

    protected void hideDialog() {
        FragmentActivity activity = (FragmentActivity) getActivity();
        if (activity instanceof IDialog) {
            ((IDialog) activity).hideWaitDialog();
        }
    }

    protected WaitDialog showDialog() {
        return showDialog(R.string.loading);
    }

    protected WaitDialog showDialog(int resid) {
        FragmentActivity activity = (FragmentActivity) getActivity();
        if (activity instanceof IDialog) {
            ((IDialog) activity).showWaitDialog(resid);
        }
        return null;
    }

    protected WaitDialog showDialog(String str) {
        FragmentActivity activity = (FragmentActivity) getActivity();
        if (activity instanceof IDialog) {
            ((IDialog) activity).showWaitDialog(str);
        }
        return null;
    }


    @Override
    public void initView(View view) {


    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
