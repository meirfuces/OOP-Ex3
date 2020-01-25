package gameClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.json.JSONObject;

import Server.Game_Server;

import Server.game_service;
import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Fruit;
import dataStructure.Robot;
import dataStructure.graph;
import dataStructure.node_data;
import utils.StdDraw;
/**
 * class that manngae the whole game
 * @author Ginton and Meir
 *
 */
public class GameManager 
{
	static MyGameGUI mg;
	game_service game_;
	ArrayList<Fruit> FruitsList;
	ArrayList<Robot> RobotsList;
	graph gr;
	
	public GameManager (game_service game) 
	{
		game_= game;
		
	}
	
//*********Start , Auto ,Manual************8
	public static void StartGame () 
{
		MyGameGUI mg = new MyGameGUI();
		mg.initGUI();
}
	public static void playAuto (MyGameGUI _mg) 
{
		mg = _mg;
		mg.playAuto();
}
	public static void playmanual (MyGameGUI _mg) 
{
		mg = _mg;
		mg.playManual();
}

/**
 * Helper- give the specfic sleep time to every snceario
 * @param scenario_num - level number
 * @return
 */
public int chooseTimeSleep (int scenario_num)  
{
		
		if (scenario_num==0) 
		{
			return 35; 
		}
		if (scenario_num==1) 
		{
			return 40;
			
		}
			
		if (scenario_num==3) 
		{
			return 40; 
		}
			
		if (scenario_num==5) 
		{
			return 105; 
		}
			
		if (scenario_num==9) 
		{
			return 85; 
		}
			
		if (scenario_num==11) 
		{
				
			return 100; 
		}
			
		if (scenario_num==13) 
		{
				
			return 100; 
		}
			
		if (scenario_num==16) 
		{
				
			return 100; 
		}
			
		if (scenario_num==19) 
		{
				
			return 110; 
		}
	
		if (scenario_num==20) 
		{
				
			return 99; 
		}
	
	
		if (scenario_num==23) 
		{
		
		return 35; 
		}
		
		return 0;
		
	}

	
	

	
//*******init Objects*****************
	
	
	/**
	 * Init List of Fruit
	 * @return
	 */
	public ArrayList<Fruit> GetFruitList ()
	{

		Iterator<String> fruit_iter = game_.getFruits().iterator();
		//clear fruits collection if needed
		
		if(FruitsList!= null)
			FruitsList.clear();
		else
			FruitsList = new ArrayList<Fruit>();
		
		
		//set all fruits in their places
		while(fruit_iter.hasNext())
		{
			String sFruit = fruit_iter.next();
			Fruit f = new Fruit();
			f.initFruit(sFruit);
			FruitsList.add(f);
		}
		return FruitsList;
	}
	
	/**
	 * Init Robots
	 * @param game - data from server
	 * @param _gr - current graph
	 * @return robot list
	 */
	public ArrayList<Robot> initRobots(game_service game, graph _gr)
	{
		this.gr = _gr;
		RobotsList = new ArrayList<Robot>();

		
		List<String> Jrobots = game.getRobots();
		for (String jrobot : Jrobots) 
		{
			Robot CurRob = new Robot();
			CurRob.UpdateGraph(gr);
			CurRob.initRobot(jrobot);
			RobotsList.add(CurRob);
		}
		return RobotsList;
	}

}