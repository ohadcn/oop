package oop.ex1.orders;

import java.text.ParseException;

/**
 * This class represents an exception that is thrown if the format of the an order is illegal.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
public class OrderFormatException extends ParseException {

	private static final long serialVersionUID = 5869465751634140707L;
	
	/**
	 * Construct a OrderFormatException.
	 * @param err the error message.
	 */
	public OrderFormatException(String s, int errorOffset) {
		super(s, errorOffset);
	}

}
