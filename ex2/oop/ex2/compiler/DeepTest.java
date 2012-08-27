package oop.ex2.compiler;

import java.util.*;

import oop.ex2.misc.*;

/**
 * this class has static methods for deep analyzing of a Sjava code
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 */
public class DeepTest {
	
	/**
	 * do a deep test of Sjava file and returns true if the file has legal code
	 * @param file the file to test
	 * @param globals a map that holds declaration of members and methods
	 * @return true if this file contains legal Sjava code and false otherwise
	 * @throws SjavaError in case unknown error
	 * @throws IlegalReturnType if method tries to return other value type than it's declared one
	 * @throws UnreachableCodeException if statements appear after method return statement
	 * @throws IlegaMemberUseException if someone tries to put illegal value to a variable
	 * or set the value of a readonly member
	 * @throws TypeConversionException if type conversion can't be done
	 * @throws FinalMemberNoValueException if a final member declaration do not set it value
	 * @throws IlegalNameException if a method or variable has illegal name
	 * @throws DoubledMemberException if two members with the same name exist in the same scope
	 * @throws MemberNotInitializedException in case of using a member without initializing it first
	 */
	public static boolean deepTest(FileRead file,Map<String,Member> globals) throws SjavaError {
		while(file.hasNext()) {
			String line=file.next();
			switch (ExpressionType.detect(line)) {
			case MEMBER_DECLARATION:
				testDeclaration(line,globals);
				break;

			case METHOD:
				testBlock(file,globals,getParams(line),
						Types.valueOf(line.substring(0, line.indexOf(' ')).toUpperCase()),true);
				break;
				
			default://other expressions are not allowed outside methods
				throw new SjavaError("illegal declation at this scope"+line);
			}
		}
			
		return true;//indicate that everything goes well
	}

	//test a block or a method
	//gets file, map of global (=overridable and initialized) variables
	//map of local variables, return type and boolean that force it to end with a return statement
	private static void testBlock(FileRead file, Map<String, Member> globals,
			Map<String, Member> locals,Types retVal,boolean isMethod) throws SjavaError {
		String line;
		do {
			line=file.next();
			switch (ExpressionType.detect(line)) {
				
			case RETURN:
				if((retVal==Types.VOID))
					if(!line.matches("return *;"))
						throw new IlegalReturnType("this method returns void\n"+line);
				
				if(Types.typeOf(line.replaceAll("(^return |;$)", ""), new DualMap(globals, locals))!=retVal)
					throw new IlegalReturnType("return value must much return type whitch is "+retVal+"\n"+line);
				line=file.next();
				if(!(ExpressionType.detect(line)==ExpressionType.BLOCK_END))
					throw new UnreachableCodeException("unreachable code:"+line);
				return;
				
			case BLOCK_START:
				testBlockDeclaration(line, new DualMap(globals, locals));
				testBlock(file,globals,new DualMap(locals, new HashMap<String, Member>()),retVal,false);
				break;
				
			case METHOD_CALL://illegal call throw exception
				MethodCall.getType(new MethodCall(line), new DualMap(globals, locals));
				break;
				
			case BLOCK_END:
				if(isMethod)
					throw new UnreachableCodeException("method must end with a return statement");
				return;
				
			case MEMBER_DECLARATION:
				testDeclaration(line, locals, globals);//this method also add it to the map
				break;
				
			case COMMAND://like a=5;
				testCommand(line,locals,globals);
				break;
				
			default://usually UNKNOWN
				throw new SjavaError("unknown or illegal expression at this scope"+line);
			}
		}while(!line.equals("}"));
		
		throw new UnreachableCodeException("a method must end with return statement");
	}

	//test a commands like a=5;
	private static void testCommand(String line, Map<String, Member> locals,
			Map<String, Member> globals) throws SjavaError {
		String varName=line.substring(0, line.indexOf(' ')).trim();
		Member var=null;
		if(globals.containsKey(varName))
			var=globals.get(varName);
		
		if(locals.containsKey(varName))
			var=locals.get(varName);
		
		if(var==null)
			throw new IlegaMemberUseException(varName+" is not declared in this scope\n"+line);
		
		if(var.isReadOnly())
			throw new IlegaMemberUseException(varName+" is final\n"+line);
		
		if(Types.typeOf(line.substring(line.indexOf('=')+1).replaceAll("(^ |;$)", ""),//just the value
				new DualMap(locals, globals))!=var.getType())
			throw new TypeConversionException(varName+" is "+var.getType()+", you can't put other values in it");
		
	}

	//test declaration of members, usually global ones
	//gets the line and the map of the known globals
	private static void testDeclaration(String line, Map<String, Member> globals) throws SjavaError {
		//trying to update it without removing first may cause overriding error
		String name=line.replaceAll("("+ExpressionType.FINAL +" )?.*? ([^ ;=]*).*$", "$2");
		if(globals.containsKey(name)) 
			globals.remove(name);
		testDeclaration(line,globals,new HashMap<String, Member>());
		
	}

	//test declaration of a member
	//gets the declaration line, map of non-overridable (=local) members
	//and map of overridable (=global) variables
	private static void testDeclaration(String line, Map<String, Member> unOverridable,
			Map<String, Member> overridable) throws SjavaError {

		String[] properties=line.split(" ");
		int current=0;
		if(properties[current].equals(ExpressionType.FINAL))
			if(!line.contains("="))
				throw new FinalMemberNoValueException("you must initialize final members at declaration.\n"+line);
			else
				current++;
		
		if(!properties[current].matches(Types.TYPES))
			throw new SjavaError(line);
		
		current++;
		if(properties[current].endsWith(";"))
			properties[current]=properties[current].replaceAll(";$", "");
		if(!properties[current].matches(ExpressionType.NAME))
			throw new IlegalNameException("illegal variable name"+properties[current]);
		if(unOverridable.containsKey(properties[current])&&unOverridable.get(properties[current])!=null)
			throw new DoubledMemberException(properties[current] +" is already declared in this scope\n"+line);
		
		Member var= new Member(properties[current], Types.valueOf(properties[current-1].toUpperCase()),
				line.startsWith(ExpressionType.FINAL));
		unOverridable.put(properties[current],var);
		
		if(properties.length<3) {//member not initialized
			return;
		}
		
		current++;
		if(!properties[current].equals("="))
			throw new IlegalNameException("variable names mus'nt contain spaces"+line);
		
		current++;
		if(properties[current].endsWith(";"))
			properties[current]=properties[current].replaceAll(";$", "");
		
		if(properties[current].equals(var.getName()))
			throw new SjavaError("you can't initialize a variable with it's own value\n"+line);
		
		var.initialize();
		if(Types.isType(var.getType(), line.replaceAll(".*= *(.*);$", "$1")))
			return;

		//isolate the value potted into the variable
		if(Types.typeOf(line.replaceAll(".*= *(.*);$", "$1"),
				new DualMap(unOverridable, overridable))!=var.getType())
			throw new TypeConversionException(
					"variable can be initialized only by the same type as it's own\n"+line);
		
		//if the member exist but is not initialized
		if(unOverridable.containsKey(properties[current])&&!unOverridable.get(properties[current]).isInitialized()&&
				!properties[current].matches(ExpressionType.METHOD_CALL_FORMAT+"?"))
			throw new MemberNotInitializedException(properties[current]+
					" is not initialized yet so you can't use it\n"+line);
	}
	
	//returns a map of the parameters to a method
	private static Map<String, Member> getParams(String method) throws SjavaError {
		Map<String, Member> result = new HashMap<String, Member>();

		String[] params=method.substring(method.indexOf("(")+1, method.lastIndexOf(")")).split(", ?");
		for(String parm:params)
			putMember(parm,result);
		
		return result;
	}

	//put a member by it's declaration to a map
	private static void putMember(String line, Map<String, Member> result) throws SjavaError {
		if(line.length()==0)
			return;
		String[] declr=line.trim().split(" ");
		int current=0;
		if(declr.length<2)
			throw new SjavaError("method parameter must contain at least type and name\n"+line);
		
		if(declr.length>4)
			throw new IlegalNameException("parameter name must not have spaces"+line);
		
		if(declr[current].equals(ExpressionType.FINAL))
			current++;
		
		if(!declr[current].matches(Types.TYPES))
			throw new UnknownTypeException(declr[current]+"is not a legal type");
		
		current++;
		if(!declr[current].matches(ExpressionType.NAME))
			throw new IlegalNameException(declr[1]+"is not a legal variable name");

		if(result.containsKey(declr[current]))
			throw new DoubledMemberException("variable "+declr[current]+" is already declared in this scope");
		
		result.put(declr[current], new Member(declr[current], Types.valueOf(declr[0].toUpperCase()),
				line.startsWith(ExpressionType.FINAL)));
		
		result.get(declr[current]).initialize();
		
	}

	//test the start of if or while blocks
	private static void testBlockDeclaration(String line, DualMap map) throws SjavaError {
		if(!line.matches(ExpressionType.BLOCK))
			throw new SjavaError("illegal expression:\n"+line);
		
		String bool=line.substring(line.indexOf("(")+1, line.lastIndexOf(")")).trim();
		
		if(map.containsKey(bool))
			if(map.get(bool).getType()==Types.BOOLEAN ){
				if(map.get(bool).isInitialized())
					return;
				else
					throw new MemberNotInitializedException(bool+" is not initialized yet, so you can't use it");
			}
			else//the type of the member is not boolean
				throw new TypeConversionException(map.get(bool)+" is variable of type "+
						map.get(bool).getType()+", but boolean expected");
		
		if(Types.isBoolean(bool)||Types.typeOf(bool, map)==Types.BOOLEAN)
			return;
		
		throw new TypeConversionException(bool+" is not applicable of boolean\nat line: "+line);
	}

}
