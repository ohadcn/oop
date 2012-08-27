package oop.ex3.crosswords;

/**
 * this class implements methods for testing the legality of moves in a crosswords
 * in a crossword moves are possible places to put words
 * @author Ohad Cohen <ohadcn@cs.huji.ac.il>
 */
public class MoveTest {

	/**
	 * check the legality of putting the string <i>term</i> in place <i>pos</i> on a board with <i>words</i> already
	 * in the crossword
	 * @param term the term to put on the board
	 * @param pos the desired position for string term
	 * @param words the words that already exist on the board
	 * @return true if putting <i>term</i> in position <i>pos</i> do not collide with other terms already in the
	 * crossword
	 */
	public static boolean isLegalMove(String term,CrosswordPosition pos,Iterable<CrosswordEntry> words) {
		if(pos.isVertical())
			return isLegalVert(term, pos.getX(), pos.getY(), words);
		else
			return isLegalHorizonal(term, pos.getX(), pos.getY(), words);
	}
	
	//check if it's possible to put string term in place (posX,posY,isVertical=false)
	private static boolean isLegalHorizonal(String term, int posX, int posY,
			Iterable<CrosswordEntry> words) {
		for(CrosswordEntry entry:words) {
			CrosswordPosition pos = entry.getPosition();

			if(pos.isVertical()){
				if(!crossTest(term, new MyCrosswordPosition(posX, posY, false), entry.getTerm(), pos))
					return false;
			}
			else
				if(pos.getY()==posY && !sameLineTest(entry.getTerm(), pos.getX(), term, posX))
					return false;
		}
		return true;
	}

	//check if it's possible to put string term in place (posX,posY,isVertical=true)
	private static boolean isLegalVert(String term, int posX, int posY,
			Iterable<CrosswordEntry> words) {
		for(CrosswordEntry entry:words) {
			CrosswordPosition pos = entry.getPosition();
			if(!pos.isVertical()){
				if(!crossTest( entry.getTerm(), pos,term, new MyCrosswordPosition(posX, posY, true)))
					return false;
			}
			else
				if(pos.getX()==posX && !sameLineTest(entry.getTerm(), pos.getY(), term, posY))
					return false;
			
		}
		return true;
	}

	//check if term1 and term2 can lie at the same line of the crossword, term1 at position pos1
	//and term2 at position term2
	private static boolean sameLineTest(String term1, int pos1, String term2, int pos2) {
		int delta=pos1-pos2;
		if(delta==0)
			return false;
		
		if(delta>0)
			if(delta>term2.length())
				return true;
			else
				return term2.regionMatches(delta, term1, 0, Math.min(term1.length(), term2.length()-delta));
		else
			if(-delta>term1.length())
				return true;
			else
				return term1.regionMatches(-delta, term2, 0, Math.min(term2.length(),term1.length() + delta));
	}

	//check if horizTerm can lie horizontally at posHor while vertTerm lie at position posVert vertically
	private static boolean crossTest(String horizTerm,CrosswordPosition posHor,String vertTerm,
			CrosswordPosition posVert) {
		int dx=posVert.getX()-posHor.getX(),
				dy=posHor.getY()-posVert.getY();

		if(dx<0||dy<0||dx>=horizTerm.length()||dy>=vertTerm.length())
			return true;

		return (horizTerm.charAt(dx)==vertTerm.charAt(dy));
	}
		
}
