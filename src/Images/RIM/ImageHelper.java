package Images.RIM;

import java.net.*;
import java.awt.*;

import javax.swing.*;

public class ImageHelper
{

	private ImageHelper()
	{
	}

	public static ImageIcon loadImageIcon(String name, String description)
	{
		ImageIcon image = null;
		if (name != null && !name.equals(""))
		{
			URL url = ImageHelper.class.getResource(name);
			try
			{
				if (url != null)
				{
					Image img = new ImageIcon(url, description).getImage();
					if (img != null) image = new ImageIcon(img);
				}
			}
			catch (Throwable ex)
			{
				System.out.println("ERROR: loading image, not found" + url);
			}
		}
		return image;
	}

	public static ImageIcon loadImageIcon(String name, String description, int width, int height, int scaleType)
	{
		ImageIcon image = null;
		if (name != null && !name.equals(""))
		{
			URL url = ImageHelper.class.getResource(name);
			try
			{

				if (url != null)
				{
					Image img = new ImageIcon(url, description).getImage().getScaledInstance(width, height, scaleType);
					if (img != null) image = new ImageIcon(img);
				}
			}
			catch (Throwable ex)
			{
				System.out.println("ERROR: loading image, not found" + url);
			}
		}
		return image;
	}

	public static Image loadImage(String path, String description)
	{
		URL imageURL = ImageHelper.class.getResource(path);
		if (imageURL == null)
		{
			System.err.println("ERROR: loading image, not found " + path);
			return null;
		}
		else
		{
			return (new ImageIcon(imageURL, description)).getImage();
		}
	}

	public static ImageIcon getScaledImageIcon(ImageIcon imageIcon, int width, int height, int scale)
	{
		ImageIcon image = null;
		try
		{
			if (imageIcon != null)
			{
				Image img = imageIcon.getImage().getScaledInstance(width, height, scale);
				if (img != null) image = new ImageIcon(img);
			}
		}
		catch (Throwable ex)
		{
			System.out.println("Could not scale ImageIcon");
		}
		return image;
	}
}
