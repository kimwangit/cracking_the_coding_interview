package cracking.the.coding.interview;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Question_16_3_philosopher_dining {

	static class ChopStick {
		Lock lock = new ReentrantLock();
		
		public boolean pickUp() {
			return lock.tryLock();
		}
		
		public void putDown() {
			lock.unlock();
		}
	}
	
	static class Philosopher extends Thread{
		ChopStick left;
		ChopStick right;
		String name;
		
		public Philosopher(String name, ChopStick left, ChopStick right) {
			this.name = name;
			this.left = left;
			this.right = right;
		}
		
		public void run() {
			while (!pickUp()) {	
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			chew();
			putDown();
		}
		
		public boolean pickUp() {
			if (!left.pickUp()) {
				return false;
			}
			if (!right.pickUp()) {
				left.putDown();
				return false;
			}
			return true;
		}
		
		public void putDown() {
			left.putDown();
			right.putDown();
		}
		
		public void chew() {
			System.out.println("Philosopher " + name + " is eating.");
		}
		
	}

	public static void main(String[] args) {
		ChopStick[] sticks = new ChopStick[5];
		for (int i=0; i<5; i++)
			sticks[i] = new ChopStick();
		Philosopher[] phis = new Philosopher[5];
		for (int i=0; i<5; i++) {
			phis[i] = new Philosopher("Philosopher " + i, sticks[i], sticks[(i+1)%5]);
			phis[i].start();
		}
	}

}
