package oop.ex2.main;

import java.io.IOError;

import oop.ex2.compiler.Compiler;
import oop.ex2.compiler.SjavaError;
import oop.ex2.misc.FileRead;

/**
 * this is the main class of the program,
 * it has the main method.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 *
 */
public class Sjavac {
	
	/**
	 * the main method of my program... :)
	 * @param args the parameters of the program.
	 * the only important one is the first, contain the name of the file to compile
	 */
	public static void main(String[] args) {
		if(args.length<1) {//invalid usage: show usage message
			Errors.IOError(USAGE);
		}
		FileRead f;
		try {
			f = new FileRead(args[0]);
			Compiler.compile(f);
			//everything is OK
			System.out.println("you are a good S-java programmer :)\nno errors found in your code");
			System.exit(0);
			
		} catch (SjavaError e) {//handling errors in the code
			Errors.SjavaError(e.getMessage());
		}
		catch (IOError e) {
			Errors.IOError(e.getMessage());
		}
	}

	//usage string
	private static String USAGE="usage:\tjava oop.ex1.main.Sjavac <filename>\n"+
			"Sjavac is a s-java basic compiler that just test your files for having a valid s-java code\n"+
			"if file  <filename> contains valid s-java code Sjavac will exit with success status (0)\n"+
			"otherwise Sjavac will whow you the errors in your s-java code and exit with status code 1";
}
