package com.yida.app.InstitutionForThrAged.ui.news.activity;

import android.support.v7.widget.RecyclerView;

import com.yida.app.InstitutionForThrAged.R;
import com.yida.app.InstitutionForThrAged.base.ui.BaseActivity;

import butterknife.BindView;

/**
 * Created by think on 2017/3/5.
 */

public class NewsManagerActivity extends BaseActivity {

    @BindView(R.id.rv_news_manager_list)
    RecyclerView recyclerView;
    
    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }

    @Override
    protected int getActionBarTitle() {
        return R.string.news_manager_title;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_news_manager;
    }
}
