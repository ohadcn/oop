package oop.ex1.orders;

import java.io.*;
import java.util.*;
import oop.ex1.misc.*;

/**
 * This class has a static method for creating file comparators out of strings.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
public final class OrderFactory {
		
	/**
	 * Parse strings for files comparators, it assumes no more than one comparator exists
	 * in case that there is no ordering request at all it returns a by-name comparator.
	 * @param file a Queue<String> that holds a commands file, seeked to an ORDER section.
	 * @return a Comparator<File> represent the requested order.
	 * @throws OrderFormatException in case the order is not recognized.
	 */
	public static Comparator<File> factory(FileRead file) throws OrderFormatException  {
		
		//handle cases where the word order dosn't appear
		if(!file.hasNext() || !file.isNext("ORDER"))//no order subsection
			return new AbsOrder();
		
		file.next();//remove the ORDER word
		
		//handle cases of empty order section
		if(!file.hasNext()||file.isNext("FILTER"))//empty order subsection
			return new AbsOrder();

		String order=file.next();
		
		switch(Orders.valueOf(order)) {
		
		case abs:
			return new AbsOrder();
		
		case file:
			return new Comparator<File>() { //I love anonymous classes :)
				@Override public int compare(File o1, File o2) {
					int result=o1.getName().compareTo(o2.getName());
					return (result==0?AbsOrder.compareNames(o1,o2):result);
				}
			};
			
		case mod:
			return new Comparator<File>() {
				@Override public int compare(File o1, File o2) {
					int result=NumCompare.compare(o1.lastModified()/Calender.DAY, o2.lastModified()/Calender.DAY);
					return (result==0?AbsOrder.compareNames(o1,o2):result);
				}
			};
			
		case size:
			return new Comparator<File>() {
				@Override public int compare(File o1, File o2) {
					int result=NumCompare.compare(o1.length(), o2.length());
					return (result==0?AbsOrder.compareNames(o1,o2):result);
				}
			};
			
		default:
			throw new OrderFormatException("can\'t detect order: "+order, 1);
		}
	}
}
