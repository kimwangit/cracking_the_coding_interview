package careercup.interview;
import java.util.Arrays;


public class FlipZero {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] input = new int[]{1, 1, 0, 1, 1, 0, 0, 1, 1, 1};
		System.out.println(Arrays.toString(flipMax1(input, 2)));
	}
	
	public static int[] flipMax1(int[] arr, int m) {
		int[] EMPTY = new int[0];
		if (arr.length == 0 || m < 1) {
			return EMPTY;
		}
		int wL=0, wR=-1, nZero=0;
		int bestWidth=-1, bestL=0, bestR=0;
		while (wR < arr.length-1) {
			if (nZero <= m) {
				wR++;
				if (arr[wR] == 0) nZero++;
			}
			if (wL < wR && nZero > m) {
				if (arr[wL] == 0) nZero--;
				wL++;
			}
			if (wR-wL > bestWidth) {
				bestWidth = wR-wL;
				bestL=wL;
				bestR=wR;
			}
		}
		int[] ans = new int[nZero];
		int index=0;
		for (int i=bestL; i<=bestR; i++) {
			if (arr[i] == 0) {
				ans[index]=i;
				index++;
			}
		}
		return ans;
	}
	
	public static int[] flipMax(int[] arr, int m) {
		int[] EMPTY = new int[0];
		if (arr.length == 0 || m < 1) {
			return EMPTY;
		}
		int[] A = new int[arr.length];
		int pre = arr[0], n=0;
		for (int i=0; i<arr.length; i++) {
			if (arr[i] != pre) {
				pre=arr[i];
				n++;
			}
			A[n]++;
		}
		if (n==0 && arr[0]==1) return EMPTY;
		int[] S = new int[n+1];
		S[0]=A[0];
		for (int i=1; i<=n; i++) {
			S[i] = S[i-1]+A[i];
		}
		int z = arr[0]==0 ? 0:1;
		int ans=0, ansStart=-1, ansZ=0, ansM=0;
		while (z <= n) {
			for (int f=0; f<A[z]; f++){
				if (m < A[z]-f) {
					if ((f == 0 && z == 0) || f > 0) {
						if (m > ans) {
							ans = m;
							ansStart = f;
							ansZ = z;
							ansM = m;
						}	
					} else {
						if (A[z-1] + m > ans) {
							ans = A[z-1] + m;
							ansStart = f;
							ansZ = z;
							ansM = m;
						}
					}
				} else {
					int m1=m-(A[z]-f), e=z+2;
					while (e <= n && A[e] <= m1) {
						e = e+2;
						m1 = m1-A[e];
					}
					int sum = A[z]-f;
					if (f == 0 && z > 0) {
						sum += A[z-1];
					}
					if (e > n && z < n) {
						sum += S[n]-S[z];
					} else if (e <= n) {
						sum += S[e-1] -S[z] + m1;
					}
					if (sum > ans) {
						ans = sum;
						ansStart = f;
						ansZ = z;
						ansM = m-m1;
					}
				}
			}
			z = z+2;
		}
		int[] positions = new int[ansM];
		int index = 0, start = ansStart;
		z = ansZ;
		while (index < ansM) {
			for (int i=start; i<A[z] && index < ansM; i++, index++) {
				int prefix = z==0? 0:S[z-1];
				positions[index]=prefix + i;
			}
			start=0;
			z=z+2;
		}
		return positions;
	}

}
