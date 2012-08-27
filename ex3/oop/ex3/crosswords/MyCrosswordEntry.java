package oop.ex3.crosswords;

/**
 * this class implements a CrosswordEntry
 * a CrosswordEntry composed of a term, definition and position on the board
 * @author Ohad Cohen <ohadcn@cs.huji.ac.il>
 */
public class MyCrosswordEntry implements CrosswordEntry {

	
	private String definition;
	private String term;
	private CrosswordPosition position;

	/**
	 * construct a new MyCrosswordEntry object
	 * @param term the term of the entry
	 * @param definition the definition of the term
	 * @param position the position of the term on the board
	 */
	public MyCrosswordEntry(String term,String definition,CrosswordPosition position) {
		this.definition=definition;
		this.term=term;
		this.position=position;
	}
	
	@Override
	public CrosswordPosition getPosition() {
		return position;
	}

	@Override
	public String getDefinition() {
		return definition;
	}

	@Override
	public String getTerm() {
		return term;
	}

	@Override
	public int getLength() {
		return term.length();
	}

}
