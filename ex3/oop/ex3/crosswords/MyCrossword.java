package oop.ex3.crosswords;

import java.util.*;

import oop.ex3.crosswords.CrosswordStructure.SlotType;
import oop.ex3.search.SearchBoardNode;

/**
 * this class implements a Crossword
 * @author Ohad Cohen <ohadcn@cs.huji.ac.il>
 */
public class MyCrossword implements Crossword {

	//an iterator over available moves,
	//this class is returned by the method MyCrossword.getMovesIterator(),
	//and handles iterating over the available positions to put the word in the crossword
	//putting this as inner class meant to prevent other classes from using it
	private class MovesIterator implements Iterator<CrosswordEntry>{

		int moveIndex;//the index of the current desired move in avaliablePositions array
		private final int termIndex;//the index of the term we iterating over
		private final MyCrossword parent;//the parent crossword
		private final int termSize;//the size of the term, avaliablePositions save the positions by word size
		
		/**
		 * construct a new MovesIterator object
		 * a MovesIterator iterates over available positions to <i>term</i> in the crossword
		 * @param parent the parent board
		 * @param term the term you want to put on the board
		 */
		public MovesIterator(MyCrossword parent,int term) {
			this.parent=parent;
			this.termIndex=term;
			termSize=parent.terms[term].length()-1;
			
			//next() method first increase moveIndex so it needs to start at -1, not 0
			moveIndex=-1;
		}
		
		/**
		 * returns false if this iterator already iterate over all the available positions on the board
		 * true if this Iterator has more moves
		 * @return true if this Iterator has more moves
		 */
		@Override
		public boolean hasNext() {
			return moveIndex<0||parent.avaliablePositions[termSize][moveIndex]!=null;
		}

		/**
		 * returns the next possible move in the crossword
		 * @return the next possible move in the crossword
		 */
		@Override
		public CrosswordEntry next() {
			do{
				moveIndex++;
			}while(parent.avaliablePositions[termSize][moveIndex]!=null
					&& !MoveTest.isLegalMove(parent.terms[termIndex],
							parent.avaliablePositions[termSize][moveIndex], parent.enteries.values())) ;

			if(parent.avaliablePositions[termSize][moveIndex]==null)
				return null;
			
			return new MyCrosswordEntry(parent.terms[termIndex],
					parent.glossary.getTermDefinition(parent.terms[termIndex]),
					parent.avaliablePositions[termSize][moveIndex]);
		}

		/**
		 * this implementations of Iterator do not implements that method
		 */
		@Override
		public void remove() {			
		}
		
	}
	
	private CrosswordGlossary glossary;//the glossary
	private CrosswordStructure structure;//the structure
	private Map<String,CrosswordEntry> enteries;
	private String[] terms;
	
	//keep available positions for words by their length
	private CrosswordPosition[][] avaliablePositions;
	private int quality;
	private int qualityBound;
	private int moves;
	
	/**
	 * construct new MyCrossword object
	 */
	public MyCrossword() {
		enteries=new HashMap<String, CrosswordEntry>();
		quality=0;
		moves=0;
	}
	
	/**
	 * construct new MyCrossword object from an existing one
	 * @param crossword the crossword to copy
	 */
	public MyCrossword(MyCrossword crossword) {
		glossary=crossword.glossary;
		structure=crossword.structure;
		enteries=new HashMap<String, CrosswordEntry>();
		enteries.putAll(crossword.enteries);
		quality=crossword.quality;
		moves=crossword.moves;
		terms=crossword.terms;
		avaliablePositions=crossword.avaliablePositions;
		this.qualityBound=crossword.qualityBound;
	}
	
	/**
	 * Returns true if a node object represents a one of the best possible solutions of the problem, false otherwise.
	 * using this method before attaching a glossary to this MyCrossword throws a NullPointerException
	 * return true if a node object represents a one of the best possible solutions of the problem, false otherwise.
	 */
	@Override
	public boolean isBestSolution() {
		return quality==glossary.allTermsLength();
	}

	@Override
	public Iterator<CrosswordEntry> getMovesIterator() {
		if(moves>=terms.length)//if we testsed all the words return empty Iterator
			return (new ArrayList<CrosswordEntry>()).iterator();
		
		return new MovesIterator(this, moves);
	}

	@Override
	public int getQuality() {
		return quality;
	}

	/**
	 * This function allows estimation of potential upper bound on quality of solutions
	 * The returned value have to be always greater or equal to the best possible quality obtained
	 * through doMove operations.
	 * using this method before attaching a glossary to this MyCrossword throws a NullPointerException
	 * @return the maximum available quality of a board
	 */
	@Override
	public int getQualityBound() {
		return qualityBound;
	}

	/**
	 * Performs a move on the board reversible changing the current board object.
	 * null move means that the current word should not appear on the board
	 * @param move the move to perform, null to skip the current word on the board
	 */
	@Override
	public void doMove(CrosswordEntry move) {
		moves++;
		if(move==null){
			qualityBound-=terms[moves-1].length();
			return;
		}
		enteries.put(move.getTerm(), move);
		quality+=move.getLength();
	}

	@Override
	public void undoMove(CrosswordEntry move) {
		moves--;
		if(move==null){
			qualityBound+=terms[moves].length();
			return;
		}
		enteries.remove(move.getTerm());
		quality-=move.getLength();
	}

	@Override
	public SearchBoardNode<CrosswordEntry> getCopy() {
		return new MyCrossword(this);
	}

	@Override
	public void attachGlossary(CrosswordGlossary glossary) {
		this.glossary=glossary;
		qualityBound=glossary.allTermsLength();
		updateTerms();
		if(structure!=null)
			updateAvaliablePositions();
	}

	//update the array avaliablePositions of all the available positions for a word by it's length
	//the available positions are places that the word do not collide with places not available for letters on the board.
	private void updateAvaliablePositions() {
		avaliablePositions=new CrosswordPosition[glossary.maxTermSize()][];
		
		//for every word size
		for(int size=0;size<glossary.maxTermSize();size++) {
			avaliablePositions[size]=new CrosswordPosition[structure.getHeight()*structure.getWidth()*2];
			int index=0;
			
			//for every 
			for(int x=0;x<structure.getWidth();x++)
				for(int y=0;y<structure.getHeight();y++) {
					boolean legalMove=true;
					for(int k=0;k<=size;k++)
						if(structure.getSlotType(x+k,y)==SlotType.FRAME_SLOT)
							legalMove=false;
					if(legalMove)
						avaliablePositions[size][index++]=new MyCrosswordPosition(x, y, false);

					legalMove=true;
					for(int k=0;k<=size;k++)
						if(structure.getSlotType(x,y+k)==SlotType.FRAME_SLOT)
							legalMove=false;
					if(legalMove)
						avaliablePositions[size][index++]=new MyCrosswordPosition(x, y, true);
					
				}
		}
	}

	//update the terms list and sort it
	//the sorting of terms by length is a optimization required by the exercise specs
	private void updateTerms() {
		Set<String> glos=glossary.getTerms();
		terms=new String[glos.size()];
		int i=0;
		for(String term:glos)
			terms[i++]=term;
		
		Arrays.sort(terms, new Comparator<String>() {

			@Override
			public int compare(String s0, String s1) {
				//compare by String length
				return s1.length()-s0.length();
			}
		});
	}

	@Override
	public void attachStructure(CrosswordStructure structure) {
		this.structure=structure;
		if(glossary!=null)
			updateAvaliablePositions();
	}

	@Override
	public Collection<CrosswordEntry> getCrosswordEntries() {
		return enteries.values();
	}

}
