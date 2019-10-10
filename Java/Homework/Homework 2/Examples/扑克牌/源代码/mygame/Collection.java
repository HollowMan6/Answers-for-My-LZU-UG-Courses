package mygame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.ImageIcon;



public class Collection {
	public int order;
	public LinkedList<Card> list;
	public Collection(int o) 
	{
		list=new LinkedList<Card>();
		this.order=o;
	}
	public boolean isContain(int mx,int my)
	{
		Rectangle rect=new Rectangle(order*100+500,10,71,100);
		return rect.contains(mx, my);
	}
	public boolean canCollect(Lin lin)
	{
		Card card1 = lin.list.get(0);
		if (list.size()!=0) 
		{
			Card card2 = list.getLast();
			if (card1.face==card2.face+1 && card1.suit==card2.suit) 
			{
				return true;
			} 
		} 
		if (list.size()==0&&lin.list.get(0).face==1) 
		{
			return true;
		} 
		else
		{
			return false;
		}
		
	}
	public void Merge(Lin lin)
	{
		 list.addAll(lin.list);
	}
	public void Display(Graphics g) 
	{
		if (list.size()==0) 
		{
			Image myImage=new ImageIcon("扑克图/2/背面3.gif").getImage();	
			g.drawImage(myImage,order*100+500,10,null);	
		} 
		else
		{
			Image myImage=new ImageIcon("扑克图/1/"+list.getLast().face+"-"+list.getLast().suit+".gif").getImage();	
			g.drawImage(myImage,order*100+500,10,null);	
		}
	}
	public boolean is13()
	{
		if (list.size()==13) 
		{
			return true;
		} 
		return false;
	}
	public int CollectedSize()
	{
		return list.size();
	}
}