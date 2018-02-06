package com.yida.app.InstitutionForThrAged.ui.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yida.app.InstitutionForThrAged.R;
import com.yida.app.InstitutionForThrAged.base.ui.BaseFragment;
import com.yida.app.InstitutionForThrAged.presenter.contract.NewsFragmentContract;
import com.yida.app.InstitutionForThrAged.ui.news.activity.NewsManagerActivity;
import com.yida.app.InstitutionForThrAged.ui.news.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by black on 2017/2/27.
 */

public class NewsFragment extends BaseFragment implements NewsFragmentContract.View {

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    public static NewsFragment newInstance(String str) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("NewsArgs", str);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    @Override
    public void initData() {
        List<View> viewList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();

        titleList.add("Android");
        titleList.add("Ios");
        titleList.add("前端");
        titleList.add("拓展资源");
        titleList.add("休息视频");
        titleList.add("福利");
        titleList.add("All");

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View androidView = layoutInflater.inflate(R.layout.news_viewpager_android, null);
        View iosView = layoutInflater.inflate(R.layout.news_viewpager_ios, null);
        View noseView = layoutInflater.inflate(R.layout.news_viewpager_nose, null);
        View expandView = layoutInflater.inflate(R.layout.news_viewpager_expand, null);
        View videoView = layoutInflater.inflate(R.layout.news_viewpager_video, null);
        View welfareView = layoutInflater.inflate(R.layout.news_viewpager_welfare, null);
        View allView = layoutInflater.inflate(R.layout.news_viewpager_all, null);

        viewList.add(androidView);
        viewList.add(iosView);
        viewList.add(noseView);
        viewList.add(expandView);
        viewList.add(videoView);
        viewList.add(welfareView);
        viewList.add(allView);

        NewsAdapter newsAdapter = new NewsAdapter(viewList, titleList);
        viewPager.setAdapter(newsAdapter);

        /**
         * tabLayout与viewPager关联
         */
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        /**
         * 设置选中下划线颜色
         */
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(android.R.color.white));
    }

    @OnClick(R.id.fab)
    public void onClick() {
        startActivity(new Intent(getActivity(), NewsManagerActivity.class));
    }

    @Override
    public void initView(View view) {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        initData();
        initView(view);
        Bundle bundle = getArguments();
        String msgArgs = bundle.getString("NewsArgs");
        return view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void setPresenter(NewsFragmentContract.Presenter presenter) {

    }

 /*   @Override
    public void updataTab(List<NewsManagerItemBean> list) {

    }

    @Override
    public void jumpToManager(NewsManagerBean bean) {

    }*/
}
