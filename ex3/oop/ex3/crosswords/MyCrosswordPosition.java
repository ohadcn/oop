package oop.ex3.crosswords;

/**
 * this class implements a CrosswordPosition
 * a CrosswordPosition composed of X and Y coordinates, and it can be vertical or horizontal
 * @author Ohad Cohen <ohadcn@cs.huji.ac.il>
 */
public class MyCrosswordPosition implements CrosswordPosition {

	private int X;
	private int Y;
	private boolean isVertical;

	/**
	 * construct a new MyCrosswordPosition object
	 * @param x the x coordinate of the position
	 * @param y the y coordinate of the position
	 * @param isVertical a boolean representing whether this position is vertical (true) or horizontal (false)
	 */
	public MyCrosswordPosition(int x,int y,boolean isVertical) {
		this.isVertical=isVertical;
		this.X=x;
		this.Y=y;
	}
	
	@Override
	public int getX() {
		return X;
	}

	@Override
	public int getY() {
		return Y;
	}

	@Override
	public boolean isVertical() {
		return isVertical;
	}

}
