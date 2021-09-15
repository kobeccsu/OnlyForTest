package com.leizhou.dto;

final public class MyFinal {
    public final String getMessage(){
        return "My final class";
    }

    public static String getDatabaseConnection(String url) {
        return "http:///production/" + url;
    }
}
