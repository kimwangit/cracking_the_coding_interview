package google.code.jam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class Question_minesweeper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] map = new char[][] {
				{'.', '.', '*'},
				{'.', '.', '*'},
				{'*', '*', '.'}	
		};
		
		Question_minesweeper solution = new Question_minesweeper();
		
//		System.out.println(solution.mineSweeper(map));
		
		char[][] map2 = new char[][] {
				{'.', '.', '*', '.', '.'},
				{'.', '.', '*', '.', '.'},
				{'.', '*', '.', '.', '*'},
				{'.', '*', '.', '.', '.'},
				{'.', '*', '.', '.', '.'}	
		};
		
//		System.out.println(solution.mineSweeper(map2));
		
		solution.readData();
	}
	
	class Node {
		int row, col;
		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public void readData() {
		String inFile = "E:\\A-large-practice.in";
		String outFile = "E:\\minesweeper_large.out";
		Scanner in;
		PrintWriter out;
		try {
			in = new Scanner(new File(inFile));
			out = new PrintWriter(new File(outFile));
			int T = in.nextInt();
			for (int i=1; i<=T; i++) {
				int N = in.nextInt();
				in.nextLine();
				char[][] map = new char[N][];
				for (int j=0; j<N; j++)
					map[j] = in.nextLine().trim().toCharArray();
				out.println("Case #" + i + ": " + mineSweeper(map));
			}
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
		}
	}

	public int mineSweeper(char[][] map) {
		if (map == null || map.length == 0 || map[0].length == 0) return 0;
		int ROWS = map.length, COLS = map[0].length;
		int[][] nMap = getNeighborNumber(map);
		boolean[][] states = new boolean[ROWS][COLS];
		LinkedList<Node> queue = new LinkedList<>();
		for (int row=0; row<ROWS; row++)
			for (int col=0; col<COLS; col++)
				if (nMap[row][col] == 0) queue.offer(new Node(row, col));
		int ans = 0;
		while (queue.size() > 0) {
			Node node = queue.poll();
			if (states[node.row][node.col]) continue;
			click(node, nMap, states);
			ans++;
		}
		for (int row=0; row<ROWS; row++)
			for (int col=0; col<COLS; col++)
				if (nMap[row][col] > 0 && !states[row][col]) {
					ans++;
				}
		return ans;
	}

	private int[][] getNeighborNumber(char[][] map) {
		int N = map.length, M = map[0].length;
		int[][] nMap = new int[N][M];
		for (int row=0; row<N; row++)
			for (int col=0; col<M; col++) {
				if (map[row][col] == '*') {
					nMap[row][col] = -1;
				} else {
					for (int i=row-1; i<=row+1; i++)
						for (int j=col-1; j<=col+1; j++) {
							if (i >= 0 && i < N && j >= 0 && j < M && map[i][j] == '*') {
								nMap[row][col]++;
							}
						}
				}
			}
		return nMap;
	}

	private void click(Node root, int[][] nMap, boolean[][] states) {
		int N = nMap.length, M = nMap[0].length;
		LinkedList<Node> queue = new LinkedList<>();
		queue.offer(root);
		while (queue.size() > 0) {
			Node top = queue.poll();
			states[top.row][top.col] = true;
			for (int i=top.row-1; i<=top.row+1; i++)
				for (int j=top.col-1; j<=top.col+1; j++)
					if (i >= 0 && i < N && j >= 0 && j < M && nMap[i][j] >= 0 && !states[i][j]) {
						if (nMap[i][j] > 0) states[i][j] = true;
						else {
							states[i][j] = true;
							queue.offer(new Node(i, j));
						}
					}
		}
	}

}
