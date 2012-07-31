/*********************************************************************
Program Name	:	CalendarForm.java
Description		:	A CalendarForm class that is the GUI for a calender
Done by			:	Lee Kai Quan
Admin No		:	114173S
Module Group	:	IT2297-08
Last Edited		:	6-2-2012
**********************************************************************/
package View.SOM;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.*;

public class CalendarForm {
	int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
	int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);;
	JLabel l = new JLabel("", JLabel.CENTER);
	String day = "";  //  @jve:decl-index=0:
	JDialog d;
	JButton[] button = new JButton[49];

	JPanel p1 = new JPanel(new GridLayout(7, 7));
	
	
	//creates the basic frame and header
	public CalendarForm(JFrame parent) {
		d = new JDialog();
		d.setModal(true);
		String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
		//JPanel p1 = new JPanel(new GridLayout(7, 7));
		p1.setPreferredSize(new Dimension(430, 200));

		for (int x = 0; x < button.length; x++) {
			final int selection = x;
			button[x] = new JButton();
			button[x].setFocusPainted(false);
			button[x].setBackground(Color.white);
			if (x > 6){
				button[x].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						day = button[selection].getActionCommand();
						d.dispose();
					}
				});
			}
			if (x < 7) {
				button[x].setText(header[x]);
				button[x].removeNotify();
				button[x].setEnabled(false);
				button[x].setForeground(Color.red);
				button[x].setBorderPainted(false);
			}
			p1.add(button[x]);
		}
	
		JPanel p2 = new JPanel(new GridLayout(1, 3));
		JButton previous = new JButton("<< Previous");
		previous.setFocusable(false);
		previous.setFocusPainted(false);
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				month--;
				displayDate();
			}
		});
		p2.add(previous);
		p2.add(l);
		JButton next = new JButton("Next >>");
		next.setFocusable(false);
		next.setFocusPainted(false);
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				month++;
				displayDate();
			}
		});
		p2.add(next);
		d.add(p1, BorderLayout.CENTER);
		d.add(p2, BorderLayout.SOUTH);
		d.pack();
		d.setLocationRelativeTo(parent);
		displayDate();
		d.setVisible(true);
	}

	
	
	
	
	
	
	
	
	
	//sets the date and the validation 
	public void displayDate() {
		//creates the buttons
		for (int x = 7; x < button.length; x++){
			button[x].setText("");
			button[x].setEnabled(false);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(year, month, 1);
		int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
		int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
		
		//writting the date numbers
		for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++){
			button[x].setText("" + day);
		}
		l.setText(sdf.format(cal.getTime()));
		d.setTitle("Select Prefered Date");
	
		//disabe the non use buttons
		 for(int i=0;i<button.length;i++){
	        	String text=button[i].getText();
	        	if(!text.equals("")){
	        		button[i].setEnabled(true);
	        		button[i].addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent ae) {
	                        d.dispose();
	                    }
	                });  
	        	}
	        	else{
	        		button[i].setEnabled(false);
	        	}
	        }
		 for(int i=0; i<7;i++){
			 button[i].removeNotify();
		 }
		//do another validation to check if data is yesterday
		 for(int i=dayOfWeek+6;i<button.length;i++){
			 if(button[i].getText().equals("")) return;
			 GregorianCalendar d2= new GregorianCalendar(year, month,Integer.parseInt(button[i].getText().toString()));
			 if(getDifference(d2)<0) button[i].setEnabled(false);
			 
		 }
	}
	
	
	
	
	
	//check the date if is yesterday and disable it
	public static long getDifference (GregorianCalendar d2){
		GregorianCalendar d1=new GregorianCalendar();
		long secDate = d1.getTimeInMillis();
		long firstDate = d2.getTimeInMillis();
		return (firstDate - secDate)/(24*60*60*1000);
	}

	
	//retuns the date selected
	public String getPickedDate() {
		if (day.equals(""))return day;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(year, month, Integer.parseInt(day));
		System.out.println(sdf.format(cal.getTime()));
		return sdf.format(cal.getTime());
	}
}


