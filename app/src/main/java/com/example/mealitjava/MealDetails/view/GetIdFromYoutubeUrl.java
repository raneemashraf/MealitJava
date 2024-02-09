package com.example.mealitjava.MealDetails.view;

public class GetIdFromYoutubeUrl {
    public static String getId(String link) {
        if (link != null && link.split("\\?v=").length > 1)
            return link.split("\\?v=")[1];
        else return "";
    }
}
