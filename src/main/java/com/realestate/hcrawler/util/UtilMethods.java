package com.realestate.hcrawler.util;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilMethods {

	/*
	 * Returns yyyy-MM-dd format remove the rest
	 */
	public static Date formatDate(Date d) {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String dateS = dt.format(d);
		Date date;
		try {
			date = dt.parse(dateS);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Format a date String
	 * 
	 */
	public static String formatDateString(Date d, String format) {
		SimpleDateFormat dt = new SimpleDateFormat(format);
		String dateS = dt.format(d);
		return dateS;
	}

	public static boolean isXpath(String expression) {
		XPathFactory xPathFactory = XPathFactory.newInstance();
		XPath xpath = xPathFactory.newXPath();
		try {
			xpath.compile(expression);
			return true;
		} catch (XPathExpressionException e) {
			return false;
		}
	}

}
