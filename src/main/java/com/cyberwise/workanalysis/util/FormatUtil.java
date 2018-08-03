package com.cyberwise.workanalysis.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static DecimalFormat df = new DecimalFormat("#0.00");

	public static Date parseDate(String date) {
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String formateDate(Date date) {
		return sdf.format(date);
	}

	public static String parseDouble(double d) {
		d *= 100;
		return df.format(d);
	}
	
	public static String formatDouble(double d) {
		return df.format(d);
	}
}
