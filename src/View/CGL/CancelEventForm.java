package View.CGL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class CancelEventForm {

	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="66,8"
	private JPanel jContentPane = null;
	private JPanel jContentPane1 = null;
	private JScrollPane pne = null;
	private JTree tree = null;
	private JLabel lblEventName = null;
	private JLabel lblEventDescript = null;
	private JLabel lblNoOfGuests = null;
	private JLabel lblEventTime = null;
	private JTextField textField = null;
	private JTextField textField_1 = null;
	private JTextField textField_2 = null;
	private JTextField textField_3 = null;
	private JLabel lblTotalPrice = null;
	private JTextField textField_4 = null;
	private JLabel lblEventDetails = null;
	private JSeparator separator = null;
	private JSeparator separator_1 = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JLabel lblNewLabel = null;
	private JTextField jTextField = null;
	private JLabel jLabel1 = null;
	private JScrollPane pne1 = null;
	private JTextArea jTextArea = null;
	private JLabel lblBallroom = null;
	private JTextField textField_5 = null;
	private JLabel jLabel2 = null;
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(722, 359));
			jFrame.setTitle("Cancel Event");
			jFrame.setContentPane(getJContentPane());
		}
		return jFrame;
	}

	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJContentPane1(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jContentPane1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane1() {
		if (jContentPane1 == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(150, 162, 12, 15));
			jLabel2.setText("+");
			jLabel2.setFont(new Font("Dialog", Font.BOLD, 14));
			lblBallroom = new JLabel("Ballroom :");
			lblBallroom.setBounds(new java.awt.Rectangle(437,124,78,14));
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(153, 222, 109, 16));
			jLabel1.setText("Event Description :");
			lblNewLabel = new JLabel("Location Date :");
			lblNewLabel.setBounds(new java.awt.Rectangle(154,125,74,14));
			lblNewLabel.setText("Location :");
			lblEventDetails = new JLabel("Event Details");
			lblEventDetails.setBounds(new java.awt.Rectangle(356,11,96,20));
			lblEventDetails.setFont(new Font("SansSerif", Font.BOLD, 13));
			lblTotalPrice = new JLabel("Total Price :");
			lblTotalPrice.setBounds(new java.awt.Rectangle(161,163,69,14));
			lblEventTime = new JLabel("Event Time :");
			lblEventTime.setBounds(new java.awt.Rectangle(435,86,74,14));
			lblNoOfGuests = new JLabel("No Of Guests :");
			lblNoOfGuests.setBounds(new java.awt.Rectangle(435,48,80,14));
			lblEventDescript = new JLabel("Event Date :");
			lblEventDescript.setBounds(new java.awt.Rectangle(154,87,66,14));
			lblEventName = new JLabel("Event Name :");
			lblEventName.setBounds(new java.awt.Rectangle(154,49,76,14));
			jContentPane1 = new JPanel();
			jContentPane1.setLayout(null);
			jContentPane1.setBounds(new Rectangle(9, 8, 10, 10));
			jContentPane1.add(getPne(), getPne().getName());
			jContentPane1.add(lblEventName, lblEventName.getName());
			jContentPane1.add(lblEventDescript, lblEventDescript.getName());
			jContentPane1.add(lblNoOfGuests, lblNoOfGuests.getName());
			jContentPane1.add(lblEventTime, lblEventTime.getName());
			jContentPane1.add(getTextField(), getTextField().getName());
			jContentPane1.add(getTextField_1(), getTextField_1().getName());
			jContentPane1.add(getTextField_2(), getTextField_2().getName());
			jContentPane1.add(getTextField_3(), getTextField_3().getName());
			jContentPane1.add(lblTotalPrice, lblTotalPrice.getName());
			jContentPane1.add(getTextField_4(), getTextField_4().getName());
			jContentPane1.add(lblEventDetails, lblEventDetails.getName());
			jContentPane1.add(getSeparator(), getSeparator().getName());
			jContentPane1.add(getSeparator_1(), getSeparator_1().getName());
			jContentPane1.add(getJButton1(), null);
			jContentPane1.add(getJButton2(), null);
			jContentPane1.add(lblNewLabel, lblNewLabel.getName());
			jContentPane1.add(getJTextField(), null);
			jContentPane1.add(jLabel1, null);
			jContentPane1.add(getPne1(), getPne1().getName());
			jContentPane1.add(lblBallroom, lblBallroom.getName());
			jContentPane1.add(getTextField_5(), getTextField_5().getName());
			jContentPane1.add(jLabel2, null);
		}
		return jContentPane1;
	}

	/**
	 * This method initializes tree	
	 * 	
	 * @return javax.swing.JTree	
	 */
	private JTree getTree() {
		if (tree == null) {
			tree = new JTree();
		}
		return tree;
	}

	/**
	 * This method initializes pne	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getPne() {
		if (pne == null) {
			pne = new JScrollPane(getTree(),
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			pne.setBounds(new java.awt.Rectangle(10,14,119,264));
			pne.setVisible(true);
		}
		return pne;
	}

	/**
	 * This method initializes textField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(new java.awt.Rectangle(244,46,137,20));
			textField.setColumns(10);
		}
		return textField;
	}

	/**
	 * This method initializes textField_1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setBounds(new java.awt.Rectangle(244,84,135,20));
			textField_1.setColumns(10);
		}
		return textField_1;
	}

	/**
	 * This method initializes textField_2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextField_2() {
		if (textField_2 == null) {
			textField_2 = new JTextField();
			textField_2.setBounds(new java.awt.Rectangle(526,45,135,20));
			textField_2.setColumns(10);
		}
		return textField_2;
	}

	/**
	 * This method initializes textField_3	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextField_3() {
		if (textField_3 == null) {
			textField_3 = new JTextField();
			textField_3.setBounds(new java.awt.Rectangle(526,83,135,20));
			textField_3.setColumns(10);
		}
		return textField_3;
	}

	/**
	 * This method initializes textField_4	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextField_4() {
		if (textField_4 == null) {
			textField_4 = new JTextField();
			textField_4.setBounds(new java.awt.Rectangle(245,160,135,20));
			textField_4.setColumns(10);
		}
		return textField_4;
	}

	/**
	 * This method initializes separator	
	 * 	
	 * @return javax.swing.JSeparator	
	 */
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBounds(new java.awt.Rectangle(132,11,20,276));
			separator.setOrientation(SwingConstants.VERTICAL);
		}
		return separator;
	}

	/**
	 * This method initializes separator_1	
	 * 	
	 * @return javax.swing.JSeparator	
	 */
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
			separator_1.setBounds(new java.awt.Rectangle(10,285,694,8));
		}
		return separator_1;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(344, 263, 144, 15));
			jButton1.setText("Send Email To RI");
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setBounds(new Rectangle(26, 294, 86, 21));
			jButton2.setText("Refresh");
		}
		return jButton2;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(244, 122, 137, 20));
		}
		return jTextField;
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setBounds(new Rectangle(281, 213, 388, 35));
			jTextArea.setWrapStyleWord(true);
			jTextArea.setLineWrap(true);
		}
		return jTextArea;
	}

	/**
	 * This method initializes pne1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getPne1() {
		if (pne1 == null) {
			pne1 = new JScrollPane(getJTextArea(),
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			pne1.setBounds(new Rectangle(281, 213, 388, 35));
			pne1.setVisible(true);
		}
		return pne1;
	}

	/**
	 * This method initializes textField_5	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextField_5() {
		if (textField_5 == null) {
			textField_5 = new JTextField();
			textField_5.setBounds(new java.awt.Rectangle(526,121,135,20));
			textField_5.setColumns(10);
		}
		return textField_5;
	}

}
