package oop.ex1.misc;

/**
 * This class has method that can compare two numbers.
 * file:NumCompare.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
public final class NumCompare {
	
	/**
	 * This method compares two numbers,
	 *  returns 1 if the first is greater, -1 if the 2nd is greater or 0 if they are equal.
	 * @param a the first number.
	 * @param b the 2nd number to compare.
	 * @return 1 if a is greater, -1 if b is greater or 0 if a & b are equals.
	 */
	public static int compare(long a,long b) {
		if(a==b)
			return 0;
		if(a<b)
			return -1;
		//b<a
		return 1;
	}

}
