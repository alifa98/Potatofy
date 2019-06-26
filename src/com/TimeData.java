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
        long num;
        if (difference > 0 && difference < ((long) 60 * 1000)) {
            num = difference / (1000);
            str = "About " + num + " second"+ ((num>1)?"s":"" )+" ago";
        }else if(difference < ((long) 60 * 60 * 1000)){
            num = difference / (60 * 1000);
            str = "About " + num + " minute"+ ((num>1)?"s":"" )+" ago";
        } else if (difference < 24 * 60 * 60 * 1000)  {
            num = difference / ((long) 60 * 60 * 1000);
            str = "About " + num + " hour"+ ((num>1)?"s":"" )+" ago";
        } else if (difference < 7 * 24 * 60 * 60 * 1000) {
            num = difference / (24 * 60 * 60 * 1000);
            str = "About " + num+ " day"+ ((num>1)?"s":"" )+" ago";
        } else if (difference < ((long) 4 * 7 * 24 * 60 * 60 * 1000)) {
            num = difference / (7 * 24 * 60 * 60 * 1000);
            str = "About " + num + " week"+ ((num>1)?"s":"" )+" ago";
        } else {
            num = difference / ( 4 * 7 * 24 * 60 * 60 * 1000L);
            str = "About " + num + " month"+ ((num>1)?"s":"" )+" ago";
        }
        return str;
    }

    public static String reformatMilisecForSong(long time){
        int totalSecs=(int)(time/1000);
        return String.format("%2d:%02d",totalSecs/60,totalSecs%60);

    }
}
