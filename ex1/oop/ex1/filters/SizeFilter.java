package oop.ex1.filters;

import java.io.*;

/**
 * A FileFilter that accepts only files that fulfill conditions on their size.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
class SizeFilter implements FileFilter {

	private static final int KB_SIZE=128;
	
	private final double size;
	private final boolean greater;
	
	/**
	 * Construct a SizeFilter object.
	 * @param size the size at which to start the filter from.
	 * @param greater filter larger files or smaller files.
	 */
	public SizeFilter(String size, boolean greater) {
		this.size=(Double.parseDouble(size)*KB_SIZE)+(greater?-1:0);
		this.greater=greater;
	}
	
	/**
	 * Returns true if the file passed the filter.
	 * @param pathname the file to test.
	 * @return true if the file passed that filter and false otherwise.
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File pathname) {
		return (size<pathname.length())^greater;
	}
}
