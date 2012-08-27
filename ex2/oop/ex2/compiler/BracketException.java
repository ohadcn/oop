package oop.ex2.compiler;

/**
 * this exception is thrown when brackets are not closed
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 */
public class BracketException extends SjavaError {

	private static final long serialVersionUID = 4517131094522203480L;

	/**
	 * construct new BracketException object
	 * @param message the message that describe this exception
	 */
	public BracketException(String message) {
		super(message);
	}

}
