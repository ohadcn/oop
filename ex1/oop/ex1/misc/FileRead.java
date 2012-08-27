package oop.ex1.misc;

import java.util.Scanner;

/**
 * This class reads files.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
public class FileRead {

	private final String COMMENT="\\$";

	private Scanner scanner;
	private String comments;

	/**
	 * Initializes the File for reading.
	 * @param scanner the scanner of the file.
	 */
	public FileRead(Scanner scanner) {
		this.scanner=scanner;
		comments="";
		
		while (scanner.hasNext(COMMENT))
			comments+=scanner.nextLine()+System.getProperty ( "line.separator" );

	}
	
	/**
	 * returns the next line in the file
	 * @return the next line in the file
	 */
	public String next() {
		String result = scanner.nextLine();
		
		while (scanner.hasNext(COMMENT))
			comments+=scanner.nextLine()+System.getProperty ( "line.separator" );
		
		return result;
	}
	
	/**
	 * Checks if the next string is equivalent to the one in the input.
	 * @param regexp the string to look for.
	 * @return true for is the string does exist, false otherwise.
	 */
	public boolean isNext(String regexp) {
		return scanner.hasNext(regexp);
	}
	
	/**
	 * Cuts the comments so far collected.
	 * @return the comments so far collected.
	 */
	public String pollComments() {
		String temp=comments;
		comments="";
		return temp;
	}

	/**
	 * checks if there is more to read.
	 * @return true for yes, false for no.
	 */
	public boolean hasNext() {
		return scanner.hasNext();
	}
}
