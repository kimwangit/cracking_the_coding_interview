package cracking.the.coding.interview;

import java.util.ArrayList;
import java.util.List;

public class Question_18_4_get_number_of_2s {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getNumberOfTwos(25));
	}
	
	public static int getNumberOfTwos(int n) {
		if (n <= 0) return 0;
		List<Integer> bits = new ArrayList<>();
		while (n > 0) {
			bits.add(n%10);
			n = n/10;
		}
		return getNumberOfTwos(bits, bits.size()-1);
	}

	public static int getNumberOfTwos(List<Integer> bits, int current) {
		int bitValue = bits.get(current);
		if (current == 0) {
			if (bitValue >= 2) return 1;
			else return 0;
		} else {
			int ans = getNumberOfTwos(bits, current-1);
			if (bitValue > 2) {
				ans += (int) Math.pow(10, current);
			} else if (bitValue == 2) {
				ans += getValue(bits, current-1) + 1;
			} 
			ans += current * bitValue* (int) Math.pow(10, current-1);
			return ans;
		}
	}

	public static int getValue(List<Integer> bits, int highestBit) {
		int ans = 0, factor = 1;
		for (int i=0; i<=highestBit; i++) {
			ans += factor * bits.get(i);
			factor *= 10;
		}
		return ans;
	}

}
