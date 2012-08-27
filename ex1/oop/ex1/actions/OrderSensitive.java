package oop.ex1.actions;

import java.io.File;
import java.util.Comparator;

/**
 * This interface is used to tell ActionFactory that an Action needs to
 *  use a sorting algorithm.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
public interface OrderSensitive {
	
	/**
	 * Set the sorting algorithm.
	 * @param order a Comparator<File> to sort the files by it.
	 */
	public void setOrder(Comparator<File> order);
	
}
