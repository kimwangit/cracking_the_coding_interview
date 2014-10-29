package cracking.the.coding.interview;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Question_9_6_parentheses {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printParens(3);
	}
	
	static List<String> printParentheses(int n) {
		List<String> allCombs = new ArrayList<>();
		Hashtable<String, Boolean> map = new Hashtable<>();
		if (n == 0) {
			return allCombs;
		} else if (n == 1) {
			allCombs.add("()");
			return allCombs;
		} 
		List<String> allSubCombs = printParentheses(n-1);
		for (String comb : allSubCombs) {
			String comb1 = "(" + comb + ")";
			String comb2 = "()" + comb;
			String comb3 = comb + "()";
			if (!map.containsKey(comb1)) {
				allCombs.add(comb1);
				map.put(comb1, true);
			}
			if (!map.containsKey(comb2)) {
				allCombs.add(comb2);
				map.put(comb2, true);
			}
			if (!map.containsKey(comb3)) {
				allCombs.add(comb3);
				map.put(comb3, true);
			}
		}
		return allCombs;
	}
	
	public static void printParens(int n) {
		char[] comb = new char[2*n];
		printParens(comb, 0, n, n);
	}
	
	public static void printParens(char[] comb, int n, int leftRemains, int rightRemains) {
		if (leftRemains == 0 && rightRemains == 0) {
			String ans = String.copyValueOf(comb);
			System.out.println(ans);
			return ;
		}
		if (leftRemains > 0) {
			comb[n] = '(';
			printParens(comb, n+1, leftRemains-1, rightRemains);
		}
		
		if (rightRemains > leftRemains) {
			comb[n] = ')';
			printParens(comb, n+1, leftRemains, rightRemains-1);
		}
	}

}
