package com.example.myrestaurants_v1.Model;

public class Category {
    private long id_;
    private String specialty;

    public long getId_() {
        return id_;
    }

    public void setId_(long id_) {
        this.id_ = id_;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Category(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id_=" + id_ +
                ", specialty='" + specialty + '\'' +
                '}';
    }
}
