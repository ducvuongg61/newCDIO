package com.example.demo.config;

public class MyConstants {
    public static final String MY_EMAIL = "luytlong122@gmail.com";

    // Replace password!!
    public static final String MY_PASSWORD = "946287123aA";

    // And receiver!
    public static  String FRIEND_EMAIL;

    public static String getMyEmail() {
        return MY_EMAIL;
    }

    public static String getMyPassword() {
        return MY_PASSWORD;
    }

    public static String getFriendEmail() {
        return FRIEND_EMAIL;
    }

    public static void setFriendEmail(String friendEmail) {
        FRIEND_EMAIL = '"'+friendEmail+'"';
    }
}
