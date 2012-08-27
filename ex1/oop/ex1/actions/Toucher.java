package oop.ex1.actions;

import java.io.*;

import oop.ex1.misc.LS;

/**
 * This class represents an action - setting the last modified property of the file.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
class Toucher implements Action {

	private long date; //the date to set
	
	private FileFilter filter; //choose files to process
	

	/**
	 * Constructor for Toucher class.
	 * @param date the date to set on the files, in milliseconds from 1/1/1970
	 * @param filter filter files to work on.
	 */
	public Toucher(long date, FileFilter filter) {
		this.date=date;
		this.filter=filter;
	}

	/**
	 * Touch the files (set their last modified attribute).
	 * @param CWD the directory to start setting last modified attribute on it's files.
	 * @throws IOException if there is an error accessing filesystem or files properties.
	 */
	@Override public void commit(File CWD) {
		File[] list=LS.LSRecursive(filter, CWD);
		
		for(File file:list) 
			file.setLastModified(date);
	}

}
