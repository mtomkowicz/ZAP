/**
 * @author Tomkowicz Małgorzata S12281
 */
package zad31.model;


public class EndlessTask extends AbstractTask {

	public EndlessTask(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String call() throws Exception {
		while (true) {
			this.setStatus(TaskStatus.InProgres);
			if (Thread.currentThread().isInterrupted())
			{
				this.setStatus(TaskStatus.Aborted);
				break;
			}
		}
		
		return "EndlessTask was interrupted!!!";
	}
	
	

}
