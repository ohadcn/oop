package oop.ex3.crosswords;

import java.io.IOException;
import java.util.Set;

/**
 * Represents a glossary that keeps words and their matching definitions.
 * @author OOP
 */
public interface CrosswordGlossary {
	
	/**
	 * Given a term, the method returns the matching definition.
	 * 
	 * @param term The given term
	 * @return The definition string
	 */
	String getTermDefinition(String term);

	/**
	 * Retrieves the set of terms in the glossary.
	 * @return the set of terms in the glossary.
	 */
	Set<String> getTerms();

	/**
	 * returns the length of the longest term in this glossary
	 * 
	 * @return the length of the longest term in this glossary
	 */
	int maxTermSize();
	
	/**
	 * returns the total length of all the terms in this glossary
	 * 
	 * @return the total length of all the terms in this glossary
	 */
	int allTermsLength();
	
	/**
	 * Loads glossary from a text file. The glossary format is defined in ex3 pdf.
	 * 
	 * @param glossFileName name of a glossary file.
	 * @throws IOException on any I/O error.
	 */
	void load(String glossFileName) throws IOException;
}
