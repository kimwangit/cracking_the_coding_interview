package cracking.the.coding.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question_9_9_nqueens {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question_9_9_nqueens instance = new Question_9_9_nqueens();
		Point[] path = new Point[8];
		instance.backtrack(path, -1);
	}
	
	class Point {
		int x;
		int y;	
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public String toString() {
			return "(" + x + "," + y + ")";
		}
	}

	void backtrack(Point[] path, int k) {
		if (k == 7) {
			System.out.println(Arrays.toString(path));
			return;
		}
		Point[] candidates = construct_candidates(path, k);
		k = k+1;
		for (int i=0; i<candidates.length; i++) {
			path[k] = candidates[i];
			backtrack(path, k);
		}
	}

	Point[] construct_candidates(Point[] path, int k) {
		List<Point> result = new ArrayList<>();
		for (int row=0; row<8; row++) {
			boolean available = true;
			for (int i=0; i<=k; i++) {
				if (path[i].y == row || Math.abs(k+1-path[i].x) == Math.abs(row-path[i].y)) {
					available = false;
					break;
				}
			}
			if (available) result.add(new Point(k+1, row));
		}
		return result.toArray(new Point[0]);
	}

}
