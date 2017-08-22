package com.example.naveed.firebaseintro;

/**
 * Created by Naveed on 22/08/2017.
 */

public class User {
    private  String fullname;
    private  String uni;



    public User() {

    }

    public User(String fullname , String uni) {
        this.fullname = fullname;
        this.uni = uni;

    }
    public String getUni() {
        return uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
