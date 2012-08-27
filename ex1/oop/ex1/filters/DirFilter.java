package oop.ex1.filters;

import java.io.File;
import java.io.FileFilter;

/**
 * A FileFilter that accepts only directories.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
public class DirFilter implements FileFilter {
	
	@Override
	/**
	 * Returns true if the file passed the filter.
	 * @param pathname the file to test.
	 * @return true if the file passed that filter and false otherwise.
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	public boolean accept(File file) {
		return file.isDirectory();
	}

}
