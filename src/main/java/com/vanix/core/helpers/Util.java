package com.vanix.core.helpers;

import net.md_5.bungee.api.ChatColor;

public class Util {

    public static String bar(int current) {
        return bar(ChatColor.GREEN, ChatColor.RED, 20, current);
    }

    public static String bar(ChatColor c1, ChatColor c2, int bars, double percent) {
        return bar(c1, c2, '|', bars, percent);
    }

    public static String bar(ChatColor c1, ChatColor c2, char symbol, int bars, double percent) {
        StringBuilder builder = new StringBuilder();
        int paint = (int)Math.round((percent/100)*bars);
        for(int i = 0; i < paint; i++) {
            builder.append(c1).append(symbol);
        }
        for(int i = 0; i < bars - paint; i++) {
            builder.append(c2).append(symbol);
        }
        return builder.toString();
    }

    public static String time(int totalSeconds) {
        int minutes = totalSeconds/60,
                seconds = totalSeconds%60;
        return (minutes < 10 ? "0" : "") + minutes + ":" + (seconds < 10 ? "0" : "") + seconds;
    }

    public static String formatMillis(long millis) {
        return (System.currentTimeMillis() - millis) + "ms";
    }

}
