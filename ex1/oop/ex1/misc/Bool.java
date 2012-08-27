package oop.ex1.misc;
/**
 * This enum is like boolean, the only difference is that
 *  it uses yes/no while boolean uses true/false.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
public enum Bool{
	
	YES(true),
	NO(false);
	
	private boolean rep;//a boolean representation of this value
	
	/**
	 * Returns the value of this Bool object as a boolean primitive.
	 * @return the value of this Bool object as a boolean primitive.
	 */
	public boolean toBoolean() {
		return rep;
	}
	
	/**
	 * Constructor for the Bool object.
	 * @param bool the value.
	 */
	Bool(boolean bool){
		rep=bool;
	}
}
