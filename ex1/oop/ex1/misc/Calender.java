package oop.ex1.misc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class has a method and constant for parsing dates and times.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
public final class Calender {
	
	/**
	 * The number of milliseconds in a day.
	 */
	public static final long DAY=86400000;
	
	/**
	 * Parse a date in dd/MM/yyyy format (e,g: 01/04/2012) to a java.util.Date.
	 * @param date a string representation of a date (e,g: 01/04/2012).
	 * @return a java.util.Date of that date.
	 * @throws ParseException in case of invalid date format.
	 */
	public static Date strToDate(String date) throws ParseException {
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
		return format.parse(date);
	}
	
}
