package zad31.model;

import java.util.concurrent.Callable;


public abstract class AbstractTask implements Callable<String> {

	private final String name;
	protected static int counter = 0;
	
	public AbstractTask(String name) {
		this.name = name;
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

}
