package oop.ex1.actions;

import java.io.*;
import java.text.*;

import oop.ex1.misc.LS;

/**
 * This class implements a file copying mechanism.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
class Copier implements Action {
	
	private String target;
	
	private FileFilter filter;
		
	/**
	 * Construct a Copier with a specified target and filter.
	 * @param dir a command in format copy%target, note that target must be relative,
	 * an absolute target will cause an error.
	 * @param filter a filter to be used to copy only some files.
	 * @throws ParseException if target is absolute path
	 */
	public Copier(String dir,FileFilter filter) throws IlligalActionException {
		if(dir.startsWith("/"))
			throw new IlligalActionException("illigal action:"+dir,1);
		this.target = dir;
		this.filter=filter;
	}
	
	/**
	 * Copy the files.
	 * @param CWD the source directory.
	 * @throws IOException in case of a problem while coping.
	 */
	public void commit(File CWD) throws IOException {
		String targetDir = CWD.getCanonicalPath()+"//"+target+"//";
		File tarDir=new File(targetDir);
		if(!tarDir.isDirectory())
			tarDir.mkdirs();
		
		commit(tarDir,CWD);
	}
	
	/**
	 * Copy files from source to target.
	 * @param target the target folder to copy files to.
	 * @param source the source directory to copy files from.
	 * @throws IOException if a directory dosn't exist, or the user has not enough privileges to copy files.
	 */
	private void commit(File target, File source) throws IOException {
		File[] list=LS.LSRecursive(filter, source);
		
		for(File file:list) 
			copy(file,new File(target, file.getName()));
	}
	
	/**
	 * Copy a file from <i>source</i> to <i>target</i>.
	 * @param source the source file.
	 * @param target the target file.
	 * @throws IOException in case of a problem  while coping.
	 */
	private void copy(File source, File target) throws IOException {
		FileInputStream sourceFile = new FileInputStream(source);
		FileOutputStream targetFile = new FileOutputStream(target);
		byte[] buffer = new byte[sourceFile.available()];
		sourceFile.read(buffer);
		targetFile.write(buffer);
		sourceFile.close();
		targetFile.close();
	}
	

}
