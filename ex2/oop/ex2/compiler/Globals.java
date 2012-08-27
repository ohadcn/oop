package oop.ex2.compiler;

import java.util.*;

import oop.ex2.misc.FileRead;

/**
 * this class has a method that returns a map of all the global members and methods in file <i>file</i>
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 */
public class Globals {

	/**
	 * returns all the global members and methods in a given file
	 * @param file the file to return values from
	 * @return all the global members and methods declared in <i>file</i>
	 * @throws IlegalCommentException if illegal comment appears 
	 * @throws FinalMemberNoValueException if a global final member is declared without assigning it a value
	 * @throws DoubledMemberException if a member declared twice
	 * @throws UnknownTypeException if a member declaration with illegal type exist
	 * @throws IlegalNameException if a name is illegal
	 * @throws NestedMethodDeclatationException if a declaration of method appears inside another method
	 */
	public static Map<String, Member> getGlobals(FileRead file)
				throws IlegalCommentException, FinalMemberNoValueException, DoubledMemberException,
				UnknownTypeException, IlegalNameException, NestedMethodDeclatationException {
		Map<String, Member> result = new HashMap<String, Member>();
		while(file.hasNext()) {
			String line=file.next();
			switch (ExpressionType.detect(line)) {
			
			case METHOD:
				getMethodSign(line, result);
				run(file); //run to the end of the method
				break;

			case MEMBER_DECLARATION:
				getMemberSign(line, result);
				
			default:
				break;
			}
		}
		return result;
	}
	
	//get signature of a member / variable
	private static void getMemberSign(String line,Map<String, Member> result)
		throws FinalMemberNoValueException, UnknownTypeException, DoubledMemberException, IlegalNameException {
		String[] parts=line.split(" ");
		int current=0;
		if(parts[current].equals(ExpressionType.FINAL)) {
			current++;
			if(!line.contains("="))
				throw new FinalMemberNoValueException
					("final member declaration must be assigned a value\nat line:"+line);
		}
		
		if(!parts[current].matches(Types.TYPES))
			throw new UnknownTypeException("unknown type:"+parts[current]+"\nat line:"+line);
		current++;
		
		if(!parts[current].matches(ExpressionType.NAME+";?"))
			throw new IlegalNameException("choose another name to your variable:"+parts[current]);
		
		if(parts[current].endsWith(";"))
			parts[current]=parts[current].substring(0, parts[current].length()-1);
		
		if(result.containsKey(parts[current]))
			throw new DoubledMemberException("two members with the same name:"+parts[current]+"\n"+line);
		
		Types type=Types.valueOf(parts[current-1].toUpperCase());
		result.put(parts[current], new Member(parts[current],type,line.startsWith(ExpressionType.FINAL)));

	}
	
	//get the signature of a method
	private static void getMethodSign(String line,Map<String,Member> map)
			throws DoubledMemberException, IlegalNameException {
		Types type=Types.valueOf(line.substring(0, line.indexOf(' ')).toUpperCase());
		String name=line.substring(line.indexOf(' ')+1, line.indexOf('('));
		String params=line.substring(line.indexOf('(')+1, line.indexOf(')'));
		String sgn=name+"(";
		for(String par:params.split(", ?")) {
			String[] content=par.trim().split(" ");
			if((content.length!=2 || (!content[0].matches(Types.TYPES)) ||
					(!content[1].matches(ExpressionType.NAME)))&&par.length()!=0)
				throw new IlegalNameException("Illegal param to method:"+par+" at line\n"+line);
			sgn+=content[0].toUpperCase()+",";
		}
		sgn=sgn.replaceAll(",$", "")+")";//remove the last comma and add closing bracket
		if(map.containsKey(sgn))
			throw new DoubledMemberException("two methods can't have the same name and parameters\n"+line);
		map.put(sgn, new Member(sgn,type,true));
		map.get(sgn).initialize();
	}

	//run <i>file</i> to the end of the current block
	private static void run(FileRead file) throws IlegalCommentException, NestedMethodDeclatationException {
		String line;
		do {
			line=file.next();
			
			switch(ExpressionType.detect(line)) {
			
			case BLOCK_START:
				run(file);
				break;
			
			case BLOCK_END:
				return;
			
			case METHOD:
				throw new NestedMethodDeclatationException("can't declare a method inside another\n"+line);
				
			default:
				break;
			}
		}while(!line.equals(ExpressionType._END));
		
	}

}
