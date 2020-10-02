package com.rbc.databanqbackend.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	public static String DEFAULT_ZONE_ID = "UTC";
	
	public static String convertDateToStringUntilSeconds(Date date) {
		if (date == null) {
			return null;
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//format.setTimeZone(TimeZone.getTimeZone(DEFAULT_ZONE_ID));
		return format.format(date);
	}
	public static String convertDateToDayString(Date date) {
		if (date == null) {
			return null;
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//format.setTimeZone(TimeZone.getTimeZone(DEFAULT_ZONE_ID));
		return format.format(date);
	}
	public static String convertDateToDayStringUTC(Date date) {
		if (date == null) {
			return null;
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(TimeZone.getTimeZone(DEFAULT_ZONE_ID));
		return format.format(date);
	}
	public static String convertDateToMonStringUTC(Date date) {
		if (date == null) {
			return null;
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM");
		format.setTimeZone(TimeZone.getTimeZone(DEFAULT_ZONE_ID));
		return format.format(date);
	}
	public static Date convertMonStringToDate(String monString) {
		if (monString == null) {
			return null;
		}
		
		DateFormat format = new SimpleDateFormat("yyyy-MM");
		try {
			return format.parse(monString);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String convertLongToStringUntilSecondsUTC(Long timestamp) {
		if (timestamp == null) {
			return null;
		}
		Date date = new Date(timestamp);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone(DEFAULT_ZONE_ID));
		return format.format(date);
	}
	public static Date convertDayStringToDate(String dateString) {
		if (dateString == null) {
			return null;
		}
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.parse(dateString);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Date convertDayStringUntilSecondsToDate(String dateString) {
		if (dateString == null) {
			return null;
		}
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return format.parse(dateString);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Date convertDayStringUntilSecondsToDateWithTimeZone(String dateString) {
		if (dateString == null) {
			return null;
		}
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone(DEFAULT_ZONE_ID));
		try {
			return format.parse(dateString);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String convertDateToStringUntilSecondsWithTimeZone(Date date) {
		if (date == null) {
			return null;
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone(DEFAULT_ZONE_ID));
		return format.format(date);
	}
	/*
	public static String convertCalToBinanceFormat(Calendar cal) {
		return String.valueOf(cal.get(Calendar.YEAR))+NumberUtil.convertIntegerToString(cal.get(Calendar.MONTH),2)+
				NumberUtil.convertIntegerToString(cal.get(Calendar.DAY_OF_MONTH),2)+NumberUtil.convertIntegerToString(cal.get(Calendar.HOUR_OF_DAY),2)+
				NumberUtil.convertIntegerToString(cal.get(Calendar.MINUTE),2);
	}*/
	
	public static Date getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone(DEFAULT_ZONE_ID));
		cal.setTime(date);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		return cal.getTime();
	}
	public static Date getDateFromDate(Date date, int field, int diff) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, diff);
		return cal.getTime();
	}
	
	public static Integer getDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone(DEFAULT_ZONE_ID));
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	public static Date getFirstDateOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone(DEFAULT_ZONE_ID));
		cal.setTime(date);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
}
