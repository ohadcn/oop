package oop.ex1.filters;

import java.io.File;
import java.io.FileFilter;

/**
 * A FileFilter that accepts only files with the specified writable attribute.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
class WriteableFilter implements FileFilter {

	private boolean write;

	/**
	 * Construct an WriteableFilter object.
	 * @param writeable sets if this object returns
	 *  only writable files or only non writable ones.
	 */
	public WriteableFilter(boolean writeable) {
		this.write=!writeable;
	}

	/**
	 * Returns true if the file passed the filter.
	 * @param path the file to test.
	 * @return true if the file passed the filter and false otherwise.
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File path) {
		return write^path.canWrite();
	}

}
