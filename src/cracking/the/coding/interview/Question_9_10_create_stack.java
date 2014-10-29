package cracking.the.coding.interview;

import java.util.ArrayList;
import java.util.HashMap;

public class Question_9_10_create_stack {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	class Box {
		int w, h, d;
		
		public boolean canBeAbove(Box a) {
			return w < a.w && h < a.h && d < a.d;
		}
	}
	
	public int createStack(Box[] boxes) {
		HashMap<Box, ArrayList<Box>> map = new HashMap<>();
		int max_height = 0;
		for (int i=0; i<boxes.length; i++) {
			max_height = Math.max(max_height, computeHeight(createStackDP(boxes, boxes[i], map)));
		}
		return max_height;
	}
	
	public ArrayList<Box> createStackDP(Box[] boxes, Box bottom, HashMap<Box, ArrayList<Box>> map) {
		if (bottom != null && map.containsKey(bottom))	return map.get(bottom);
		int max_height = 0;
		ArrayList<Box> max_stack = null;
		for (int i=0; i<boxes.length; i++) {
			if (boxes[i].canBeAbove(bottom)) {
				ArrayList<Box> subStack = createStackDP(boxes, boxes[i], map);
				int height = computeHeight(subStack);
				if (height > max_height) {
					max_height = height;
					max_stack = subStack;
				}
			}
		}
		if (max_stack == null)	max_stack = new ArrayList<>();
		max_stack.add(0, bottom);
		map.put(bottom, max_stack);
		return (ArrayList<Box>) max_stack.clone();
	}
	
	public int computeHeight(ArrayList<Box> list) {
		int sum = 0;
		for (int i=0; i<list.size(); i++)
			sum += list.get(i).h;
		return sum;
	}

}
