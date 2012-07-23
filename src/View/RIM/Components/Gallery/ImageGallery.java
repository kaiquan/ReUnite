package View.RIM.Components.Gallery;

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import Images.RIM.ImageHelper;
import net.miginfocom.swing.MigLayout;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class ImageGallery extends JFrame
{
	private static MySlider mySlider;
	private static GalleryImage pic1;
	private static GalleryImage pic2;
	
	ImageGallery()
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new MigLayout("", "[46px][grow]", "[41px,grow]"));
		
		mySlider = new MySlider();
		mySlider.setMinimumSize(new Dimension(300, 350));
		
		pic1 = new GalleryImage();
		pic1.setIcon(ImageHelper.loadImageIcon("myPic.jpg", "testing"));
		pic1.setSize(mySlider.getSize());
		mySlider.addSliderComponent(pic1);
		
		pic2 = new GalleryImage();
		pic2.setIcon(ImageHelper.loadImageIcon("bradPitt.jpg", "testing"));
		pic2.setSize(mySlider.getSize());
		mySlider.addSliderComponent(pic2);
		
		mySlider.refresh();
		
		mainPanel.add(mySlider, "cell 0 0,alignx left,aligny top");
		
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(100, 10));
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);
		
		mainPanel.add(panel, "cell 1 0,grow");
		
	
		
		setContentPane(mainPanel);
		setVisible(true);
		setSize(500, 400);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(new WindowListener()
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
		addComponentListener(new ComponentListener()
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
