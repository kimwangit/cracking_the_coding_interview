package cracking.the.coding.interview;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Question_10_3_findMissingInteger {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static int findMissing(File file) throws FileNotFoundException {
		long numberOfInts = ((long) Integer.MAX_VALUE) + 1;
		int[] bitfield = new int[(int) (numberOfInts / 32)];
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextInt()) {
			int number = scanner.nextInt();
			bitfield[number/32] |= 1<<(number%32);
		}
		for (int i=0; i<bitfield.length; i++) {
			if (bitfield[i] != 0xFFFFFFFF) {
				int j=1, bit=1; 
				for (; j<=32; j++) {
					if ((bitfield[i] & bit) != 0) break;
					bit <<=1;
				}
				return i * 32 + j;
			}
		}
		return -1;
	}
	
	public static int findMissing1(File file) throws FileNotFoundException {
		int blockNum = 4096;
		int[] blocks = new int[blockNum];
		int bitSize = 1<<20;
		byte[] bitfield = new byte[bitSize/8];
		
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextInt()) {
			int number = scanner.nextInt();
			blocks[number / bitSize]++;
		}
		
		int starting = -1;
		for (int i=0; i<blocks.length; i++)
			if (blocks[i] < bitSize) {
				starting = i * bitSize;
				break;
			}
		
		scanner = new Scanner(file);
		while (scanner.hasNextInt()) {
			int number = scanner.nextInt();
			if (number >= starting && number < starting + bitSize) {
				int seq = number - starting;
				bitfield[seq/8] |= 1<<(seq%8);
			}
		}
		
		for (int i=0; i<bitfield.length; i++) {
			if (bitfield[i] != 0xFF) {
				for (int j=0; j<8; j++) 
					if ((bitfield[i] & (1<<j)) == 0) {
						return i * 8 + j + starting;
					}
			}
		}
		
		return -1;
	}
 
}
