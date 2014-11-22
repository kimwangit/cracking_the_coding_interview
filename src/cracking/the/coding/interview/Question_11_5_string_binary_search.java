package cracking.the.coding.interview;

import java.util.Arrays;

public class Question_11_5_string_binary_search {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] array = new String[]{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
		System.out.println(binarySearch(array, "ball"));
	}
	
	public static int binarySearch(String[] array, String target) {
		if (array == null || array.length < 1) return -1;
		int N = array.length;
		int[] lefts = new int[N];
		lefts[0] = -1;
		for (int i=0; i<N; i++) {
			if (!array[i].equals("")) lefts[i]=i;
			else if (i > 0) lefts[i]=lefts[i-1];
		}
		int low=0, high=N-1;
		while (low <= high) {
			int mid = lefts[(low+high)/2];
			if (mid < low) return -1;
			int compare = array[mid].compareTo(target);
			if (compare == 0) return mid;
			else if (compare < 0)	low = (low+high)/2+1;
			else high = mid-1;
		}
		return -1;
	}

}
