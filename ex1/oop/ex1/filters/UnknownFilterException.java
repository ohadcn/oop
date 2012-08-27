package oop.ex1.filters;

import java.text.ParseException;

/**
 * This class represents an exception that is thrown if the format of the a filter is illegal.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
public class UnknownFilterException extends ParseException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construct an UnknownFilterException.
	 * @param err the error message.
	 * @param errorOffset the ID of the error.
	 */
	public UnknownFilterException(String err, int errorOffset) {
		super(err, errorOffset);
	}

}
