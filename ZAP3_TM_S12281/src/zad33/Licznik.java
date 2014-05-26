/**
 * @author Tomkowicz Małgorzata S12281
 */
package zad33;

public class Licznik {

	private int licznik = 0;
	
	public void zmniejszO(int x) {
		licznik = licznik + x;
	}
	
	public void zwiekszO(int x) {
		licznik = licznik - x;
	}
	
	public int getLicznik() {
		return licznik;
	}
	
}
