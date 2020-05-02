package com.cogni.app.util;

/**
 * Constant class to store static data to be used in application..
 */
public class AppConstants {

    public static final String ERR_INVALID_FORMAT="Input data format should be HH:MM:SS " +
            "where HH is for hours 00 to 23, " +
            "MM is for minutes 00 to 59 and SS is for seconds 00 to 59";
    public static final String ERR_MANDATORY_INPUT="Both inputs are mandatory";
    public static final String ERR_END_DATE="End date should not be before start date";
    public static final String VALIDATION_REGGEX="(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)";
    public static final String TIME_FORMAT="HH:mm:ss";
    public static final String START_TIME="00:00:00";
    public static final int ZERO=0;
    public static final int ONE=1;
    public static final int THREE=3;

    /**
     * Enum to store all input validation error messages
     */
    public enum AppError {
        MANDATORY_INPUT(ERR_MANDATORY_INPUT),
        INVALID_FORMAT(ERR_INVALID_FORMAT),
        END_DATE(ERR_END_DATE);

        private String errorDesc;

        AppError(final String errorDesc){
           this.errorDesc=errorDesc;
        }

        public String getErrorDesc() {
            return errorDesc;
        }
    }
}
