package Controller;

/*******************************************************************************************
 * Project: ReUnite
 *
 * Class: MySQLController
 *
 * Author: Adeel M. Ateeque (112013Z)
 * 
 * Lecturer: Ms Lim Ai Hua
 * 
 * Purpose:  A controller class to connect to the online database and to maintain a pool of
 * connections to the database to ensure rapid connection and reduce waiting time.
 * 
 * Honor Code: I pledge that this program represents my own program code. 
 * I received help from no one in designing, coding and debugging my program.
 *******************************************************************************************/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class MySQLController
{
	private static BoneCP connectionPool = null;

	Connection connection = null;

	public MySQLController()
	{
		if (connectionPool == null)
		{
			try 
			{
				DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl("jdbc:mysql://205.134.253.65/saharp5_adeel_school");
			config.setUsername("saharp5_guest");
			config.setPassword("guest123");
			config.setMinConnectionsPerPartition(1);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(3);
			try
			{
				connectionPool = new BoneCP(config);// setup the connection pool
			}
			catch (SQLException e)
			{
				JOptionPane.showMessageDialog(null, e.getCause().getMessage());
				System.out.println(e.getCause().getLocalizedMessage());
			}

			if (connectionPool != null)
			{
				System.out.println("Successfully created a pool of connection");
			}
		}
	}

	/********************************************************
	 * Method Name : testDriver Input Parameter : nil Purpose : To test if the
	 * driver is properly installed (whether your J-Connector for MySQL is
	 * installed properly) Return :nil
	 *******************************************************/
	public void testDriver() throws Exception
	{
		System.out.println("Initializing Server... ");
		try
		{
			Class.forName("org.gjt.mm.mysql.Driver");
			System.out.println(" Driver Found.");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println(" Driver Not Found, exiting..");
			throw (e);
		}
	}

	public void getConnection()// fetch a connection
	{
		if (connectionPool != null)
		{
			try
			{
				connection = connectionPool.getConnection();
				
				if (connection != null)
				{
					System.out.println("Successfully got a connection from the pool");
				}
			}
			catch (SQLException e)
			{
				JOptionPane.showMessageDialog(null, e.getCause().getMessage());
			}

			
		}
		else
		{
			System.out.println("Could not get connection, no connection available...");
		}
	}

	/************************************************************
	 * Method Name : readRequest Input Parameter : String (database query)
	 * Purpose : Obtain the result set from the db query Return : resultSet
	 * (records from the query)
	 ************************************************************/
	public ResultSet readRequest(String dbQuery)
	{
		ResultSet rs = null;
		try
		{
			getConnection();

			if (connection != null)
			{
				System.out.println("DB Query: " + dbQuery);
				// create a statement object
				Statement stmt = connection.createStatement();

				// execute an SQL query and get the result
				rs = stmt.executeQuery(dbQuery);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (connection != null)
			{
				try
				{
					System.out.println("Returning connection to the pool!");
					connection.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}

			}
		}
		return rs;
	}

	/***********************************************************
	 * Method Name : updateRequest Input Parameter : String (database query)
	 * Purpose : Execute update using the db query Return : int (count is 1 if
	 * successful)
	 ***********************************************************/
	public int updateRequest(String dbQuery)
	{
		int count = 0;
		try
		{
			getConnection();

			if (connection != null)
			{
				System.out.println("DB Query: " + dbQuery);
				// create a statement object
				Statement stmt = connection.createStatement();
				// execute an SQL query and get the result
				count = stmt.executeUpdate(dbQuery);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (connection != null)
			{
				try
				{
					System.out.println("Returning connection to the pool!");
					connection.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}

			}
		}
		return count;
	}

	/***********************************************************
	 * Method Name : getColumnNames Purpose : Obtain the column names of the
	 * result set Return : Array of String
	 **********************************************************/
	public String[] getColumnNames(ResultSet rs)
	{
		String[] columnNames = null;
		try
		{
			// Get result set meta data
			ResultSetMetaData rsmd = rs.getMetaData();
			columnNames = new String[rsmd.getColumnCount()];

			// Get the column names; column indices start from 1
			for (int i = 0; i < columnNames.length; i++)
			{
				columnNames[i] = rsmd.getColumnName(i + 1);
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error in getColumnNames() in DBController");
			e.printStackTrace();
		}
		return columnNames;
	}

	/***********************************************************
	 * Method Name : terminate Input Parameter : nil Purpose : Shutdown the
	 * Connection Pool Return :nil
	 **********************************************************/
	public void terminate()
	{
		// close connection
		try
		{
			if (connection != null)
			{
				System.out.println("Returning connection to the pool!");
				connection.close();
			}

			if (connectionPool != null)
			{
				connectionPool.shutdown();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}