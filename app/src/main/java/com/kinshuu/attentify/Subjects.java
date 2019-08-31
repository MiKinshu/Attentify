package com.kinshuu.attentify;

import android.os.Parcel;
import android.os.Parcelable;


public class Subjects implements Parcelable {
    private int pres;
    private String day;
    private String date;
    private String name;
    private String roll;

    Subjects(int pres) {
        this.pres = pres;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public String getDate() {
        return date;
    }
    public void setPres(int pres) {
        this.pres = pres;
    }
    public int getPres() {
        return pres;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDay() {
        return day;
    }
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(roll);
        dest.writeString(name);
        dest.writeString(day);
        dest.writeString(date);
        dest.writeInt(pres);
    }

    public Subjects(Parcel source) {
        this.roll=source.readString();
        this.name=source.readString();
        this.pres=source.readInt();
        this.day=source.readString();
        this.date=source.readString();
    }

    public static final Creator<Subjects> CREATOR = new Creator<Subjects>() {

        @Override
        public Subjects createFromParcel(Parcel source) {
            return new Subjects(source);
        }

        @Override
        public Subjects[] newArray(int size) {
            return new Subjects[size];
        }
    };

}
