package com.xxoo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 描述：爱好的bean类
 *
 * @author coder-pig： 2016/10/26 11:15
 */
public class Hobbies implements Parcelable{
    private String pet; //喜欢的宠物
    private String color;   //喜欢的颜色
    private String music;   //喜欢的音乐
    private String snacks;  //喜欢的小吃

    public Hobbies() { }

    public Hobbies(String pet, String color, String music, String snacks) {
        this.pet = pet;
        this.color = color;
        this.music = music;
        this.snacks = snacks;
    }

    protected Hobbies(Parcel in) {
        this.pet = in.readString();
        this.color = in.readString();
        this.music = in.readString();
        this.snacks = in.readString();
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getSnacks() {
        return snacks;
    }

    public void setSnacks(String snacks) {
        this.snacks = snacks;
    }

    public static Creator<Hobbies> getCREATOR() {
        return CREATOR;
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pet);
        dest.writeString(this.color);
        dest.writeString(this.music);
        dest.writeString(this.snacks);
    }

    public static final Creator<Hobbies> CREATOR = new Creator<Hobbies>() {
        @Override
        public Hobbies createFromParcel(Parcel source) {
            return new Hobbies(source);
        }

        @Override
        public Hobbies[] newArray(int size) {
            return new Hobbies[size];
        }
    };

    @Override public String toString() {
        return "妹子喜欢：" +
                "宠物：" + pet + '\'' +
                "，颜色：" + color + '\'' +
                ", 音乐：" + music + '\'' +
                ", 小吃：" + snacks + '\'' +
                '}';
    }
}
