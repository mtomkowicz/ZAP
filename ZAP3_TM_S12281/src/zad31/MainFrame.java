package zad31;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import zad31.model.AbstractTask;
import zad31.model.EndlessTask;
import zad31.model.FibonacciTask;
import zad31.model.PowerTask;

public class MainFrame extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4380454293239142196L;
	
	
	private Map<AbstractTask, Future<String>> submittedTasks = new HashMap<>();
	private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	private List<AbstractTask> tasks = new ArrayList<>();

	public MainFrame() {
		setTitle("ZAP3_TM_S12281 Zad31");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		prepareView();
		setSize(600, 600);
	}
	
	private void prepareView() {
		setLayout(new BorderLayout(10, 10));
		
		final DefaultListModel<AbstractTask> taskModel = new DefaultListModel<>();
		final JList<AbstractTask> taskList = new JList<>(taskModel);
		taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		JPanel taskPanel = new JPanel(new BorderLayout(10, 10));
		taskPanel.add(new JScrollPane(taskList), BorderLayout.CENTER);
		add(taskPanel, BorderLayout.CENTER);
		
		JPanel taskControlPanel = new JPanel();
		JButton cancelButton = new JButton("Anuluj");
		JButton addTasksButton = new JButton("Uruchom zadania");
				
		taskControlPanel.add(addTasksButton);
		taskControlPanel.add(cancelButton);
				
		final JButton showResultsButton = new JButton("Wyniki");
		showResultsButton.setEnabled(false);
		
		taskControlPanel.add(showResultsButton);
		
		JButton exitButton = new JButton("Zakończ program");
		taskControlPanel.add(exitButton);
		
		taskPanel.add(taskControlPanel, BorderLayout.NORTH);
		
		JPanel statusPanel = new JPanel(new BorderLayout());
		statusPanel.setPreferredSize(new Dimension(640, 240));
		
		final JLabel statusLabel = new JLabel("Stan: --nie wybrano zadania--");
		statusLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 3, 0));
		statusPanel.add(statusLabel, BorderLayout.NORTH);
		
		final JEditorPane editorPane = new JEditorPane("text/plain", "--brak wyników--");
		editorPane.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(editorPane);
		
		statusPanel.add(scrollPane, BorderLayout.CENTER);
		statusPanel.add(Box.createHorizontalStrut(10), BorderLayout.WEST);
		statusPanel.add(Box.createHorizontalStrut(10), BorderLayout.EAST);

		add(statusPanel, BorderLayout.SOUTH);
		
		addTasksButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				tasks.add(new PowerTask("Power 10", 10, 500));
				tasks.add(new EndlessTask("Endless"));
				tasks.add(new FibonacciTask("Fibonacci", 100));
				for (AbstractTask task : tasks) {
					Future<String> submitted = cachedThreadPool.submit(task);
					submittedTasks.put(task, submitted);
					taskModel.addElement(task);
				}
			}
		});
		
		
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				cachedThreadPool.shutdownNow();
				
				
				dispose();
			}
		});
		
		taskList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				AbstractTask task = taskList.getSelectedValue();
				Future<String> taskStatus = submittedTasks.get(task);
				if (taskStatus.isDone() && !taskStatus.isCancelled()) {
					statusLabel.setText("Stan: Zakończone");
					showResultsButton.setEnabled(true);
				} else if (taskStatus.isCancelled()) {
					statusLabel.setText("Stan: Anulowane");
					showResultsButton.setEnabled(false);					
				} else {
					statusLabel.setText("Stan: W toku");
					showResultsButton.setEnabled(false);										
				}				
			}
		});
		
		showResultsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractTask task = taskList.getSelectedValue();
				Future<String> result = submittedTasks.get(task);
				if (result.isCancelled()) {
					editorPane.setText("Brak wyników. Zadanie zostało anulowane...");					
				} else if (!result.isDone()) {
					editorPane.setText("Brak wyników. Zadanie jeszcze się nie skończyło...");
				} else {
					try {
						editorPane.setText(result.get());
					} catch (InterruptedException | ExecutionException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractTask task = taskList.getSelectedValue();
				if (task != null) {
					Future<String> taskToCancel = submittedTasks.get(task);
					if (taskToCancel.cancel(true)) {
						statusLabel.setText("Stan: Anulowane");
					}					
				}
			}
		});
	}
	
	
	
	@Override
	public void run() {
		setVisible(true);
		Thread.yield();
	}

}
