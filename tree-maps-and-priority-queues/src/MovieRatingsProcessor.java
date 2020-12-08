/*
 * SD2x Homework #5
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
		List<String> result = new ArrayList<String>();
		if (movieRatings == null || movieRatings.isEmpty()) return result;
		// Add titles and remove them from treeMap until not empty
		while (!movieRatings.isEmpty()) {
			String current = movieRatings.firstKey();
;			// Only add to result list if above minimum rating
			if (movieRatings.get(current).peek() > Integer.valueOf(rating)) {
				result.add(current);				
			}
			movieRatings.remove(current);
		}
		return result;
}
	
	public static TreeMap<String, Integer> removeAllRatingsBelow(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		TreeMap<String, Integer> result = new TreeMap<String, Integer>();
		// Handle base cases: null or empty input TreeMap
		if (movieRatings == null || movieRatings.isEmpty()) return result;
		// Create holding ArrayList for the keys to remove
		List<String> keysToRemove = new ArrayList<String>();
		// Loop through each movie
		for (Entry<String, PriorityQueue<Integer>>  movie : movieRatings.entrySet()) {
			int count = 0;
			// Check the first (lowest) review to see if any need to be added
			if (movie.getValue().peek() < rating) {
				// Remove all ratings below the minimum rating input
				while(!movie.getValue().isEmpty() && movie.getValue().peek() < rating) {
					count = count + 1;
					movie.getValue().remove();
				}
				// Add to the result
				result.put(movie.getKey(), count);
				// Add the keys of completely empty PriorityQueues to the keysToRemove array
				if (movie.getValue().isEmpty()) {
					keysToRemove.add(movie.getKey());
				}
			}
		}	
		// Remove keys from movieRatings
		for (String key : keysToRemove) {
			movieRatings.remove(key);
		}
		return result;
	}
}
