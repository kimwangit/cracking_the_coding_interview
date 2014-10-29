package poj;

import java.util.Scanner;

public class ID1019_NumberSequence {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		for (int i=0; i<num; i++) {
			int index = in.nextInt();
			System.out.println(getBit(index));
		}
	}
	
	private static int getBit(int index) {
		long total = 0;
		long segCount = 0;
		int i;
		for (i=1; ; i++) {
			segCount += (int)Math.log10(i) + 1;
			total += segCount;
			if (total >= index) break;
		}
		int len=0;
		int pos = (int)(index - (total-segCount));
		for (i=1; ;i++) {
			len += (int)Math.log10(i)+1;
			if (len >= pos) break;
		}
		return i/(int)Math.pow(10, len-pos)%10;
	}
}
