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

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
/*******************************************************************************************
 * Project: ReUnite
 *
 * Class: IOHelper
 *
 * Author: Adeel M. Ateeque (112013Z)
 * 
 * Lecturer: Ms�Lim�Ai�Hua
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
		File inputFile = new File(source);
		try 
		{
			mImage = ImageIO.read(inputFile);
		} 
		catch (IOException ex) 
		{
	
		}
    	return mImage.getScaledInstance(-1, -1, 5);
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
    
    /**
     * Creates the complete directory structure for a complete
     * <b>FOLDER</b> pathname.
     * <p>
     * @param folder    The folder pathname.
     * <p>
     * @return          The folder pathname.
     * <p>
     * @throws IOException
     */
    public static String createDirectoryTreeForFolder(String folder)
        throws IOException
    {
        if( folder==null )
        {
            throw new IOException("The requested folder is null!");
        }

        if( !folder.endsWith( BACKSLASH ) && !folder.endsWith( SLASH ) )
        {
            folder += SLASH;
        }

        File f = new File( folder );
        if( !f.exists() )
        {
            boolean result = f.mkdirs(); // build all required directories!
            if( result==false )
            {
                throw new IOException("Cannot create folder structure for: " +
                    folder);
            }
        }
        else if( !f.isDirectory() )
        {
            throw new IOException(
                "Invalid directory/Cannot create directory: " + folder);
        }
        else
        {
            /* the folder already exists */
        }

        return( folder );
    }


    /**
     * Creates the complete directory structure for a complete
     * <b>FILE</b> pathname.
     * <p>
     * @param file  The file pathname.
     * <p>
     * @throws IOException
     */
    public static void createDirectoryTreeForFile(String file)
        throws IOException
    {
        if( file==null )
        {
            throw new IOException("null file requested!");
        }

        String path = null;
        int sep1 = file.lastIndexOf( BACKSLASH );
        int sep2 = file.lastIndexOf( SLASH );
        if( sep1==-1 && sep2==-1 )
        {
            path = file;
        }
        else if( sep1!=-1 )
        {
            path = file.substring(0, sep1);
        }
        else if( sep2!=-1 )
        {
            path = file.substring(0, sep2);
        }
        else
        {
            // impossible
            throw new IOException("Cannot create folder structure for: " +
                file);
        }

        createDirectoryTreeForFolder( path );
    }


    /**
     * Determines if two filename paths refer to the same file.
     * <p>
     * @param pathname1     The first pathname.
     * @param pathname2     The second pathname.
     * <p>
     * @return              <code>true</code> if two filename paths refer
     *                      to the same file.
     * <p>
     * @throws IOException
     * <p>
     * @see <a href="http://javaalmanac.com/egs/java.io/Canonical.html">
     * http://javaalmanac.com/egs/java.io/Canonical.html</a>
     */
    public static boolean isTheSameFile(String pathname1, String pathname2)
        throws IOException
    {
        File file1 = new File( pathname1 );
        File file2 = new File( pathname2 );

        // Normalize the paths
        file1 = file1.getCanonicalFile();
        file2 = file2.getCanonicalFile();

        return( file1.equals(file2) );
    }


    /**
     * Determines if a file or directory exists.
     * <p>
     * @param pathname  The file or directory pathname.
     * <p>
     * @return          <code>true</code> if it exists.
     */
    public static boolean fileOrDirectoryExists(String pathname)
    {
        File f = new File(pathname);
        return( f.exists() );
    }


    /**
     * Determines if a file exists.
     * <p>
     * @param pathname  The file to check.
     * <p>
     * @return          <code>true</code> if it exists.
     */
    public static boolean isFile(String pathname)
    {
        File f = new File( pathname );
        return( f.isFile() );
    }


    /**
     * Determines if a directory exists.
     * <p>
     * @param pathname  The directory pathname.
     * <p>
     * @return          <code>true</code> if it exists.
     */
    public static boolean isDirectory(String pathname)
    {
        File f = new File( pathname );
        return( f.isDirectory() );
    }


    /**
     * Saves a string to a text file.
     * <p>
     * @param pathname  The pathname to the file.
     * @param data      The string to be saved.
     * @param append    <code>true</code> if the string is to be appended
     *                  to the end of existing text.
     * <p>
     * @throws IOException
     */
    public static void saveTxtFile(String pathname, String data, boolean append)
        throws IOException
    {
        saveTxtFile(new File(pathname), data, append);
    }


    /**
     * Saves a string to a text file.
     * <p>
     * @param f         The file.
     * @param data      The string to save.
     * @param append    <code>true</code> if the string is to be appended
     *                  to the end of existing text.
     * <p>
     * @throws IOException
     */
    public static void saveTxtFile(File f, String data, boolean append)
        throws IOException
    {
        BufferedWriter out = null;

        try
        {
            out = new BufferedWriter( new FileWriter(f, append) );
            out.write( data );
        }
        catch(IOException e)
        {
            throw( e );
        }
        finally
        {
            if( out!=null )
            {
                try
                {
                    out.close();
                }
                catch(IOException ex)
                {
                    //throw( ex );
                    ex.printStackTrace();
                }
            }
        }
    }


    /**
     * Reads a text file into a string.
     * <p>
     * @param pathname  The path to the text file.
     * <p>
     * @return          The text in the file.
     * <p>
     * @throws IOException
     */
    public static String readTxtFile(String pathname)
        throws IOException
    {
        return( readTxtFile( new File(pathname) ) );
    }


    /**
     * Reads a text file into a string.
     * <p>
     * @param f     The file.
     * <p>
     * @return      The text in the file.
     * <p>
     * @throws IOException
     */
    public static String readTxtFile(File f)
        throws IOException
    {
        BufferedReader in = null;
        StringBuilder strBuf = new StringBuilder();
        try
        {
            in = new BufferedReader( new FileReader(f) );
            String str = null;
            while( (str = in.readLine()) != null )
            {
                strBuf.append( str );
                strBuf.append( "\n" );
            }
        }
        catch(IOException e)
        {
            throw( e );
        }
        finally
        {
            if( in!=null )
            {
                try
                {
                    in.close();
                }
                catch(IOException ex)
                {
                    //throw( ex );
                    ex.printStackTrace();
                }
            }
        }

        return( strBuf.toString() );
    }


    /**
     * Deletes all files and subdirectories under <i>dir</i>.
     * Returns <code>true</code> if all deletions were successful.
     * If a deletion fails, the method stops attempting
     * to delete and returns <code>false</code>.
     * <p>
     * @param dir   The directory to delete from.
     * <p>
     * @return      <code>true</code> if all deletions were successful.
     */
    public static boolean deleteDirectory(File dir)
    {
        if( dir.isDirectory() )
        {
            String[] children = dir.list();
            for(int i=0; i<children.length; i++)
            {
                boolean success = deleteDirectory( new File(dir, children[i]) );
                if( !success )
                {
                    return( false );
                }
            }
        }

        // This is a file or an empty directory, so just delete it
        return( dir.delete() );
    }


    /**
     * Deletes all files and subdirectories under <i>dir</i>.
     * Returns <code>true</code> if all deletions were successful.
     * If a deletion fails, the method stops attempting
     * to delete and returns <code>false</code>.
     * <p>
     * @param pathname  The path to the directory.
     * <p>
     * @return          <code>true</code> if all deletions were successful.
     */
    public static boolean deleteDirectory(String pathname)
    {
        return( deleteDirectory( new File(pathname) ) );
    }


    /**
     * Deletes a file.
     * <p>
     * @param pathname  The pathname to the file.
     * <p>
     * @return          <code>true</code> if successful.
     */
    public static boolean deleteFile(String pathname)
    {
        File f = new File( pathname );
        return( f.delete() );
    }


    /**
     * Copies a file to another location/file.
     * <p>
     * @param fromName      The file to copy from.
     * @param toName        The file to copy to.
     * @param overwrite     <code>true</code> to overwrite if the file already
     *                      exists.
     * <p>
     * @return              <code>true</code> if file copied or
     *                      <code>false</code> if not (possibly the file existed
     *                      and 'overwrite' was not set).
     * <p>
     * @throws IOException
     */
    public static boolean copyFile(String fromName, String toName,
            boolean overwrite)
        throws IOException
    {
        if( fromName==null )
            throw new IOException("source filename is null!");

        if( toName==null )
            throw new IOException("destination filename is null!");

        File fromFile = new File(fromName);
        File toFile = new File(toName);

        return( copyFile(fromFile, toFile, overwrite) );
    }


    /**
     * Copies a file to another location/file.
     * <p>
     * @param fromFile      The file to copy from.
     * @param toFile        The file to copy to.
     * @param overwrite     <code>true</code> to overwrite if the file already
     *                      exists.
     * <p>
     * @return              <code>true</code> if file copied or
     *                      <code>false</code> if not (possibly the file existed
     *                      and 'overwrite' was not set).
     * <p>
     * @throws IOException
     */
    public static boolean copyFile(File fromFile, File toFile,
            boolean overwrite)
        throws IOException
    {
        if( fromFile==null )
            throw new IOException("source file is null!");

        if( toFile==null )
            throw new IOException("destination file is null!");

        // make sure that source file exists
        if( !fromFile.exists() )
            throw new IOException("no such source file: " +
                fromFile.getAbsoluteFile());

        if( !fromFile.isFile() )
            throw new IOException("can't copy directory: " +
                fromFile.getAbsoluteFile());

        if( !fromFile.canRead() )
            throw new IOException("source file is unreadable: " +
                fromFile.getAbsoluteFile());

        if( toFile.isDirectory() )
            toFile = new File(toFile, fromFile.getName());

        if( toFile.exists() )
        {
            if( !toFile.canWrite() )
                throw new IOException("destination file is unwriteable: " +
                    toFile.getAbsoluteFile());

            // check if we should overwrite it
            if( !overwrite )
            {
            	  return( false );
            }
        }
        else
        {
            // if the file dosn't exist, check if the directory exists and is
            // writeable. If getParent() returns null, then the directory is the
            // current dir. so look up the user.dir system property to find out
            // what that is.
            String parent = toFile.getParent(); // the destination dir
            if( parent==null ) // if none use the current dir
                parent = System.getProperty("user.dir");

            File dir = new File( parent ); // convert it to a file
            if( !dir.exists() )
                throw new IOException("destination directory doesn't exist: "
                    + parent);

            if( dir.isFile() )
                throw new IOException("destination is not a directory: "
                    + parent);

            if( !dir.canWrite() )
                throw new IOException("destination directory is unwritable: "
                    + parent);
        }

        // if we've gotten this far then everything is ok
        // so we copy the file one buffer of bytes at a time
        FileInputStream from = null; // Stream to read from source
        FileOutputStream to = null; // Stream to write to destination
        try
        {
            from = new FileInputStream(fromFile); // Create input stream
            to = new FileOutputStream(toFile); // Create output stream
            byte[] buffer = new byte[IO_BUF_SIZE]; // to hold file data
            int bytesRead;

            // read a chunk of bytes into the buffer then write them out
            // looping until we reach the EOF (when read() returns -1)
            // Note the combination of assignment and comparison in this
            // while loop. This is a common I/O programming idiom.
            while( (bytesRead = from.read(buffer)) != -1 ) // read until EOF
            {
                to.write(buffer, 0, bytesRead); // write
            }
        }
        catch(IOException e)
        {
            throw( e );
        }
        finally
        {
            if( from!=null )
            {
                try
                {
                    from.close();
                }
                catch(IOException ex)
                {
                    //throw( ex );
                    ex.printStackTrace();
                }
            }

            if( to!=null )
            {
                try
                {
                    to.close();
                }
                catch(IOException ex)
                {
                    //throw( ex );
                    ex.printStackTrace();
                }
            }
        }

        return( true );
    }

}
