package cracking.the.coding.interview;

public class Question_11_3_rotated_binary_search {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = new int[] {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
		System.out.println(binarySearch(A, 0, A.length-1, 5));
	}
	
	public static int binarySearch(int[] A, int s, int e, int t) {
		if (s > e || (s == e && A[s] != t)) return -1;
		int mid = (s+e)/2;
		if (A[mid] == t) return mid;
		if (A[s] < A[mid]) {
			if (A[s] <= t && A[mid] >= t) {
				return binarySearch(A, s, mid-1, t);
			} else {
				return binarySearch(A, mid+1, e, t);
			}
		} else if (A[mid] < A[e]) {
			if (A[mid] <= t && t <= A[e]) {
				return binarySearch(A, mid+1, e, t);
			} else {
				return binarySearch(A, s, mid-1, t);
			}
		} else if (A[s] == A[mid]){
			if (A[mid] != A[e]) {
				return binarySearch(A, mid+1, e, t);
			} else {
				int result = binarySearch(A, s, mid-1, t);
				if (result == -1) {
					result = binarySearch(A, mid+1, e, t);
				}
				return result;
			}
		}
		return -1;
	}

}
