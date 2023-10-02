package com.example.myrestaurants_v1.Model;

import androidx.annotation.NonNull;

public class Restaurant{
    private String name;
    private String address;
    private boolean onTable;
    private boolean delivery;
    private boolean takeAway;

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
}
