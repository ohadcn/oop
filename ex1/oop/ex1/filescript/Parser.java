package oop.ex1.filescript;

import java.io.*;
import java.text.*;
import java.util.*;

import oop.ex1.actions.*;
import oop.ex1.filters.*;
import oop.ex1.misc.*;
import oop.ex1.orders.*;

/**
 * This class parses string into commands that the computer can process.
 * file:Parser.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
public final class Parser {
	
	/**
	 * This method gets a Queue<String> that has a commands file
	 *  and returns List<Action> that holds a compiled version of it.
	 * @param file the strings of the command.
	 * @return List<Action> of compiled commands.
	 * @throws ParseException if a command can't be resolved
	 * @throws IllegalFileFormatException in case the command file has an incorrect structure.
	 */
	public static List<Action> parse(FileRead file) throws ParseException, IllegalFileFormatException {
		List<Action> result = new ArrayList<Action>();
		while(file.hasNext()) {
			FileFilter filters=FilterFactory.factory(file);
			List<Action> current=ActionFactory.parseActions(file, filters);
			Comparator<File> order=OrderFactory.factory(file);
			for(Action act:current)
				if(act instanceof OrderSensitive)
					((OrderSensitive)act).setOrder(order);
			current.add(new Commenter(file.pollComments()));
			result.addAll(current);
		}
		return result;
	}
}
