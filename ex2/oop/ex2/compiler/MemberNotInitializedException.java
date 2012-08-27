package oop.ex2.compiler;

/**
 * this class has exception for using a variable without initializing it
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 */
public class MemberNotInitializedException extends IlegaMemberUseException {

	private static final long serialVersionUID = 6362834649620509073L;

	/**
	 * construct new MemberNotInitializedException object
	 * @param message the message that describe this exception
	 */
	public MemberNotInitializedException(String message) {
		super(message);
	}

}
