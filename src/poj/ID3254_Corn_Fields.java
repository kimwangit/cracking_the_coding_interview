package poj;

import java.util.Scanner;

public class ID3254_Corn_Fields {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int M = in.nextInt();
		int N = in.nextInt();
		int[] fertileState = new int[M];
		for (int i = 0; i < M; i++) {
		   for (int j = 0; j < N; j++) {
			int isFertile = in.nextInt();
			fertileState[i] = (fertileState[i] << 1) + ((isFertile + 1) % 2);
			}
		}
			
		int MAX_STATE = 1 << N;
		int[][][] result = new int[2][MAX_STATE][MAX_STATE];
		int[][] transMatrix = new int[MAX_STATE][MAX_STATE];
		for (int i = 0; i < MAX_STATE; i++)
			for (int j = 0; j < MAX_STATE; j++) {
				result[0][i][i] = 1;
				transMatrix[i][j] = calcMatrix(i, j);
			}
		int[][] revisedMatrix = new int[MAX_STATE][MAX_STATE];
		int r = 0;
		for (int i = 0; i < M; i++) {
			for (int row = 0; row < MAX_STATE; row++)
				for (int col = 0; col < MAX_STATE; col++) {
					if ((row | fertileState[i]) == (row + fertileState[i])) {
						revisedMatrix[row][col] = transMatrix[row][col];
					} else {
						revisedMatrix[row][col] = 0;
					}
				}
			mulMatrix(MAX_STATE, result[r], revisedMatrix, result[r ^ 1]);
			r = r ^ 1;
		}
		int sum = 0;
		for (int i = 0; i < MAX_STATE; i++) {
			sum += result[r][i][0];
			if (sum >= 100000000)
				sum -= 100000000;
		}
		System.out.println(sum);
	}

	private static void mulMatrix(int len, int[][] a, int[][] b, int[][] c) {
		int k = 0;
		for (int i = 0; i < len; i++)
			for (int j = 0; j < len; j++)
				for (k = c[i][j] = 0; k < len; k++) {
					long multiply = ((long) a[i][k]) * ((long) b[k][j])
							% 100000000;
					c[i][j] += (int) multiply;
					if (c[i][j] >= 100000000)
						c[i][j] -= 100000000;
				}
	}

	private static int calcMatrix(int leftState, int rightState) {
		if (!isValidState(leftState) || !isValidState(rightState)
				|| (leftState | rightState) != (leftState + rightState)) {
			return 0;
		} else {
			return 1;
		}
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
