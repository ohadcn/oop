package oop.ex1.filters;

import java.io.File;
import java.io.FileFilter;

/**
 * A FileFilter that accepts only files not accepted by the other FileFilter.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
class NotFilter implements FileFilter{

	FileFilter filter;
	
	/**
	 * Construct a NotFilter object.
	 * @param filter the FileFilter to reverse.
	 */
	public NotFilter(FileFilter filter) {
		this.filter=filter;
	}

	/**
	 * Returns true if the file <i>pathname</i> passed the filter.
	 * @param pathname the file to test.
	 * @return true if the file <i>pathname</i> passed that filter and false otherwise.
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File pathname) {
		return !filter.accept(pathname);
	}

}
