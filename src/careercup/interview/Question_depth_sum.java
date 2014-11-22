package careercup.interview;

import java.util.List;

public class Question_depth_sum {
	public int depthSum(List<NestedInteger> input) {
		return depthSum(input, 1);
	}

	public int depthSum(List<NestedInteger> input, int depth) {
		if (input == null) return 0;
		int sum = 0;
		for (NestedInteger item: input) {
			if (item.isInteger()) {
				sum += item.getInteger() * depth;
			} else {
				sum += depthSum(item.getList(), depth+1);
			}
		}
		return sum;
	}
	
	public interface NestedInteger {
		boolean isInteger();
		
		Integer getInteger();
		
		List<NestedInteger> getList();
	}
}
