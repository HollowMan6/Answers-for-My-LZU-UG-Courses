package mygame;

import java.awt.Graphics;
import java.util.List;


public class Lin 
{
	  public List<Card> list;
	  public int x;
	  public int y;
	  public int z;
	  public Lin( List<Card> list) 
	  {
		   this.list=list;
	  }   
	  public void Display(Graphics g) 
	  {
		 if (z==1) 
		{
			for (int i=0;i<list.size();i++)
			{
			   g.fillRect(x*100, 200+y*30, 70,  50+30*list.size());
			}
	  } 
		else
		{
			for (int i=0;i<list.size();i++)
			{
			   g.fillRect(x*100, 30, 70,  50+30*list.size());
			}
		}
	  }	
}