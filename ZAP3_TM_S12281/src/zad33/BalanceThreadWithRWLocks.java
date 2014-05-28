/**
 * @author Tomkowicz Małgorzata S12281
 */

package zad33;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BalanceThreadWithRWLocks extends Thread {

	public static ReentrantReadWriteLock rrwLock = new ReentrantReadWriteLock();
	 private Balance b; 		
	 private int max; 			
	 private Lock readLock;
	 private Lock writeLock;
	 
	 public BalanceThreadWithRWLocks(String name, Balance b, int max) {
	   super(name);
	   this.b = b;
	   this.max = max;
	   readLock = rrwLock.readLock();
	   writeLock = rrwLock.writeLock();
	   start();
	 }

	 public void run() {
	   int wynik = 0;
	   for (int i = 0; i < max; i++) {
		// zapis
	     writeLock.lock();
	     b.increase(1);
	     b.decrease(1);
		 writeLock.unlock();
		 // odczyt
		 readLock.lock();
		 wynik = b.getBalance();
		 //System.out.println(Thread.currentThread().getName() + " : " + wynik);
		 readLock.unlock();

		 if (wynik != 0) {
			 System.out.println(Thread.currentThread().getName() + " : Brak synchronizacji!");
			 break;
		 }
	   }
	 }
}
