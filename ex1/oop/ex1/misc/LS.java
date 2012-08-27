package oop.ex1.misc;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

import oop.ex1.filters.DirFilter;

/**
 * This class was made for dealing with the directories properly??????
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
public class LS {
	
	
	/**
	 * returns an array of all the files in the directory CWD and it's sub-directories that math filter filter
	 * @param filter a FileFilter 
	 * @param CWD the directory to start at
	 * @return an array of files in the directory and sub-directories that match filter filter
	 */
	public static File[] LSRecursive(FileFilter filter,File CWD){
		File[] list=CWD.listFiles(filter);
		for(File dir:CWD.listFiles(new DirFilter()))
			list=merge(list, LSRecursive(filter, dir.getAbsoluteFile()));
		return list;

	}
	
	/**
	 * merge to arrays
	 * @param frst first array to merge
	 * @param scnd second array to merge
	 * @return a new array, contains both the elements in the arrays
	 */
	private static File[] merge(File[] frst,File[] scnd){
		File[] result = Arrays.copyOf(frst, frst.length + scnd.length);
		System.arraycopy(scnd, 0, result, frst.length, scnd.length);
		return result;
	}
}
