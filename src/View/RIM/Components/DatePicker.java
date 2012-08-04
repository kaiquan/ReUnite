package View.RIM.Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class DatePicker
{
	int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
	int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
	JComboBox<Integer> yearComboBox;
	String day = "";
	JDialog d;
	JButton[] button = new JButton[49];

	public DatePicker()
	{
		d = new JDialog();
		d.setModal(true);
		String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
		JPanel p1 = new JPanel(new GridLayout(7, 7));
		p1.setOpaque(false);
		p1.setPreferredSize(new Dimension(430, 200));

		ArrayList<Integer> tempList = new ArrayList<Integer>();
		for (int i = 1940; i <=Calendar.YEAR; i++)
		{
			tempList.add(i);
		}
		Integer[] years = new Integer[tempList.size()];
		tempList.toArray(years);
		yearComboBox = new JComboBox<Integer>(years);
		
		for (int x = 0; x < button.length; x++)
		{
			final int selection = x;
			button[x] = new JButton();
			button[x].setFocusPainted(false);
			button[x].setForeground(Color.BLACK);
			button[x].setFont(new Font("Segoe UI", Font.PLAIN, 12));
			button[x].setOpaque(false);
			button[x].setBorderPainted(false);
			button[x].setBackground(Color.white);
			if (x > 6) button[x].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					day = button[selection].getActionCommand();
					d.dispose();

				}
			});
			if (x < 7)
			{
				button[x].setText(header[x]);
				button[x].setForeground(Color.RED);
			}
			p1.add(button[x]);
		}
		JPanel p2 = new JPanel(new GridLayout(1, 3));
		p2.setOpaque(false);

		JButton previous = new JButton("<< Previous");
		previous.setOpaque(false);
		previous.setBorderPainted(false);
		previous.setForeground(Color.BLACK);
		previous.setContentAreaFilled(false);
		previous.setBackground(null);
		previous.setFont(new Font("Segoe UI", Font.BOLD, 14));
		previous.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				month--;
				displayDate();
			}
		});
		p2.add(previous);
		p2.add(yearComboBox);
		JButton next = new JButton("Next >>");
		next.setOpaque(false);
		next.setBorderPainted(false);
		next.setForeground(Color.BLACK);
		next.setContentAreaFilled(false);
		next.setBackground(null);
		next.setFont(new Font("Segoe UI", Font.BOLD, 14));
		next.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				month++;
				displayDate();
			}
		});
		p2.add(next);
		d.add(p1, BorderLayout.CENTER);
		d.add(p2, BorderLayout.SOUTH);
		d.pack();
		d.setLocationRelativeTo(null);
		displayDate();
	}

	public void setVisible(boolean visible)
	{
		d.setVisible(visible);
	}

	public void displayDate()
	{
		for (int x = 7; x < button.length; x++)
			button[x].setText("");
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(year, month, 1);
		int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
		int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
		for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
			button[x].setText("" + day);
		// yearComboBox.setText(sdf.format(cal.getTime()));

		yearComboBox.setForeground(Color.BLACK);
		yearComboBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				year = (int) yearComboBox.getSelectedItem();
			}
			
		});
		yearComboBox.setFont(new Font("Segoe UI", Font.BOLD, 14));
		d.setTitle("Date Picker");
	}

	public String getPickedDate()
	{
		if (day.equals("")) return day;
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(year, month, Integer.parseInt(day));
		return sdf.format(cal.getTime());
	}

	public void setPickedDate(String date)
	{
		Scanner sc = new Scanner(date);
		sc.useDelimiter("-");
		day = sc.next();
		month = sc.nextInt();
		year = sc.nextInt();
	}

	public static void main(String[] args)
	{

	}
}
