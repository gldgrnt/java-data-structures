import java.util.LinkedList;

/*
 * SD2x Homework #1
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 * 
 * run:
 * 1: javac LinkListUtils.class
 * 2: java -cp .:junit-dist.jar:homework1-tests.jar Homework1Grader
 */

public class LinkedListUtils {

    /**
     * This method assumes the input LinkedList is already sorted in non-descending order (i.e.,such 
     * that each element is greater than or equal to the one that is before it, and inserts the input 
     * int value into the correct location of the list. Note that the method does not return anything, 
     * but rather modifies the input LinkedList as a side effect. 
     * If the input LinkedList is null, this method should simply terminate.
     *
     */
	public static void insertSorted(LinkedList<Integer> list, int value) {
		// Handle list being null
		if (list == null) {
			return;
		}
		
		// Handle list being empty
		if (list.isEmpty()) {
			list.addFirst(value);
		} 
		// handle placing first 
		else if (list.getFirst() >= value) {
			list.addFirst(value);
		}
		// handle placing last
		else if (list.getLast() <= value) {
			list.addLast(value);
		} 
		// Place somewhere in the middle
		else { 
			// Loop through all (except for first and last)
			for (int i = 1; i < list.size(); i++) {
				int current = list.get(i);
				// only place if current is less than or equal
				if (current <= value) {
					// determine placing index
					int indexToPlace = current == value ? i : i + 1;
					list.add(indexToPlace, value);
					break;
				}
			}
		}
	}
    
    /**
     * This method removes all instances of the N largest values in the LinkedList. Because the values are 
     * Strings, you will need to use the String class’ compareTo method to find the largest elements; see the 
     * Java API for help with that method. If the input LinkedList is null or if N is non-positive, this method 
     * should simply return without any modifications to the input LinkedList. Keep in mind that if any of the N 
     * largest values appear more than once in the LinkedList, this method should return remove all instances, so 
     * it may remove more than N elements overall. The other elements in the LinkedList should not be modified and 
     * their order must not be changed.
     */
	public static void removeMaximumValues(LinkedList<String> list, int N) {
		// Handle base cases: list = null & N < 1 & empty list
		if (list == null || N <  1 || list.isEmpty()) {
			return;
		}
		
		// Return a cleared list if we want to remove more items than there are in the list
		if (N >= list.size()) {
			list.clear();
			return;
		}
		
		// Create currentLargest value placeholder and i for loop count
		String currentLargest = null; 
		int i = 0;
		
		// Loop through and remove N largest items
		while (i < N) {
			// Loop through once to find the currentLargest String value
			for (int j = 0; j < list.size(); j++) {
				if (currentLargest == null || list.get(j).length() > currentLargest.length()) {
					currentLargest = list.get(j);
				}
			}
			
			// Remove all occurrences of that string 
			while (list.indexOf(currentLargest) != -1) {
				list.remove(currentLargest);
			}
			
			// Set currentLargest back to null once all occurrences are removed
			currentLargest = null;
			
			// Increment i
			i++;
		}
	}

    
    /**
     * This method determines whether any part of the first LinkedList contains all elements of the second in 
     * the same order with no other elements in the sequence, i.e. it should return true if the second 
     * LinkedList is a subsequence of the first, and false if it is not. The method should return false 
     * if either input is null or empty.
     */
	public static boolean containsSubsequence(LinkedList<Integer> one, LinkedList<Integer> two) {

		/* IMPLEMENT THIS METHOD! */
		
		return true; // this line is here only so this code will compile if you don't modify it
	}
}
