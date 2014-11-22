package cracking.the.coding.interview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Question_17_14_unconcatenating {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question_17_14_unconcatenating instance = new Question_17_14_unconcatenating();
		
		HashSet<String> set = new HashSet<String>();
		set.add("looked");
		set.add("just");
		set.add("like");
		set.add("her");
		set.add("brother");
		
		System.out.println(instance.parse(set, "jesslookedjustliketimherbrother"));
		
	}
	
	class Result {
		List<String> words = new ArrayList<>();
		int unrecognized = 0;
		public Result clone() {
			Result c = new Result();
			c.words.addAll(words);
			c.unrecognized = unrecognized;
			return c;
		}
		
		public void add(String word) {
			words.add(0, word);
		}
		
		public void setUnrecognized(int unrec) {
			unrecognized = unrec;
		}
		
		public String toString() {
			return words.toString();
		}
	}

	Result parse(HashSet<String> dict, String document) {
		if (document == null || document.length() == 0) 
			return new Result();
		int len = document.length();
		Result[] results = new Result[len];
		for (int i=len-1; i>=0; i--) {
			int minUnrecognized = len-i, minEnd = len-1;
			for (int j=i; j<len; j++) {
				int unRec = 0;
				if (j < len-1) {
					unRec = results[j+1].unrecognized;
				}
				if (!dict.contains(document.substring(i, j+1))) {
					unRec += j-i+1;
				}
				if (unRec <= minUnrecognized) {
					minUnrecognized = unRec;
					minEnd = j;
				}
			}
			if (minEnd >= len-1) 
				results[i] = new Result();
			else 
				results[i] = results[minEnd+1].clone();
			String word = document.substring(i, minEnd+1);
			if (dict.contains(word)) {
				results[i].add(word);
			} else {
				results[i].add(word.toUpperCase());
			}
			results[i].setUnrecognized(minUnrecognized);
		}
		return results[0];
	}
}
