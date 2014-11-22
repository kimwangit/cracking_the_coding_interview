package cracking.the.coding.interview;

public class Question_11_1_merge_B_into_A {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	void merge(int[] A, int n, int[] B, int m) {
		int k=n+m-1;
		int i=n-1, j=m-1;
		while (k >= 0) {
			if (i < 0 || (j >= 0 && B[j] >= A[i])) {
				A[k] = B[j];
				k--;
				j--;
			} else {
				A[k] = A[i];
				k--;
				i--;
			}
		}
	}

}
