import java.util.Scanner;
import java.util.List;

/*
 * SD2x Homework #8
 * This class represents the Presentation Tier in the three-tier architecture.
 * Implement the appropriate methods for this tier below. 
 * Also implement the start method as described in the assignment description.
 */

public class PresentationTier {
	
	private LogicTier logicTier; // link to the Logic Tier
	private Scanner scanner = new Scanner(System.in);;
	
	public PresentationTier(LogicTier logicTier) {
		this.logicTier = logicTier;
	}
	
	public void start() {
		String choiceInput = _getUserInput("Choose a mode (with number):\n1:Book titles by author\n2:Number of books in a given year\n3:Test");
		int choice = Integer.parseInt(choiceInput);
		switch(choice) {
			case 1:
				String authorChoice = _getUserInput("Search an author's name:");
				showBookTitlesByAuthor(authorChoice);
				break;
				
			case 2:
				int yearChoice = Integer.parseInt(_getUserInput("Search a year:"));
				showNumberOfBooksInYear(yearChoice);
				break;
				
			case 3:
				showBookTitles();
		}
		scanner.close();
	}
	
	private String _getUserInput(String msg) {
		System.out.println(msg);
		return scanner.nextLine();
	}
	
	public void showBookTitlesByAuthor(String name) {
		List<String> titles = logicTier.findBookTitlesByAuthor(name);
		for ( String title : titles) {
			System.out.println(title);
		}	
	}
	
	public void showNumberOfBooksInYear(int year) {
		int numberOfBooks = logicTier.findNumberOfBooksInYear(year);
		System.out.println(numberOfBooks);
	}
	
	public void showBookTitles() {
		List<String> titles = logicTier.getAllBookTitles();
		System.out.println(titles.toString());
	}
}
