package oop.ex2.compiler;

/**
 * this exception is thrown when an error occurs in S-java code
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 */
public class SjavaError extends Exception {

	/**
	 * construct new SjavaError object
	 * @param message the message that describe this exception
	 */
	public SjavaError(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
