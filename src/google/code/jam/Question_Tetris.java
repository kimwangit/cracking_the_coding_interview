package google.code.jam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Question_Tetris {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question_Tetris instance = new Question_Tetris();
		instance.solution();
	}

	char[][][] types = new char[7][][];

	void solution() {
		init();
		String inFile = "E:\\google-code-jam\\tetris_test.in";
		String outFile = "E:\\google-code-jam\\tetris_test.out";
		
//		String inFile = "E:\\google-code-jam\\D-small-practice.in";
//		String outFile = "E:\\google-code-jam\\D-small-practice.out";
		
//		String inFile = "E:\\google-code-jam\\D-large-practice.in";
//		String outFile = "E:\\google-code-jam\\D-large-practice.out";
		
		try {
			Scanner in = new Scanner(new File(inFile));
			PrintWriter out = new PrintWriter(new File(outFile));
			int T = in.nextInt();
			for (int caseId=1; caseId<=T; caseId++) {
				int W = in.nextInt();
				int H = in.nextInt();
				int N = in.nextInt();
				char[][] map = new char[H][W];
				for (int i=0; i<map.length; i++)
					for (int j=0; j<map[0].length; j++)
						map[i][j] = '.';
				boolean gameOver = false;
				for (int n=0; n<N; n++) {
					int t = in.nextInt();
					int r = in.nextInt();
					int x = in.nextInt();
					if (!gameOver && !fall(map, t, r, x)) gameOver = true;
				}
				out.println("Case #" + caseId + ":");
				if (gameOver) out.println("Game Over!");
				else {
					for (int i=0; i<map.length; i++) {						
						for (int j=0; j<map[0].length; j++) {
							out.print(map[i][j]);
						}
						out.println();
					}
				}
			}
			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	void init() {
		types[0] = new char[][] { { 'x', '.' }, { 'x', 'x' }, { '.', 'x' } };
		types[1] = new char[][] { { '.', 'x' }, { 'x', 'x' }, { 'x', '.' } };
		types[2] = new char[][] { { 'x', '.' }, { 'x', '.' }, { 'x', 'x' } };
		types[3] = new char[][] { { '.', 'x' }, { '.', 'x' }, { 'x', 'x' } };
		types[4] = new char[][] { { 'x', 'x' }, { 'x', 'x' } };
		types[5] = new char[][] { { 'x' }, { 'x' }, { 'x' }, { 'x' } };
		types[6] = new char[][] { { '.', 'x', '.' }, { 'x', 'x', 'x' } };
	}

	char[][] convertType(int t, int r) {
		char[][] origin = types[t - 1];
		int rows = origin.length, cols = origin[0].length;
		if (r == 1 || r == 3) {
			rows = origin[0].length;
			cols = origin.length;
		}
		char[][] target = new char[rows][cols];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++) {
				if (r == 0)
					target[i][j] = origin[i][j];
				else if (r == 1)
					target[i][j] = origin[j][rows - i - 1];
				else if (r == 2)
					target[i][j] = origin[rows - i - 1][cols - j - 1];
				else
					target[i][j] = origin[cols - j - 1][i];
			}
		return target;
	}

	boolean fall(char[][] map, int t, int r, int x) {
		char[][] sprite = convertType(t, r);
		int leftBy = sprite.length - 1;
		if (collide(map, sprite, x, leftBy))
			return false;
		while (!collide(map, sprite, x, leftBy)) {
			leftBy++;
		}
		leftBy--;
		fillRegion(map, sprite, x, leftBy);
		clearLines(map);
		return true;
	}

	boolean collide(char[][] map, char[][] sprite, int leftBx, int leftBy) {
		if (leftBy >= map.length)
			return true;
		int rows = sprite.length, cols = sprite[0].length;
		for (int y = leftBy; y >= Math.max(0, leftBy - rows + 1); y--)
			for (int x = leftBx; x <= leftBx + cols - 1; x++)
				if (map[y][x] == 'x'
						&& sprite[rows - 1 + (y - leftBy)][x - leftBx] == 'x')
					return true;
		return false;
	}

	void fillRegion(char[][] map, char[][] sprite, int leftBx, int leftBy) {
		int rows = sprite.length, cols = sprite[0].length;
		for (int y = leftBy; y >= Math.max(0, leftBy - rows + 1); y--)
			for (int x = leftBx; x <= leftBx + cols - 1; x++) {
				char ch = sprite[rows - 1 + (y - leftBy)][x - leftBx];
				if (ch == 'x') map[y][x] = ch;
			}
	}

	void clearLines(char[][] map) {
		int newRow = map.length - 1;
		for (int row = map.length - 1; row >= 0; row--) {
			if (isEmptyLine(map[row])) break;
			if (checkLineFilled(map[row])) {
				Arrays.fill(map[row], '.');
			} else {
				if (row < newRow) {
					System.arraycopy(map[row], 0, map[newRow], 0, map[0].length);
					Arrays.fill(map[row], '.');
				}
				newRow--;
			}
		}
	}

	boolean checkLineFilled(char[] line) {
		for (int i = 0; i < line.length; i++)
			if (line[i] == '.')
				return false;
		return true;
	}
	
	boolean isEmptyLine(char[] line) {
		for (int i = 0; i < line.length; i++)
			if (line[i] == 'x')
				return false;
		return true;
	}
}
