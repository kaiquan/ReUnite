package View.SOM;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class testing {

	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="272,3"
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel3 = null;
	private JButton jButton = null;
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(714, 298));
			jFrame.setVisible(true);
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
			jContentPane.setBackground(SystemColor.control);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getJPanel1(), null);
			jContentPane.add(getJPanel2(), null);
			jContentPane.add(getJPanel3(), null);
			jContentPane.add(getJButton(), null);
			t.setSize(new Dimension(210, 113));
			t.add(getJPanel3());
	
		}
		return jContentPane;
	}


	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.setBounds(new Rectangle(12, 19, 292, 104));
			jPanel.setBackground(SystemColor.textHighlight);
			jPanel.addMouseListener(new java.awt.event.MouseAdapter() {   
				public void mouseEntered(java.awt.event.MouseEvent e) {    
					System.out.println("mouseEntered()"); // TODO Auto-generated Event stub mouseEntered()
				}
				public void mouseReleased(java.awt.event.MouseEvent e) {
					System.out.println("mouseReleased()"); // TODO Auto-generated Event stub mouseReleased()
				}
			});
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.setBounds(new Rectangle(12, 142, 292, 94));
			jPanel1.setBackground(SystemColor.textHighlight);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new GridBagLayout());
			jPanel2.setBounds(new Rectangle(329, 136, 320, 102));
			jPanel2.setBackground(SystemColor.textHighlight);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setLayout(new GridBagLayout());
			jPanel3.setBounds(new Rectangle(335, 23, 156, 97));
			jPanel3.setBackground(SystemColor.textHighlight);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(495, 36, 143, 79));
			jButton.setBackground(SystemColor.textHighlight);
			jButton.setIcon(new ImageIcon(getClass().getResource("/Images/SOM/closer.gif")));
			jButton.setHorizontalTextPosition(SwingConstants.RIGHT);
			jButton.setBorderPainted(false);
			JPanel p= new JPanel();
			p.add(new JLabel("dsa"));
			jButton.add(p);
			p.setBackground(SystemColor.textHighlight);
			jButton.setText("dsadsfdsffffffffffffffffffffff");
		}
		return jButton;
	}

	public static void main(String a[]){
	testing t= new testing();
	t.getJFrame().setVisible(true);
}
}
