/**
 * @author Tomkowicz Małgorzata S12281
 */
package zad33;

public class Balance {

	private int balance = 0;
	
	public void increase(int x) {
		balance = balance + x;
	}
	
	public void decrease(int x) {
		balance = balance - x;
	}
	
	public int getBalance() {
		return balance;
	}
	
}
