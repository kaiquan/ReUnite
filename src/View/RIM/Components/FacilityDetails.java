package View.RIM.Components;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Model.Ballroom;
import Model.Facility;

public class FacilityDetails {
	public FacilityDetails(Ballroom ballroom, Facility facility){
		getJTextField_facilityName().setText(facility.getFacilityName().toString());
		getJTextArea_address().setText(facility.getFacilityAddress().toString());
		getJTextArea_facilityDescription().setText(facility.getFacilityDescription().toString());
		getJTextField_contact().setText(facility.getFacilityContact().toString());
		
		//ballroom object
		getJTextField_ballroomName().setText(ballroom.getBallroomName().toString());
		getJTextField_ballroomSize().setText(ballroom.getBallroomSize().toString());
		getJTextField_ballroomFinalPrice().setText("$"+ballroom.getBallroomFinalPrice());
		
		getJScrollPane().setPreferredSize(new Dimension());
		getJScrollPane().setViewportView(getJPanel());
		
		
	}
	private JPanel jPanel = null;  //  @jve:decl-index=0:visual-constraint="135,51"
	private JLabel jLabel_FacilityName = null;
	private JLabel jLabel_address = null;
	private JLabel jLabel_description = null;
	private JLabel jLabel_contact = null;
	private JLabel jLabel_ballroomName = null;
	private JLabel jLabel_ballroomSize = null;
	private JLabel jLabel_ballroomFinalPrice = null;
	private JTextField jTextField_facilityName = null;
	private JScrollPane jScrollPane_address = null;
	private JTextArea jTextArea_address = null;
	private JScrollPane jScrollPane_facilityDescription = null;
	private JTextArea jTextArea_facilityDescription = null;
	private JTextField jTextField_contact = null;
	private JTextField jTextField_ballroomName = null;
	private JTextField jTextField_ballroomSize = null;
	private JTextField jTextField_ballroomFinalPrice = null;
	private JScrollPane jScrollPane = null;  //  @jve:decl-index=0:visual-constraint="30,86"

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel_ballroomFinalPrice = new JLabel();
			jLabel_ballroomFinalPrice.setBounds(new Rectangle(15, 600, 150, 30));
			jLabel_ballroomFinalPrice.setText("Price :");
			jLabel_ballroomSize = new JLabel();
			jLabel_ballroomSize.setBounds(new Rectangle(15, 550, 150, 30));
			jLabel_ballroomSize.setText("Size");
			jLabel_ballroomName = new JLabel();
			jLabel_ballroomName.setBounds(new Rectangle(15, 500, 150, 30));
			jLabel_ballroomName.setText("Ballroom Name");
			jLabel_contact = new JLabel();
			jLabel_contact.setBounds(new Rectangle(15, 450, 150, 30));
			jLabel_contact.setText("Contact :");
			jLabel_description = new JLabel();
			jLabel_description.setBounds(new Rectangle(15, 270, 150, 30));
			jLabel_description.setText("Description");
			jLabel_address = new JLabel();
			jLabel_address.setBounds(new Rectangle(17, 100, 150, 29));
			jLabel_address.setText("Address :");
			jLabel_FacilityName = new JLabel();
			jLabel_FacilityName.setBounds(new Rectangle(15, 30, 150, 30));
			jLabel_FacilityName.setText("Facility Name");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setSize(new Dimension(633, 662));
			jPanel.add(jLabel_FacilityName, null);
			jPanel.add(jLabel_address, null);
			jPanel.add(jLabel_description, null);
			jPanel.add(jLabel_contact, null);
			jPanel.add(jLabel_ballroomName, null);
			jPanel.add(jLabel_ballroomSize, null);
			jPanel.add(jLabel_ballroomFinalPrice, null);
			jPanel.add(getJTextField_facilityName(), null);
			jPanel.add(getJScrollPane_address(), null);
			jPanel.add(getJScrollPane_facilityDescription(), null);
			jPanel.add(getJTextField_contact(), null);
			jPanel.add(getJTextField_ballroomName(), null);
			jPanel.add(getJTextField_ballroomSize(), null);
			jPanel.add(getJTextField_ballroomFinalPrice(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextField_facilityName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_facilityName() {
		if (jTextField_facilityName == null) {
			jTextField_facilityName = new JTextField();
			jTextField_facilityName.setBounds(new Rectangle(165, 30, 400, 30));
		}
		return jTextField_facilityName;
	}

	/**
	 * This method initializes jScrollPane_address	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane_address() {
		if (jScrollPane_address == null) {
			jScrollPane_address = new JScrollPane();
			jScrollPane_address.setBounds(new Rectangle(165, 100, 400, 150));
			jScrollPane_address.setViewportView(getJTextArea_address());
		}
		return jScrollPane_address;
	}

	/**
	 * This method initializes jTextArea_address	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea_address() {
		if (jTextArea_address == null) {
			jTextArea_address = new JTextArea();
		}
		return jTextArea_address;
	}

	/**
	 * This method initializes jScrollPane_facilityDescription	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane_facilityDescription() {
		if (jScrollPane_facilityDescription == null) {
			jScrollPane_facilityDescription = new JScrollPane();
			jScrollPane_facilityDescription.setBounds(new Rectangle(165, 270, 400, 150));
			jScrollPane_facilityDescription.setViewportView(getJTextArea_facilityDescription());
		}
		return jScrollPane_facilityDescription;
	}

	/**
	 * This method initializes jTextArea_facilityDescription	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea_facilityDescription() {
		if (jTextArea_facilityDescription == null) {
			jTextArea_facilityDescription = new JTextArea();
		}
		return jTextArea_facilityDescription;
	}

	/**
	 * This method initializes jTextField_contact	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_contact() {
		if (jTextField_contact == null) {
			jTextField_contact = new JTextField();
			jTextField_contact.setBounds(new Rectangle(165, 450, 400, 30));
		}
		return jTextField_contact;
	}

	/**
	 * This method initializes jTextField_ballroomName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_ballroomName() {
		if (jTextField_ballroomName == null) {
			jTextField_ballroomName = new JTextField();
			jTextField_ballroomName.setBounds(new Rectangle(165, 500, 400, 30));
		}
		return jTextField_ballroomName;
	}

	/**
	 * This method initializes jTextField_ballroomSize	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_ballroomSize() {
		if (jTextField_ballroomSize == null) {
			jTextField_ballroomSize = new JTextField();
			jTextField_ballroomSize.setBounds(new Rectangle(165, 550, 400, 30));
		}
		return jTextField_ballroomSize;
	}

	/**
	 * This method initializes jTextField_ballroomFinalPrice	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_ballroomFinalPrice() {
		if (jTextField_ballroomFinalPrice == null) {
			jTextField_ballroomFinalPrice = new JTextField();
			jTextField_ballroomFinalPrice.setBounds(new Rectangle(165, 600, 400, 30));
		}
		return jTextField_ballroomFinalPrice;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setSize(new Dimension(88, 84));
		}
		return jScrollPane;
	}

}
