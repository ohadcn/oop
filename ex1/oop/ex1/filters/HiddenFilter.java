package oop.ex1.filters;

import java.io.File;
import java.io.FileFilter;

/**
 * A FileFilter that accepts only files with the specified hidden attribute.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
class HiddenFilter implements FileFilter {

	private boolean hidden;

	/**
	 * Construct a HiddenFilter object.
	 * @param hidden tell the filter to return only hidden files or visible files.
	 */
	public HiddenFilter(boolean hidden) {
		this.hidden=hidden;
	}

	/**
	 * Returns true if the file passed the filter.
	 * @param pathname the file to test.
	 * @return true if the file passed that filter and false otherwise.
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File pathname) {
		return (hidden==pathname.isHidden());
	}

}
