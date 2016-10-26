package com.xxoo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 描述：
 *
 * @author coder-pig： 2016/10/26 19:32
 */
public class LoveInfo implements Parcelable{
    private String ask;

    public LoveInfo() { }

    public LoveInfo(String ask) {
        this.ask = ask;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public static Creator<LoveInfo> getCREATOR() {
        return CREATOR;
    }

    protected LoveInfo(Parcel in) {
        ask = in.readString();
    }

    public static final Creator<LoveInfo> CREATOR = new Creator<LoveInfo>() {
        @Override
        public LoveInfo createFromParcel(Parcel in) {
            return new LoveInfo(in);
        }

        @Override
        public LoveInfo[] newArray(int size) {
            return new LoveInfo[size];
        }
    };

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ask);
    }
}
