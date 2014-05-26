/**
 * @author Tomkowicz Małgorzata S12281
 */
package zad31.model;


public class EndlessTask extends AbstractTask {

	public EndlessTask(String name) {
		super(name);
	}

	@Override
	public String call() throws Exception {
		while (true) {
			if (Thread.currentThread().isInterrupted())
				break;
			
		}
		
		return "Niekończące się zadanie zostało przerwane...";
	}
	

}
