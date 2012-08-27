package oop.ex2.compiler;

/**
 * this exception is thrown when illegal declaration appears
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 */
public class MemberDeclarationException extends SjavaError {

	private static final long serialVersionUID = 3970794305516476951L;

	/**
	 * construct new MemberDeclarationException object
	 * @param message the message that describe this exception
	 */
	public MemberDeclarationException(String message) {
		super(message);
	}

}
