package View.SOM;

import java.awt.BorderLayout;
import java.awt.Container;
import java.lang.Thread.State;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class progressBar extends JPanel {

  private static final State TERMINATED = null;

JProgressBar pbar;

  static int min = 0;

  static int max = 100;

  public progressBar() {
    pbar = new JProgressBar();
    pbar.setMinimum(min);
    pbar.setMaximum(max);
    add(pbar);
    
    JFrame frame = new JFrame("Progress Bar Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(this);
    frame.pack();
    frame.setVisible(true);

    for (int i = min; i <= max; i++) {
      final int percent = i;
      try {
        SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            updateBar(percent);
        	  //pbar.setIndeterminate(true);
          }
        });
        Thread.sleep(100);
      } catch (InterruptedException e) {
      }
    }
  }

  public void updateBar(int newValue) {
    pbar.setValue(newValue);
  }

  @SuppressWarnings("deprecation")
public static void main(String args[]) {
	  final boolean end = false;
	  final Thread thread2 = new Thread () {
		  public void run () {
			  new progressBar();
		  }
		};
	  Thread thread1 = new Thread () {
		  public void run () {
			  int i=0;
		   while(thread2.isAlive()){
			   System.out.println(i);
			   i++;
		   }
		   return;
		  }
		};
		

		thread1.start();
		thread2.start();
  }
}