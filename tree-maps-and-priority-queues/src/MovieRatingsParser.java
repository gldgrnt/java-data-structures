/*
 * SD2x Homework #5
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MovieRatingsParser {
	
	/*
	 * If the input List is null or empty, parseMovieRatings should return an empty TreeMap
	 * 
	 * If the input List contains any null UserMovieRatings object, or a UserMovieRatings 
	 * object whose movie title is null or an empty string, or a UserMovieRatings object 
	 * whose rating is negative, parseMovieRatings should ignore that UserMovieRatings object
	 * 
	 * The movie titles should be considered case-insensitive, i.e. if two UserMovieRatings 
	 * objects have the same title that differ only in case (upper or lower), they should be 
	 * considered the same movie. The movie titles stored in the TreeMap must use lower case letters.
	 */
	
	public static TreeMap<String, PriorityQueue<Integer>> parseMovieRatings(List<UserMovieRating> allUsersRatings) {
		TreeMap<String, PriorityQueue<Integer>> result = new TreeMap<String, PriorityQueue<Integer>>();
		// Handle base cases
		if (allUsersRatings == null || allUsersRatings.isEmpty()) return result;
		// Loop through and add distinct / compliant UserMovieRatings to the TreeMap
		for (UserMovieRating current : allUsersRatings) {
			// Skip any unusable UserMovieRatings
			boolean shouldSkip = current == null || current.movie == null || current.movie.isEmpty() || current.userRating < 0;
			if (shouldSkip) continue;
			// Create placeholder objects to be added to the result
			String title = current.movie.toLowerCase();
			Integer rating = Integer.valueOf(current.userRating);
			// If movie already in the result, add to it's rating min-heap
			if (result.containsKey(title)) {
				result.get(title).add(rating);
			} 
			// If not add a new key value pair
			else {
				PriorityQueue<Integer> ratings = new PriorityQueue<Integer>();
				ratings.add(rating);
				result.put(title, ratings);
			}
		}
			
		return result;
	}

}
