package oop.ex1.filters;

import java.io.*;

/**
 * A FileFilter that is a combination of two FileFilters.
 * This FileFilter accepts only files that are accepted by both the filters.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
class AndFilter implements FileFilter {

	private FileFilter first,second;
	
	/**
	 * Construct an AndFilter.
	 * an AndFilter is a FileFilter that is a combination of two FileFilters
	 *  and it accepts only files that are accepted by both the filters.
	 * @param one the first FileFilter
	 * @param two the second FileFilter
	 */
	public AndFilter(FileFilter one,FileFilter two) {
		first=one;
		second=two;
		if(second==null)
			second = new FileFilter() {
				
				@Override
				public boolean accept(File pathname) {
					return true;
				}
			};
	}
	
	/**
	 * Returns true if the file passed the double filter.
	 * @param pathname the file to test.
	 * @return true if the file passed the filter and false otherwise.
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File pathname) {
		return first.accept(pathname) && second.accept(pathname);
	}

}
