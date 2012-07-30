package View.RIM.Components.Gallery;

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import com.explodingpixels.macwidgets.HudWindow;

import Images.RIM.ImageHelper;
import net.miginfocom.swing.MigLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class ImageGallery extends HudWindow
{
	private static MySlider mySlider;
	private static GalleryImage pic1;
	private static GalleryImage pic2;
	
	ImageGallery()
	{
		JPanel mainPanel = new JPanel();
		
		pic1 = new GalleryImage();
		pic1.setIcon(ImageHelper.loadImageIcon("myPic.jpg", "testing"));
		
		pic2 = new GalleryImage();
		pic2.setIcon(ImageHelper.loadImageIcon("bradPitt.jpg", "testing"));
		
	
		
		setContentPane(mainPanel);
		
		mySlider = new MySlider();
		mySlider.setBackground(new Color(240, 240, 240));
		mySlider.setMinimumSize(new Dimension(300, 350));
		pic1.setSize(mySlider.getSize());
		mySlider.addSliderComponent(pic1);
		pic2.setSize(mySlider.getSize());
		mySlider.addSliderComponent(pic2);
		
		mySlider.refresh();
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		mainPanel.add(mySlider);
		getJDialog().setVisible(true);
		getJDialog().setSize(500, 400);
		
		getJDialog().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getJDialog().addWindowListener(new WindowListener()
		{

			@Override
			public void windowActivated(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0)
			{
				JOptionPane.showConfirmDialog(null, "Are you sure you want to close this you wanko?");
				
			}

			@Override
			public void windowDeactivated(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}
			
		});
		getJDialog().addComponentListener(new ComponentListener()
		{

			@Override
			public void componentHidden(ComponentEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentResized(ComponentEvent arg0)
			{
				mySlider.setSize(arg0.getComponent().getSize());
				
			}

			@Override
			public void componentShown(ComponentEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	public static void main(String args[])
	{
		new ImageGallery();
	}
}
