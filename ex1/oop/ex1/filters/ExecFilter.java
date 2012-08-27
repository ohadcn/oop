package oop.ex1.filters;

import java.io.*;

/**
 * A FileFilter that accepts only files with specified executable attributes.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
class ExecFilter implements FileFilter {

	private boolean exec;

	/**
	 * Construct an ExecFilter object.
	 * @param exec set if this object returns only executable files or only unexecutable.
	 */
	public ExecFilter(boolean exec) {
		this.exec=!exec;
	}

	/**
	 * Returns true if the file passed the filter.
	 * @param path the file to test
	 * @return true if the file passed the filter and false otherwise
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File path) {
		return exec^path.canExecute();
	}

}
