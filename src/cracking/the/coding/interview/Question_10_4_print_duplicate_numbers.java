package cracking.the.coding.interview;

public class Question_10_4_print_duplicate_numbers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	void printDupNumbers(int[] array) {
		byte[] bitfield = new byte[1<<11];
		
		for (int i=0; i<4; i++) {
			for (int j=0; j<bitfield.length; j++)	bitfield[j] &= 0;
			int min = 8000*i, max = 8000*(i+1);
			for (int j=0; j<array.length; j++) {
				if (array[j] > min && array[j] <= max) {
					int num = array[j]-min-1;
					if ((bitfield[num/4] & (2<<(num%4*2))) == 0)
						if ((bitfield[num/4] & (1<<(num%4*2))) == 0) {
							bitfield[num/4] |= 1<<(num%4*2);
						} else {
							bitfield[num/4] |= 2<<(num%4*2);
						}
				}
			}
			for (int j=0; j<bitfield.length; j++) {
				for (int k=0; k<8; k=k+2) {
					if ((bitfield[j] & (2<<(2*k))) != 0) {
						System.out.println(4*j + k/2 + 1 + min);
					}
				}
			}
		}
	}
	
	public static void checkDuplicates(int[] array) {
		
	}
	
	class BitSet {
		int[] bits;
		
		public BitSet(int size) {
			bits = new int[size>>5];
		}
		
		public boolean getBit(int num) {
			return (bits[num>>5] & (1<<(num%32))) != 0;
		}
		
		public void setBit(int num) {
			bits[num>>5] |= 1<<(num%32);
		}
	}

}
