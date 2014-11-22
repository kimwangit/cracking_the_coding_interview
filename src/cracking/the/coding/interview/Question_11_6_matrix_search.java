package cracking.the.coding.interview;

public class Question_11_6_matrix_search {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public boolean matrixSearch(int[][] A, int t) {
		if (A.length == 0 || A[0].length == 0) return false;
		int n = A.length, m = A[0].length;
		int i=0, j=m;
		while (i < n) {
			while (j >= 0 && A[i][j] > t) j--;
			if (j == -1) return false;
			if (A[i][j] == t) return true;
			i++;
		}
		return false;
	}

}
