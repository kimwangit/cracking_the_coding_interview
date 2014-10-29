package poj;

import java.util.Arrays;
import java.util.Scanner;

public class ID2513_ColoredSticks {
	private static final int MAX = 250002;
	private static int[] f = new int[MAX];
	private static int[] d = new int[MAX];

	public static void main(String[] args) {
		TrieTree root = new TrieTree();
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String line = in.nextLine().trim();
            if (line.equals("")) break;
			String[] words = line.split("\\s+");
			System.out.println(Arrays.toString(words));
			int id1 = root.insert(words[0], 0);
			int id2 = root.insert(words[1], 0);
			d[id1]++;
			d[id2]++;
			union(id1, id2);
		}
		int rid=0;
		int num=0;
		for (int i=1; i<=root.counter; i++) {
			if (d[i]%2 == 1) num++;
			int parent = find(i);
			if (rid == 0) rid = parent;
			else if (rid != parent) {
				System.out.println("Impossible");
				return ;
			}
		}
		if (num == 0 || num == 2) System.out.println("Possible");
		else System.out.println("Impossible");
	}
	
	private static int find(int x) {
		while (f[x] > 0)
			x = f[x];
		return x;
	}
	
	private static void union(int x, int y) {
		int fx = find(x);
		int fy = find(y);
		if (fx != fy)
			f[fy] = fx;
	}
	
	private static class TrieTree {
		public static int counter = 0;
		public TrieTree[] next = new TrieTree[26];
		public int id = 0;
		
		public int insert(String word, int index) {
			int idx = word.charAt(index)- 'a';
			TrieTree node = next[idx];
			if (node == null) {
				node = new TrieTree();
				next[idx] = node;
			}
			if (index == word.length()-1) {
				if (node.id == 0) {
					counter++;
					node.id=counter;
				}
				return node.id;
			} else {
				return node.insert(word, index+1);
			}
		}
	}
}
