package oop.ex2.compiler;

/**
 * this class has exception for illegal use of variables
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 */
public class IlegaMemberUseException extends SjavaError {

	private static final long serialVersionUID = 5213481741928202907L;

	/**
	 * construct new IlegaMemberUseException object
	 * @param message the message that describe this exception
	 */
	public IlegaMemberUseException(String message) {
		super(message);
	}

}
