package Controller.RIM.Utils;
/*******************************************************************************************
 * Project: ReUnite
 *
 * Class: GUIUtils
 *
 * Author: Adeel M. Ateeque (112013Z)
 * 
 * Lecturer: Ms Lim Ai Hua
 * 
 * Purpose: A utility class, contains static methods that are used all the time. For GUI
 * related tasks.
 * 
 * Honor Code: I pledge that this program represents my own program code. 
 * I received help from no one in designing, coding and debugging my program.
 *******************************************************************************************/

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.table.TableColumn;

public final class GUIUtils {

    /**
     * Execute a task on EDT and block's it, until the task is finished.
     * If there is an exception, it is converted to a RuntimeException.
     *
     * @param task The process to run.
     */
    public static void invokeAndWait(final Runnable task)
    {
        if( EventQueue.isDispatchThread() )
        {
            task.run();
        }
        else
        {
            try
            {
                EventQueue.invokeAndWait( task );
            }
            catch(Exception ex)
            {
                throw new RuntimeException(ex.getMessage(), ex);
            }
        }
    }

    /**
     * Centers a window on screen.
     * <p>
     * @param w     The window to center.
     */
    public static void centerOnScreen(Window w)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension splashSize = w.getPreferredSize();
        w.setLocation(screenSize.width / 2 - (splashSize.width / 2),
                      screenSize.height / 2 - (splashSize.height / 2));
    }


    /**
     * Maximizes a JFrame, just like the 'maximize window' button does.
     * <p>
     * @param f     The frame to maximize.
     */
    public static void maximizeJFrame(JFrame f)
    {
        f.setExtendedState( Frame.MAXIMIZED_BOTH );
    }


    /**
     * Locks a Jtable's column width with 'pixels' size.
     * <p>
     * @param tc    	The table column.
     * @param pixels 	The desired pixels.
     */
    public static void lockJTableColumnWidth(TableColumn tc, int pixels)
    {
        if( tc!=null )
        {
            tc.setMinWidth( pixels );
            tc.setMaxWidth( pixels );
            tc.setPreferredWidth( pixels );
            tc.setResizable( false );
        }
    }


    /**
     * Hides a specific column of a JTable.
     * <p>
     * @param tc    	The table column.
     */
    public static void hideJTableColumn(TableColumn tc)
    {
        lockJTableColumnWidth(tc, 0);
    }

}
