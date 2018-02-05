package com.yida.app.InstitutionForThrAged.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.yida.app.InstitutionForThrAged.R;
import com.yida.app.InstitutionForThrAged.base.ui.BaseActivity;
import com.yida.app.InstitutionForThrAged.ui.me.MeFragment;
import com.yida.app.InstitutionForThrAged.ui.message.MsgFragment;
import com.yida.app.InstitutionForThrAged.ui.news.NewsFragment;
import com.yida.app.InstitutionForThrAged.ui.strategy.StrategyFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements AHBottomNavigation.OnTabSelectedListener {

    private static String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.bottom_navigation)
    AHBottomNavigation ahBottomNavigation;

    private ArrayList<Fragment> fragment;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        super.init(saveInstanceState);

        ButterKnife.bind(this);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.mipmap.news, R.color.colorPrimaryDark);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.mipmap.strategy, R.color.colorAccent);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.mipmap.msg, R.color.colorBottomNavigationSelectedBackground);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab_4, R.mipmap.me, R.color.colorAccent);

        // Add items
        ahBottomNavigation.addItem(item1);
        ahBottomNavigation.addItem(item2);
        ahBottomNavigation.addItem(item3);
        ahBottomNavigation.addItem(item4);

        // Set background color
        /**
         * 设置选中颜色
         */
        ahBottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));

        // Disable the translation inside the CoordinatorLayout
        ahBottomNavigation.setBehaviorTranslationEnabled(true);

        // Enable the translation of the FloatingActionButton
        //ahBottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);

        // Change colors
        ahBottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        ahBottomNavigation.setInactiveColor(Color.parseColor("#747474"));

        // Force to tint the drawable (useful for font with icon for example)
        ahBottomNavigation.setForceTint(true);

// <item name="android:windowTranslucentNavigation">true</item>
// <item name="android:fitsSystemWindows">true</item>
        ahBottomNavigation.setTranslucentNavigationEnabled(true);

        // Manage titles
        /**
         * 设置title的显示状态
         */
//        ahBottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        ahBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
//        ahBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);

        /**
         * 开启对应选项的背景颜色
         */
//        ahBottomNavigation.setColored(true);

// Set current item programmatically
        /**
         * 默认选中
         */
        ahBottomNavigation.setCurrentItem(0);

        // Customize notification (title, background, typeface)
        /**
         * 定义通知小圆点颜色
         */
        ahBottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));

        // Add or remove notification for each item
        /**
         * 添加通知
         *参数一：消息个数
         * 参数二：对应的选项
         */

//        ahBottomNavigation.setNotification("1", 3);

        //OR

//        AHNotification notification = new AHNotification.Builder()
//                .setText("1")
//                .setBackgroundColor(ContextCompat.getColor(this, R.color.black))
//                .setTextColor(ContextCompat.getColor(this, R.color.white))
//                .build();
//        ahBottomNavigation.setNotification(notification, 1);

        //获取Fragment
        fragment = getFragment();

        setDefaultFragment();

        ahBottomNavigation.setOnTabSelectedListener(this);
    }

    private void setDefaultFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        NewsFragment newsFragment = new NewsFragment();
        transaction.add(R.id.layFrame, newsFragment.newInstance("News"));
        transaction.commit();
    }

    private ArrayList<Fragment> getFragment() {
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(NewsFragment.newInstance("News"));
        list.add(StrategyFragment.newInstance("Strategy"));
        list.add(MsgFragment.newInstance("Msg"));
        list.add(MeFragment.newInstace("Me"));
        return list;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void useNightMode(boolean isNight) {
        
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        if (fragment != null) {
            if (position < fragment.size()) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                Fragment frag = this.fragment.get(position);
                ft.replace(R.id.layFrame, frag);
                ft.commitAllowingStateLoss();
            }
        }
        return true;
    }
}
