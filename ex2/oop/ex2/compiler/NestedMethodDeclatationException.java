package oop.ex2.compiler;

/**
 * this exception is thrown when a declaration of a method appears inside another method
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 */
public class NestedMethodDeclatationException extends
		MemberDeclarationException {

	private static final long serialVersionUID = -4795729426683846903L;

	/**
	 * construct new NestedMethodDeclatationException object
	 * @param message the message that describe this exception
	 */
	public NestedMethodDeclatationException(String message) {
		super(message);
	}

}
