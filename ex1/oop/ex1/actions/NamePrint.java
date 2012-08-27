package oop.ex1.actions;

import java.io.*;
import java.util.*;

import oop.ex1.misc.LS;

/**
 * This class implements printing file properties mechanism.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
class NamePrint implements OrderSensitive, Action{

	private Comparator<File> sorter;
	
	private FileFilter filter;

	/**
	 * Construct a new NamePrint instance, the NamePrint action prints info about the files.
	 * @param filter a FileFilter that filters files which should be printed.
	 */
	public NamePrint(FileFilter filter){
		this.filter=filter;
	}
	
	/**
	 * Set the order with witch files should be printed.
	 * @param order a Comparator<File> who can tell the NamePrint which file to print first.
	 */
	public void setOrder(Comparator<File> order) {
		sorter = order;
	}
	
	/**
	 * Print the files.
	 * @param CWD the directory to start printing at.
	 * @throws IOException if there is an error accessing filesystem or files properties.
	 */
	public void commit(File CWD) throws IOException {
		File[] list=LS.LSRecursive(filter, CWD);
		Arrays.sort(list,sorter);
		for(File file:list) 
			System.out.println(toString(file));
	
	}

	/**
	 * @param file a file to convert into a String
	 * @return a String representation of the file
	 * @throws IOException in case of errors accessing <b>file</b>
	 */
	protected String toString(File file) throws IOException {
		return file.getName();
	}

}
