package careercup.interview;

public class Question_max_length_palindrome_subsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] values = new int[] { 4, 1, 2, 3, 4, 5, 6, 5, 4, 3, 4, 4, 4, 4, 4,
				4, 4 };
		System.out.println(maxLengthPalindrome(values));
	}

	public static int maxLengthPalindrome(int[] values) {
		if (values == null || values.length == 0)
			return 0;
		int N = values.length;
		int[][] dp = new int[N][N];
		for (int len = 1; len <= N; len++)
			for (int start = 0; start <= N - len; start++) {
				int end = start + len - 1;
				if (values[start] == values[end]) {
					if (end - start > 1)
						dp[start][end] = 2 + dp[start + 1][end - 1];
					else if (end - start == 1)
						dp[start][end] = 2;
					else
						dp[start][end] = 1;
				} else {
					dp[start][end] = Math.max(dp[start][end - 1],
							dp[start + 1][end]);
				}
			}
		return dp[0][N - 1];
	}

}
