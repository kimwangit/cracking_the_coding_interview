package poj;

import java.util.Scanner;

public class ID1850_Code{

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String source = in.nextLine();
		int LEN = source.length();
		if (LEN < 1 || LEN > 10) {
			System.out.println("0");
			return ;
		}
		int pre = 'a'-1;
		for (int i=0; i<LEN; i++) {
			if (source.charAt(i) <= pre) {
				System.out.println("0");
				return ;
			}
			pre = source.charAt(i);
		}
		int[][] dp = new int[LEN][27];
		for (int start = 0; start < 26; start++) {
			dp[0][start]=1;
		}
		dp[0][26]=26;
		for (int len=1; len<LEN; len++) 
			for (int start=0; start<26; start++) {
				for (int next=start+1; next<26; next++) 
					dp[len][start] += dp[len-1][next];
				dp[len][26] += dp[len][start];	
			}
		long num = 0;
		for (int len=0; len<LEN-1; len++)
			num += dp[len][26];
		for (int i=pre=0; i<LEN; i++) {
			int cur = source.charAt(i)-'a';
			for (int start=pre; start<cur; start++)
				num += dp[LEN-1-i][start];
			pre=cur+1;
		}
		num++;
		System.out.println(num);
	}
}
