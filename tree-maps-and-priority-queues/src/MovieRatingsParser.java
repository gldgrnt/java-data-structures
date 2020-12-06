/*
 * SD2x Homework #5
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MovieRatingsParser {

	public static TreeMap<String, PriorityQueue<Integer>> parseMovieRatings(List<UserMovieRating> allUsersRatings) {
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
		
		return null; // this line is here only so this code will compile if you don't modify it
	}

}
