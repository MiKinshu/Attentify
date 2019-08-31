package com.kinshuu.attentify;

import android.os.Parcel;
import android.os.Parcelable;

public class Model implements Parcelable {

    private boolean isSelected;
    private String name;
    private String roll;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }
    public void setRoll(String roll) {
        this.roll = roll;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
    public Model() {

    }
    public Model(Parcel source) {
        this.roll=source.readString();
        this.name=source.readString();
        this.isSelected=source.readByte() != 0;
    }
    public static final Creator<Model> CREATOR = new Creator<Model>() {

        @Override
        public Model createFromParcel(Parcel source) {
            return new Model(source);
        }

        @Override
        public Model[] newArray(int size)
        {
            return new Model[size];
        }
    };
}