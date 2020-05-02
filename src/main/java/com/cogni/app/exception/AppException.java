package com.cogni.app.exception;

import com.cogni.app.util.AppConstants;


/**
 * Exception class to handle error situation in calculation logic
 * .....
 */
public class AppException extends Exception {

    private static final long serialVersionUID = -4908881206111894083L;

    private final String errorCode;
    
    


    /**
     * Constructor
     *
     * @param errorData error code & description
     * @param cause a cause for the exception
     */
    public AppException(AppConstants.AppError errorData, Throwable cause) {
        this(errorData.getErrorDesc(), cause, errorData.name());
    }

    /**
     * Constructor
     *
     * @param message a message for the exception
     * @param cause a cause for the exception
     * @param errorCode a Error code for the exception
     */
    public AppException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * get error code
     *
     * @return  error code for exception
     */
    public String getErrorCode() {
        return errorCode;
    }
}