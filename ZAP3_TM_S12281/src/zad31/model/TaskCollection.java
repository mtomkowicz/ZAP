package zad31.model;

import javax.swing.table.DefaultTableModel;


public class TaskCollection extends AbstractCollection<AbstractTask> {

final Class<?>[] columnClasses = {String.class, String.class}; 
    
    public TaskCollection(){
    	super();
    	columnNames = new String[]{"Name", "Status"};
    }
	
	@Override
	public DefaultTableModel ToTableModel() {
		int i = Items.size();	
		Object[][] data = new Object[i][4];
		
		while (--i >= 0){
			AbstractTask t = Items.get(i);
			try {
				data[i] = new Object[]{ t.getName(), t.getStatus()};
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return new DefaultTableModel(data, columnNames){
		      /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		      public Class<?> getColumnClass(int columnIndex)
		      {
		        return columnClasses[columnIndex];
		      }
		};
	}

}
