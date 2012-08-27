package oop.ex1.filters;

import java.io.*;

/**
 * A FileFilter that accepts only files with specific conditions on their names.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
class NameFilter implements FileFilter {

	private String regexp;

	private boolean isCase;
	/**
	 * Construct a NameFilter object, case sensitive or not case sensitive.
	 * @param exp a regular expression to test on file names.
	 * @param caseSence true for case sensitive.
	 */
	public NameFilter(String exp,boolean caseSence) {
		isCase=caseSence;
		if(isCase)
			regexp=exp.toLowerCase();
		else
			regexp=exp;
	}
	
	/**
	 * Construct a NameFilter object, case sensitive.
	 * @param regExp a regular expression to test on file names.
	 */
	public NameFilter(String regExp) {
		this(regExp,true);
	}

	/**
	 * Returns true if the file passed the filter.
	 * @param pathname the file to test.
	 * @return true if the file passed the filter and false otherwise.
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File pathname) {
		String name=(isCase?pathname.getName().toLowerCase():pathname.getName());
		return name.matches(regexp);//.toLowerCase().matches(regexp);
	}

}
