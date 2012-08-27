package oop.ex3.misc;

import java.util.Timer;
import java.util.TimerTask;

/**
 * this class can stop the running of Interruptible class after a given time
 * @author Ohad Cohen <ohadcn@cs.huji.ac.il>
 */
public class Stopper extends TimerTask {

	Timer timer;
	Interruptible obj;//the object to stop
	
	/**
	 * construct a new Stopper object
	 * @param obj the object to stop
	 * @param timeOut time to stop after
	 */
	public Stopper(Interruptible obj,long timeOut) {
		this.obj=obj;
		this.timer=new Timer();
		timer.schedule(this, timeOut);
	}

	/**
	 * stop the stopper: do not interrupt the object at the planned time
	 */
	public void stop() {
		timer.cancel();
	}
	
	@Override
	public void run() {
		obj.timeOut();
		stop();
	}

}
