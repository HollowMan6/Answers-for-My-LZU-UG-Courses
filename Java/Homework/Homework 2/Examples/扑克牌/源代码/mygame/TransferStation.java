package mygame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.ImageIcon;


public class TransferStation 
{
	 public int order;
	 public LinkedList<Card> list;
	 int x;
	public TransferStation(int o) 
	{
		this.order=o;
		list=new LinkedList<Card>();
	}
	public boolean isContain(int mx,int my)
	{
		Rectangle rect=new Rectangle(order*100,10,71,100);
		return rect.contains(mx, my);
	}
    public boolean canTake(int mx,int my)
	{
		if (list.size()==0) 
		{
			return false;
		} 
		return true;
	}
	public Lin newLin(int mx,int my)
	{
	    return new Lin(list);
	}
	public void Merge(Lin lin)
	{
		 list.addAll(lin.list);
	}
	public boolean canPut()
	{
		if (list.size()==0) 
		{
			return true;
		} 
		return false;
	}
	public void removeLast()
	{
		list.removeLast();
		this.x=0;
	}
	public void Display(Graphics g) 
	{
		if (list.size()==0) 
		{
			Image myImage=new ImageIcon("扑克图/1/背面3.gif").getImage();	
			g.drawImage(myImage,order*100,10,null);	
		} 
		else
		{
			Image myImage=new ImageIcon("扑克图/2/"+list.get(0).face+"-"+list.get(0).suit+".gif").getImage();	
			g.drawImage(myImage,order*100,10,null);	
		}
	}
}