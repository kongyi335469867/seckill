package com.seckill.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 日期格式转换 */
public class DateTimeUtil {
	
	// 获取系统时间并格式化
	public static String getSysDatetimeStr() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return df.format(date);
	}

	// 处理日期格式:Date->String
	public static String getDateFormat(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		return dateString;
	}

	// 处理日期格式:String->Date
	public static Date getStringFormat(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

}
