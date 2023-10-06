package com.example.myrestaurants_v1.Model;

import androidx.annotation.NonNull;

public class Restaurant{
    private String name;
    private String address;
    private String phone;
    private String web;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
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

    public Restaurant(String name, String address, String phone, String web) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.web = web;
        this.onTable = true;
        this.delivery = false;
        this.takeAway = false;
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
                ", phone='" + phone + '\'' +
                ", web='" + web + '\'' +
                ", onTable=" + onTable +
                ", delivery=" + delivery +
                ", takeAway=" + takeAway +
                '}';
    }
}
