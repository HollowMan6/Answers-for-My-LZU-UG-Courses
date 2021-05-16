import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Mycalendar {
	public static void main(String[] args) {
		new CalendarWindow();
	}
}

class CalendarWindow extends JFrame implements ChangeListener {
	JSpinner jsp = null;
	CalPane calPane = null;
	Container container = null;

	public CalendarWindow() {
		SpinnerModel model = new SpinnerNumberModel(2019, 1990, 2090, 1);
		jsp = new JSpinner(model);
		jsp.addChangeListener(this);
		jsp.setEditor(new JSpinner.NumberEditor(jsp, "#"));
		container = this.getContentPane();
		container.add(jsp, BorderLayout.NORTH);
		calPane = new CalPane(2019);
		container.add(calPane, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 100);
		setSize(400, 240);
		setVisible(true);
		validate();
	}

	public void stateChanged(ChangeEvent arg0) {
		container.remove(calPane);
		int year = (Integer) jsp.getValue();
		calPane = new CalPane(year);
		container.add(calPane, BorderLayout.CENTER);
		container.validate();
		validate();
	}
}

class CalPane extends JPanel implements ActionListener {
	JTable table;
	String a[][] = null;
	String colName[] = { "日", "一", "二", "三", "四", "五", "六" };
	JButton nextMonthButton, previousMonthButton;
	int year = 2019, month = 1;
	JLabel showMessageJLabel = new JLabel("", JLabel.CENTER);

	public CalPane(int y) {
		year = y;
		setLayout(new BorderLayout());
		a = getclendar(year, month);
		table=new JTable(a,colName);
		table.setRowSelectionAllowed(false);
		nextMonthButton = new JButton("下月");
		nextMonthButton.addActionListener(this);
		previousMonthButton = new JButton("上月");
		previousMonthButton.addActionListener(this);
		JPanel pNorthJPanel = new JPanel(), pSouthJPanel = new JPanel();
		pNorthJPanel.add(previousMonthButton);
		pNorthJPanel.add(nextMonthButton);
		pSouthJPanel.add(showMessageJLabel);
		showMessageJLabel.setText("日历" + year + "年" + month + "月");
		add(new JScrollPane(table), BorderLayout.CENTER);
		add(pNorthJPanel, BorderLayout.NORTH);
		add(pSouthJPanel, BorderLayout.SOUTH);
		validate();
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == nextMonthButton) {
			month++;
			if (month > 12) {
				month = 1;
			}
			a = getclendar(year, month);
			table.repaint();
			for(int i=0;i<6;++i) {
				for(int j=0;j<7;++j) {
					System.out.print(a[i][j]);
				}
				System.out.println();
			}
			System.out.println("repaint");
		} else if (event.getSource() == previousMonthButton) {
			month--;
			if (month < 1) {
				month = 12;
			}
			a = getclendar(year, month);
			table.repaint();
		}
		showMessageJLabel.setText("日历" + year + "年" + month + "月");
	}

	public static String[][] getclendar(int y, int m) {
		String a[][] = new String[6][7];
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(y, m-1, 0);
		int startday = calendar.get(Calendar.DAY_OF_WEEK);
		int totday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println(startday);
		System.out.println(totday);
		for (int i = 0; i < startday; ++i)
			a[0][i] = " ";
		int n = 1;
		for (int i = startday; i < 7; ++i)
			a[0][i] = "" + n++;
		for (int i = 1; i < 6; ++i) {
			for (int j = 0; j < 7; ++j) {
				if (n > totday) {
					a[i][j]=" ";
				}
				else {
				a[i][j] = "" + n++;
				}
			}
		}
		
		return a;
	}
}
