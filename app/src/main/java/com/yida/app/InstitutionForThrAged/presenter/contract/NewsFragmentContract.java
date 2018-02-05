package com.yida.app.InstitutionForThrAged.presenter.contract;

import com.yida.app.InstitutionForThrAged.base.BasePresenter;
import com.yida.app.InstitutionForThrAged.base.BaseView;

/**
 * Created by think on 2017/3/5.
 */

public interface NewsFragmentContract {

    interface View extends BaseView<Presenter> {

//        void updataTab(List<NewsManagerItemBean> list);

//        void jumpToManager(NewsManagerBean bean);

    }

    interface Presenter extends BasePresenter {

        void initManagerList();

        void setManagerList();

    }

}
