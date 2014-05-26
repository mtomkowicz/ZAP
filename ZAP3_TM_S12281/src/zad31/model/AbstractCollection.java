package zad31.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public abstract class AbstractCollection<T> {

	protected String[] columnNames;
	protected List<T> Items;
	public abstract DefaultTableModel ToTableModel();

	public AbstractCollection(){
		Items = new ArrayList<T>();
	}
	public void add(T item) {
		Items.add(item);
	}
}
