package google.code.jam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Question_broken_calculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question_broken_calculator  solution = new Question_broken_calculator();
		int[] keys = new int[] {0, 1, 1, 0, 0, 1, 0, 0, 0, 0};
		HashMap<Integer, Integer> cache = new HashMap<>();
		
//		System.out.println(solution.getMinClick(60, keys, cache) + 1);
		
		solution.readData();
	}
	
	public void readData() {
//		String inFile = "E:\\C-small-practice.in";
//		String outFile = "E:\\C-small-practice.out";
		String inFile = "E:\\C-large-practice.in";
		String outFile = "E:\\C-large-practice.out";
//		String inFile = "E:\\test.txt";
//		String outFile = "E:\\test.out";
		try {
			Scanner in = new Scanner(new File(inFile));
			PrintWriter out = new PrintWriter(new File(outFile));
			int T = in.nextInt();
			for (int caseId = 1; caseId <= T; caseId++) {
				out.print("Case #" + caseId + ": ");
				int[] keys = new int[10];
				for (int i=0; i<10; i++)
					keys[i] = in.nextInt();
				int x = in.nextInt();
				HashMap<Integer, Integer> cache = new HashMap<>();
				int ans = getMinClick(x, keys, cache);
				if (ans < Integer.MAX_VALUE) 
					out.println(ans+1);
				else
					out.println("Impossible");
			}
			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 	
	int getMinClick(int x, int[] keys, HashMap<Integer, Integer> cache) {
		if (cache.containsKey(x)) return cache.get(x);
		int direct = getDirect(x, keys);
		if (direct > 0)  return direct;
		int ans = Integer.MAX_VALUE;
		for (int i=2; i*i<=x; i++) {
			if (x%i != 0) continue;
			int factor1 = getMinClick(i, keys, cache);
			int factor2 = getMinClick(x/i, keys, cache);
			if (factor1 < ans && factor2 < ans && factor1 + factor2 + 1< ans) { 
				ans = factor1 + factor2 + 1;
			}
		}
		cache.put(x, ans);
		return ans;
	}

	int getDirect(int x, int[] keys) {
		int ans = 0;
		while (x > 0) {
			int lastBit = x % 10;
			if (keys[lastBit] == 0) return -1;
			x = x/10;
			ans++;
		}
		return ans;
	}

}
