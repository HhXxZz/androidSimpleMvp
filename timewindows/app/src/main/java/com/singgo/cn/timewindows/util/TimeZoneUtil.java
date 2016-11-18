package com.singgo.cn.timewindows.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@SuppressLint("SimpleDateFormat")
public class TimeZoneUtil {
	 private final static String DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * 判断用户的设备时区是否为东八区（中国） 2014年7月31日
	 * @return
	 */
	public static boolean isInEasternEightZones() {
		boolean defaultVaule = true;
		if (TimeZone.getDefault() == TimeZone.getTimeZone("GMT+08"))
			defaultVaule = true;
		else
			defaultVaule = false;
		return defaultVaule;
	}

	/**
	 * 根据不同时区，转换时间 2014年7月31日
	 * @param date
	 * @return
	 */
	public static Date transformTime(Date date, TimeZone oldZone, TimeZone newZone) {
		Date finalDate = null;
		if (date != null) {
			int timeOffset = oldZone.getOffset(date.getTime())
					- newZone.getOffset(date.getTime());
			finalDate = new Date(date.getTime() - timeOffset);
		}
		return finalDate;
	}
	
	public static String getNow(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(System.currentTimeMillis());
	
	
	}
	/**
	 * 获取当前日期时间年月日
	 * @return
	 */
	public static String getCalendar(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(System.currentTimeMillis());
	}
	
	/**
	 * 获取当前日期  日
	 * @return
	 */
	public static String getNowDay(){
		return new SimpleDateFormat("dd").format(new Date());
	
	
	}public static Date getDateBeforeOrAfter(Date curDate, int iDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(Calendar.DAY_OF_MONTH, iDate);
        return cal.getTime();
    
   
	}public static Date getCurrDate() {
        return new Date();
    
   
	}  public static Boolean compareDay(Date date1, int compday) {
        if (date1 == null)
            return false;
        Date dateComp = getDateBeforeOrAfter(date1, compday);
        Date nowdate = new Date();
        if (dateComp.after(nowdate))
            return true;
        else
            return false;
        
    } public static int daysBetween(Date smdate,Date bdate) throws ParseException {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
        return Integer.parseInt(String.valueOf(between_days));           
    }  
}
