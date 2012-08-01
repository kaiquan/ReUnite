package View.RIM.Components.Splashscreen;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import Controller.RIM.LookAndFeelController;
import Images.RIM.ImageHelper;
import View.MM.LogInForm;

public class SplashPanel
{

	public SplashPanel()
	{
		init_lnf();

		initComponents();
	}

	private void initComponents()
	{
		new LogInForm().setVisible(true);
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
		String[] loadingMessages = {
				null, // at first, don't display a message
				"Initializing", "Loading libraries", "Applying configuration", "Setting up logger", "Applying license", "Connecting to db",
				"Quering customers", "Quering products & catalogs", "Starting up GUI", null // at
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
				true, // do we want a progress bar?
				displayMessages, // do we want messages displayed during
									// loading?
				displayPercent, // do we want percent displayed instead of
								// messages?
				"v1.00", // do we want to display a text message at the
									// bottom-right corner e.g. version number
				new Font("Segoe UI", 0, 12), // do we want another font?
				Color.BLACK); // do we need another color other than black?

		// when we start our main() method, we invoke:
		m_splash.splashOn();

		// in several points in our code we invoke m_splash.setProgress()
		// in order to increase the progress bar...
		// e.g. m_splash.setProgress( 10 );
		// (call some methods);
		// m_splash.setProgress( 30 );
		// (call some methods);
		// m_splash.setProgress( 100 ); // finished!
		//
		// Here we will do just a simulation
		//
		Random r = new Random();
		for (int i = 0, j = 0; i <= 100; i += 10, j++)
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
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				new SplashPanel();
			}
		});
	}


}
