package cracking.the.coding.interview;

import java.util.Arrays;

public class Question_11_7_circus {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] h = new int[]{65, 70, 56, 75, 60, 68};
		int[] w = new int[]{100, 150, 90, 190, 95, 110};
		Question_11_7_circus instance = new Question_11_7_circus();
		System.out.println(instance.getLargest(h, w));
	}
	
	class Person implements Comparable<Person>{
		int height, weight;
		
		public Person(int h, int w) {
			height = h;
			weight = w;
		}
		
		public int compareTo(Person another) {
			if (height == another.height) {
				if (weight == another.weight) return 0;
				else if (weight < another.weight) return -1;
				else return 1;
			} else if (height < another.height) return -1;
			else return 1;
		}
		
		public boolean canBeAbove(Person another) {
			return this.height < another.height && this.weight < another.weight;
		}
	}
	
	public int getLargest(int[] h, int[] w) {
		if (h == null || h.length < 1 || w == null || w.length != h.length) return 0;
		int N = h.length;
		Person[] persons = new Person[N];
		for (int i=0; i<N; i++)
			persons[i] = new Person(h[i], w[i]);
		return getLargest(persons);
	}

	public int getLargest(Person[] persons) {
		if (persons == null || persons.length < 1) return 0;
		Arrays.sort(persons);
		int N = persons.length;
		int[] dp = new int[N];
		Arrays.fill(dp, 1);
		for (int i=1; i<N; i++)
			for (int j=0; j<i; j++)
				if (persons[j].canBeAbove(persons[i]))
					dp[i] = Math.max(dp[i], dp[j]+1);
		int ans=1;
		for (int i=0; i<N; i++)
			ans = Math.max(ans, dp[i]);
		return ans;
	}

}
