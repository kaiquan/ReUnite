package View.RIM.Components;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Model.Entertainment;
import Model.EntertainmentMenu;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class EntertainmentDetails {

	public EntertainmentDetails(Entertainment entertainment){
		getJTextField_title().setText(entertainment.getEntertainmentTitle().toString());
		getJTextArea_description().setText(entertainment.getEntertainmentDescription().toString());
		getJTextField_finalPrice().setText("$"+entertainment.getEntertainmentFinalPrice());
		
		//need the entertainment menu?
		EntertainmentMenu menu= new EntertainmentMenu();
		DefaultTableModel model= menu.RETRIEVE_ENTERTAINMENT_MENU_BY_ID(entertainment.getEntertainmentID());
		getJTable_entertainmentMenu().setModel(model);
		getJTable_entertainmentMenu().getColumnModel().getColumn(0).setPreferredWidth(565);
		getJTable_entertainmentMenu().getColumnModel().getColumn(1).setPreferredWidth(135);
		getJTable_entertainmentMenu().getColumnModel().getColumn(2).setPreferredWidth(698);
		
		getJScrollPane().setPreferredSize(new Dimension());
		getJScrollPane().setViewportView(getJPanel());
	}
	private JPanel jPanel = null;  //  @jve:decl-index=0:visual-constraint="121,51"
	private JLabel jLabel_tite = null;
	private JTextField jTextField_title = null;
	private JLabel jLabel_description = null;
	private JScrollPane jScrollPane_description = null;
	private JTextArea jTextArea_description = null;
	private JLabel jLabel_finalPrice = null;
	private JTextField jTextField_finalPrice = null;
	private JScrollPane jScrollPane = null;  //  @jve:decl-index=0:visual-constraint="26,178"
	private JScrollPane jScrollPane1 = null;
	private JTable jTable_entertainmentMenu = null;

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getJPanel() {
		if (jPanel == null) {
			jLabel_finalPrice = new JLabel();
			jLabel_finalPrice.setBounds(new Rectangle(15, 270, 150, 30));
			jLabel_finalPrice.setText("Price");
			jLabel_description = new JLabel();
			jLabel_description.setBounds(new Rectangle(13, 100, 150, 30));
			jLabel_description.setText("Description");
			jLabel_tite = new JLabel();
			jLabel_tite.setBounds(new Rectangle(15, 30, 150, 30));
			jLabel_tite.setText("Title :");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setSize(new Dimension(613, 501));
			jPanel.add(jLabel_tite, null);
			jPanel.add(getJTextField_title(), null);
			jPanel.add(jLabel_description, null);
			jPanel.add(getJScrollPane_description(), null);
			jPanel.add(jLabel_finalPrice, null);
			jPanel.add(getJTextField_finalPrice(), null);
			jPanel.add(getJScrollPane1(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextField_title	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_title() {
		if (jTextField_title == null) {
			jTextField_title = new JTextField();
			jTextField_title.setBounds(new Rectangle(165, 30, 400, 30));
		}
		return jTextField_title;
	}

	/**
	 * This method initializes jScrollPane_description	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane_description() {
		if (jScrollPane_description == null) {
			jScrollPane_description = new JScrollPane();
			jScrollPane_description.setBounds(new Rectangle(165, 100, 400, 150));
			jScrollPane_description.setViewportView(getJTextArea_description());
		}
		return jScrollPane_description;
	}

	/**
	 * This method initializes jTextArea_description	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea_description() {
		if (jTextArea_description == null) {
			jTextArea_description = new JTextArea();
		}
		return jTextArea_description;
	}

	/**
	 * This method initializes jTextField_finalPrice	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_finalPrice() {
		if (jTextField_finalPrice == null) {
			jTextField_finalPrice = new JTextField();
			jTextField_finalPrice.setBounds(new Rectangle(165, 270, 400, 30));
		}
		return jTextField_finalPrice;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setSize(new Dimension(94, 123));
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(15, 331, 550, 150));
			jScrollPane1.setViewportView(getJTable_entertainmentMenu());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTable_entertainmentMenu	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable_entertainmentMenu() {
		if (jTable_entertainmentMenu == null) {
			String []Header={"Entertainment Name","Price/hr","Description"};
			DefaultTableModel temp= new DefaultTableModel();
			temp.setColumnIdentifiers(Header);
			jTable_entertainmentMenu = new JTable(temp);
			jTable_entertainmentMenu.getColumnModel().getColumn(0).setPreferredWidth(565);
			jTable_entertainmentMenu.getColumnModel().getColumn(1).setPreferredWidth(135);
			jTable_entertainmentMenu.getColumnModel().getColumn(2).setPreferredWidth(698);
		}
		return jTable_entertainmentMenu;
	}

}
