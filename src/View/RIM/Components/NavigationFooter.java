package View.RIM.Components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import Images.RIM.ImageHelper;

import java.awt.event.*;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class NavigationFooter extends JPanel
{
	String step;

	public NavigationFooter(String step)
	{
		super();
		if (step.equals("step1"))
		{
			setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			setBackground(new Color(255, 204, 51));
			setLayout(new MigLayout("", "[3.00,grow][58.00,grow][grow]", "[64px]"));

			JLabel label = new JLabel("");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent arg0)
				{
					navigationButtonClicked();
				}
			});

			JLabel lblOf = new JLabel("(1 of 2)");
			lblOf.setFont(new Font("Segoe UI", Font.BOLD, 14));
			add(lblOf, "flowx,cell 0 0,alignx right");
			label.setIcon(new ImageIcon(ImageHelper.class.getResource("nextIcon.png")));
			add(label, "cell 1 0,alignx center");

			JLabel lblNewLabel = new JLabel("Next");
			lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
			add(lblNewLabel, "cell 2 0,alignx left");
		}
		else
		{
			setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			setBackground(new Color(255, 204, 51));
			setLayout(new MigLayout("", "[3.00,grow][252.00,grow][grow]", "[64px]"));

			JLabel label2 = new JLabel("");
			label2.setHorizontalAlignment(SwingConstants.RIGHT);
			label2.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent arg0)
				{
					sendButtonClicked();
				}
			});
			
						JLabel label = new JLabel("");
						label.setHorizontalAlignment(SwingConstants.LEFT);
						label.addMouseListener(new MouseAdapter()
						{
							public void mouseClicked(MouseEvent arg0)
							{
								navigationButtonClicked();
							}
						});
						
									label.setIcon(new ImageIcon(ImageHelper.class.getResource("prevIcon.png")));
									add(label, "flowx,cell 0 0,alignx left");

			label2.setIcon(ImageHelper.loadImageIcon("sendInvitationIcon.png", "", -1, 64, 5));
			add(label2, "flowy,cell 2 0,alignx center,growy");
			
						JLabel lblNewLabel = new JLabel("Send invitation");
						lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
						add(lblNewLabel, "cell 2 0,alignx right");
						
						JLabel lblPrevious = new JLabel("Previous");
						lblPrevious.setFont(new Font("Segoe UI", Font.BOLD, 14));
						add(lblPrevious, "cell 0 0");
		}
	}

	//Methods to override
	
	public void navigationButtonClicked()
	{
	}

	public void sendButtonClicked()
	{
	}
	
}
