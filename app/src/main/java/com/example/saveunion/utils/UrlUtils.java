package com.example.saveunion.utils;

public class UrlUtils {

    public static String createHomePageUrl(int materialId, int page) {
        return "discovery/" + materialId + "/" + page;
    }

}
