package dataStructure;

import utils.Point3D;

public class Robot 
{
	private int id;
	private double value;
	private int src;
	private int dest;
	private double speed; //maybe delete
	private Point3D pos;
	
	public Robot(int id1,double val1, int sr,int dst,double spd,Point3D loc)
	{
		this.id = id1;
		this.value=val1;
		this.src=sr;
		this.dest=dst;
		this.speed=spd;
		this.pos=loc;
	}
	public Robot(int id1,double val1, int sr,int dst,Point3D loc)
	{
		this.id = id1;
		this.value=val1;
		this.src=sr;
		this.dest=dst;
		//.speed=spd;
		this.pos=loc;
	}



}
