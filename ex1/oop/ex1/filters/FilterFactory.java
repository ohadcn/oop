package oop.ex1.filters;

import java.io.*;
import java.text.*;

import oop.ex1.misc.*;

/**
 * This class has a static method for creating file filters.
 * file:FilterFactory.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
public final class FilterFactory {
	
	/**
	 * This method creates an object that implements the FileFilter interface.
	 * @param file a Queue<String> that holds a commands file, seeked to an FILTER section.
	 * @return FileFilter of the filters that <i>file</i> contains in this section.
	 * @throws ParseException in case that file is not seeked to FILTER section or invalid filter exist
	 * @throws IllegalFileFormatException for illeagal file formats.
	 */
	public static FileFilter factory(FileRead file) throws ParseException, IllegalFileFormatException {
		
		if(!file.next().equals("FILTER"))
			throw new IllegalFileFormatException("illigal commands file format: FILTER expected ");
		
		FileFilter filter=new NotFilter(new DirFilter());
		
		while(!file.isNext("ACTION")) {
			filter=new AndFilter(parseLine(file.next()), filter);
		}
		
		return filter;
	}
	
	/**
	 * This method converts string representations of filters separated by space(s)
	 *  to a FileFilter that combines them with soft relations.
	 * This means that each file that can be accepted by one of them
	 *  is accepted by the returned filter.
	 * @param line a string representation of filters, separated by space.
	 * @return a FileFilter that combines the filters in <i>line</i> with soft relations.
	 * @throws ParseException if the parameters of a filters can't be resolved.
	 */
	private static FileFilter parseLine(String line) throws ParseException {
		String[] list=line.split(" ");
		FileFilter filters=null;
		for(String filter:list) {
			String[] pars=filter.split("%");
			if(pars.length<2)
				throw new UnknownFilterException("filter format must have parameters: "+filter,1);
			FileFilter temp;
			switch(Filters.valueOf(pars[0])) {
			
				case after:
					temp=new DateFilter(pars[1], true);
					break;
					
				case before:
					temp=new DateFilter(pars[1], false);
					break;
					
				case greater_than:
					temp=new SizeFilter(pars[1], false);
					break;

				case smaller_than:
					temp=new SizeFilter(pars[1], true);
					break;
				
				case file:
					temp=new NameFilter(pars[1]);
					break;
				
				case prefix:
					temp=new NameFilter(pars[1]+".*",false);
					break;
				
				case suffix:
					temp=new NameFilter(".*"+pars[1],false);
					break;
				
				case executable:
					temp=new ExecFilter(Bool.valueOf(pars[1]).toBoolean());
					break;
				
				case hidden:
					temp=new HiddenFilter(Bool.valueOf(pars[1]).toBoolean());
					break;
				
				case writable:
					temp=new WriteableFilter(Bool.valueOf(pars[1]).toBoolean());
					break;
				default:
					throw new UnknownFilterException("illigal filter format",1);
			}
			
			if(pars.length>2 && pars[2].equals("NOT"))
				temp=new NotFilter(temp);
			
			filters=new OrFilter(temp,filters);
		}
				
		return filters;
	}
}
