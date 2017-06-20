package com.example.dell.mytestapprajkumar;

import java.util.ArrayList;

/**
 * Created by dell on 20/06/2017.
 */
public class RandomUser {
    //Data Variables
    private String title;
    private String first;
    private String last;
    private String email;
    private String street;
    private String thumbnail;
    private String city;
    private String state;
    private String postcode;

    //Getters and Setters
    public RandomUser(String city, String email, String first, String last, String postcode, String state, String street, String thumbnail, String title) {
        this.city = city;
        this.email = email;
        this.first = first;
        this.last = last;
        this.postcode = postcode;
        this.state = state;
        this.street = street;
        this.thumbnail = thumbnail;
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
