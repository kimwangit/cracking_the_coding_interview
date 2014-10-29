package poj;

import java.util.Scanner;

public class ID3252_Round_Number {

public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int start = scanner.nextInt();
	int finish = scanner.nextInt();
	int result = getRNNumbers(finish) - getRNNumbers(start) + isRoundNumber(start);
	System.out.println(result);
}

private static int getRNNumbers(int a) {
	if (a <= 1) return 1;
	int[] f1 = new int[32];
	int[] f2 = new int[32];
	f2[0]=1; f2[1]=1;
	f1[1]=1;
	if ((a & 1) == 1) f1[0]=1;
	a = a>>1;
	int result = f2[1];
	int bits = 1;
	while (a > 1) {
		bits++;
		int bigHalf = (bits+1) >> 1;
		for (int n=bits; n>=1; n--) {
			if (n >= bigHalf) result += f2[n];
			f2[n] += f2[n-1];
			if ((a&1)==1) {
				f1[n]=f1[n] + f2[n-1];
			} else {
				f1[n]=f1[n-1];
			}
		}
		if ((a&1)==0) f1[0]=0;
		a = a >> 1;
	}
	bits++;
	int bigHalf = (bits+1)>>1;
	for (int n = bits-1; n >= bigHalf; n--) result += f1[n];
	return result;
}

private static int isRoundNumber(int a) {
	if (a == 0) return 1;
	int isRN = 0;
	int zeros = 0;
	int bits = 0;
	while (a > 0) {
		if ((a & 1) == 0) zeros++;
		a = a>>1;
		bits++;
	}
	if (zeros >= (bits+1)/2) isRN=1;
	return isRN;
}

}
