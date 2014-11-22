package cracking.the.coding.interview;

public class Question_17_6_get_smallest_sequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = new int[] {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19 };
		
		getSSequence(array);
	}
	
	static void getSSequence(int[] array) {
		int n = array.length;
		if (n <= 1) {
			System.out.println("-1, -1");
			return ;
		}
		int rStart = 0, rEnd = n-1;
		
		boolean[] isAscending = new boolean[n];
		isAscending[0] = true; 
		for (int i=1; i<n; i++)
			if (array[i] >= array[i-1])	isAscending[i] = true;
			else	break;
			
		boolean[] after = new boolean[n];
		int min = array[n-1];
		for (int i=n-2; i>=0; i--)
			if (array[i] > min) after[i] = true;
			else min = array[i];
			
		for (rStart = 0; rStart < n; rStart++) {
			if (after[rStart] || !isAscending[rStart]) break;
		}
		
		if (rStart >= n) {
			System.out.println("-1, -1");
			return ;
		}
		
		boolean[] isDescending = new boolean[n];
		isDescending[n-1] = true;
		for (int i=n-2; i>=rStart; i--)
			if (array[i] <= array[i+1]) isDescending[i]=true;
			else break;
		
		boolean[] before = new boolean[n];
		int max = array[rStart];
		for (int i=rStart+1; i<n; i++)
			if (array[i] < max) before[i] = true;
			else max = array[i];
		
		for (rEnd = n-1; rEnd >= rStart; rEnd--) {
			if (before[rEnd] || !isDescending[rEnd]) break;
		}
		System.out.println(rStart + "," + rEnd);
	}

}
