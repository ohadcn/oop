package oop.ex2.compiler;

//import oop.ex2.main.Errors;

/**
 * this enum has constants and method for parsing Sjava code and classifying it
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 *
 */
public enum ExpressionType {
	METHOD,
	BLOCK_END,
	BLOCK_START,
	MEMBER_DECLARATION,
	METHOD_CALL,
	RETURN,
	COMMAND,
	UNKNOWN;//error
	
	/**
	 * this character appear each time a block is started
	 */
	private static final char _START='{';
	
	/**
	 * this character appear at the end of all the blocks
	 */
	public static final char _END='}';
		
	/**
	 * legal types that legal for methods types
	 */
	public static final String METHOD_TYPES="("+Types.TYPES+"|void)";
	
	/**
	 * legal method or members names
	 */
	public static final String NAME="_?[a-zA-Z][_\\w]*";
	
	/**
	 * list of legal block types
	 */
	public static final String BLOCKS_TYPES="(while|if)";
	
	/**
	 * this string appears in the declaration of variables and means they are read-only.
	 */
	public static final String FINAL = "final";
	
	/**
	 * member declaration format
	 */
	public static final String MEMBER_DECLARE="^("+FINAL+" )?"+Types.TYPES+" "+NAME+"( ?=.*)?;$";
	
	/**
	 * method declaration format
	 */
	public static final String METHOD_DECLARE = METHOD_TYPES+" [a-zA-Z][_\\w]* ?\\(.*\\) ?\\"+_START;
	
	/**
	 * match the return statement
	 */
	public static final String RETURN_STAT = "return.*;";
	
	/**
	 * match block start statement
	 */
	public static final String BLOCK=BLOCKS_TYPES+" ?\\(.*\\) ?\\"+_START;
	
	/**
	 * match method call statements
	 */
	public static final String METHOD_CALL_FORMAT = NAME+"\\(.*\\);";
	
	/**
	 * the format of a command
	 */
	public static final String COMMAND_FORMAT=NAME+" ?=.*;$";
	
	/**
	 * return the type of the given String
	 * @param line the line to test
	 * @return an ExpressionType, representing the kind of the given String
	 */
	public static ExpressionType detect(String line) {
		
		//i think it's more efficient than checking full line at a time
		//i prefer first check the last character for early classifying
		switch (line.charAt(line.length()-1)) {
		case ';':
			
			if(line.matches(MEMBER_DECLARE))
				return MEMBER_DECLARATION;
			
			if(line.matches(RETURN_STAT))
				return RETURN;
			
			if(line.matches(METHOD_CALL_FORMAT))
				return METHOD_CALL;
			
			if(line.matches(COMMAND_FORMAT))
				return COMMAND;
			break;

		case _START:
			
			if(line.matches(METHOD_DECLARE))
				return METHOD;
			
			if(line.matches(BLOCK))
				return BLOCK_START;
			break;
			
		case _END:
			
			return BLOCK_END;
		
		}

		return UNKNOWN;
	}

}
