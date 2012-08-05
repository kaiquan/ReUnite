package View.RIM.Components;
import javax.swing.JPanel;
import Model.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class EntertainmentPanel extends JPanel{

	public EntertainmentPanel(Entertainment entertainment)
	{
		setLayout(null);
		
		JLabel lblEntertainmentTitle = new JLabel("Title:");
		lblEntertainmentTitle.setBounds(7, 7, 114, 14);
		add(lblEntertainmentTitle);
		
		JLabel lblEntertainmentTitleText = new JLabel("");
		lblEntertainmentTitleText.setBounds(17, 32, 66, 14);
		lblEntertainmentTitleText.setText(entertainment.getEntertainmentTitle());
		add(lblEntertainmentTitleText);
		
		JLabel lblDescription = new JLabel("Description: ");
		lblDescription.setBounds(10, 72, 60, 14);
		add(lblDescription);
		
		JTextArea textAreaDescription = new JTextArea();
		textAreaDescription.setBackground(SystemColor.control);
		textAreaDescription.setLineWrap(true);
		textAreaDescription.setBounds(20, 97, 293, 99);
		textAreaDescription.setText(entertainment.getEntertainmentDescription());
		add(textAreaDescription);
		
		
		JLabel entertainmentFinalPriceLbl = new JLabel(Double.toString(entertainment.getEntertainmentFinalPrice()));
		add(entertainmentFinalPriceLbl);
		
		JLabel lblFinalPrice = new JLabel("Final price:");
		lblFinalPrice.setBounds(12, 207, 60, 14);
		add(lblFinalPrice);
		
		JLabel label = new JLabel("");
		label.setBounds(17, 232, 46, 14);
		label.setText(Double.toString(entertainment.getEntertainmentFinalPrice()));
		add(label);
	}
}
