/**
 * @author Tomkowicz Małgorzata S12281
 */
package zad33;

public class LicznikThread extends Thread {

	public static Object lock = new Object();
	 private Licznik l;
	 private int max;

	 public LicznikThread(String name, Licznik l, int max) {
	   super(name);
	   this.l = l;
	   this.max = max;
	   start();
	 }

	 public void run() {
	   int wynik = 0;
	   for (int i = 0; i < max; i++) {
			 // write
			 synchronized (lock) {
				 l.zwiekszO(1);
			     l.zmniejszO(1);
			 }
			 // read
			 synchronized (lock) {
				 wynik = l.getLicznik();
				 //System.out.println(Thread.currentThread().getName() + " : " + wynik);
			 }
			 if (wynik != 0) {
				 System.out.println(Thread.currentThread().getName() + " : Brak synchronizacji!");
				 break;
			 }
	   }
	 }
	
}
