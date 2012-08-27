package oop.ex2.compiler;

import java.util.Map;
import java.util.regex.*;

/**
 * this class has static and non static methods for analyzing methods legality and return values
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 *
 */
public class MethodCall {

	private Matcher call;
	
	/**
	 * construct a new MethodCall object
	 * @param call the String that present the call
	 */
	public MethodCall(String call) {
		Pattern t=Pattern.compile("[^,\\(\\)]*([^\\)],|\\(|\\))");
		this.call=t.matcher(call.replaceAll(" ", ""));
		this.call.find();
	}
	
	//the next element in the call
	private String next() {
		String result= call.group();
		call.find();
		return result;
	}
	
	//check if this MethodCall has more elements
	private boolean hasNext() {
		return !call.hitEnd();
	}
	
	/**
	 * returns the type of the return value by this call
	 * @param obj a MethodCall object that holds the call
	 * @param known all known variables and method signatures in this scope 
	 * @return the type of the return value of this call
	 * @throws SjavaError in case of an unknown error
	 * @throws BracketException if there are unclosed brackets
	 * or a closing unopened bracket
	 * @throws MemberDontExistExcetion if that method does not exist or trying to use unknown member
	 */
	public static Types getType(MethodCall obj, Map<String, Member> known) throws SjavaError {
		Types type= getType(obj.next(),obj,known);
		if(obj.hasNext())
			throw new BracketException("extra chars in your call! "+obj.call.toString());
		return type;
	}
	
	//deep test of the call
	private static Types getType(String name, MethodCall obj,
			Map<String, Member> known) throws SjavaError {
		String sgn=name;
		String param;
		loop:do {
			if(!obj.hasNext())
				throw new BracketException("please count parentheses, i think you missed some "+obj.call.toString());
			param=obj.next();
			in:switch (param.charAt(param.length()-1)) {
			case ')':
				if(param.length()>1)
					sgn+=Types.typeOf(param.replaceAll("\\)$", ""), known)+",";
				break loop;

			case ',':
				sgn+=Types.typeOf(param.replaceAll(",$", ""), known)+",";
				break in;
				
			case '(':
				sgn+=getType(param, obj, known)+",";
				break in;
				
			default:
				throw new SjavaError(param+"is not valid in this scope");
			}
		}while(!param.endsWith(")"));
		sgn=sgn.replaceAll(",$", "")+")";
		if(!known.containsKey(sgn))
			throw new MemberDontExistExcetion(sgn+"\nsuch method does not exist");
		
		return known.get(sgn).getType();
	}

}
