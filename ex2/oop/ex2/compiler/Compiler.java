package oop.ex2.compiler;

import java.util.Map;

import oop.ex2.misc.FileRead;

/**
 * this is the main compiler, it has one static method for testing a file
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 */
public class Compiler {
	
	/**
	 * test a file for having valid Sjava code
	 * @param file the file to test
	 * @return true if file <i>file</i> has valid Sjava code and false or exception otherwise
	 * @throws SjavaError in case unknown error
	 * @throws IlegalReturnType if method tries to return other value type than it's declared one
	 * @throws UnreachableCodeException if statements appear after method return statement
	 * @throws IlegaMemberUseException if someone tries to put illegal value to a variable
	 * or set the value of a readonly member
	 * @throws UnknownTypeException if a member declaration with illegal type exist
	 * @throws IlegalCommentException if illegal comment appears 
	 * @throws BracketException if there are unclosed brackets
	 * @throws NestedMethodDeclatationException if a declaration of method appears inside another method
	 * @throws TypeConversionException if type conversion can't be done
	 * @throws FinalMemberNoValueException if a final member declaration do not set it value
	 * @throws IlegalNameException if a method or variable has illegal name
	 * @throws DoubledMemberException if two members with the same name exist in the same scope
	 * @throws MemberNotInitializedException in case of using a member without initializing it first
	 */
	public static boolean compile(FileRead file) throws SjavaError {
		
		Map<String, Member> globals=Globals.getGlobals(file);
		file.reset();
		
		if(DeepTest.deepTest(file,globals))
			return true;
		
		return false;
	}
}
