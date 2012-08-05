package View.RIM.Components;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Meal;
import Model.MealMenu;

public class MealDetails {
	public MealDetails(Meal meal){
		getJTextField_mealTitle().setText(meal.getMealTitle());
		getJTextArea_mealDescription().setText(meal.getMealDescription());
		getJTextField_mealType().setText(meal.getMealType());
		
		MealMenu menu= new MealMenu();
		DefaultTableModel Tablemodel= menu.RETRIEVE_MEAL_MENU_BY_ID(meal.getMealID());
		getJTable_MealMenu().setModel(Tablemodel);
		//jTable_MealMenu.getColumnModel().getColumn(0).setPreferredWidth(565);
		//jTable_MealMenu.getColumnModel().getColumn(1).setPreferredWidth(135);
		//jTable_MealMenu.getColumnModel().getColumn(2).setPreferredWidth(100);
		//jTable_MealMenu.getColumnModel().getColumn(3).setPreferredWidth(100);
		//jTable_MealMenu.getColumnModel().getColumn(4).setPreferredWidth(500);
		
	}
	private JPanel jPanel = null;  //  @jve:decl-index=0:visual-constraint="155,23"
	private JLabel jLabel_title = null;
	private JLabel jLabel_description = null;
	private JLabel jLabel_type = null;
	private JTextField jTextField_mealTitle = null;
	private JScrollPane jScrollPane_mealDescription = null;
	private JTextArea jTextArea_mealDescription = null;
	private JTextField jTextField_mealType = null;
	private JScrollPane jScrollPane_mealMenu = null;
	private JTable jTable_MealMenu = null;
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel_type = new JLabel();
			jLabel_type.setBounds(new Rectangle(15, 265, 150, 30));
			jLabel_type.setText("Meal Type :");
			jLabel_description = new JLabel();
			jLabel_description.setBounds(new Rectangle(15, 100, 150, 30));
			jLabel_description.setText("Description :");
			jLabel_title = new JLabel();
			jLabel_title.setBounds(new Rectangle(15, 30, 150, 30));
			jLabel_title.setText("Title :");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setSize(new Dimension(629, 496));
			jPanel.add(jLabel_title, null);
			jPanel.add(jLabel_description, null);
			jPanel.add(jLabel_type, null);
			jPanel.add(getJTextField_mealTitle(), null);
			jPanel.add(getJScrollPane_mealDescription(), null);
			jPanel.add(getJTextField_mealType(), null);
			jPanel.add(getJScrollPane_mealMenu(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextField_mealTitle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_mealTitle() {
		if (jTextField_mealTitle == null) {
			jTextField_mealTitle = new JTextField();
			jTextField_mealTitle.setBounds(new Rectangle(165, 30, 400, 30));
		}
		return jTextField_mealTitle;
	}

	/**
	 * This method initializes jScrollPane_mealDescription	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane_mealDescription() {
		if (jScrollPane_mealDescription == null) {
			jScrollPane_mealDescription = new JScrollPane();
			jScrollPane_mealDescription.setBounds(new Rectangle(165, 100, 400, 150));
			jScrollPane_mealDescription.setViewportView(getJTextArea_mealDescription());
		}
		return jScrollPane_mealDescription;
	}

	/**
	 * This method initializes jTextArea_mealDescription	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea_mealDescription() {
		if (jTextArea_mealDescription == null) {
			jTextArea_mealDescription = new JTextArea();
		}
		return jTextArea_mealDescription;
	}

	/**
	 * This method initializes jTextField_mealType	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_mealType() {
		if (jTextField_mealType == null) {
			jTextField_mealType = new JTextField();
			jTextField_mealType.setBounds(new Rectangle(165, 265, 400, 30));
		}
		return jTextField_mealType;
	}

	/**
	 * This method initializes jScrollPane_mealMenu	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane_mealMenu() {
		if (jScrollPane_mealMenu == null) {
			jScrollPane_mealMenu = new JScrollPane();
			jScrollPane_mealMenu.setBounds(new Rectangle(15, 330, 550, 150));
			jScrollPane_mealMenu.setViewportView(getJTable_MealMenu());
		}
		return jScrollPane_mealMenu;
	}

	/**
	 * This method initializes jTable_MealMenu	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable_MealMenu() {
		if (jTable_MealMenu == null) {
			DefaultTableModel temp= new DefaultTableModel();
			 String []Header={"Meal Name","Price/hr","Halal","Vegetarian","Description"};
			temp.setColumnIdentifiers(Header);
			jTable_MealMenu = new JTable();
			jTable_MealMenu.setAutoResizeMode(0);
			jTable_MealMenu.getColumnModel().getColumn(0).setPreferredWidth(565);
			jTable_MealMenu.getColumnModel().getColumn(1).setPreferredWidth(135);
			jTable_MealMenu.getColumnModel().getColumn(2).setPreferredWidth(100);
			jTable_MealMenu.getColumnModel().getColumn(3).setPreferredWidth(100);
			jTable_MealMenu.getColumnModel().getColumn(4).setPreferredWidth(500);
		}
		return jTable_MealMenu;
	}

}
