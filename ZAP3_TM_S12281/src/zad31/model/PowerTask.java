/**
 * @author Tomkowicz Małgorzata S12281
 */
package zad31.model;

public class PowerTask extends AbstractTask {
	
	private double power;
	private int max;

	public PowerTask(String name, double power, int max) {
		super(name);
		this.power = power;
		this.max = max;
	}

	@Override
	public String call() throws Exception {
		int n = 0;
		StringBuilder sb = new StringBuilder();
		while (n >= 0 && n < max && !Double.isInfinite(power)) {
			if (n == 0)
				sb.append(1);
			else if (n == 1)
				sb.append("1: 11");
			else {
				power *= 11;
				sb.append(n);
				sb.append(": ");
				sb.append(power);
			}
			sb.append("\n");
			
			Thread.sleep(100);
			
			n++;
		}
		
		return sb.toString();
		
	}

	
	
}
