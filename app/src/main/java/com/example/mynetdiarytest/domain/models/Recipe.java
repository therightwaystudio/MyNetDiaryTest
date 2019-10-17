package com.example.mynetdiarytest.domain.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Recipe implements Parcelable {

    private int id;
    private String name;
    private String url;

    public Recipe(int id, String name, String url){
        this.id = id;
        this.name = name;
        this.url = url;
    }

    protected Recipe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

}
