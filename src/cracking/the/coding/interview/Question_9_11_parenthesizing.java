package cracking.the.coding.interview;

import java.util.HashMap;

public class Question_9_11_parenthesizing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> map = new HashMap<>();
		System.out.println(f("1^0|0|1", 0, 6, false, map));
	}
	
	public static int f(String exp, int s, int e, boolean result, HashMap<String, Integer> map) {
		String key = "" + result + s + "," + e;
		if (map.containsKey(key))	return map.get(key);
		int c = 0;
		if (s == e) {
			if (exp.charAt(s) == '1' && result) {
				c = 1;
			} else if (exp.charAt(s) == '0' && !result) {
				c = 1;
			} else {
				c = 0;
			}
		} else {			
			if (result) {
				for (int i=s+1; i<=e; i=i+2) {
					char op = exp.charAt(i);
					if (op == '&') {
						c += f(exp, s, i-1, true, map) * f(exp, i+1, e, true, map);
					} else if (op == '|') {
						c += f(exp, s, i-1, true, map) * f(exp, i+1, e, true, map);
						c += f(exp, s, i-1, true, map) * f(exp, i+1, e, false, map);
						c += f(exp, s, i-1, false, map) * f(exp, i+1, e, true, map);
					} else {
						c += f(exp, s, i-1, true, map) * f(exp, i+1, e, false, map);
						c += f(exp, s, i-1, false, map) * f(exp, i+1, e, true, map);
					}
				}
				
			} else {
				for (int i=s+1; i<=e; i=i+2) {
					char op = exp.charAt(i);
					if (op == '&') {
						c += f(exp, s, i-1, true, map) * f(exp, i+1, e, false, map);
						c += f(exp, s, i-1, false, map) * f(exp, i+1, e, true, map);
					} else if (op == '|') {
						c += f(exp, s, i-1, false, map) * f(exp, i+1, e, false, map);
					} else {
						c += f(exp, s, i-1, true, map) * f(exp, i+1, e, true, map);
						c += f(exp, s, i-1, false, map) * f(exp, i+1, e, false, map);
					}
				}
			}
		}
		map.put(key, c);
		return c;
	}

}
