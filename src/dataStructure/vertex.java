package dataStructure;

import java.io.Serializable;
import java.util.HashMap;

import utils.Point3D;

public class vertex implements node_data,Serializable 
{

	private static final long serialVersionUID = 1L;

	public static int idBuilder=0;
	
	private  int key;
	private double weight;
	private int tag;
	private String info ="";
	private Point3D location;

//**********Constructors***********************		
	
	public vertex()
	{
		idBuilder++;
		this.key=idBuilder;
		this.info="";
		this.tag=0;
		this.weight=0;
	}
	
	
	public vertex (Point3D l, double w) 
	{
		idBuilder++;
		this.key=idBuilder;
		this.weight=w;
		this.location=l;
		this.info="";
		this.tag=0;
		
	}
	
	public vertex (Point3D l) 
	{
		idBuilder++;
		this.key=idBuilder;
		this.weight=0;
		this.location=l;
		this.info="";
		this.tag=0;
		
	}
	
	
	public vertex (int key,Point3D l) // new one
	{
		//idBuilder++;
		this.key=key;
		//this.weight=0;
		this.location=l;
		//this.info="";
		//this.tag=0;
		
	}
//**********Getters and Setters***********************	
	@Override
	public int getKey() 
	{
		return this.key;
	}
	
	
	@Override
	public Point3D getLocation() 
	{
		return this.location;
	}
	
	
	@Override
	public void setLocation(Point3D p)
	{
		this.location= new Point3D(p.x(),p.y());
		
	}
	
	
	@Override
	public double getWeight() 
	{
		return this.weight;
	}
	
	
	@Override
	public void setWeight(double w) 
	{
		this.weight=w;
		
	}
	
	
	@Override
	public String getInfo() 
	{
		return this.info;
	}
	
	
	@Override
	public void setInfo(String s) 
	{
		this.info=s;
		
	}
	
	
	@Override
	public int getTag() 
	{
		return this.tag;
	}
	
	
	@Override
	public void setTag(int t) 
	{
		this.tag=t;
		
	}
	

}
