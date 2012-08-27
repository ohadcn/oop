package oop.ex1.filters;

import java.io.*;

/**
 * A FileFilter that is a combination of two FileFilters.
 * This FileFilter accept all the files that are accepted by any one of the filters.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
class OrFilter implements FileFilter{

	private FileFilter first,second;
	
	/**
	 * Construct an OrFilter: a combination of two FileFilters that accepts
	 *  the files that are accepted by any one of the filters.
	 * @param one the first FileFilter.
	 * @param two the second FileFilter.
	 * two may be null, while one mustn't
	 */
	public OrFilter(FileFilter one,FileFilter two) {
		first=one;
		second=two;
		if(second==null)
			second = new FileFilter() {
				
				@Override
				public boolean accept(File pathname) {
					return false;
				}
			};
	}
	
	/**
	 * Returns true if the file passed the filter.
	 * @param pathname the file to test.
	 * @return true if the file passed that filter and false otherwise.
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File pathname) {
		return first.accept(pathname)||second.accept(pathname);
	}

}
