package poj;

import java.util.Scanner;

public class ID3254_Corn_Fields1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int M = in.nextInt();
		int N = in.nextInt();
		int[] fertileState = new int[M];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				int isFertile = in.nextInt();
				fertileState[i] = (fertileState[i] << 1) + (isFertile ^ 1);
			}
		}

		int MAX_STATE = 1 << N;
		int[][] dp = new int[M][MAX_STATE];
		for (int state = 0; state < MAX_STATE; state++) {
			if ((fertileState[0] & state) == 0 && isValidState(state)) {
				dp[0][state] = 1;
			} else {
				dp[0][state] = 0;
			}
		}
		for (int m = 1; m < M; m++)
			for (int curState = 0; curState < MAX_STATE; curState++)
				for (int preState = 0; preState < MAX_STATE; preState++) {
					if ((fertileState[m] & curState) == 0
							&& isValidState(curState)
							&& (curState & preState) == 0) {
						dp[m][curState] += dp[m - 1][preState];
						if (dp[m][curState] >= 100000000) {
							dp[m][curState] -= 100000000;
						}
					}
				}
		int result = 0;
		for (int state = 0; state < MAX_STATE; state++) {
			result += dp[M - 1][state];
			if (result >= 100000000) {
				result = result - 100000000;
			}
		}
		System.out.println(result);
	}

	private static boolean isValidState(int state) {
		int lastBit = 0;
		while (state > 0) {
			if ((state & 1) == 1 && lastBit == 1) {
				return false;
			}
			lastBit = state & 1;
			state = state >> 1;
		}
		return true;
	}
}
