package com.example.calarie.api;

public class User {


    private String username, nama;

    public User( String username, String nama) {

        this.username = username;
        this.nama= nama;

    }


    public String getUsername() {
        return username;
    }

    public String getNama() {
        return nama;
    }

}
