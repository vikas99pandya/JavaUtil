package com.cogni.app.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TestMain {
    public static void main(String[] args)  {

        String startTime="16:15:00";
        String endTime="17:00:00";

        String[] startTimeArr=startTime.split(":");
        String[] endTimeArr=endTime.split(":");

        LocalTime localTime = LocalTime.parse("16:15:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println(localTime.plusSeconds(1));
        localTime.plusMinutes(1);
        System.out.println(localTime);

    }
}
