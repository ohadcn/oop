package oop.ex2.compiler;

/**
 * this exception is thrown when referring to a member but it's not declared first
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 */
public class MemberDontExistExcetion extends IlegaMemberUseException {

	private static final long serialVersionUID = 1920936547324941172L;

	/**
	 * construct new MemberDontExistExcetion object
	 * @param message the message that describe this exception
	 */
	public MemberDontExistExcetion(String message) {
		super(message);
	}

}
