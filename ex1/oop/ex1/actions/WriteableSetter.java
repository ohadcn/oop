package oop.ex1.actions;

import java.io.*;

import oop.ex1.misc.LS;

/**
 * This class represents a an action for setting the read-only property of the file.
 * file:WriteableSetter.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
class WriteableSetter implements Action {

	private boolean canWrite;
	
	private FileFilter filter;

	/**
	 * Construct WriteableSetter instance.
	 * @param isWriteable decides whether you want to set writeable to true or false.
	 * @param filter to filter out files.
	 */
	public WriteableSetter(boolean isWriteable, FileFilter filter) {
		canWrite = isWriteable;
		this.filter=filter;
	}
	
	/**
	 * Set the files' writeable attributes.
	 * @param CWD the directory to start setting write-ability attribute on
	 * @throws IOException if there is an error accessing filesystem or files properties
	 */
	@Override public void commit(File CWD) {
		File[] list=LS.LSRecursive(filter, CWD);
		
		for(File file:list) 
			file.setWritable(canWrite);
	}

}
