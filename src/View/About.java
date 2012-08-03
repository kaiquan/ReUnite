package View;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


@SuppressWarnings("serial")
public class About extends JDialog {

    
    public About() {
    	setTitle("About ReUnite");
        init();
    }
    
    private void init() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(BorderFactory.createEmptyBorder());
        JPanel aboutPanel = new JPanel();
        aboutPanel.setBorder(BorderFactory.createEmptyBorder(32, 8, 8, 8));
        tabbedPane.add("About", aboutPanel);
       
        contentPanel.add(tabbedPane, BorderLayout.CENTER);
        setContentPane(contentPanel);
        showDialog();
    }
    
    private void showDialog() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dlgSize = new Dimension(640, 480);
        int dlgPosX = (screenSize.width / 2) - (dlgSize.width / 2);
        int dlgPosY = (screenSize.height / 2) - (dlgSize.height / 2);
        setLocation(dlgPosX, dlgPosY);
        setSize(dlgSize);
        setVisible(true);
    }
    
   
}
