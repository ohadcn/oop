package oop.ex1.actions;

import java.io.*;


/**
 * this interface represent a command that can be done on files.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
public interface Action {
	
	/**
	 * Run the action the specific class is to do.
	 * @param CWD the directory to action on.
	 * @throws IOException if a problem occurred while processing.
	 */
	public void commit(File CWD) throws IOException;
	
}
