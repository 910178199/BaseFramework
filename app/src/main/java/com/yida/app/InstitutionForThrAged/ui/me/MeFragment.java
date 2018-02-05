package com.yida.app.InstitutionForThrAged.ui.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yida.app.InstitutionForThrAged.R;
import com.yida.app.InstitutionForThrAged.base.ui.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by black on 2017/2/27.
 */

public class MeFragment extends BaseFragment {

    @BindView(R.id.name)
    TextView name;

    public static MeFragment newInstace(String str) {
        MeFragment meFragment = new MeFragment();
        Bundle args1 = new Bundle();
        args1.putString("MeArgs", str);
        meFragment.setArguments(args1);
        return meFragment;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(View view) {


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        initData();
        initView(view);
        ButterKnife.bind(this,view);
        Bundle count = getArguments();
        String meArgs = count.getString("MeArgs");
        name.setText(meArgs);
        return view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }
}
