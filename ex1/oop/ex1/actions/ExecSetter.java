package oop.ex1.actions;

import java.io.*;

import oop.ex1.misc.LS;

/**
 * This class implements a file set executable enabled/disabled.
 * It allows to change the executability of the files it deals with.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
class ExecSetter implements Action{

	private boolean isRunnable;
	
	private FileFilter filter;
	
	/**
	 * Construct an ExecSetter instance.
	 * @param canRun set is this class should disable or enable running files.
	 * @param filter a filter for processing only specific files.
	 */
	public ExecSetter(boolean canRun, FileFilter filter) {
		this.isRunnable=canRun;
		this.filter=filter;
	}
	
	/**
	 * Change the files attributes.
	 * @param CWD the directory to work on.
	 * @throws IOException in case of a problem while reading/setting.
	 */
	public void commit(File CWD) {
		File[] list=LS.LSRecursive(filter, CWD);
		
		for(File file:list) 
			file.setExecutable(isRunnable);
	}

}
