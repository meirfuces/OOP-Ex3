package gameClient;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Server.Game_Server;
import Server.game_service;
import algorithms.Graph_Algo;
import dataStructure.*;
import utils.Point3D;
import utils.Range;
public class MyGameGUI extends JFrame implements ActionListener, MouseListener
{

	private static final long serialVersionUID = 1L;
	private DGraph TempGameGui;
	private List<String> FruitsList;
	private List<String> RobotsList ;
	private Range rx = new Range(Integer.MAX_VALUE,Integer.MIN_VALUE);
	private Range ry = new Range(Integer.MAX_VALUE,Integer.MIN_VALUE);
	private game_service game = null;

	
	public MyGameGUI()
	{
		
		
	}
	
	
	public static void main(String[] args) 
	{


		DGraph g = new DGraph();
		MyGameGUI myg = new MyGameGUI();
		myg.initGUI(g);
		myg.setVisible(true);
		
			
	}	
	
	
	
	private void initGUI(DGraph g) 
	{
		this.TempGameGui=g;
		//this.setSize(720, 720);
		this.setSize(1400, 600);
		
		this.setTitle("The Robitics Game of Ginton & Fooxi !");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MenuBar menuBar = new MenuBar();
		this.setMenuBar(menuBar);
		
		Menu New = new Menu("New Game");
		menuBar.add(New);
		
		Menu Options  = new Menu("Options");
		menuBar.add(Options);

		
//**************New Game Button*****************		
		MenuItem item1 = new MenuItem("Manual Game");
		item1.addActionListener(this);
		New.add(item1);
		
		
		MenuItem item2 = new MenuItem("Automatic Game");
		item2.addActionListener(this);
		New.add(item2);
		
//		MenuItem item3 = new MenuItem("Save to File");
//		item3.addActionListener(this);
//		New.add(item3);
		
		
//**************Options button*****************
		MenuItem item9 = new MenuItem("Save as KML");
		item9.addActionListener(this);
		Options.add(item9);
		
//		
//		MenuItem item5 = new MenuItem("Shortest Path");
//		item5.addActionListener(this);
//		Options.add(item5);
//		
//		MenuItem item6 = new MenuItem("TSP");
//		item6.addActionListener(this);
//		Options.add(item6);
//		
//		MenuItem item7 = new MenuItem("Is Conncected ");
//		item7.addActionListener(this);
//		Options.add(item7);
//		
//		MenuItem item8 = new MenuItem("Shortest Path Distance");
//		item8.addActionListener(this);
//		Options.add(item8);
	 
		
		this.addMouseListener(this);
		
	}
	
	
	
	public void paint(Graphics d) 
	{
		super.paint(d);
		
		if (TempGameGui != null) 
		{
			//draw fruits

			if(FruitsList!=null)
			{
			for(int i = 0; i<FruitsList.size(); i++) 
			{
				try {
					String fruit_json = FruitsList.get(i);
					JSONObject line = new JSONObject(fruit_json);
					JSONObject f = line.getJSONObject("Fruit");
					String pos = f.getString("pos");
					int type = f.getInt("type");
					
					Point3D pBefore = new Point3D(pos);
					double offsetx = (pBefore.x() - rx.get_min())/(rx.get_max() - rx.get_min());
					double x = 1200 * offsetx + 100; 
					double offsety = (pBefore.y() - ry.get_min())/(ry.get_max() - ry.get_min());
					double y = 400 * offsety;
					y = (400 - y) + 100;
					Point3D pAfter = new Point3D(x, y);
					
					if(type<0)
						d.setColor(Color.ORANGE);
					else 
						d.setColor(Color.GREEN);
					
					d.fillOval((int)pAfter.x() , (int)pAfter.y() , 18, 18);


				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			
			}
			
			
			
			
			//draw robots
			
			if(RobotsList!=null)
			{
				System.out.println(RobotsList.size());
				for(int i = 0; i<RobotsList.size(); i++) {
					try {
						String robot_json = RobotsList.get(i);
						JSONObject line = new JSONObject(robot_json);
						JSONObject r = line.getJSONObject("Robot");
						String pos = r.getString("pos");
						Point3D pBefore = new Point3D(pos);
						double offsetx = (pBefore.x() - rx.get_min())/(rx.get_max() - rx.get_min());
						double x = 1200 * offsetx + 100; 
						double offsety = (pBefore.y() - ry.get_min())/(ry.get_max() - ry.get_min());
						double y = 400 * offsety;
						y = (400 - y) + 100;
						Point3D pAfter = new Point3D(x, y);
						d.setColor(Color.RED);
						d.drawOval((int)pAfter.x(), (int)pAfter.y(), 15, 15);


					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			
			//get nodes
			Collection<node_data> nodes = TempGameGui.getV();

			for (node_data n : nodes) 
			{
				

				
				//draw nodes
				Point3D p = n.getLocation();
				d.setColor(Color.BLUE);

				setRange();
				
				double offsetx = (p.x() - rx.get_min())/(rx.get_max() - rx.get_min());
				double x = 1200 * offsetx + 100; 
				double offsety = (p.y() - ry.get_min())/(ry.get_max() - ry.get_min());
				double y = 400 * offsety;
				y = (400 - y) + 100;
				
				
				//d.fillOval(p.ix(), p.iy(), 10, 10);
				
				d.fillOval((int)x,(int)y,10,10); 
				
				//draw nodes-key's
				d.setColor(Color.BLUE);
				//d.drawString(""+n.getKey(), p.ix()-4, p.iy()-4);
				d.drawString(""+n.getKey(), ((int)x)-4, ((int)y)-4);
				
				//check if there are edges
				if (TempGameGui.edgeSize()==0)
					continue;
				if ((TempGameGui.getE(n.getKey())!=null))
				{
					//get edges
					Collection<edge_data> edges = TempGameGui.getE(n.getKey());
					for (edge_data e : edges) 
					{
						//draw edges
						d.setColor(Color.RED);
						Point3D p2 = TempGameGui.getNode(e.getDest()).getLocation();
						
						//d.drawLine(p.ix()+5, p.iy()+5, p2.ix()+5, p2.iy()+5);
						//setRange();
						double offsetx1 = (p2.x() - rx.get_min())/(rx.get_max() - rx.get_min());
						double x1 = 1200 * offsetx1 + 100; 
						double offsety1 = (p2.y() - ry.get_min())/(ry.get_max() - ry.get_min());
						double y1 = 400 * offsety1;
						y1 = (400 - y1) + 100;
						d.drawLine(((int)x)+5, ((int)y)+5, ((int)x1)+5, ((int)y1)+5);
						
						//draw direction
						d.setColor(Color.YELLOW);
						//d.fillOval((int)((p.ix()*0.1)+(0.9*p2.ix()))+7, 1+(int)((p.iy()*0.1)+(0.9*p2.iy())), 7, 7);
						//d.fillOval((int)((((int)x)*0.1)+(0.9*((int)x1)))+7, 1+(int)((((int)y)*0.1)+(0.9*((int)y1))), 7, 7);
						
						//draw weight
						String WeightString = ""+String.valueOf(e.getWeight());
						d.setColor(Color.RED);
						//d.drawString(WeightString, 3+(int)((p.ix()*0.1)+(0.9*p2.ix()))+7, 3+(int)((p.iy()*0.1)+(0.9*p2.iy())));
						//d.drawString(WeightString, 3+(int)((((int)x)*0.1)+(0.9*((int)x1)))+7, 3+(int)((((int)y)*0.1)+(0.9*((int)y1))));
						
					}
				}	
			}
		}	
	}
	
	
	
	
	

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	
	
	@Override
	public void actionPerformed(ActionEvent Command) 
	{
		String str = Command.getActionCommand();		
		Graph_Algo TempGrAl=new Graph_Algo();
		JFileChooser jfc;
		
		//FileNameExtensionFilter filter;
		
		switch(str) 
		{

			
//******************Init Random Graph******************************
		case "Automatic Game":
			System.out.println("Starting an Automatic Game..");
			

				initGUI(this.TempGameGui);
		
			
			break;	
			

			
//			TempGrAl=new Graph_Algo();
//
//			jfc = new JFileChooser(FileSystemView.getFileSystemView());
//			jfc.setDialogTitle("Open Graph"); 
//
//			int userSelection = jfc.showOpenDialog(null);
//			if(userSelection == JFileChooser.APPROVE_OPTION) 
//			{
//				System.out.println("The file:" + jfc.getSelectedFile().getName()+" is successful Opened.");
//				TempGrAl.init(jfc.getSelectedFile().getAbsolutePath());
//				graph gr_new=TempGrAl.copy();
//				
//				initGUI(gr_new);	
//			}			
//			break;	
//***************Manual Game***************************************		
		case "Manual Game": 
			System.out.println("Starting an Automatic Game..");
			
			try {
				JFrame mg = new JFrame();
				int SnrNum=0;
				try {
					String StrScenario = JOptionPane.showInputDialog(mg,"Enter Scenario Number:\n(Number from the Range 0 - 23)");
					SnrNum = Integer.parseInt(StrScenario);
					}
				catch (Exception e) 
				{
					JOptionPane.showMessageDialog(mg, "Error: illegal Scenario Number");
					System.out.println("Error: illegal Scenario Number");
					break;
				}
				
				if (SnrNum<0 || SnrNum>23) 
				{
					JOptionPane.showMessageDialog(mg, "Error: illegal Scenario Number");
					System.out.println("Error: illegal Scenario Number");
					break;
				}
				
				JOptionPane.showMessageDialog(mg, "Scenario Number: "+SnrNum+", is Now Loading..");
				System.out.println("Scenario Number: "+SnrNum+", is Now Loading..");
				game=Game_Server.getServer(SnrNum);
				

				FruitsList = game.getFruits();
				
				game.addRobot(0);
				game.addRobot(5);
				game.addRobot(9);
				
				RobotsList=game.getRobots();
				DGraph mgGraph = new DGraph();
				mgGraph.init(game.getGraph());
				
				this.initGUI(mgGraph);
				
				
			}
		
			catch (Exception e) 
			{
				e.printStackTrace();
			}
				break;
			
		}
	}
	
	


	private void setRange() 
	{
		Collection<node_data> c = TempGameGui.getV();
		Iterator<node_data> itrV = c.iterator();
		while(itrV.hasNext()) 
		{
			node_data n = itrV.next();
			Point3D p = n.getLocation();
			double x = p.x();
			double y = p.y();
			if(x<rx.get_min())
				rx.set_min(x);
			else if(x>rx.get_max())
				rx.set_max(x);
			if(y<ry.get_min())
				ry.set_min(y);
			else if(y>ry.get_max())
				ry.set_max(y);
		}
	}
}
			
	
			
////***************Save Graph to File***************************************
//		case "Save to File":  
//			System.out.println("Save to File:");
//			TempGrAl=new Graph_Algo((DGraph)this.TempGraphGui);		
//
//			jfc = new JFileChooser(FileSystemView.getFileSystemView());
//			jfc.setDialogTitle("Save As");
//
//			int userSelection1 = jfc.showSaveDialog(null);
//			if (userSelection1 == JFileChooser.APPROVE_OPTION) 
//			{
//				System.out.println("The Graph saved in this path: " + jfc.getSelectedFile().getAbsolutePath());
//				TempGrAl.save(jfc.getSelectedFile().getAbsolutePath());
//			}
//			break;
//			
////************Shortest Path	*********************
//			
//		case "Shortest Path":
//			System.out.println("Shortest Path:");
//			String StrPath="";
//			try {
//				JFrame sp = new JFrame();
//
//				int src=0;
//				int dest=0;
//				try {
//					String StrSource = JOptionPane.showInputDialog(sp,"Source-Vertex:");
//					String StrDest = JOptionPane.showInputDialog(sp,"Destnation-Vertex:");
//					src = Integer.parseInt(StrSource);
//					dest = Integer.parseInt(StrDest);
//					}
//				catch (Exception e) 
//				{
//					JOptionPane.showMessageDialog(sp, "Error: illegal Vertex-key");
//					System.out.println("Error: Vertex-key");
//					break;
//				}
//				
//				if (this.TempGraphGui.getNode(src)==null) 
//				{
//					JOptionPane.showMessageDialog(sp, "Error: illegal Vertex-key");
//					System.out.println("Error: illegal Vertex-key");
//					break;
//				}
//				
//				if (this.TempGraphGui.getNode(dest)==null) 
//				{
//					JOptionPane.showMessageDialog(sp, "Error: illegal Vertex-key");
//					System.out.println("Error: illegal Vertex-key");
//					break;
//				}
//				
//				Graph_Algo GSP = new Graph_Algo();
//				GSP.init(TempGraphGui);
//
//				if(((GSP.shortestPathDist(src, dest)==Double.POSITIVE_INFINITY))/*||SPList.size()<2*/) //HAVE TO CHECK WHAT THE PROBLEM$$$$$$$$$$%%%%%
//				{
//					JOptionPane.showMessageDialog(sp,"There is no Path,\nThe Graph is NOT connect!!");
//					System.out.println("There is no Path,\nThe Graph is NOT connect!!");
//					break;
//				}
//				
//				List<node_data> SPList = GSP.shortestPath(src, dest);
//				
//			
//					
//				
//				graph gr_new=new DGraph();
//				gr_new.addNode(SPList.get(0));
//				StrPath=StrPath+SPList.get(0).getKey();
//				for (int i=1; i<SPList.size(); ++i) 
//				{
//
//					gr_new.addNode(SPList.get(i));
//					gr_new.connect(SPList.get(i-1).getKey(), SPList.get(i).getKey(), this.TempGraphGui.getEdge(SPList.get(i-1).getKey(), SPList.get(i).getKey()).getWeight());
//					StrPath=StrPath+("->"+SPList.get(i).getKey());
//				}
//				this.initGUI(gr_new);
//				JOptionPane.showMessageDialog(sp,"The Short Path is:\n"+ StrPath);
//				System.out.println(StrPath);
//			}	
//			catch (Exception e) 
//			{
//				e.printStackTrace();
//			}
//			
//			break;
//			
////***************Shortest Path Distance***************************
//			
//		case "Shortest Path Distance": 
//			System.out.println("Shortest Path Distance:");
//			
//			try {
//				JFrame sp = new JFrame();
//
//				int src=0;
//				int dest=0;
//				try {
//					String StrSource = JOptionPane.showInputDialog(sp,"Source-Vertex:");
//					String StrDest = JOptionPane.showInputDialog(sp,"Destnation-Vertex:");
//					src = Integer.parseInt(StrSource);
//					dest = Integer.parseInt(StrDest);
//					}
//				catch (Exception e) 
//				{
//					JOptionPane.showMessageDialog(sp, "Error: illegal Vertex-key");
//					System.out.println("Error: Vertex-key");
//					break;
//				}
//				
//				if (this.TempGraphGui.getNode(src)==null) 
//				{
//					JOptionPane.showMessageDialog(sp, "Error: illegal Vertex-key");
//					System.out.println("Error: illegal Vertex-key");
//					break;
//				}
//				
//				if (this.TempGraphGui.getNode(dest)==null) 
//				{
//					JOptionPane.showMessageDialog(sp, "Error: illegal Vertex-key");
//					System.out.println("Error: illegal Vertex-key");
//					break;
//				}
//				
//				Graph_Algo GSP = new Graph_Algo();
//				GSP.init(TempGraphGui);
//				double dis = GSP.shortestPathDist(src, dest);
//				
//				
//				JOptionPane.showMessageDialog(sp,"The Shortest Path Distance is:\n"+ dis);
//			}	
//			catch (Exception e) 
//			{
//				e.printStackTrace();
//			}
//			
//			break;
//			
//		
//	}
//
//}

