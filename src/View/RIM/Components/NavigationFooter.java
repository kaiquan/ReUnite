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
	public NavigationFooter()
	{
		super();
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBackground(new Color(255, 204, 51));
		setLayout(new MigLayout("", "[3.00,grow][58.00,grow][grow]", "[64px]"));

		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0)
			{
				nextButtonClicked();
			}
		});

		JLabel lblOf = new JLabel("(1 of 2)");
		lblOf.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblOf, "flowx,cell 0 0,alignx right");
		label.setIcon(new ImageIcon(ImageHelper.class.getResource("nextIcon.png")));
		add(label, "cell 1 0,alignx center");

		JLabel lblNewLabel = new JLabel("Next");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblNewLabel, "cell 2 0,alignx left");
	}

	public void nextButtonClicked()
	{
		
	}
}
