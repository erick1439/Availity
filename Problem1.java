// Author: Erick Pena

import java.util.*;

public class Problem1 {

    public static boolean checkParenteses(String s) {
        
        // If we have a empty string we wil return true
        if (s == null || s.length() == 0)
            return true;

        Stack<Character> stack = new Stack<>();

        // We will process every character in the string
        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            // Every opening parentheses will go into our stack, since we dont know if we will see a matching
            // closing paretheses later on
            if (c == '(' || c == '[' || c == '{')
                stack.add(c);

            else {

                // If we have a closing parentheses, but our stack is empty we return false since this current
                // parentheses does not have a matching opening pair
                if (stack.isEmpty())
                    return false;
                
                // If our closing parentheses is not cancelling the correct opening paretheses we will return
                // false
                if (c == ')' && stack.pop() != '(')
                    return false;
                
                else if (c == ']' && stack.pop() != '[')
                    return false;
                
                else if (c == '}' && stack.pop() != '{')
                    return false;

            }
        }

        // If our stack is empty at the end it means that all opening parentheses had a matching closing parentheses
        // and we return true, other wise we return false
        return stack.isEmpty();
    }

    public static void main(String [] args) {

        List<String> testCases = new ArrayList<>();
        testCases.add(")))()(");
        testCases.add("((([])))");
        testCases.add("[]()()");
        testCases.add("[[((()))]]");
        testCases.add("(({()]))");

        for (String temp : testCases) {
            System.out.println("Test case value: " + temp);
            System.out.println("Result: " + checkParenteses(temp));
            System.out.println();
        }
    }
}