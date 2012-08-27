package oop.ex2.compiler;

import java.util.Map;

/**
 * this enum has methods and constants for detecting types of expressions
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 */
public enum Types {
	INT,
	CHAR,
	STRING,
	DOUBLE,
	BOOLEAN,
	VOID;
	
	
	/**
	 * legal types
	 */
	public static final String TYPES="(int|double|String|boolean|char)";

	
	private static final String INT_FORMAT="-?\\d+";
	private static final String DOUBLE_FORMAT="-?\\d+(\\.\\d+)?";
	
	//keep in mind that numbers also accepted as boolean values
	private static final String BOOLEAN_FORMAT="(true|false|"+DOUBLE_FORMAT+")";
	private static final String CHAR_FORMAT="'.'";
	private static final String STRING_FORMAT="\".*\"";
	
	/**
	 * return a <b> Types </b> object that represent the value of <i> exp </i>
	 * @param exp the String to process
	 * @return a <b> Types </b> object that represent the value of <i> exp </i>
	 */
	public static Types which(String exp) {
		//just check manually each type..
		if(exp.matches(INT_FORMAT))
			return INT;
		
		if(exp.matches(DOUBLE_FORMAT))
			return DOUBLE;
		
		if(exp.matches(CHAR_FORMAT))
			return CHAR;
		
		if(exp.matches(BOOLEAN_FORMAT))
			return BOOLEAN;
		
		if(exp.matches(STRING_FORMAT))
			return STRING;
		
		return VOID;
	}
	
	/**
	 * return a <b> Types </b> object that represent the value of <i> obj </i>
	 * considering that all the variables and method in <i> known </i> do exist in the called scope
	 * @param obj the String to process
	 * @param known all known variables and methods in the scope of the calling block
	 * @return a <b> Types </b> object that represent the value of <i> obj </i>
	 * @throws SjavaError in case of an unknown error
	 * @throws BracketException if there are unclosed brackets
	 * or a closing unopened bracket
	 * @throws MemberDontExistExcetion if that method does not exist or trying to use unknown member
	 */
	public static Types typeOf(String obj,Map<String, Member> known) throws SjavaError {
		if(which(obj)!=VOID)
			return which(obj);
		
		if(known.containsKey(obj))
			return known.get(obj).getType();
		
		if(obj.length()==0)
			return VOID;
		
		obj=obj.replaceAll(" ", "");
		if(obj.matches(ExpressionType.METHOD_CALL_FORMAT+"?"))
			return MethodCall.getType(new MethodCall(obj),known);
			
		return VOID;
	}
	
	/**
	 * returns true if <i> exp </i> is valid form of <i> type </i>
	 * @param type the requested type
	 * @param exp the String that needs to be checked
	 * @return true if <i> exp </i> is valid form of <i> type </i> and false otherwise
	 */
	public static boolean isType(Types type,String exp) {
		switch (type) {
		
		case INT:
			return isInt(exp);
		
		case STRING:
			return isString(exp);

		case CHAR:
			return isChar(exp);
			
		case DOUBLE:
			return isDouble(exp);
			
		case BOOLEAN:
			return isBoolean(exp);
			
		case VOID:
			return exp.length()==0;
			
		default:
			return false;
		}
	}

	/**
	 * returns true if <i> exp </i> is legal boolean
	 * keep in mind that number are also legal booleans
	 * @param exp the expression to test
	 * @return true if <i> exp </i> is legal boolean
	 */
	public static boolean isBoolean(String exp) {
		return exp.matches(BOOLEAN_FORMAT);
	}

	//test double values
	private static boolean isDouble(String exp) {
		return exp.matches(DOUBLE_FORMAT);
	}

	//test char values
	private static boolean isChar(String exp) {
		return exp.matches(CHAR_FORMAT);
	}

	//test integer values
	private static boolean isInt(String exp) {
		return exp.matches(INT_FORMAT);
	}
	
	//test String values
	private static boolean isString(String exp) {
		return exp.matches(STRING_FORMAT);
	}
}
