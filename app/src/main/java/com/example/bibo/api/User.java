package com.example.bibo.api;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("response")
    private String Response;

    @SerializedName("username")
    private String username;

    public String getResponse() {
        return Response;
    }

    public String getUsername() {
        return username;
    }

    ///////Checking
    @SerializedName("bID")
    private String bID;

    @SerializedName("title")
    private String title;

    @SerializedName("genre")
    private String genre;

    @SerializedName("pYear")
    private String pYear;

    @SerializedName("qty")
    private String qty;

    @SerializedName("price")
    private String price;

    public String getbID() {
        return bID;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getpYear() {
        return pYear;
    }

    public String getQty() {
        return qty;
    }

    public String getPrice() {
        return price;
    }
}
