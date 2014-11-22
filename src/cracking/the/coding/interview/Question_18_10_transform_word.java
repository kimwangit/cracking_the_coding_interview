package cracking.the.coding.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Question_18_10_transform_word {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet<String> dict = new HashSet<>();
		dict.add("DAMP");
		dict.add("LAMP");
		dict.add("LIMP");
		dict.add("LIME");
		dict.add("LIKE");

		transform("DAMP", "LIKE", dict);
	}
	
	static void transform(String source, String target, HashSet<String> dict) {
		if (source == null || target == null || source.length() != target.length() || source.length()==0) {
			System.out.println("Impossible");
			return;
		}
		HashMap<String, String> cache = new HashMap<>();
		List<String> lastLevel = new ArrayList<>();
		lastLevel.add(source);
		cache.put(source, source);
		boolean found = false;
		while (lastLevel.size() > 0 && !found) {
			List<String> newLevel = new ArrayList<>();
			for (String word : lastLevel) {
				if (word.equals(target)) {
					found = true;
					break;
				}
				char[] array = word.toCharArray();
				for (int i=0; i<word.length(); i++) {
					char old = array[i];
					for (char ch = 'A'; ch <= 'Z'; ch++) {
						array[i] = ch;
						String newWord = String.valueOf(array);
						if (dict.contains(newWord) && !cache.containsKey(newWord)) {
							cache.put(newWord, word);
							newLevel.add(newWord);
						}
					}
					array[i] = old;
				}
			}
			lastLevel = newLevel;
		}
		
		if (!found) {
			System.out.println("Impossible");
			return;
		}
		ArrayList<String> result = new ArrayList<>();
		String word = target;
		result.add(word);
		while (!word.equals(source)) {
			word = cache.get(word);
			result.add(word);
		}
		for (int i=result.size()-1; i>0; i--) {
			System.out.print(result.get(i) + "->");
		}
		System.out.println(result.get(0));
	}

}
