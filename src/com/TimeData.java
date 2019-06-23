package com;

import java.sql.Timestamp;
import java.util.Date;

public class TimeData {
    private Long timestamp;

    public TimeData(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getAbsoluteTime() {
        return (new Timestamp(timestamp)).toString();
    }

    public String getRelativeTime() {
        String str;
        Date date = new Date();
        long currentTime = date.getTime();
        long difference = currentTime - timestamp;
        if (difference > 0 && difference < ((long) 60 * 60 * 1000)) {
            str = "About" + (difference / (60 * 1000)) + "minutes ago";
        } else if (difference < 24 * 60 * 60 * 1000) {
            str = "About " + (difference / ((long) 60 * 60 * 1000)) + "hour(s) ago";
        } else if (difference < 7 * 24 * 60 * 60 * 1000) {
            str = "About " + (difference / (24 * 60 * 60 * 1000)) + "day(s) ago";
        } else if (difference < ((long) 4 * 7 * 24 * 60 * 60 * 1000)) {
            str = "About " + (difference / (7 * 24 * 60 * 60 * 1000)) + "week(s) ago";
        } else {
            str = "About " + (difference / ((long) 4 * 7 * 24 * 60 * 60 * 1000)) + "month(s) ago";
        }
        return str;
    }
}
