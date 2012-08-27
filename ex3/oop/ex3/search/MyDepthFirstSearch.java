package oop.ex3.search;

import java.util.*;
import oop.ex3.misc.*;

/**
 * this class implements a DepthFirstSearch, for running over a moves tree and get the best possible solution
 * @author Ohad Cohen <ohadcn@cs.huji.ac.il>
 */
public class MyDepthFirstSearch<BoardType extends SearchBoardNode<MovesType>, MovesType extends SearchMove>
		implements DepthFirstSearch<BoardType, MovesType>,Interruptible {

	//setting this to true will stop the running of the search
	private boolean isTimeOut;
	
	@Override
	public void timeOut() {
		isTimeOut = true;
	}
	

	//this method does the search itself, running over the possible nodes and return the best solution
	@SuppressWarnings("unchecked")
	private BoardType search(BoardType startBoard, int maxDepth) {
		if(startBoard.isBestSolution()||maxDepth==0||isTimeOut)
			return startBoard;
		
		BoardType best=startBoard;
		int score=startBoard.getQuality();
		Iterator<MovesType> moves= startBoard.getMovesIterator();
		while(moves.hasNext()&&!isTimeOut) {
			MovesType move=moves.next();
			startBoard.doMove(move);
			
			if(startBoard.getQualityBound()<=score){
				//no point in continuing
				startBoard.undoMove(move);
				continue;
			}
			
			BoardType current=search(startBoard, maxDepth-1);
			if(current.isBestSolution())
				return current;
			
			if(current.getQuality()>score) {
				score=current.getQuality();
				best=(BoardType) current.getCopy();
			}
			
			startBoard.undoMove(move);
		}
		return best;
	}
	
	@Override
	public BoardType search(BoardType startBoard, int maxDepth, long timeOut) {
		isTimeOut=false;
		//set a stoper to stop the running of this class when timeout occurred
		Stopper stoper =new Stopper(this,timeOut);
		
		BoardType result=search(startBoard, maxDepth);
		stoper.stop();//stop the Stoper if the best solution obtained before timeout
		return result;
	}

}
