package myWeb;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest implements Runnable{
	public static ReentrantLock lock = new ReentrantLock();
	public static int i = 0;
	public void run() {
		for(int j=0;j<1000000;j++) {
			lock.lock();
			try {
				i++;
			}finally {
				lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ThreadTest tl = new ThreadTest();
		Thread t1 = new Thread(tl);
		Thread t2 = new Thread(tl);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
}
