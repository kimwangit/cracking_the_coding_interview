package poj;

import java.util.Scanner;

public class ID1185_Canon {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int M = in.nextInt();
		if (N == 0 || M == 0)
			System.out.println(0);
		in.nextLine();
		int[] map = new int[N];
		for (int i=0; i<N; i++) {
			String line = in.nextLine();
			for (int j=0; j<M; j++) {
				int bit = 0;
				if (line.charAt(j) == 'H')	bit = 1;
				map[i] = (map[i]<<1) + bit;
			}
		}
		
		int MAX_STATE = 1<<M;
		int[] num = new int[MAX_STATE];
		boolean[] valid = new boolean[MAX_STATE];
		for (int i=0; i<MAX_STATE; i++) {
			num[i] = getBits(i);
			valid[i] = isValidState(i);
		}
		int[][][] dp = new int[2][MAX_STATE][MAX_STATE];
		int r=0;
		//initialize the first row.
		for (int curState=0; curState<MAX_STATE; curState++) {
			if ((map[0] & curState) == 0 && valid[curState]) {
				for (int preState=0; preState<MAX_STATE; preState++)
					dp[r][curState][preState]=num[curState];
			}
		}
		for (int row=1; row<N; row++) {
			r = r^1;
			for (int curState=0; curState<MAX_STATE; curState++)
				if ((map[row] & curState) == 0 && valid[curState]) {
					for (int preState1=0; preState1<MAX_STATE; preState1++) {
						if ((map[row-1] & preState1) ==0 && (curState & preState1) == 0 && valid[preState1]) {
							for (int preState2=0; preState2<MAX_STATE; preState2++) {
								if ((curState & preState2) == 0 && (preState1 & preState2) == 0)
									dp[r][curState][preState1] = Math.max(dp[r][curState][preState1], 
										num[curState] + dp[r^1][preState1][preState2]);
							}
						}
					}
				}
		}
		int result=0;
		for (int curState=0; curState<MAX_STATE; curState++)
			for (int preState=0; preState<MAX_STATE; preState++)
				result = Math.max(result, dp[r][curState][preState]);
		System.out.println(result);
	}

	private static boolean isValidState(int state) {
		int lastBit = -3;
		int index=0;
		while (state > 0) {
			if ((state & 1) == 1) {
				if ((index - lastBit)<=2) return false;
				else lastBit = index;
			} 
			index++;
			state = state >> 1;
		}
		return true;
	}
	
	private static int getBits(int state) {
		int result = 0;
		while (state > 0) {
			if ((state & 1) == 1)
				result++;
			state = state >> 1;
		}
		return result;
	}
}
