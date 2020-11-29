import java.util.Queue;
import java.util.Stack;

/*
 * SD2x Homework #2
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

public class HtmlValidator {
	
	/**
	 * In HtmlValidator.java, implement the isValidHtml method. isValidHtml should take as input a 
	 * Queue of HtmlTags and return a Stack of HtmlTags that verifies the correctness of the tag structure, 
	 * according to the specification described below.
	 * 
	 * The method should be implemented as follows:
	 * 	#1: Tags closed in incorrect order
	 * 	#2: Closing tag with no opening tag
	 * 	#3: Opening tag never closed
	 * 	#4 (the tricky part!): Closing tag with no opening tag, everything okay until then
	 * 
	 * If the HTML file is not well formatted, the method should return the Stack in its current state (i.e., with its current values) 
	 * at the time the code determined that the tags were not balanced. Otherwise, the Stack should be empty
	 */
	
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
		// Handle base cases: Queue.isEmpty() or first tag is invalid
		if (tags.isEmpty()) {
			return null;
		}
		
		if (!tags.peek().isOpenTag() && !tags.peek().isSelfClosing()) {
			return null;
		}
		
		// Instantiate Stack
		Stack<HtmlTag> htmlStack = new Stack<HtmlTag>();
		
		// Iterate through Queue of tags
		while (!tags.isEmpty()) {
			HtmlTag currentTag = tags.remove();
			
			// Check if currentTag is a closing tag
			if (!currentTag.openTag && !currentTag.isSelfClosing()) {
				// If the stack is empty and the currentTag is closing return null (invalid html)
				if (htmlStack.isEmpty()) {
					return null;
				}
				
				// Get the tag at the top of the stack
				HtmlTag stackTop = htmlStack.peek();
				
				// If the closing tag doesn't match the one at the top of the stack,
				// return current Stack (break from while loop)
				if (!currentTag.matches(stackTop)) {
					break;
				}
				
				// If closing tag matches, then remove opening tag from Stack
				htmlStack.pop();
				continue;
			}
			
			// Skip over any selfClosing tags
			if (currentTag.isSelfClosing()) {
				continue;
			}
			
			// Push opening tags to the Stack
			htmlStack.push(currentTag);
		}
		
		// Return empty Stack if valid, or current Stack if invalid
		return htmlStack; 
	}
	

}

