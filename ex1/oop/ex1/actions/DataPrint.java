package oop.ex1.actions;

import java.io.*;
import java.util.Date;

/**
 * This class implements printing file properties mechanism.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
public class DataPrint extends NamePrint {

	/**
	 * construct new DataPrint object
	 * @param filter a FileFilter
	 */
	public DataPrint(FileFilter filter) {
		super(filter);
	}

	/**
	 * @param file a file to convert into a String
	 * @return a String representation of the file
	 * @throws IOException in case of errors accessing <b>file</b>
	 */
	@Override
	protected String toString(File file) throws IOException{
		return ((file.isHidden()?"h":"-")+
				(file.canWrite()?"w":"-")+
		(file.canExecute()?"x":"-")+" "+
		(file.length()/128.0)+" "+
		new Date(file.lastModified())+" "+
		file.getCanonicalPath());

	}

}
