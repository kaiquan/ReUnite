package View.PRFM;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

import Controller.PRFM.*;

import Model.Event;
import Model.PRFM.*;

import java.awt.Rectangle;
public class AdministrateFeedbackForm {
	
	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="147,9"
	private JPanel jContentPane = null;
	private JMenuBar jMenuBar = null;
	private JMenu questionMenu = null;
	private JMenuItem createQuestionMenuItem = null;
	private JMenuItem retrieveQuestionMenuItem = null;
	private JMenuItem updateQuestionMenuItem = null;
	private JMenuItem deleteQuestionMenuItem = null;
	private JMenu formMenu = null;
	private JMenuItem createFormMenuItem = null;
	private JMenuItem retrieveFormMenuItem = null;
	private JMenuItem updateFormMenuItem = null;
	private JMenuItem deleteFormMenuItem = null;
	private JMenu resultMenu = null;
	private JMenuItem createResultMenuItem = null;
	private JMenu retrieveResultMenu = null;
	private JMenuItem fqResultMenuItem = null;
	private JMenuItem ffResultMenuItem = null;
	private JLabel imgLabel = null;
	private JMenu optionMenu = null;
	private JMenu updateOptionMenu = null;
	private JMenuItem ratingQuestionMenuItem = null;
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(450, 250));
			jFrame.setJMenuBar(getJMenuBar());
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
			imgLabel = new JLabel(new ImageIcon("icons/Feedback.png"));
			imgLabel.setBounds(new Rectangle(100, 30, 241, 127));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(imgLabel, null);
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes jMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJMenuBar() {
		if (jMenuBar == null) {
			jMenuBar = new JMenuBar();
			jMenuBar.add(getQuestionMenu());
			jMenuBar.add(getFormMenu());
			jMenuBar.add(getResultMenu());
			jMenuBar.add(getOptionMenu());
		}
		return jMenuBar;
	}

	/**
	 * This method initializes questionMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getQuestionMenu() {
		if (questionMenu == null) {
			questionMenu = new JMenu();
			questionMenu.setText("Feedback Question");
			questionMenu.add(getCreateQuestionMenuItem());
			questionMenu.add(getRetrieveQuestionMenuItem());
			questionMenu.add(getUpdateQuestionMenuItem());
			questionMenu.add(getDeleteQuestionMenuItem());
		}
		return questionMenu;
	}

	/**
	 * This method initializes createMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCreateQuestionMenuItem() {
		if (createQuestionMenuItem == null) {
			createQuestionMenuItem = new JMenuItem("Create", new ImageIcon("icons/add.png"));
		}
		
		createQuestionMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(getJFrame(), "Do you want to search for similar feedback questions before creating?");

				if (option == JOptionPane.YES_OPTION){
					RetrieveFeedbackQuestion retrieve = new RetrieveFeedbackQuestion("Create Feedback Question");
					retrieve.getJFrame().setVisible(true);
				}
				else{
					CreateFeedbackQuestion create = new CreateFeedbackQuestion();
					create.getJFrame().setVisible(true);
				}
				
			}
		});
		
		return createQuestionMenuItem;
	}

	/**
	 * This method initializes retrieveMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getRetrieveQuestionMenuItem() {
		if (retrieveQuestionMenuItem == null) {
			retrieveQuestionMenuItem = new JMenuItem("Retrieve", new ImageIcon("icons/folder_open.png"));
		}
		
		retrieveQuestionMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				RetrieveFeedbackQuestion retrieve = new RetrieveFeedbackQuestion("Retrieve Feedback Question");
				retrieve.getJFrame().setVisible(true);	
			}
		});
		
		return retrieveQuestionMenuItem;
	}

	/**
	 * This method initializes updateMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getUpdateQuestionMenuItem() {
		if (updateQuestionMenuItem == null) {
			updateQuestionMenuItem = new JMenuItem("Update", new ImageIcon("icons/edit.png"));
			
			updateQuestionMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					RetrieveFeedbackQuestion retrieve = new RetrieveFeedbackQuestion("Update Feedback Question");
					retrieve.getJFrame().setVisible(true);	
				}
			});
		}
		return updateQuestionMenuItem;
	}

	/**
	 * This method initializes deleteMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getDeleteQuestionMenuItem() {
		if (deleteQuestionMenuItem == null) {
			deleteQuestionMenuItem = new JMenuItem("Delete", new ImageIcon("icons/delete.png"));

			deleteQuestionMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					RetrieveFeedbackQuestion retrieve = new RetrieveFeedbackQuestion("Delete Feedback Question");
					retrieve.getJFrame().setVisible(true);	
				}
			});
		}
		return deleteQuestionMenuItem;
	}

	/**
	 * This method initializes formMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFormMenu() {
		if (formMenu == null) {
			formMenu = new JMenu();
			formMenu.setText("Feedback Form");
			formMenu.add(getCreateFormMenuItem());
			formMenu.add(getRetrieveFormMenuItem());
			formMenu.add(getUpdateFormMenuItem());
			formMenu.add(getDeleteFormMenuItem());
		}
		return formMenu;
	}

	/**
	 * This method initializes createFormMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCreateFormMenuItem() {
		if (createFormMenuItem == null) {
			createFormMenuItem = new JMenuItem("Create", new ImageIcon("icons/add.png"));
		}
		
		createFormMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(getJFrame(), "Do you want to browse through existing feedback forms before creating?");

				if (option == JOptionPane.YES_OPTION){
					RetrieveFeedbackForm retrieve = new RetrieveFeedbackForm("Create Feedback Form");
					retrieve.getJFrame().setVisible(true);
				}
				else{
					CreateFeedbackForm createFf = new CreateFeedbackForm();
					createFf.getJFrame().setVisible(true);
					RetrieveFeedbackQuestion retrieveFq = new RetrieveFeedbackQuestion("Select Feedback Question to create Feedback Form", createFf);
					retrieveFq.getJFrame().setVisible(true);
					RetrieveEvent retrieveEvent = new RetrieveEvent("Select Event to create Feedback Form", createFf);
					retrieveEvent.getJFrame().setVisible(true);
				}
				
			}
		});
		
		return createFormMenuItem;
	}

	/**
	 * This method initializes retrieveFormMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getRetrieveFormMenuItem() {
		if (retrieveFormMenuItem == null) {
			retrieveFormMenuItem = new JMenuItem("Retrieve", new ImageIcon("icons/folder_open.png"));
		}
		
		retrieveFormMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				RetrieveFeedbackForm retrieve = new RetrieveFeedbackForm("Retrieve Feedback Form");
				retrieve.getJFrame().setVisible(true);	
			}
		});
		return retrieveFormMenuItem;
	}

	/**
	 * This method initializes updateFormMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getUpdateFormMenuItem() {
		if (updateFormMenuItem == null) {
			updateFormMenuItem = new JMenuItem("Update", new ImageIcon("icons/edit.png"));
		}
		
		updateFormMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				RetrieveFeedbackForm retrieve = new RetrieveFeedbackForm("Update Feedback Form");
				retrieve.getJFrame().setVisible(true);
			}
		});
		
		return updateFormMenuItem;
	}

	/**
	 * This method initializes deleteFormMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getDeleteFormMenuItem() {
		if (deleteFormMenuItem == null) {
			deleteFormMenuItem = new JMenuItem("Delete", new ImageIcon("icons/delete.png"));
		}
		
		deleteFormMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				RetrieveFeedbackForm retrieve = new RetrieveFeedbackForm("Delete Feedback Form");
				retrieve.getJFrame().setVisible(true);
			}
		});
		
		return deleteFormMenuItem;
	}

	/**
	 * This method initializes resultMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getResultMenu() {
		if (resultMenu == null) {
			resultMenu = new JMenu();
			resultMenu.setText("Feedback Result");
			resultMenu.add(getCreateResultMenuItem());
			resultMenu.add(getRetrieveResultMenu());
		}
		return resultMenu;
	}

	/**
	 * This method initializes createResultMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCreateResultMenuItem() {
		if (createResultMenuItem == null) {
			createResultMenuItem = new JMenuItem("Create", new ImageIcon("icons/add.png"));
		}
		
		createResultMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				Event event = new Event(), allEvent = new Event();
				AdministrateEventController eController = new AdministrateEventController();
				AdministrateFeedbackFormController ffController = new AdministrateFeedbackFormController();
				allEvent = eController.processRetrieve();
				
				for (int i = 0; i < allEvent.getEventDate().size(); i++){
					if (ffController.compareCurrentDate(allEvent.getEventDate().get(i)) == 0){
						event.setEventDate(allEvent.getEventDate().get(i));
						event.setEventDescription(allEvent.getEventDescription().get(i));
						event.setEventID(allEvent.getEventID().get(i));
						event.setEventName(allEvent.getEventName().get(i));
						event.setEventStatus(allEvent.getEventStatus().get(i));
						event.setEventTime(allEvent.getEventTime().get(i));
						event.setPackageID(allEvent.getPackageID().get(i));
						event.setUserName(allEvent.getUserName().get(i));
					}
				}
				
				if (event.getEventID().size() == 0){
					JOptionPane.showMessageDialog(getJFrame(), "There are no events today.");
				}
				else{
					EventForm ef = new EventForm();
					ef = ffController.processEventFormSearchTerm(0, event.getEventID().get(0));
					
					if (event.getEventID().size() > 1){
						for (int i = 0; i < event.getEventID().size(); i++){
							
						}
					}
					else{
						if (ef.getEventID().isEmpty()){
							JOptionPane.showMessageDialog(getJFrame(), "There's no feedback form assigned to Event #" + event.getEventID().get(0));
						}
						else{
							CreateFeedbackResult create = new CreateFeedbackResult(ef.getCode().get(0));
							create.getJFrame().setVisible(true);
						}
					}
				}
			}
		});
		return createResultMenuItem;
	}

	/**
	 * This method initializes retrieveResultMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getRetrieveResultMenu() {
		if (retrieveResultMenu == null) {
			retrieveResultMenu = new JMenu();
			retrieveResultMenu.setText("Retrieve");
			retrieveResultMenu.setIcon(new ImageIcon("icons/folder_open.png"));
			retrieveResultMenu.add(getFqResultMenuItem());
			retrieveResultMenu.add(getFfResultMenuItem());
		}
		return retrieveResultMenu;
	}
	
	private JMenuItem getFqResultMenuItem() {
		if (fqResultMenuItem == null) {
			fqResultMenuItem = new JMenuItem("Question Result", new ImageIcon("icons/help_16.png"));
		}
		
		fqResultMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				RetrieveFeedbackQuestion retrieve = new RetrieveFeedbackQuestion("Select Feedback Question to View Result");
				retrieve.getJFrame().setVisible(true);	
			}
		});
		return fqResultMenuItem;
	}
	
	private JMenuItem getFfResultMenuItem() {
		if (ffResultMenuItem == null) {
			ffResultMenuItem = new JMenuItem("Form Result", new ImageIcon("icons/docs_16.png"));
		}
		
		ffResultMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				RetrieveFeedbackForm retrieve = new RetrieveFeedbackForm("Select Feedback Form to View Result");
				retrieve.getJFrame().setVisible(true);	
			}
		});
		return ffResultMenuItem;
	}

	/**
	 * This method initializes optionMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getOptionMenu() {
		if (optionMenu == null) {
			optionMenu = new JMenu();
			optionMenu.setText("Feedback Option");
			optionMenu.add(getUpdateOptionMenu());
		}
		return optionMenu;
	}

	/**
	 * This method initializes updateOptionMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getUpdateOptionMenu() {
		if (updateOptionMenu == null) {
			updateOptionMenu = new JMenu();
			updateOptionMenu.setIcon(new ImageIcon("icons/edit.png"));
			updateOptionMenu.setText("Update");
			updateOptionMenu.add(getRatingQuestionMenuItem());
		}
		return updateOptionMenu;
	}

	/**
	 * This method initializes ratingQuestionMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getRatingQuestionMenuItem() {
		if (ratingQuestionMenuItem == null) {
			ratingQuestionMenuItem = new JMenuItem(new ImageIcon("icons/opnbr_16.png"));
			ratingQuestionMenuItem.setText("Rating Options");
		}
		
		ratingQuestionMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				UpdateRatingQuestion update = new UpdateRatingQuestion();
				update.getJFrame().setVisible(true);	
			}
		});
		return ratingQuestionMenuItem;
	}

	public static void main(String[] args){
		AdministrateFeedbackForm form = new AdministrateFeedbackForm();
		form.getJFrame().setVisible(true);
	}
	
}
