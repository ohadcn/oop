package oop.ex1.actions;

import java.io.*;

/**
 * This class implements an action that just print a message to the console.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
public class Commenter implements Action {

	
	private String comment;
	
	/**
	 * construct new Commenter object
	 * @param comment the message to print
	 */
	public Commenter(String comment) {
		this.comment=comment;
	}
	
	/**
	 * print the message of this action
	 * @param CWD just for compability, has no effect
	 */
	@Override
	public void commit(File CWD) throws IOException {
		System.out.print(comment);

	}

}
