import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import java.io.File;

/*
 * SD2x Homework #3
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */
public class Analyzer {
	
	/*
	 * Implement this method in Part 1
	 * 
	 * This method should take the name of the file to read and read it one line at a time, 
	 * creating Sentence objects and putting them into the List. Note that the method returns 
	 * a List containing Sentence objects.
	 * 
	 * Your code should ignore (and not create a Sentence object for) any line that is not well-formatted, 
	 * which we assume to mean “starts with an int between -2 and 2 (inclusive), has a single 
	 * whitespace, and then is followed by more text.” 
	 * 
	 * However, if the file cannot be opened for reading or if the input filename is null, 
	 * this method should return an empty List.
	 */
	public static List<Sentence> readFile(String filepath)  {
		List<Sentence> result = new ArrayList<Sentence>(); 
		
		// Try to read file or return
		try {
			// Instantiate objects
			File file = new File(filepath);
			Scanner scanner = new Scanner(file);			
			
			// Loop through all lines
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				
				// Skip empty lines
				if (line.isBlank() || line.isEmpty()) {
					continue;
				}
				
				// Create ArrayList from the current line
				List<String> lineList = Arrays.asList(line.split(" "));
				lineList = new ArrayList<String>(lineList);
				
				// Skip line if rating is out of range
				int ratingInt = tryParseInt(lineList.remove(0));
				
				if (ratingInt < -2 || ratingInt > 2 || lineList.isEmpty()) {
					continue;
				}
				
				// Join rest of the line back together
				String restOfLine = String.join(" ", lineList);
			
				// Add to results lists
				result.add(new Sentence(ratingInt, restOfLine));
			}
			// Close file scanner
			scanner.close();
		} catch(Exception e) {
			return result;
		}
		

		// Return result list
		return result;

	}
	
	// Helper function to try parsing an integer (so as not to nest try catch blocks)
	private static int tryParseInt (String string) {
		try {
			return Integer.parseInt(string);
		} catch(Exception e) {
			// Return a number outside what we want, akin to returning null
			return -10;
		}
	}
	
	
	/*
	 * Implement this method in Part 2
	 * 
	 * This method should find all of the individual tokens/words in the text field of each 
	 * Sentence in the List and create Word objects for each distinct word. The Word objects 
	 * should keep track of the number of occurrences of that word in all Sentences, and the 
	 * total cumulative score of all Sentences in which it appears. This method should then 
	 * return a Set of those Word objects.
	 * 
	 * If the input List of Sentences is null or is empty, the allWords method should return an empty Set.
	 * 
	 * If a Sentence object in the input List is null, this method should ignore it and process the non-null Sentences.
	 */ 
	public static Set<Word> allWords(List<Sentence> sentences) {
		Set<Word> result = new HashSet<Word>();
		
		// Handle base cases: null or empty list => empty set
		if (sentences == null || sentences.isEmpty()) {
			return result;
		}
		
		// Create holder array for all Word objects
		ArrayList<Word> wordsHolder = new ArrayList<Word>();
		
		// Loop through each sentence and add each word to the holder
		for (Sentence current : sentences) {
			
			if (current == null) {
				continue;
			}
			
			// Split up all the words
			String[] words = current.getText().split(" ");
			
			for (String word : words) {
				word = word.toLowerCase();
				
				// Filter out badly formatted words 
				if (!word.matches("(^[a-z])([a-z]*)")) {
					continue;
				}
				
				// Create new instance of word, update the value & add to holding array
				Word newWord = new Word(word);
				newWord.increaseTotal(current.score);
				wordsHolder.add(newWord);
			}
		}
		
		// Check if any words have been added to the holding array
		if (wordsHolder.isEmpty()) {
			return result;
		}
		
		// Loop through holding array and add each distinct word to the result
		while (!wordsHolder.isEmpty()) {
			Word currentWord = wordsHolder.get(0);
			
			// Make sure holding array is long enough 
			if (wordsHolder.size() > 1) {
				for (int i = 1; i < wordsHolder.size(); i++) {
					if (currentWord.getText() == wordsHolder.get(i).getText()) {
						currentWord.increaseTotal(wordsHolder.get(i).getTotal());
						// Remove matching words from the holding array
						wordsHolder.remove(i);
					}
				}
			}
			
			// Add to results set and remove from holding array
			result.add(currentWord);
			wordsHolder.remove(0);
		}
		
		return result;

	}
	
	/*
	 * Implement this method in Part 3
	 */
	public static Map<String, Double> calculateScores(Set<Word> words) {

		/* IMPLEMENT THIS METHOD! */
		
		return null; // this line is here only so this code will compile if you don't modify it

	}
	
	/*
	 * Implement this method in Part 4
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {

		/* IMPLEMENT THIS METHOD! */
		
		return 0; // this line is here only so this code will compile if you don't modify it

	}
	
	/*
	 * This method is here to help you run your program. Y
	 * You may modify it as needed.
	 */
	public static void main(String[] args) {
		allWords(readFile("./test6.txt"));
	}
}
