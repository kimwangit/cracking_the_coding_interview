package cracking.the.coding.interview;

import java.util.ArrayList;
import java.util.List;

public class Question_9_4_subsets {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> set = new ArrayList<>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(4);
		set.add(5);
		Question_9_4_subsets instance = new Question_9_4_subsets();
		System.out.println(instance.getSubsets(set));
	}
	
	List<List<Integer>> getSubsets(List<Integer> set) {
		List<List<Integer>> emptySet = new ArrayList<>();
		emptySet.add(new ArrayList<Integer>());
		return getSubsets(set, 0, emptySet);
	}

	List<List<Integer>> getSubsets(List<Integer> set, int index, List<List<Integer>> subset) {
		if (index >= set.size()) return subset;
		List<List<Integer>> newSubset = new ArrayList<>();
		for (List<Integer> aSet : subset) {
			List<Integer> nList1 = new ArrayList<>();
			List<Integer> nList2 = new ArrayList<>();
			nList1.addAll(aSet);
			nList2.addAll(aSet);
			nList2.add(set.get(index));
			newSubset.add(nList1);
			newSubset.add(nList2);
		}
		return getSubsets(set, index+1, newSubset);
	}

}
