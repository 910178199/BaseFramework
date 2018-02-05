/*
package com.reading.app.reading.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

*/
/**
 * Created by think on 2017/3/5.
 *//*


public class NewsManagerItemBean extends RealmObject implements Parcelable {

    private int index;

    private boolean isSelect;

    public NewsManagerItemBean() {
    }

    public NewsManagerItemBean(int index, boolean isSelect) {
        this.index = index;
        this.isSelect = isSelect;
    }

    protected NewsManagerItemBean(Parcel in) {
        index = in.readInt();
        isSelect = in.readByte() != 0;
    }

    public static final Creator<NewsManagerItemBean> CREATOR = new Creator<NewsManagerItemBean>() {
        @Override
        public NewsManagerItemBean createFromParcel(Parcel in) {
            return new NewsManagerItemBean(in);
        }

        @Override
        public NewsManagerItemBean[] newArray(int size) {
            return new NewsManagerItemBean[size];
        }
    };

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(index);
        dest.writeByte((byte) (isSelect ? 1 : 0));
    }
}
*/
