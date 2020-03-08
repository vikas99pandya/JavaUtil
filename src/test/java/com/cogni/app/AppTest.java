package com.cogni.app;

import com.cogni.app.exception.AppException;
import com.cogni.app.util.AppConstants;
import com.cogni.app.util.AppUtility;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;


public class AppTest {

    private AppUtility appUtility;

    @Before
    public void setUp(){
        appUtility = new AppUtility();
    }

    @Test
    public void whenExceptionThrown_nullEndDate() {
        String startTime="16:15:00";
        String endTime=null;
        try {
            appUtility.count(startTime, endTime);
            fail();
        }
        catch(AppException appException){
            assertEquals(AppConstants.ERR_MANDATORY_INPUT, appException.getMessage());
        }
    }

    @Test
    public void whenExceptionThrown_nullStartDate(){
        String startTime=null;
        String endTime="17:00:00";
        try {
        appUtility.count(startTime, endTime);
            fail();
        }
        catch(AppException appException){
            assertEquals(AppConstants.ERR_MANDATORY_INPUT, appException.getMessage());
        }
    }

    @Test
    public void whenExceptionThrown_emptyStartDate(){
        String startTime=" ";
        String endTime="17:00:00";
        try {
            appUtility.count(startTime, endTime);
            fail();
        }
        catch(AppException appException){
            assertEquals(AppConstants.ERR_MANDATORY_INPUT, appException.getMessage());
        }
    }

    @Test
    public void whenExceptionThrown_emptyEndDate(){
        String startTime="16:15:00";
        String endTime=" ";
        try {
            appUtility.count(startTime, endTime);
            fail();
        }
        catch(AppException appException){
            assertEquals(AppConstants.ERR_MANDATORY_INPUT, appException.getMessage());
        }
    }

    @Test
    public void whenExceptionThrown_wrongStartDate1(){
        String startTime="16:15:00ABC";
        String endTime="17:00:00";
        try {
            appUtility.count(startTime, endTime);
            fail();
        }
        catch(AppException appException){
            assertEquals(AppConstants.ERR_INVALID_FORMAT, appException.getMessage());
        }
    }

    @Test
    public void whenExceptionThrown_wrongStartDate2(){
        String startTime="24:15:00";
        String endTime="17:00:00";
        try {
            appUtility.count(startTime, endTime);
            fail();
        }
        catch(AppException appException){
            assertEquals(AppConstants.ERR_INVALID_FORMAT, appException.getMessage());
        }
    }

    @Test
    public void whenExceptionThrown_wrongStartDate3(){
        String startTime="23:15:61";
        String endTime="17:00:00";
        try {
            appUtility.count(startTime, endTime);
            fail();
        }
        catch(AppException appException){
            assertEquals(AppConstants.ERR_INVALID_FORMAT, appException.getMessage());
        }
    }


    @Test
    public void whenExceptionThrown_wrongEndDate1(){
        String startTime="16:15:00";
        String endTime="007:00:00";
        try {
            appUtility.count(startTime, endTime);
            fail();
        }
        catch(AppException appException){
            assertEquals(AppConstants.ERR_INVALID_FORMAT, appException.getMessage());
        }
    }

    @Test
    public void whenExceptionThrown_wrongEndDate2(){
        String startTime="15:15:00";
        String endTime="24:00:00";
        try {
            appUtility.count(startTime, endTime);
            fail();
        }
        catch(AppException appException){
            assertEquals(AppConstants.ERR_INVALID_FORMAT, appException.getMessage());
        }
    }

    @Test
    public void whenExceptionThrown_wrongEndDate3(){
        String startTime="16:15:00";
        String endTime="17:61:00";
        try {
            appUtility.count(startTime, endTime);
            fail();
        }
        catch(AppException appException){
            assertEquals(AppConstants.ERR_INVALID_FORMAT, appException.getMessage());
        }
    }


    @Test
    public void whenExceptionThrown_startDateMoreThanEndDate(){
        String startTime="18:15:00";
        String endTime="17:00:00";
        try {
            appUtility.count(startTime, endTime);
            fail();
        }
        catch(AppException appException){
            assertEquals(AppConstants.ERR_END_DATE, appException.getMessage());
        }
    }

    @Test
    public void testAppLogic_count1() throws AppException{
        String startTime="16:15:00";
        String endTime="17:00:00";
        int count=appUtility.count(startTime, endTime);
        assertEquals(count, 2);
    }

    @Test
    public void testAppLogic_count2() throws AppException{
        String startTime="00:00:00";
        String endTime="23:59:59";
        int count=appUtility.count(startTime, endTime);
        assertEquals(count, 501);
    }

    @Test
    public void testAppLogic_count3() throws AppException{
        String startTime="01:00:00";
        String endTime="02:00:00";
        int count=appUtility.count(startTime, endTime);
        assertEquals(count, 17);
    }

    @Test
    public void testAppLogic_count4() throws AppException{
        String startTime="01:54:00";
        String endTime="01:54:00";
        int count=appUtility.count(startTime, endTime);
        assertEquals(count, 0);
    }

    @Test
    public void testAppLogic_count5() throws AppException{
        String startTime="02:00:00";
        String endTime="02:00:00";
        int count=appUtility.count(startTime, endTime);
        assertEquals(count, 1);
    }

    @Test
    public void testAppLogic_count6_WithSpace() throws AppException{
        String startTime=" 01:00:00 ";
        String endTime="  01:10:00  ";
        int count=appUtility.count(startTime, endTime);
        assertEquals(count, 9);
    }

}
