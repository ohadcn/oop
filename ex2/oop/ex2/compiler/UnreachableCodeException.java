package oop.ex2.compiler;

/**
 * this class has exception for unreachable code
 * unreachable code appears after method return statement
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 */
public class UnreachableCodeException extends SjavaError {

	private static final long serialVersionUID = 6317246324134583406L;

	/**
	 * construct new UnreachableCodeException object
	 * @param message the message that describe this exception
	 */
	public UnreachableCodeException(String message) {
		super(message);
	}

}
