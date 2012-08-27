package oop.ex2.compiler;

/**
 * this exception is thrown when final member is declared but assigned no value at the declaration
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 */
public class FinalMemberNoValueException extends MemberDeclarationException {

	private static final long serialVersionUID = -2766895170582784438L;

	/**
	 * construct new FinalMemberNoValueException object
	 * @param message the message that describe this exception
	 */
	public FinalMemberNoValueException(String message) {
		super(message);
	}

}
