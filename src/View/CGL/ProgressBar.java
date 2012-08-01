package View.CGL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ProgressBar {

	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="0,1"
	private JPanel jContentPane = null;
	private JProgressBar jProgressBar = null;
	private JLabel jLabel = null;
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	public JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(346, 145));
			jFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(25, 80, 285, 16));
			jLabel.setFont(new Font("Dialog", Font.BOLD, 12));
			jLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel.setText("");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJProgressBar(), BorderLayout.SOUTH);
			jContentPane.add(jLabel, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	private JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setValue(0);
			jProgressBar.setStringPainted(true);
			jProgressBar.setBounds(new Rectangle(26, 52, 285, 14));
		}
		return jProgressBar;
	}

	public void setValue(int x){
		jProgressBar.setValue(x);
		jProgressBar.updateUI();
		jContentPane.updateUI();		
	}
	
	public void setText(String text){
		jProgressBar.setString(text);
		jProgressBar.updateUI();
		jContentPane.updateUI();
	}
	
	public void setStatus(String text){
		jLabel.setText(text);
		jProgressBar.updateUI();
		jContentPane.updateUI();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ProgressBar pb = new ProgressBar();
		pb.getJFrame().setVisible(true);
		

	}

}
