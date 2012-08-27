package oop.ex1.filescript;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import oop.ex1.actions.*;
import oop.ex1.misc.*;

/**
 * This is the main script for the program (ex1 oop 2012).
 * file:MyFileScript.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
public class MyFileScript {

	/**
	 * The main method for this program: this method is called each time program is started.
	 * @param args an array of string - the parameters the program gets;
	 * this program needs two parameters - 1: a directory 2: a commands file.
	 */
	public static void main(String[] args) {
		try {
			
			if(args.length!=2)
				error(); //TODO replace with showUsage()
			
			//initialize the directory
			File CWD = new File(args[0]);; //current working directory
			
			//convert the command file to a queue for easier working
			Scanner file = new Scanner(new FileReader(args[1]));
			if(file.findInLine(Pattern.compile("^\\$")) != null) //if file starts with a comment
				throw new IllegalFileFormatException("commands file can't start with a comment");

			//initialize actions list and run it
			List<Action> actions=Parser.parse(new FileRead(file));
			for(Action act:actions)
				act.commit(CWD);
			
		} catch (Exception e) {
			error();
		}
	}

	/**
	 * This method handles errors,
	 *  All it does is print the word ERROR and quit the program.
	 *  (the exercise specs force me to do that,
	 *  even though we believe in more descriptive errors)
	 */
	private static void error() {
		System.out.println("ERROR");
		System.exit(1);
	}

}
