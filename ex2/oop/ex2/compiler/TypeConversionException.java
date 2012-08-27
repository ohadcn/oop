package oop.ex2.compiler;

/**
 * this class has exception for trying to put a value where it's not belongs
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 */
public class TypeConversionException extends IlegaMemberUseException {

	private static final long serialVersionUID = 9219441845001028445L;

	/**
	 * construct new MemberNotInitializedException object
	 * @param message the message that describe this exception
	 */
	public TypeConversionException(String message) {
		super(message);
	}

}
