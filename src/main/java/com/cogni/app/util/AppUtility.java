package com.cogni.app.util;

import com.cogni.app.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Utility class to calculate the amount of times where only 2 digits
 * appear in every possible combination in a specific time range.
 */
public class AppUtility {

    private static final Logger LOG = LoggerFactory.getLogger(AppUtility.class);


    /**
     * To validate input data provided by user
     *
     * @param startTimeStr input start date provided by user
     * @param endTimeStr input end date provided by user
     * @throws AppException   if error occurs while calculation logic
     * @return int amount of times where only 2 digits appear in every possible combination in a specific time range
     */
    public  int  count(String startTimeStr, String endTimeStr) throws AppException {

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(AppConstants.TIME_FORMAT);
        int totalCount=AppConstants.ZERO;

        if(validateInputData(startTimeStr,endTimeStr)) {

            LocalTime startTime = LocalTime.parse(startTimeStr.trim(), DateTimeFormatter.ofPattern(AppConstants.TIME_FORMAT));
            LocalTime endTime = LocalTime.parse(endTimeStr.trim(), DateTimeFormatter.ofPattern(AppConstants.TIME_FORMAT));

            if(!(endTime.compareTo(startTime) >= AppConstants.ZERO)){
                    throw new AppException(AppConstants.AppError.END_DATE, null);
            }

            while (endTime.compareTo(startTime) >= AppConstants.ZERO) {
                HashSet<Character> appSet = new HashSet();
                String newLocalTime = startTime.format(timeFormatter);

                List<Character> localChars = newLocalTime.chars()
                        .mapToObj(e -> (char) e).collect(Collectors.toList());
                localChars.stream().forEach(ch -> {
                    appSet.add(ch);
                });
                if (appSet.size() == AppConstants.THREE) {
                    totalCount++;
                }

                startTime = startTime.plusSeconds(AppConstants.ONE);

                if(startTime.format(timeFormatter).equalsIgnoreCase(AppConstants.START_TIME)) {
                   break;
                }

            }
        }

        return totalCount;
    }

    /**
     * To validate input data provided by user
     *
     * @param startDateStr input start date provided by user
     * @param endDateStr input end date provided by user
     * @throws AppException   if error occurs while validating date
     * @return boolean returns true if validation is success else false
     */
    private boolean validateInputData(String startDateStr,String endDateStr) throws AppException{
        if((startDateStr==null || startDateStr.trim().isEmpty()) || ( endDateStr==null || endDateStr.trim().isEmpty())){
            throw new AppException(AppConstants.AppError.MANDATORY_INPUT, null);
        }

        if (!(validateInputFormat(startDateStr.trim()) && validateInputFormat(endDateStr.trim()))) {
            throw new AppException(AppConstants.AppError.INVALID_FORMAT, null);
        }
        return true;
    }

    /**
     * To check input date format : HH:MM:SS
     *  HH : should be between 00 & 23 inclusive
     *  MM : should be between 00 & 59 inclusive
     *  SS : should be between 00 & 59 inclusive
     * @param inputDate input date provided by user
     * @return boolean true if validation is success else false
     */
    private boolean validateInputFormat(String inputDate){
        if (!inputDate.matches(AppConstants.VALIDATION_REGGEX)) {
           return false;
        }
        return true;
    }
}
