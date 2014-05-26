/**
 * @author Tomkowicz Małgorzata S12281
 */

package zad31.model;

import java.util.concurrent.Callable;


public abstract class AbstractTask implements Callable<String> {

	private final String name;
	protected static int counter = 0;
	private TaskStatus status;
	
	public AbstractTask(String name) {
		this.name = name;
		counter++;
		status = TaskStatus.New;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof AbstractTask) {
			AbstractTask task = (AbstractTask) obj;
			return name.equals(task.name);
		}
		
		return false;
	}


	@Override
	public String toString() {
		return "Task: " + name;
	}

	public TaskStatus getStatus() {
	
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}



	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
