package oop.ex3.misc;

/**
 * this interface mark the class work as work that can be stopped at any time
 * @author Ohad Cohen <ohadcn@cs.huji.ac.il>
 */
public interface Interruptible {
	
	/**
	 * stop the running of this class
	 */
	public void timeOut();
}
