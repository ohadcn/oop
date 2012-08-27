package oop.ex1.actions;

import java.io.*;
import java.util.*;
import oop.ex1.misc.*;

import java.text.*;

/**
 * This class has a static method for creating file actions.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
public final class ActionFactory {
	
	/**
	 * This method creates objects that implement the Action interface.
	 * @param file a Queue<String> that holds a commands file, seeked to an ACTION section.
	 * @param filters a FileFilter that will be be applied to all the actions created by this method.
	 * @return List<Action> of the actions that <i>file</i> contain in this section.
	 * @throws IllegalFileFormatException in case that file is not seeked to ACTION section.
	 * @throws ParseException when a date can't be resolved 
	 */
	public static List<Action> parseActions(FileRead file, FileFilter filters)
			throws IllegalFileFormatException, ParseException {
		if(!(file.hasNext() && file.next().equals("ACTION")))
				throw new IllegalFileFormatException("illigal commands file format, Action expected ");

		List<Action> result = new ArrayList<Action>();
		while((file.hasNext() && !file.isNext("ORDER") && !file.isNext("FILTER"))) {
			String command=file.next();
			switch(command.charAt(0)) {
			
			case 'p':
				if(command.equals("print_data")) {
					result.add(new DataPrint(filters));
					break;
				}
				if(command.equals("print_name")) {
					result.add(new NamePrint(filters));
					break;
				}
				throw new IlligalActionException("unknown Action:"+command,1);
				
			case 'c':
				if(command.startsWith("copy")) {
					result.add(new Copier(command.split("%")[1], filters));
					break;	
				}
				throw new IlligalActionException("unknown Action:"+command,1);
			
			case 'e':
				if (command.startsWith("exec")){
					result.add(new ExecSetter(Bool.valueOf(command.split("%")[1]).toBoolean(), filters));
					break;		
				}
				throw new IlligalActionException("unknown Action:"+command,1);
			
			case 'w':
				if (command.startsWith("write")){
					result.add(new WriteableSetter(Bool.valueOf(command.split("%")[1]).toBoolean(), filters));
					break;		
				}
				throw new IlligalActionException("unknown Action:"+command,1);
			
			case 'l':
				if (command.startsWith("last_mod")){
					result.add(new Toucher(Calender.strToDate(command.split("%")[1]).getTime(), filters));
					break;		
				}
				throw new IlligalActionException("unknown Action:"+command,1);				
			
			default:
				throw new IlligalActionException("unknown Action:"+command,1);

			}
		}
		if(result.isEmpty())
			throw new IllegalFileFormatException("empty ACTION section");
		return result;
	}
}
