package View.PRFM;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JTextArea;
import java.awt.Rectangle;

public class ResultDetails {

	private int fqCode;
	private String result;
	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="169,10"
	private JPanel jContentPane = null;
	private JTextArea jTextArea = null;

	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setTitle("Feedback Result for Feedback Question #" + fqCode);
			jFrame.setSize(new Dimension(500, 200));
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
			jContentPane.add(getJTextArea(), null);
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setBounds(new Rectangle(43, 25, 400, 110));
			jTextArea.setBorder(new JTextField().getBorder());
			jTextArea.setEditable(false);
			jTextArea.setLineWrap(true);
			jTextArea.setWrapStyleWord(true);
			jTextArea.setText(result);
		}
		return jTextArea;
	}

	public static void main(String[] args){
		ResultDetails det = new ResultDetails();
		det.getJFrame().setVisible(true);
	}
	
	public ResultDetails(){
		
	}
	
	public ResultDetails(int fqCode, String result){
		this.fqCode = fqCode;
		this.result = result;
	}

}
