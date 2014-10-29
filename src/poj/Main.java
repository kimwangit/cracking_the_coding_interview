package poj;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String strA = scanner.nextLine();
		String strB = scanner.nextLine();
		if (strA == null || strA.trim().equals("")) {
			System.out.println(0);
			return ;
		}
		if (strB == null || strB.trim().equals("")) {
			System.out.println(0);
			return ;
		}
		
		int lenA = strA.length(), lenB = strB.length();
		int[][] dp = new int[lenB][2];
		int ans = 0;
		int r = 0, lastA = -1, lastB = -1, lastMatch = 0;
		for (int i=0; i<lenA; i++) {
			int candidate = 0;
			int j=0;
			for (; j<lenB; j++) {
				int old = 0;
				if (i > 0 && j > 0) old = dp[j-1][r];
				if (strA.charAt(i) == strB.charAt(j)) {
					dp[j][r^1] = old + 1;
					if (i > 0 && j > 0 && lastA == i-1 && lastB == j-1) {
						candidate = lastMatch + 1;
						break;
					}
				} else {
					dp[j][r^1] = 0;
					if (candidate >= 3) break;
				}
				 
				int revised1 = Math.max(lastB - (j-dp[j][r^1]), 0);
				int revised2 = Math.max(lastA - (i-dp[j][r^1]), 0);
				int revised = Math.max(revised1, revised2);
				if (dp[j][r^1] - revised >= 3) {
					candidate = dp[j][r^1] - revised;
				}
			}
			if (candidate >= 3) {
				if (i > 0 && j > 0 && lastA == i-1 && lastB == j-1) ans -= lastMatch;
				ans += candidate;
				lastMatch = candidate;
				lastA = i;
				lastB = j;
				System.out.println("i:" + i +",j:" + j);
			}
			r = r^1;
		}
		System.out.println(ans);
	}
}
