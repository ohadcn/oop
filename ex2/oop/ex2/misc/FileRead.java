package oop.ex2.misc;

import java.io.*;
import java.util.Scanner;

import oop.ex2.compiler.IlegalCommentException;
import oop.ex2.main.Errors;

/**
 * This class reads files.
 * this class also do some operations on the file to make it easier to compile it.
 * this operations includes: removing comments and extra spaces, and add spaces before and after "=" characters
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012
 */
public class FileRead {
	
	//the scanner for reading the file content
	private Scanner scanner;
		
	//used by reset() method
	private String filename;
	
	/**
	 * construct a new FileRead object
	 * @param filename the name of the file to open
	 * @throws IlegalCommentException if the file is starting with illegal comment
	 */
	public FileRead(String filename) throws IlegalCommentException {
		this.filename = filename;
		reset();
	}
	
	/**
	 * Advances this FileRead past the current line and returns the input that was skipped.
	 * This method returns the rest of the current line, excluding any line separator, comments or spaces at the end.
	 * The position is set to the beginning of the next line of code. 
	 * @return the line that was skipped excluding any comments or spaces at the end.
	 * @throws IlegalCommentException the next lines has illegal comment
	 */
	public String next() throws IlegalCommentException {
		String result;
		
		do {
			result=scanner.nextLine().//get the next line
					replace("=", " = ").
					replaceAll("(/\\*.*\\*/|//.*$)", " ").//remove any comments in it
					replaceAll("\\s+", " ").//make sure that spaces are not doubled
					trim();//remove any trailing or leading spaces
			
			trim();//remove trailing comments, useful for hasNext() method
		}while(result.length()<1); //ignore empty lines
		
		return result;
	}
	
	/**
	 * Returns true if the next token matches the pattern constructed from the specified string.
	 * The FileRead does not advance past any input.
	 * @param regexp a string specifying the pattern to scan
	 * @return true if and only if this FileRead has another token matching the specified pattern
	 */
	public boolean hasNext(String regexp) {
		return scanner.hasNext(regexp);
	}
	
	/**
	 * returns true if this FileRead has another token in it's input
	 * @return true if and only if this scanner has another token
	 */
	public boolean hasNext() {
		return scanner.hasNext();
	}
	
	//run to the next line of code 
	private void trim() throws IlegalCommentException {
		while(scanner.hasNext("/.*")) {
			
			if(scanner.hasNext("/[^/\\*]"))
				throw new IlegalCommentException("illigal expression at line\n"+scanner.nextLine());
			
			if(scanner.hasNext("/\\*.*"))//long comment
				scanner.findWithinHorizon("\\*/", 0);
			
			scanner.nextLine();
		}
	}

	/**
	 * take this FileRead back to the start of the file
	 * @throws IlegalCommentException if the file was changed and now is starting with illegal comment
	 */
	public void reset() throws IlegalCommentException {
		try {
			this.scanner=new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			Errors.IOError("file not found: "+filename);
		}
		trim();		
	}
	
}
