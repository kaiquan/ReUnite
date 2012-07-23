package View.RIM;

import javax.swing.JFrame;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.browser.*;


@SuppressWarnings("serial")
public class ServiceLoginWindow extends JFrame
{
	private String accessCode;
	private String serviceName;
	
	public ServiceLoginWindow(String url, final String disposeURL, String service)
	{
		serviceName = service;
		
		Display display = new Display();
		final Shell shell = new Shell(display);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		shell.setLayout(gridLayout);


		GridData data = new GridData();
		data.horizontalSpan = 3;

		Label labelAddress = new Label(shell, SWT.NONE);
		labelAddress.setText("Address");

		final Text location = new Text(shell, SWT.BORDER);
		data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.horizontalSpan = 2;
		data.grabExcessHorizontalSpace = true;
		location.setLayoutData(data);

		final Browser browser;
		try
		{
			browser = new Browser(shell, SWT.NONE);
		}
		catch (SWTError e)
		{
			System.out.println("Could not instantiate Browser: " + e.getMessage());
			display.dispose();
			return;
		}
		data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment = GridData.FILL;
		data.horizontalSpan = 3;
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		browser.setLayoutData(data);

		final Label status = new Label(shell, SWT.NONE);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		status.setLayoutData(data);

		final ProgressBar progressBar = new ProgressBar(shell, SWT.NONE);
		data = new GridData();
		data.horizontalAlignment = GridData.END;
		progressBar.setLayoutData(data);

		
		browser.addProgressListener(new ProgressListener() {
			public void changed(ProgressEvent event)
			{
				if (event.total == 0)
					return;
				int ratio = event.current * 100 / event.total;
				progressBar.setSelection(ratio);
			}

			public void completed(ProgressEvent event)
			{
				progressBar.setSelection(0);
			}
		});
		
		browser.addStatusTextListener(new StatusTextListener() {
			public void changed(StatusTextEvent event)
			{
				status.setText(event.text);
			}
		});
		
		browser.addLocationListener(new LocationListener() {
			public void changed(LocationEvent event)
			{
				if (event.top)
					location.setText(event.location);
				
				if(event.location.regionMatches(0, disposeURL, 0, disposeURL.length()))
				{
					System.out.println(event.location);
					shell.dispose();
						setAccessCode(event.location);
				}
			}

			public void changing(LocationEvent event)
			{
			}
		});
		
		location.addListener(SWT.DefaultSelection, new Listener() {
			public void handleEvent(Event e)
			{
				browser.setUrl(location.getText());
			}
		});
		
		shell.open();
		browser.setUrl(url);

		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
		
	}


	private void setAccessCode(String textToParse)
	{
		if(serviceName.equals("Hotmail"))
		{
			accessCode = textToParse.substring(textToParse.indexOf("code=")).substring(5).trim();
		}
		else if(serviceName.equals("Google"))
		{
			String temp = textToParse.substring(textToParse.indexOf("oauth_verifier=")).substring(15);
			accessCode = temp.substring(0, temp.indexOf("&oauth_token")).trim();
		}
		else if(serviceName.equals("Facebook"))
		{
//			accessCode = textToParse.substring(textToParse.indexOf("code=")).substring(5).trim();
			String temp = textToParse.substring(textToParse.indexOf("code=")).substring(5);
			accessCode = temp.substring(0, temp.indexOf("#_=_")).trim();
		}
	}
	
	public String getAccessCode()
	{
		return accessCode;
	}
	
	public static void main(String[] args)
	{
		new ServiceLoginWindow("http://www.google.com", "www.twitter.com", "Google");
	}

}