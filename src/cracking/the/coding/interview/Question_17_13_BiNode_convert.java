package cracking.the.coding.interview;

public class Question_17_13_BiNode_convert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public class BiNode {
		public BiNode node1, node2;
		public int data;
	}
	
	BiNode convert(BiNode root, BiNode previous, BiNode next) {
		if (root == null) return null;
		BiNode leftHead = convert(root.node1, previous, root);
		convert(root.node2, root, next);
		if (root.node1 == null) {
			root.node1 = previous;
			if (previous != null) previous.node2 = root;
		}
		if (root.node2 == null) {
			root.node2 = next;
			if (next != null) next.node1 = root;
		}
		return leftHead == null ? root : leftHead;
	}
}
