package oop.ex2.main;

/**
 * this class has methods for handling errors
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 */
public class Errors {
	
	//error and return values
	private static void error(String des,int val) {
		System.out.println(des);
		System.exit(val);
	}
	
	/**
	 * called in case of an IOError
	 * it prints the message <i> des </i> to system.out
	 * and exits the program with exit status 2
	 * @param des the message to display for the user
	 */
	public static void IOError(String des) {
		error(des,2);
	}
	
	/**
	 * called in case of illegal Sjava code
	 * it prints the message <i> des </i> to system.out
	 * and exits the program with exit status 1
	 * @param des the message to display for the user
	 */
	public static void SjavaError(String des) {
		error(des, 1);
	}
}
