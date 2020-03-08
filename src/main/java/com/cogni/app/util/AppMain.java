package com.cogni.app.util;

import com.cogni.app.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Main class for initiating the calculation logic
 */
public class AppMain {

    private static final Logger LOG = LoggerFactory.getLogger(AppMain.class);

    public static void main(String[] args)  {

        AppUtility appUtility = new AppUtility();
        Scanner myObj = new Scanner(System.in);
        LOG.info("Enter start time (with format HH:MM:SS)");

        String startTime = myObj.nextLine();  // Read user input

        LOG.info("Enter end time (with format HH:MM:SS)");

        String endTime = myObj.nextLine();  // Read user input
        try {
            int count = appUtility.count(startTime, endTime);
            LOG.info("Total count: " + count);
        } catch (AppException appException) {
            LOG.error("Error Code->"+appException.getErrorCode() + ", Error message->"+ appException.getMessage(), appException);
        }

    }
}
