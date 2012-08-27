package oop.ex2.compiler;

/**
 * this exception is thrown when trying to override a method or variable
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 */
public class DoubledMemberException extends IlegalNameException {

	private static final long serialVersionUID = 8624953453896726592L;

	/**
	 * construct new DoubledMemberException object
	 * @param message the message that describe this exception
	 */
	public DoubledMemberException(String message) {
		super(message);
	}

}
