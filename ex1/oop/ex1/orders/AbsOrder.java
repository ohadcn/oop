package oop.ex1.orders;

import java.io.File;
import java.util.Comparator;

/**
 * This class implements Comparator<File> to sort files by their names.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
class AbsOrder implements Comparator<File> {
	
	/**
	 * This method compares two files by lexicographic names.
	 * @param o1 the first file.
	 * @param o2 the second file.
	 * @return 1 if the first file is first, -1 if the second file is so.
	 */
	public int compare(File o1, File o2) {
		return compareNames(o1,o2);
	}
	
	/**
	 * This method compares two files by lexicographic names.
	 * @param o1 the first file.
	 * @param o2 the second file.
	 * @return 1 if the first file is first, -1 if the second file is so.
	 */
	public static int compareNames(File o1, File o2) {
		return o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
	}
}
