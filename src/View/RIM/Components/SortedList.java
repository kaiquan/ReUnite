package View.RIM.Components;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;

import Model.RIM.ListModels.*;


@SuppressWarnings("serial")
public class SortedList extends JFrame {
	
    private SortedListModel sortedModel;
    private DefaultListModel<Object> unsortedModel;
    
    private ButtonGroup bgSortOrder;
    private JButton btnAddEntry;
    private JButton btnDeleteSelection;
    private JList<Object> list;
    private JPanel panelSort;
    private JRadioButton rbAscendingSort;
    private JRadioButton rbDescendingSort;
    private JRadioButton rbUnordered;
    private JScrollPane scrollPane;
    private JTextField txtAddEntry;

    public SortedList() {
        super("Sorted List");
        unsortedModel = new DefaultListModel<Object>();
        sortedModel = new SortedListModel(unsortedModel);
        initComponents();
    }
    
   
    private void initComponents() {
        bgSortOrder = new ButtonGroup();
        scrollPane = new JScrollPane();
        list = new JList<Object>();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        scrollPane.setBorder(BorderFactory.createTitledBorder("Items"));
        scrollPane.setMinimumSize(new java.awt.Dimension(0, 0));
        scrollPane.setPreferredSize(new java.awt.Dimension(250, 150));
        list.setModel(sortedModel);
        list.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                listKeyPressed(evt);
            }
        });
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
            	listValueChanged(evt);
            }
        });

        scrollPane.setViewportView(list);
            getContentPane().setLayout(new MigLayout("", "[250px][100px][73.00px][63px]", "[150px][52.00px]"));
            getContentPane().add(scrollPane, "flowx,cell 0 0 1 2,alignx left,aligny top");
            txtAddEntry = new JTextField();
            txtAddEntry.setPreferredSize(new Dimension(100, 20));
            txtAddEntry.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtAddEntryKeyPressed(evt);
                }
            });
            
                 getContentPane().add(txtAddEntry, "flowx,cell 1 0");
					btnDeleteSelection = new JButton();
					
					        btnDeleteSelection.setText("Delete");
					btnDeleteSelection.addActionListener(new java.awt.event.ActionListener() {
					    public void actionPerformed(java.awt.event.ActionEvent evt) {
					        btnDeleteSelectionActionPerformed(evt);
					    }
					});
					btnAddEntry = new JButton();
					
					        btnAddEntry.setText("Add");
					        btnAddEntry.addActionListener(new java.awt.event.ActionListener() {
					            public void actionPerformed(java.awt.event.ActionEvent evt) {
					                btnAddEntryActionPerformed(evt);
					            }
					        });
					        getContentPane().add(btnAddEntry);
					        getContentPane().add(btnAddEntry);
					getContentPane().add(btnDeleteSelection, "cell 2 0 1 2,alignx left,aligny center");
					panelSort = new JPanel();
					rbAscendingSort = new JRadioButton();
					rbDescendingSort = new JRadioButton();
					rbUnordered = new JRadioButton();
					
					        panelSort.setBorder(BorderFactory.createTitledBorder("Sort Order"));
					bgSortOrder.add(rbAscendingSort);
					rbAscendingSort.setSelected(true);
					rbAscendingSort.setText("Ascending");
					rbAscendingSort.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
					rbAscendingSort.setMargin(new java.awt.Insets(0, 0, 0, 0));
					rbAscendingSort.addActionListener(new java.awt.event.ActionListener() {
					    public void actionPerformed(java.awt.event.ActionEvent evt) {
					        rbAscendingSortActionPerformed(evt);
					    }
					});
					
					        bgSortOrder.add(rbDescendingSort);
					        rbDescendingSort.setText("Descending");
					        rbDescendingSort.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
					        rbDescendingSort.setMargin(new java.awt.Insets(0, 0, 0, 0));
					        rbDescendingSort.addActionListener(new java.awt.event.ActionListener() {
					            public void actionPerformed(java.awt.event.ActionEvent evt) {
					                rbDescendingSortActionPerformed(evt);
					            }
					        });
					        
					                bgSortOrder.add(rbUnordered);
					                rbUnordered.setText("Unordered");
					                rbUnordered.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
					                rbUnordered.setMargin(new java.awt.Insets(0, 0, 0, 0));
					                rbUnordered.addActionListener(new java.awt.event.ActionListener() {
					                    public void actionPerformed(java.awt.event.ActionEvent evt) {
					                        rbUnorderedActionPerformed(evt);
					                    }
					                });
					                
					                        panelSort.setLayout(new FlowLayout());
					                        
					                               	panelSort.add(rbAscendingSort);
					                               	panelSort.add(rbDescendingSort);
					                               	panelSort.add(rbUnordered);
					                               	getContentPane().add(panelSort, "cell 0 1 1 2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

  private void listValueChanged(ListSelectionEvent evt) {//GEN-FIRST:event_listValueChanged
	  int sortedFirstIndex = evt.getFirstIndex();
	  int sortedLastIndex = evt.getLastIndex();
	if (!evt.getValueIsAdjusting())
	{
	  for (int sortedIndex = sortedFirstIndex; sortedIndex <= sortedLastIndex; sortedIndex++) {
	      int unsortedIndex = sortedModel.toUnsortedModelIndex(sortedIndex);
	      // do something with unsorted model index
	      System.out.printf("listValueChanged index: %d (sorted) %d (unsorted)\n", 
	              sortedIndex, unsortedIndex);
	      System.out.println(list.getModel().getElementAt(sortedFirstIndex));
	  }
	}
  }//GEN-LAST:event_listValueChanged

	private void listKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listKeyPressed
	  int key = evt.getKeyCode();
	  if (key == KeyEvent.VK_DELETE) {
	      int option = JOptionPane.showConfirmDialog(this, "Delete selected elements?");
	      if (option == JOptionPane.YES_OPTION) {
	          btnDeleteSelectionActionPerformed(null);
	      }
	  }
	}//GEN-LAST:event_listKeyPressed

    private void rbUnorderedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbUnorderedActionPerformed
        sortedModel.setSortOrder(SortedListModel.SortOrder.UNORDERED);
    }//GEN-LAST:event_rbUnorderedActionPerformed

    private void rbDescendingSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDescendingSortActionPerformed
        sortedModel.setSortOrder(SortedListModel.SortOrder.DESCENDING);
    }//GEN-LAST:event_rbDescendingSortActionPerformed

    private void rbAscendingSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAscendingSortActionPerformed
        sortedModel.setSortOrder(SortedListModel.SortOrder.ASCENDING);
        
    }//GEN-LAST:event_rbAscendingSortActionPerformed

    private void btnDeleteSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSelectionActionPerformed
        int[] sortedSelection = list.getSelectedIndices();
        int[] unsortedSelection = sortedModel.toUnsortedModelIndices(sortedSelection);
        
        for (int x = unsortedSelection.length - 1; x >= 0; --x) {
            unsortedModel.remove(unsortedSelection[x]);
        }
    }//GEN-LAST:event_btnDeleteSelectionActionPerformed

    private void txtAddEntryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddEntryKeyPressed
        int keyCode = evt.getKeyCode();
        if (keyCode == KeyEvent.VK_ENTER) {
                btnAddEntryActionPerformed(null);
        }
        // else ignore key presses
    }//GEN-LAST:event_txtAddEntryKeyPressed

    private void btnAddEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEntryActionPerformed
        String newText = txtAddEntry.getText();
        // don't add empty or null strings to model
        if (newText != null && newText.length() > 0) {
            unsortedModel.addElement(newText);
        }
        txtAddEntry.setText(null);
    }//GEN-LAST:event_btnAddEntryActionPerformed
    
    void test1() {
        String[] list = {"turtle", "frog", "anteater", 
                         "ant", "rabbit", "dog", "cat" };
        for (String data : list ) {
            unsortedModel.addElement(data);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
    	SortedList app = new SortedList();
        app.setVisible(true);
        app.test1();
    }

  
    
}
