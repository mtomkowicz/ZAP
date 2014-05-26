/**
 * @author Tomkowicz Małgorzata S12281
 */

package zad33;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LicznikThreadWithRWLocks extends Thread {

	public static ReentrantReadWriteLock rrwLock = new ReentrantReadWriteLock();
	 private Licznik l; 		
	 private int max; 			
	 private Lock readLock;
	 private Lock writeLock;
	 
	 public LicznikThreadWithRWLocks(String name, Licznik l, int max) {
	   super(name);
	   this.l = l;
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
	     l.zwiekszO(1);
	     l.zmniejszO(1);
		 writeLock.unlock();
		 // odczyt
		 readLock.lock();
		 wynik = l.getLicznik();
		 //System.out.println(Thread.currentThread().getName() + " : " + wynik);
		 readLock.unlock();

		 if (wynik != 0) {
			 System.out.println(Thread.currentThread().getName() + " : Brak synchronizacji!");
			 break;
		 }
	   }
	 }
}
