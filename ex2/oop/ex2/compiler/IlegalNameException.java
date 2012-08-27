package oop.ex2.compiler;

/**
 * this exception is thrown when a declaration of a method or a variable appears with illegal name
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 */
public class IlegalNameException extends MemberDeclarationException {

	private static final long serialVersionUID = -4169932225631708519L;

	/**
	 * construct new IlegalNameException object
	 * @param message the message that describe this exception
	 */
	public IlegalNameException(String message) {
		super(message);
	}

}
