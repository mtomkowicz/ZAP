package zad31.model;


public class FibonacciTask extends AbstractTask {

	private int max;
	
	public FibonacciTask(String name, int max) {
		super(name);
		this.max = max;
	}

	@Override
	public String call() throws Exception {
		
			StringBuilder sb = new StringBuilder();
			int n = 0;
			double f1 = 1;
			double f2 = 0;
			while (n < max) {
				if (n == 0) {
					sb.append(0);
				} else if (n == 1) {
					sb.append(1);
				} else {
					double result = f1 + f2;
					sb.append(result);
					f2 = f1;
					f1 = result;
				}
				sb.append("\n");
				n++;
			}
			
			return sb.toString();
	}

	
	
}
