import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
	 * If the input List of Sentences is null or is empty, the allWords method should return an 
	 * empty Set.
	 * 
	 * If a Sentence object in the input List is null, this method should ignore it and process 
	 * the non-null Sentences.
	 */ 
	public static Set<Word> allWords(List<Sentence> sentences) {
		Set<Word> result = new HashSet<Word>();
		
		// Handle base cases: null or empty list => empty set
		if (sentences == null || sentences.isEmpty()) return result;
		
		// Create holder array for all Word objects
		ArrayList<Word> wordsHolder = new ArrayList<Word>();
		
		// Loop through each sentence and add each word to the holder
		for (Sentence current : sentences) {
			
			if (current == null) continue;
			
			// Split up all the words
			String[] words = current.getText().split(" ");
			
			for (String word : words) {
				word = word.toLowerCase();
				
				// Filter out badly formatted words 
				if (!word.matches("^[a-z]+")) continue;
				
				// Create new instance of word, update the value & add to holding array
				Word newWord = new Word(word);
				newWord.increaseTotal(current.score);
				wordsHolder.add(newWord);
			}
		}
		
		// Check if any words have been added to the holding array
		if (wordsHolder.isEmpty()) return result;
		
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
	 * This method should iterate over each Word in the input Set, use the Word’s calculateScore 
	 * method to get the average sentiment score for that Word, and then place the text of 
	 * the Word (as key) and calculated score (as value) in a Map.
	 * 
	 * If the input Set of Words is null or is empty, the calculateScores method should return 
	 * an empty Map.
	 * 
	 * If a Word object in the input Set is null, this method should ignore it and process the
	 * non-null Words.
	 */
	
	public static Map<String, Double> calculateScores(Set<Word> words) {
		Map<String, Double> result = new HashMap<String, Double>();
		// Handle base cases
		if (words == null || words.isEmpty()) return result;
		
		for (Word word : words) {
			// Skip null or empty words
			if (word == null) continue;
			
			// Handle non-null words
			String text = word.getText();
			Double score = word.calculateScore();
			
			result.put(text, score);
		}
		
		return result; // this line is here only so this code will compile if you don't modify it

	}
	
	
	/*
	 * This method should use the Map to calculate and return the average score of all the words in 
	 * the input sentence.
	 * 
	 * Note that you will need to tokenize/split the sentence, as you did in Part 2.
	 * 
	 * If a word in the sentence does not start with a letter, then you can ignore it, but if it starts 
	 * with a letter and is not present in the Map, assign it a score of 0.
	 * 
	 * You may assume that all words in the Map consist only of lowercase letters (since this would have 
	 * been done in previous steps) but do not assume that all words in the input sentence consist only 
	 * of lowercase letters; you should convert them to lowercase before checking whether they’re contained 
	 * in the Map.
	 * 
	 * If the input Map is null or empty, or if the input sentence is null or empty or does not contain 
	 * any valid words, this method should return 0.
	 */
	
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
		double score = 0;
		
		// Handle base cases
		if (wordScores == null || wordScores.isEmpty()) return score;
		if (sentence == null || sentence.isEmpty()) return score;
		
		// Initialise counter and split sentence up by space
		int count = 0;
		String[] words = sentence.split(" ");
		
		for (String word : words) {
			double wordScore = 0;
			word = word.toLowerCase();
			
			// Check if word starts with a lower case letter
			if (!word.matches("^[a-z]+")) continue;
			
			// Get word score
			if (wordScores.containsKey(word)) {
				wordScore = wordScores.get(word);
			}
				
			score = score + wordScore;
			count++;
		}
		
		// Calculate average
		return score > 0 ? score / count : 0;
	}
	
	/*
	 * This method is here to help you run your program.
	 * You may modify it as needed.
	 */
	public static void main(String[] args) {
		allWords(readFile("./test6.txt"));
	}
}
