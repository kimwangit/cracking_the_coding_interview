package careercup.interview;

import java.util.HashMap;

public class Question_get_max_gold {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] input = new int[] { 2, 5, 4, 3 };
		System.out.println(getMaxGold2(input));
	}

	public static int getMaxGold(int[] input) {
		int N = input.length;
		int[] sum = new int[N];
		for (int i = 0; i < N; i++) {
			if (i == 0)
				sum[i] = input[i];
			else
				sum[i] = sum[i - 1] + input[i];
		}
		return getMaxGold(input, 0, N - 1, sum);
	}

	public static int getMaxGold(int[] input, int start, int end, int[] sum) {
		if (start < 0 || end > input.length-1 || start > end)
			return 0;
		else if (start == end)
			return input[start];
		int choose1 = input[start] + sum[end] - sum[start]	- getMaxGold(input, start + 1, end, sum);
		int choose2 = input[end] + sum[end - 1] - getMaxGold(input, start, end - 1, sum);
		if (start > 0)
			choose2 = choose2 - sum[start - 1];
		return Math.max(choose1, choose2);
	}

	public static int getMaxGold2(int[] input) {
		int N = input.length;
		int[] sum = new int[N];
		for (int i = 0; i < N; i++) {
			if (i == 0)
				sum[i] = input[i];
			else
				sum[i] = sum[i - 1] + input[i];
		}
		HashMap<String, Integer> map = new HashMap<>();
		return getMaxGold2(input, 0, N - 1, sum, map);
	}

	public static int getMaxGold2(int[] input, int start, int end, int[] sum,
			HashMap<String, Integer> map) {
		if (start < 0 || end > input.length-1 || start > end)
			return 0;
		else if (start == end)
			return input[start];
		else {
			String key = start + "," + end;
			if (map.containsKey(key))
				return map.get(key);
			int choose1 = input[start] + sum[end] - sum[start] - getMaxGold2(input, start + 1, end, sum, map);
			int choose2 = input[end] + sum[end - 1] - getMaxGold2(input, start, end - 1, sum, map);
			if (start > 0)
				choose2 = choose2 - sum[start - 1];
			int result = Math.max(choose1, choose2);
			map.put(key, result);

			return result;
		}
	}

}
