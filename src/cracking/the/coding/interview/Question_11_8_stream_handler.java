package cracking.the.coding.interview;


public class Question_11_8_stream_handler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question_11_8_stream_handler instance = new Question_11_8_stream_handler();
		int[] stream = new int[]{5, 1, 4, 4, 5, 9, 7, 13, 3};
		for (int i=0; i<stream.length; i++)
			instance.track(stream[i]);
		
		
		System.out.println(instance.getRankOfNumber(1));
		System.out.println(instance.getRankOfNumber(3));
		System.out.println(instance.getRankOfNumber(4));

	}
	
	class Node {
		int value;
		Node leftChild=null;
		Node rightChild=null;
		int leftSide=0;
		
		public Node(int x) {
			value = x;
		}
		
		public void insert(int x) {
			if (x <= value) {
				if (leftChild == null) {
					leftChild = new Node(x);
				} else {
					leftChild.insert(x);
				}
				leftSide++;
			} else {
				if (rightChild == null) {
					rightChild = new Node(x);
				} else {
					rightChild.insert(x);
				}
			}
		}
		
		public int getRank(int x) {
			if (x == value) {
				return leftSide;
			} else if (x < value) {
				if (leftChild == null) return -1;
				else return leftChild.getRank(x);
			} else {
				int right_rank = rightChild == null ? -1 : rightChild.getRank(x);
				if (right_rank == -1) return -1;
				else return leftSide + 1 + right_rank;
			}
		}
	}

	Node root = null;

	public void track(int x) {
		if (root == null) root = new Node(x);
		else root.insert(x);
	}

	public int getRankOfNumber(int x) {
		if (root == null) return -1;
		return root.getRank(x);
	}

}
