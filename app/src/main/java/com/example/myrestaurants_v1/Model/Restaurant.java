package com.example.myrestaurants_v1.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Restaurant implements Parcelable {
    private String name;
    private String address;
    private boolean onTable;
    private boolean delivery;
    private boolean takeAway;

    protected Restaurant(Parcel in) {
        name = in.readString();
        address = in.readString();
        onTable = in.readByte() != 0;
        delivery = in.readByte() != 0;
        takeAway = in.readByte() != 0;
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isOnTable() {
        return onTable;
    }

    public void setOnTable(boolean onTable) {
        this.onTable = onTable;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public boolean isTakeAway() {
        return takeAway;
    }

    public void setTakeAway(boolean takeAway) {
        this.takeAway = takeAway;
    }

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
        this.onTable = true;
        this.delivery = false;
        this.takeAway = false;
    }

    @NonNull
    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", onTable=" + onTable +
                ", delivery=" + delivery +
                ", takeAway=" + takeAway +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeByte((byte) (onTable ? 1 : 0));
        parcel.writeByte((byte) (delivery ? 1 : 0));
        parcel.writeByte((byte) (takeAway ? 1 : 0));
    }
}
