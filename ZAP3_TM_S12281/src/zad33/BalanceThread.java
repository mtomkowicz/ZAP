/**
 * @author Tomkowicz Małgorzata S12281
 */
package zad33;

public class BalanceThread extends Thread {

	 private Balance b;
	 private int max;

	 public BalanceThread(String name, Balance b, int max) {
	   super(name);
	   this.b = b;
	   this.max = max;
	   start();
	 }

	 public void run() {
	   int wynik = 0;
	   for (int i = 0; i < max; i++) {
			 // write
			 synchronized (b) {
				 b.increase(1);
			     b.decrease(1);
			 }
			 // read
			 synchronized (b) {
				 wynik = b.getBalance();
				 //System.out.println(Thread.currentThread().getName() + " : " + wynik);
			 }
			 if (wynik != 0) {
				 System.out.println(Thread.currentThread().getName() + " : Brak synchronizacji!");
				 break;
			 }
	   }
	 }
	
}
