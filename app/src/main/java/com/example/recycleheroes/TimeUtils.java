package com.example.recycleheroes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

abstract class TimeUtils {

    public static String getTimeAgo(String timestamp) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
            Date date = sdf.parse(timestamp);
            long timeInMillis = date.getTime();

            long currentTimeMillis = System.currentTimeMillis();
            long timeDifferenceMillis = currentTimeMillis - timeInMillis;

            long seconds = timeDifferenceMillis / 1000 % 60;
            long minutes = timeDifferenceMillis / (60 * 1000) % 60;
            long hours = timeDifferenceMillis / (60 * 60 * 1000) % 24;
            long days = timeDifferenceMillis / (24 * 60 * 60 * 1000);

            if (days > 0) {
                return days + "d";
            } else if (hours > 0) {
                return hours + "h";
            } else if (minutes > 0) {
                return minutes + "m";
            } else {
                return seconds + "s";
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return ""; // Handle parsing exception as needed
        }
    }

    public static void main(String[] args) {
    }
}
