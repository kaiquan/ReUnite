package View.RIM.Components.Gallery;

import javax.swing.*;

import Images.RIM.ImageHelper;

import com.explodingpixels.widgets.ImageButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
@SuppressWarnings("serial")
public class MySlider extends JPanel {

    JPanel pnlMain=new JPanel(new SliderCardLayout());
    
    JButton btnNext = new ImageButton(ImageHelper.loadImageIcon("slideshowNextDark.png", "gda"));
    ImageButton btnPrevious= new ImageButton(ImageHelper.loadImageIcon("slideshowPrevDark.png", "gda"));
    
    public MySlider() {
        setLayout(new BorderLayout());
 
        btnPrevious.setPressedIcon(ImageHelper.loadImageIcon("slideshowPrevDark.png", "gda"));
        btnPrevious.setOpaque(false);
        btnNext.setPressedIcon(ImageHelper.loadImageIcon("slideshowNextDark.png", "gda"));
        btnNext.setOpaque(false);
        pnlMain.setOpaque(false);
        setOpaque(false);
        setSize(400, 400);
        add(pnlMain, BorderLayout.CENTER);
        add(btnNext, BorderLayout.EAST);
        add(btnPrevious, BorderLayout.WEST);
        
 
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                next();
            }
        });
        btnPrevious.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                previous();
            }
        });
    }
    
 
    public void previous() {
        SliderCardLayout cl=(SliderCardLayout)pnlMain.getLayout();
        Component currentComp=cl.getCurrentComponent(pnlMain);
        Component previousComp=cl.getPreviousComponent(pnlMain);
        Rectangle b=currentComp.getBounds();
        previousComp.setVisible(true);
        SliderListener sl=new SliderListener(10, currentComp, previousComp, false);
        Timer t=new Timer(40,sl);
        sl.timer=t;
        t.start();
    }
 
    public void next() {
        SliderCardLayout cl=(SliderCardLayout)pnlMain.getLayout();
        Component currentComp=cl.getCurrentComponent(pnlMain);
        Component nextComp=cl.getNextComponent(pnlMain);
        Rectangle b=currentComp.getBounds();
        nextComp.setVisible(true);
        SliderListener sl=new SliderListener(10, currentComp, nextComp, true);
        Timer t=new Timer(40,sl);
        sl.timer=t;
        t.start();
    }
 
    public void addSliderComponent(JComponent c) {
        pnlMain.add(c,""+getComponentCount());
    }
 
    public void refresh() {
        revalidate();
        repaint();
    }
    
 
    public class SliderListener implements ActionListener {
        Component c1;
        Component c2;
        int steps;
        int step=0;
        Timer timer;
        boolean isNext;
 
        public SliderListener(int steps, Component c1, Component c2, boolean isNext) {
            this.steps=steps;
            this.c1=c1;
            this.c2=c2;
            this.isNext=isNext;
        }
 
        public void actionPerformed(ActionEvent e) {
            Rectangle bounds=c1.getBounds();
            int shift=bounds.width/steps;
            if (!isNext) {
                c1.setLocation(bounds.x-shift, bounds.y);
                c2.setLocation(bounds.x-shift+bounds.width, bounds.y);
            }
            else {
                c1.setLocation(bounds.x+shift, bounds.y);
                c2.setLocation(bounds.x+shift-bounds.width, bounds.y);
            }
            pnlMain.repaint();
            step++;
            if (step==steps) {
                timer.stop();
                c2.setVisible(false);
                CardLayout cl=(CardLayout)pnlMain.getLayout();
                if (isNext) {
                    cl.next(pnlMain);
                }
                else {
                    cl.previous(pnlMain);
                }
            }
        }
    } 
}
