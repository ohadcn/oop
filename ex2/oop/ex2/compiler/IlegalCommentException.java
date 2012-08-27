package oop.ex2.compiler;

/**
 * this exception is thrown when a comment not closed properly 
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 */
public class IlegalCommentException extends SjavaError {

	private static final long serialVersionUID = 8036032081143460874L;

	/**
	 * construct new IlegalCommentException object
	 * @param message the message that describe this exception
	 */
	public IlegalCommentException(String message) {
		super(message);
	}

}
