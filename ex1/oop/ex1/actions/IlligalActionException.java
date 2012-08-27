package oop.ex1.actions;

import java.text.ParseException;

/**
 * This class represents an exception that is thrown if the
 *  format the action is found to be illegal.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
public class IlligalActionException extends ParseException {

	private static final long serialVersionUID = 7374455351461504008L;

	/**
	 * Construct a IlligalActionException.
	 * @param err the error message.
	 * @param errorOffset the ID of the error.
	 */
	public IlligalActionException(String err, int errorOffset) {
		super(err, errorOffset);
	}

}
