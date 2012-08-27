package oop.ex2.compiler;

/**
 * this exception is thrown when a declaration of a method or a variable appears with illegal type
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 */
public class UnknownTypeException extends MemberDeclarationException {

	private static final long serialVersionUID = 4572086139789363739L;

	/**
	 * construct new UnknownTypeException object
	 * @param message the message that describe this exception
	 */
	public UnknownTypeException(String message) {
		super(message);
	}

}
