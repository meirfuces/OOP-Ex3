package gameClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
/**
 * Class that contain few opreation fot handle the DB Server
 * @author Ginton & Meir
 *
 */
public class MyDB  
{
	public static final String jdbcUrl="jdbc:mysql://db-mysql-ams3-67328-do-user-4468260-0.db.ondigitalocean.com:25060/oop?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
	public static final String jdbcUser="student";
	public static final String jdbcUserPassword="OOP2020student";
	public static final int id_meir = 205464712;
	public static final int id_Ginton = 203965884;
	
	
	/**
	 * Count the Number of games that ginto and meir did on the server	
	 * @return
	 */
	public static  int getNumOfGames() 
	{
			int counter = 0;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection(SimpleDB.jdbcUrl, SimpleDB.jdbcUser,
						SimpleDB.jdbcUserPassword);
				Statement statement = connection.createStatement();
				String allCustomersQuery = "SELECT * FROM Logs;";
				ResultSet resultSet = statement.executeQuery(allCustomersQuery);
				while (resultSet.next()) 
				{
					if (resultSet.getInt("UserID") == id_meir || resultSet.getInt("UserID") == id_Ginton) 
					{
						counter++;
					} //end if
				} // end while
				resultSet.close();
				statement.close();
				connection.close();
			}
			catch (SQLException sqle) 
			{
				System.out.println("SQLException: " + sqle.getMessage());
				System.out.println("Vendor Error: " + sqle.getErrorCode());
			
			} 
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return counter;
			}

	/**
	 * calculate and return what rank are you in the current level
	 * @param level
	 * @return the rank
	 */
		public int RankClass (int level)  
		{
			int myScore = GetLevelScore(level);
			int rank=1;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection(SimpleDB.jdbcUrl, SimpleDB.jdbcUser,
						SimpleDB.jdbcUserPassword);
				Statement statement = connection.createStatement();
				String allCustomersQuery = "SELECT * FROM Logs;";
				ResultSet resultSet = statement.executeQuery(allCustomersQuery);
			
				while (resultSet.next()) 
				{

			
			if (resultSet.getInt("UserID") != 205464712 && resultSet.getInt("UserID") != 205464712) 
			{
				if (resultSet.getInt("levelID") == level)
					if (resultSet.getInt("score")>myScore)
						rank++;
			}
				}
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException sqle) {
				System.out.println("SQLException: " + sqle.getMessage());
				System.out.println("Vendor Error: " + sqle.getErrorCode());
			
			} 
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
				
			return rank;
		}
		
		
		/**
		 * Get your lligal high score in the certain level
		 * @param level
		 * @return the score
		 */
		public int GetLevelScore (int level)   
		{

			int max=0;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection(SimpleDB.jdbcUrl, SimpleDB.jdbcUser,
						SimpleDB.jdbcUserPassword);
				Statement statement = connection.createStatement();
				String allCustomersQuery = "SELECT * FROM Logs;";
				ResultSet resultSet = statement.executeQuery(allCustomersQuery);
			
			
			
			
			while (resultSet.next()) 
			{

				if (resultSet.getInt("UserID") == 205464712 || resultSet.getInt("UserID") == 205464712) 
				{

					if (resultSet.getInt("levelID") == level)
					if (resultSet.getInt("score")>max)
						max = resultSet.getInt("score");
				}
			}
			
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			System.out.println("Vendor Error: " + sqle.getErrorCode());
		
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
			
		return max;		
		}
		
		
		/**
		 * The highest score in your game
		 * @return
		 */
		public int GetBestScore ()   
		{
			int bestScore=0;	
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection(SimpleDB.jdbcUrl, SimpleDB.jdbcUser,
						SimpleDB.jdbcUserPassword);
				Statement statement = connection.createStatement();
				String allCustomersQuery = "SELECT * FROM Logs;";
				ResultSet resultSet = statement.executeQuery(allCustomersQuery);

			while (resultSet.next()) {
				int score =resultSet.getInt("score") ; 
				if (resultSet.getInt("UserID") == 205464712 || resultSet.getInt("UserID") == 205464712) 
					if (score>bestScore) {
						bestScore = score;
					
				}
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			System.out.println("Vendor Error: " + sqle.getErrorCode());
		
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
					return bestScore;
	}


		
		/**
		 * this function returns the KML string as stored in the database (userID, level);
		 * @param id
		 * @param level
		 * @return
		 */
				public static String getKML(int id, int level) {
					String ans = null;
					String allCustomersQuery = "SELECT * FROM Users where userID="+id+";";
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection connection = 
						DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcUserPassword);		
						Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(allCustomersQuery);
						if(resultSet!=null && resultSet.next()) {
							ans = resultSet.getString("kml_"+level);
						}
					}
					catch (SQLException sqle) 
					{
						System.out.println("SQLException: " + sqle.getMessage());
						System.out.println("Vendor Error: " + sqle.getErrorCode());
					}
					
					catch (ClassNotFoundException e) 
					{
						e.printStackTrace();
					}
					return ans;
				}	
	
	
}