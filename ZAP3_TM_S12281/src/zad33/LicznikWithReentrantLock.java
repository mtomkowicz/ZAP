/**
 * @author Tomkowicz Małgorzata S12281
 */
package zad33;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LicznikWithReentrantLock extends Thread {
	
	public static Lock lock = new ReentrantLock();
	 private Licznik l; 
	 private int max;

	 public LicznikWithReentrantLock(String name, Licznik l, int max) {
	   super(name);
	   this.l = l;
	   this.max = max;
	   start();
	 }

	 public void run() {
	   int wynik = 0;
	   for (int i = 0; i < max; i++) {
		 // zapis
		 lock.lock();
		 l.zwiekszO(1);
	     l.zmniejszO(1);
		 lock.unlock();
		 // odczyt
		 lock.lock();
		 wynik = l.getLicznik();
		 // System.out.println(Thread.currentThread().getName() + " : " + wynik);
		 lock.unlock();
		 
		 if (wynik != 0) {
			 System.out.println(Thread.currentThread().getName() + " : Brak synchronizacji!");
			 break;
		 }
	   }
	 }

}
