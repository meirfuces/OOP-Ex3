	package gameClient;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;

import Server.Game_Server;
import Server.game_service;
import dataStructure.DGraph;
/**
 * This class represents a simple example of using MySQL Data-Base.
 * Use this example for writing solution. 
 * @author boaz.benmoshe
 *
 */
public class SimpleDB {
	public static final String jdbcUrl="jdbc:mysql://db-mysql-ams3-67328-do-user-4468260-0.db.ondigitalocean.com:25060/oop?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
	public static final String jdbcUser="student";
	public static final String jdbcUserPassword="OOP2020student";
	
	/**
	 * Simple main for demonstrating the use of the Data-base
	 * @param args
	 */
	public static void main(String[] args) 
	{

		Game_Server.login(205464712);	
		game_service game = Game_Server.getServer(0); // you have [0,23] games
		String g = game.getGraph();
		//OOP_DGraph gg = new OOP_DGraph();
		DGraph gg = new DGraph();
		MyGameGUI mg = new MyGameGUI();

		gg.init(g);
		mg.initGUI();
		String kml = getKML(205464712,20);

		System.out.println("hello");
		System.out.println("***** KML file example: ******");
		System.out.println(kml);
		System.out.println("hello");


//			allUsers();
//			printLog();
			//String kml = getKML(id1,20);
		}
	/** simply prints all the games as played by the users (in the database).
	 * 
	 */
		public static void printLog() 
		{
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = 
						DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcUserPassword);
				Statement statement = connection.createStatement();
				String allCustomersQuery = "SELECT * FROM Logs;";
				ResultSet resultSet = statement.executeQuery(allCustomersQuery);
				
				while(resultSet.next())
				{
					System.out.println("Id: " + resultSet.getInt("UserID")+","+resultSet.getInt("levelID")+","+resultSet.getInt("moves")+","+resultSet.getDate("time"));
				}
				resultSet.close();
				statement.close();		
				connection.close();		
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
			
		public static int allUsers() 
		{
			int ans = 0;
			String allCustomersQuery = "SELECT * FROM Users;";
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = 
						DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcUserPassword);		
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(allCustomersQuery);
				while(resultSet.next()) 
				{
					System.out.println("Id: " + resultSet.getInt("UserID"));
					ans++;
				}
				resultSet.close();
				statement.close();		
				connection.close();
			}
			catch (SQLException sqle) {
				System.out.println("SQLException: " + sqle.getMessage());
				System.out.println("Vendor Error: " + sqle.getErrorCode());
			}
			
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return ans;
		}
	}
		
