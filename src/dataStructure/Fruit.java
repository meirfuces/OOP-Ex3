package dataStructure;

import utils.Point3D;

public class Fruit 
{
	enum Type 
	{ 
	    Banana, Apple;
	} 
	
	private double value;
	private Type type;
	private Point3D pos;
	
	public Fruit(double val, int ty,Point3D p)
	{
		this.value=val;
		
		if(ty == -1)
			this.type=Type.Banana;
		else if(ty == 1)
			this.type=Type.Apple;
		
		this.pos=p;
			
	}

}
