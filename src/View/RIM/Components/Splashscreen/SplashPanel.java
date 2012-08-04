package Components.Splashscreen;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import Controller.MySQLController;
import Controller.RIM.LookAndFeelController;
import Images.RIM.ImageHelper;
import View.MM.LogInForm;

public class SplashPanel
{
	MySQLController db;
	
	Thread loadMySQL = new Thread(new Runnable()
	{
		@Override
		public void run()
		{
			db = new MySQLController();	
		}
	});
	
	public SplashPanel()
	{
		init_lnf();

		String[] loadingMessages = {
				null, // at first, don't display a message
				"Initializing", "Loading libraries", "Applying configuration", "Setting up logger", "Applying license", "Connecting to db", "Quering customers", "Quering events & packages",
				"Starting up GUI", null // at
										// the
										// end
										// don't
										// display
										// a
										// message,
										// keep
										// last
										// message
										// longer
										// on
										// display
		};

		boolean displayMessages = true;
		boolean displayPercent = true;

		// load and prepare the splash screen,
		// could be done with 'static final' as well...
		JSplash m_splash = new JSplash(ImageHelper.class.getResource("splash.jpg"), // the
																					// image
				true,
				displayMessages,				
				displayPercent, 
				"v1.00", 
				new Font("Segoe UI", 0, 12), Color.BLACK);

		m_splash.splashOn();

		initComponents();
		
		 Random r = new Random();
		 for (int i = 0, j = 0; i <= 100; i += 20, j++)
		 {
		 int millis = (r.nextInt(10) + 1) * 100;
		
		 if (displayMessages && !displayPercent)
		 {
			 m_splash.setProgress(i, loadingMessages[j]);
		 }
		 else
		 {
		 m_splash.setProgress(i);
		 }
		
		 try
		 {
		 Thread.sleep(millis);
		 }
		 catch (Exception e)
		 {
		 // do nothing
		 }
		 }

		// give the user a sec to see the complete splash screen 100%
		try
		{
			Thread.sleep(1000);
		}
		catch (Exception e)
		{
			// do nothing
		}

		// finished loading our application, hide splash
		m_splash.splashOff();

		// ...finally display your application GUI...
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				new LogInForm().setVisible(true);
			}
		});
	}

	private void initComponents()
	{
		loadMySQL.run();
	}

	/**
	 * setup our look and feel
	 */
	private void init_lnf()
	{
		try
		{
			LookAndFeelController.setGlobalLookAndFeel();
		}
		catch (Exception e)
		{
			System.err.println("Cannot initialize the look & feel");
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[])
	{
	}

}
