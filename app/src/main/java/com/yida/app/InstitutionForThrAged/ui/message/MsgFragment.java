package com.yida.app.InstitutionForThrAged.ui.message;

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

public class MsgFragment extends BaseFragment {

    @BindView(R.id.name)
    TextView name;


    public static MsgFragment newInstance(String str) {
        MsgFragment msgFragment = new MsgFragment();
        Bundle bundle = new Bundle();
        bundle.putString("MsgArgs",str);
        msgFragment.setArguments(bundle);
        return msgFragment;
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
        View view = inflater.inflate(getLayoutId(),container,false);
        initData();
        initView(view);
        ButterKnife.bind(this,view);
        Bundle bundle = getArguments();
        String msgArgs = bundle.getString("MsgArgs");
        name.setText(msgArgs);
        return view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }
}
