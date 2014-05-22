/**
 * @author Tomkowicz Małgorzata S12281
 */
package zad31.model;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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

	@Override
	public boolean cancel(boolean arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String get() throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String get(long arg0, TimeUnit arg1) throws InterruptedException,
			ExecutionException, TimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
