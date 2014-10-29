package cracking.the.coding.interview;

public class Question_9_3_magic_index {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = new int[] {-1, 0, 2, 4, 5, 5, 7, 8, 9};
		System.out.println(findMagic(A, 0, A.length-1));
	}
	
	public static int findMagic(int[ ] A) {
	     int low=0, high=A.length-1;
	     while (low <= high) {
	          int mid = (low + high)/2;
	          if (A[mid] == mid)      return mid;
	          else if (A[mid] < mid)     low = mid+1;
	          else     high = mid-1;
	     }
	     return -1;
	}
	
	public static int findMagic(int[] A, int s, int e) {
		if (s < 0 || e >= A.length || s > e) 	return -1;
		int mid = (s+e)/2;
		if (A[mid] == mid) 	return mid;
		
		int left = findMagic(A, s, Math.min(A[mid], mid-1));
		if (left > -1) return left;

		int right = findMagic(A, Math.max(A[mid], mid+1), e);
		return right;
	}

}
