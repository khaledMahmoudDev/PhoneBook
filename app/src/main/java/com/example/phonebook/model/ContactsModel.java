package com.example.phonebook.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "contacts_table")
public class ContactsModel implements Serializable {

    public int getContact_id() {
        return contact_id;
    }

    @PrimaryKey(autoGenerate = true)
    private int contact_id;

    private String picture_path;

    private String first_name;

    private String last_name;

    private String phone_number;

    private String country_code;

    private String mail_address;

    private String address;

    private String longitude;

    private String Latitude;

    private String date;

    public ContactsModel() {
    }

    public ContactsModel(String picture_path, String first_name, String last_name, String phone_number, String country_code, String mail_address, String address, String longitude, String latitude, String date) {
        this.picture_path = picture_path;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.country_code = country_code;
        this.mail_address = mail_address;
        this.address = address;
        this.longitude = longitude;
        Latitude = latitude;
        this.date = date;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public String getPicture_path() {
        return picture_path;
    }

    public void setPicture_path(String picture_path) {
        this.picture_path = picture_path;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getMail_address() {
        return mail_address;
    }

    public void setMail_address(String mail_address) {
        this.mail_address = mail_address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
