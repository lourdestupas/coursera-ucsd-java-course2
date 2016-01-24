package document;

import java.util.List;

/** 
 * A naive implementation of the Document abstract class. 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class BasicDocument extends Document 
{
	/** Create a new BasicDocument object
	 * 
	 * @param text The full text of the Document.
	 */
	public BasicDocument(String text)
	{
		super(text);
	}
	
	
	/**
	 * Get the number of words in the document.
	 * "Words" are defined as contiguous strings of alphabetic characters
	 * i.e. any upper or lower case characters a-z or A-Z
	 * 
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumWords()
	{
		//TODO: Implement this method.  See the Module 1 support videos 
	    // if you need help.
		List<String> words = getTokens("[a-zA-Z]+");
		
		/*
		System.out.println("");
		System.out.println("");
		System.out.println("*** words ***");
		for (String s : words) {
			System.out.println(s);
		}
		System.out.println("");
		*/
		
	    return words.size();
	}
	
	/**
	 * Get the number of sentences in the document.
	 * Sentences are defined as contiguous strings of characters ending in an 
	 * end of sentence punctuation (. ! or ?) or the last contiguous set of 
	 * characters in the document, even if they don't end with a punctuation mark.
	 * 
	 * @return The number of sentences in the document.
	 */
	@Override
	public int getNumSentences()
	{
	    //TODO: Implement this method.  See the Module 1 support videos 
        // if you need help.
		List<String> sentences = getTokens("[.!?]+");
		int numSentences = sentences.size();
	
		if ((this.getText().lastIndexOf(".") == this.getText().length() - 1) ||
			(this.getText().lastIndexOf("!") == this.getText().length() - 1) ||
			(this.getText().lastIndexOf("?") == this.getText().length() - 1)){
		}
		else
			numSentences++;
		
        return numSentences;
	}
	
	/**
	 * Get the number of syllables in the document.
	 * Words are defined as above.  Syllables are defined as:
	 * a contiguous sequence of vowels, except for a lone "e" at the 
	 * end of a word if the word has another set of contiguous vowels, 
	 * makes up one syllable.   y is considered a vowel.
	 * @return The number of syllables in the document.
	 */
	@Override
	public int getNumSyllables()
	{
	    //TODO: Implement this method.  See the Module 1 support videos 
        // if you need help.
		int numSyllables = 0;
		
		/*
		System.out.println("");
		
		//get tokens ending with a lone 'e'
		List<String> syllables = getTokens("[a-zA-Z^(aeiouyAEIOUY)]+e[.!? ]+");
		System.out.println("");
		System.out.println("*** syllables1 ***");
		for (String s : syllables) {
			System.out.println(s);
		}
		numSyllables = syllables.size();		
		
		syllables = getTokens("[aiouyAIOUY]+[ ]+");
		System.out.println("");
		System.out.println("*** syllables2 ***");
		for (String s : syllables) {
			System.out.println(s);
		}
		numSyllables += syllables.size();
		
		//syllables = getTokens("[aeiouyAEIOUY]+[a-zA-Z^(eE)]+[ .!?]");
		syllables = getTokens("[aeiouyAEIOUY]+[a-zA-Z^(aeiouyAEIOUY)]+[ .!?]");
		System.out.println("");
		System.out.println("*** syllables3 ***");
		for (String s : syllables) {
			System.out.println(s);
		}
		numSyllables += syllables.size();
		
		System.out.println("");
		*/
		
		/*
		System.out.println("");

		//get tokens ending with a lone 'e'
		syllables = getTokens(
				"[a-zA-Z^(aeiouyAEIOUY)]+e[ .!?]+"+
				"|[aeiouy]+[a-zA-Z^(aeiouyAEIOUY)]+[ .!?]*"+
				"|[aiouy]+[ ]+");
		System.out.println("syllables");
		for (String s : syllables) {
			System.out.println(s);
		}
		numSyllables = syllables.size();
		*/
		
		System.out.println("");
		System.out.println("");
		List<String> words = getTokens("[a-zA-Z]+");
		for	(String w : words) {			
			/*List<String> syllables = getTokens(w, "[aeiouyAEIOUY]+[a-zA-Z^(eE)]|"+
												  "[aeiouyAEIOUY]+[a-zA-Z^(aiouyAIOUY)]+|"+
												  "[aeiouyAEIOUY]+");*/

			List<String> syllables = getTokens(w, "[aeiouyAEIOUY]+");
			numSyllables += syllables.size();
			
			if (syllables.size() >= 2) {
				if (((w.lastIndexOf("e") == w.length()-1) || (w.lastIndexOf("E") == w.length()-1))
					&& 
					((syllables.lastIndexOf("e") == syllables.size()-1) || (syllables.lastIndexOf("E") == syllables.size()-1))) {
					numSyllables--;
				}
			}
			
			/*System.out.println("*** word: " + w);
			for (String s : syllables) {
				System.out.println("****** " + s);
			}*/
		}
		
        return numSyllables;
	}
	
	
	/* The main method for testing this class. 
	 * You are encouraged to add your own tests.  */
	public static void main(String[] args)
	{
		testCase(new BasicDocument("This is a test.  How many???  "
		        + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
				16, 13, 5);
		testCase(new BasicDocument(""), 0, 0, 0);
		testCase(new BasicDocument("sentence, with, lots, of, commas.!  "
		        + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
		testCase(new BasicDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2);
		testCase(new BasicDocument("Here is a series of test sentences. Your program should "
				+ "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
				+ "the correct amount of syllables (example, for example), "
				+ "but most of them will."), 49, 33, 3);
		testCase(new BasicDocument("Segue"), 2, 1, 1);
		testCase(new BasicDocument("Sentence"), 2, 1, 1);
		testCase(new BasicDocument("Sentences?!"), 3, 1, 1);
		testCase(new BasicDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."),
		         32, 15, 1);
		
		
	}
	
}
