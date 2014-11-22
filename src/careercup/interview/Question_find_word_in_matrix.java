package careercup.interview;

import java.util.ArrayList;
import java.util.List;

public class Question_find_word_in_matrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question_find_word_in_matrix instance = new Question_find_word_in_matrix();
		char[][] matrix = new char[][] {
				{'A', 'C', 'P', 'R', 'C'}, 
				{'X', 'S', 'O', 'P', 'C'},
				{'V', 'O', 'V', 'N', 'I'},
				{'W', 'G', 'F', 'M', 'N'},
				{'Q', 'A', 'T', 'I', 'T'}
				};
		System.out.println(instance.search(matrix, "MICROSOFT"));
	}
	
	class Point {
		public int row, col;
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public boolean search(char[][] matrix, String target) {
		if (matrix == null 
				|| matrix.length == 0 
				|| matrix[0].length == 0 
				|| target == null 
				|| target.length() == 0) 
			return false;
		
		int ROWS = matrix.length, COLS = matrix[0].length;
		boolean[][] visits = new boolean[ROWS][COLS];
		Point[] A = new Point[target.length()];
		
		return backtrack(matrix, target, A, 0, visits);
	}

	boolean backtrack(char[][] matrix, String target, Point[] A, int k, boolean[][] visits) {
		if (k == A.length) {
			return true;
		}
		else if (k == 0) {
			char nextChar = target.charAt(k);
			for (int row=0; row<matrix.length; row++)
				for (int col=0; col<matrix[0].length; col++)
					if (matrix[row][col] == nextChar) {
						A[k] = new Point(row, col);
						visits[row][col]=true;
						if (backtrack(matrix, target, A, k+1, visits)) {
							return true;
						}
						visits[row][col]=false;
					}
		} else {
			Point[] candidates = calculateCandidates(matrix, target, A, k, visits);
			for (Point candidate : candidates) {
				A[k] = candidate;
				visits[candidate.row][candidate.col] = true;
				if (backtrack(matrix, target, A, k+1, visits)) {
					return true;
				}
				visits[candidate.row][candidate.col]=false;
			}
		}
		return false;
	}

	Point[] calculateCandidates(char[][] matrix, String target, Point[] A, int k, boolean[][] visits) {
		Point cur = A[k-1];
		char nextChar = target.charAt(k);
		List<Point> candidates = new ArrayList<>();
		for (int row=cur.row-1; row<=cur.row+1; row++) {
			for (int col=cur.col-1; col<=cur.col+1; col++) 
				if (row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length) {
					if (!visits[row][col] && matrix[row][col] == nextChar) candidates.add(new Point(row, col));
				}
		}
		return candidates.toArray(new Point[0]);
	}
	
	void print(char[][] matrix, Point[] A, int k) {
		for (int i=0; i<=k; i++) {
			int row = A[i].row, col = A[i].col;
			System.out.print("[" + row + "," + col + "] ->" + matrix[row][col] + ",");
		}
		System.out.println();		
	}

}
