package oop.ex1.misc;

import java.io.StreamCorruptedException;
/**
 * This class represents an exception that is thrown if the format of the commands file is illegal.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
public class IllegalFileFormatException extends StreamCorruptedException {

	private static final long serialVersionUID = -4816853876109618745L;
	
	/**
	 * Construct an IllegalFileFormatException.
	 * @param err the error message.
	 */
	public IllegalFileFormatException(String err) {
		super(err);
	}

}
