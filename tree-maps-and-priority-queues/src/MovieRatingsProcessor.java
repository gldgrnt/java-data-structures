/*
 * SD2x Homework #5
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;


public class MovieRatingsProcessor {

	public static List<String> getAlphabeticalMovies(TreeMap<String, PriorityQueue<Integer>> movieRatings) {
		List<String> result = new ArrayList<String>();
		if (movieRatings == null || movieRatings.isEmpty()) return result;
		// Add titles and remove them from treeMap until not empty
		while (!movieRatings.isEmpty()) {
			String current = movieRatings.firstKey();
			result.add(current);
			movieRatings.remove(current);
		}
		return result;
	}

	public static List<String> getAlphabeticalMoviesAboveRating(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		
		return null; // this line is here only so this code will compile if you don't modify it
	}
	
	public static TreeMap<String, Integer> removeAllRatingsBelow(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		
		return null; // this line is here only so this code will compile if you don't modify it
	}
}
