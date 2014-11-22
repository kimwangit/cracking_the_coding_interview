package cracking.the.coding.interview;

import java.util.Arrays;
import java.util.List;

public class Question_18_7_get_longest_word {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question_18_7_get_longest_word instance = new Question_18_7_get_longest_word();
		
		String[] words = new String[]{"cat", "banana", "dog", "nana", "walk", "walker", "dogwalker"};
		System.out.println(instance.getLongestWord(Arrays.asList(words)));
	}
	
	class TrieTree {
		boolean isWord = false;
		TrieTree[] branches = new TrieTree[26];
		
		void addBranch(String word, int start) {
			if (start == word.length()) {
				isWord = true;
				return ;
			}
			int index = word.charAt(start) - 'a';
			if (branches[index] == null) branches[index] = new TrieTree();
			branches[index].addBranch(word, start+1);
		}
		
		TrieTree getBranch(String word, int start) {
			int index = word.charAt(start) - 'a';
			return branches[index];
		}
		
		boolean getWordState() {
			return isWord;
		}
	}

	String getLongestWord(List<String> words) {
		if (words == null || words.size() == 0) return null;
		TrieTree root = new TrieTree();
		for (String word : words)	root.addBranch(word, 0);
		int ans = 0;
		String target = "";
		for (String word : words) {
			TrieTree node = root;
			int last = -1;
			int i = 0;
			while (i < word.length()) {
				node = node.getBranch(word, i);
				if (node == null || (i == word.length()-1 && !node.getWordState())) {
					if (last > 0) { 
						i = last;
						last = -1;
						node = root;
					} else {
						break;
					}
				} else if (node.getWordState()){
					 last = i;
				}
				i++;
			}
			if (i == word.length() && word.length() > ans) {
				ans = word.length();
				target = word;
			}
		}
		return target;
	}

}
