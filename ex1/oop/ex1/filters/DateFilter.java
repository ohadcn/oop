package oop.ex1.filters;

import java.io.*;
import java.text.*;
import java.util.*;

import oop.ex1.misc.Calender;

/**
 * A file filter to filter out only files before/after a specific date.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
class DateFilter implements FileFilter {

	private Date date;
	private boolean before;
	
	/**
	 * Creates a DateFilter object.
	 * @param date the date to work with.
	 * @param before filter files before/after this date.
	 * @throws ParseException in case that file is not seeked to FILTER section or invalid filter exist.
	 */
	public DateFilter(String date,boolean before) throws ParseException {
		this.before=before;
		this.date=Calender.strToDate(date);
	}

	/**
	 * Returns true is the file passed the filter.
	 * @return true if the file passed the filter and false otherwise.
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File pathname) {
		Date modification=new Date(pathname.lastModified()+(before?Calender.DAY:-1));
		return (before?date.before(modification):date.after(modification));
	}

}
