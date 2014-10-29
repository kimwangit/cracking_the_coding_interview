package cracking.the.coding.interview;

import java.util.ArrayList;
import java.util.List;

public class Question_9_5_permutations {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question_9_5_permutations instance = new Question_9_5_permutations();
		System.out.println(instance.getPermutations("123"));
	}
	
	List<List<Character>> getPermutations(String a) {
		return getPermutations(a, a.length());
	}

	List<List<Character>> getPermutations(String a, int n) {
		List<List<Character>> result = new ArrayList<>();
		if (n == 0) {
			List<Character> emptyList = new ArrayList<>();
			result.add(emptyList);
			return result;
		} 
		List<List<Character>> preResult = getPermutations(a, n-1);
		for (List<Character> list : preResult) {
			for (int i=n-1; i>=0; i--) {
				List<Character> nList = new ArrayList<>(list);
				nList.add(i, a.charAt(n-1));
				result.add(nList);
			}
		}
		return result;
	}

}
