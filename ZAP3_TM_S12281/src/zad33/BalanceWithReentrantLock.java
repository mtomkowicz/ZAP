/**
 * @author Tomkowicz Małgorzata S12281
 */
package zad33;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BalanceWithReentrantLock extends Thread {
	
	public static Lock lock = new ReentrantLock();
	 private Balance b; 
	 private int max;

	 public BalanceWithReentrantLock(String name, Balance b, int max) {
	   super(name);
	   this.b = b;
	   this.max = max;
	   start();
	 }

	 public void run() {
	   int wynik = 0;
	   for (int i = 0; i < max; i++) {
		 // zapis
		 lock.lock();
		 b.increase(1);
	     b.decrease(1);
		 lock.unlock();
		 // odczyt
		 lock.lock();
		 wynik = b.getBalance();
		 // System.out.println(Thread.currentThread().getName() + " : " + wynik);
		 lock.unlock();
		 
		 if (wynik != 0) {
			 System.out.println(Thread.currentThread().getName() + " : Brak synchronizacji!");
			 break;
		 }
	   }
	 }

}
