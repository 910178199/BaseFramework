/*
package com.reading.app.reading.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmList;
import io.realm.RealmObject;

*/
/**
 * Created by codeest on 16/11/27.
 *//*


public class NewsManagerBean extends RealmObject implements Parcelable {

    public NewsManagerBean() {

    }

    private RealmList<NewsManagerBean> managerList;

    public RealmList<NewsManagerBean> getManagerList() {
        return managerList;
    }

    public void setManagerList(RealmList<NewsManagerBean> managerList) {
        this.managerList = managerList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.managerList);
    }

    protected NewsManagerBean(Parcel in) {
        this.managerList = new RealmList<>();
        in.readList(this.managerList, NewsManagerBean.class.getClassLoader());
    }

    public NewsManagerBean(RealmList<NewsManagerBean> mList) {
        this.managerList = mList;
    }

    public static final Parcelable.Creator<NewsManagerBean> CREATOR = new Parcelable.Creator<NewsManagerBean>() {
        @Override
        public NewsManagerBean createFromParcel(Parcel source) {
            return new NewsManagerBean(source);
        }

        @Override
        public NewsManagerBean[] newArray(int size) {
            return new NewsManagerBean[size];
        }
    };
}
*/
