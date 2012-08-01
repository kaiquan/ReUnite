package Controller.RIM.Utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
/*******************************************************************************************
 * Project: ReUnite
 *
 * Class: IOHelper
 *
 * Author: Adeel M. Ateeque (112013Z)
 * 
 * Lecturer: Ms Lim Ai Hua
 * 
 * Purpose: A utility class with helper methods for common IO tasks
 * 
 * Honor Code: I pledge that this program represents my own program code. 
 * I received help from no one in designing, coding and debugging my program.
 *******************************************************************************************/
public final class IOHelper {

    /**
     * Buffer size.
     */
    private static final int IO_BUF_SIZE = 1024 * 32;

    /**
     * Path separator for Windows OS.
     */
    private static final String BACKSLASH = "\\";

    /**
     * Path separator for Unix OS.
     */
    private static final String SLASH = "/";


    public static Image getImageFile()
    {
    	BufferedImage mImage = null;
    	String source = filechoose();
    	if(source!=null){
		File inputFile = new File(source);
			try 
			{
				mImage = ImageIO.read(inputFile);
				return mImage.getScaledInstance(-1, -1, 5);
			} 
			catch (IOException ex) 
			{
		
			}
    	}
    	return null;
    }
    
    public static String filechoose()
	{
		String name1 = null;
		
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
	
		chooser.setFileFilter(new FileFilter() 
		{
			public boolean accept(File f)
			{
				String name = f.getName().toLowerCase();
				return name.endsWith(".gif") || name.endsWith(".jpg")|| name.endsWith(".jpeg") || f.isDirectory();
			}
	
			public String getDescription() 
			{
				return "Image files";
			}
		});
	
		int r = chooser.showOpenDialog(null);
	
		if (r == JFileChooser.APPROVE_OPTION)
		{
			name1 = chooser.getSelectedFile().getAbsolutePath();
			StringBuffer sb=new StringBuffer();
			sb.append(name1);
		
			int l=sb.length();
			for(int i=0;i<l;i++)
			{
				if(sb.charAt(i)=='\\')
				{
					sb.insert(i, "\\");i++;
				}
			}
		}
		return name1;
	}
    
    public static void uploadFiles(File[] files, String directory) {
		FTPClient client = new FTPClient();
		FileInputStream fis = null;
		OutputStream os = null;
		if(files != null){
		try {
			client.connect("205.134.253.65");
			client.login("reunite@saharpharma.com", "guest123");
			int reply = client.getReplyCode();
			if (!client.isConnected()) {
				System.out
						.println("FTP server refused connection." + reply);
				client.disconnect();
				System.exit(1);
			} else {
				System.out.println("FTP server connected." + reply);
		        client.setControlKeepAliveTimeout(300);
		        client.enterLocalPassiveMode();
		        
				if(directory!=null)
				{
					if(!client.changeWorkingDirectory(directory))
					{
						client.makeDirectory("/"+directory);
					}
		
					client.changeWorkingDirectory(directory);
					if(client.setFileType(FTP.BINARY_FILE_TYPE)){		
						for(int i=0; i<files.length; i++)
						{
							// Create an InputStream for the file to be uploaded
							fis = new FileInputStream(files[i].getAbsolutePath());
							os = client.storeFileStream(files[i].getName());
	
			                byte buf[] = new byte[8192];
			                int bytesRead = fis.read(buf);
			                while (bytesRead != -1)
			                {
			                    os.write(buf, 0, bytesRead);
			                    bytesRead = fis.read(buf);
			                }
			                System.out.println("Successfully uploaded "+files[i].getName());
			                fis.close();
			                os.close();
			               
//							// Store file to server
//							if(client.storeUniqueFile(files[i].getName(), fis))
//							{
//								System.out.println("Successfully uploaded "+files[i].getName());
//							}
						}	
					}
				}				
			}
			client.logout();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				client.disconnect();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		}
	}
 
}
