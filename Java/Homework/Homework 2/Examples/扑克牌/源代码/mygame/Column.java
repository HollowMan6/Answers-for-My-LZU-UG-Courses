package mygame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;


public class Column 
{
	   public LinkedList<Card> list;
	   public int x;
	   public int y;
	   public int order;
	   public Column(int o) 
	   {
		   this.order=o;
		   list=new LinkedList<Card>();
	   }
	   public void Next(Card c) 
	   {
		 list.addLast(c);
	   }
	   public void Display(Graphics g) 
	   {
		  for (int i=0;i<list.size();i++)
			{
			   Card c=list.get(i);
			   c.x=order*100;
			   c.y=200+30*i;
			   c.DisplayCard(g);
			}
	   }
	   public Lin newLin(int mx, int my) 
	   {
		   int cong=(my-200)/30;
		   LinkedList<Card> xinList=new LinkedList<Card>();
		   for (int i=cong;i<list.size();i++)
			{
			   xinList.add( list.get(i));
			}
		   return new Lin(xinList);
	   }
	   public Boolean canPut(Lin lin) 
	   {
		Card card1 = lin.list.get(0);
		if (list.size()!=0) 
		{
			Card card2 = list.getLast();
			if (card1.face+1==card2.face && card1.suit%2!=card2.suit%2) 
			{
				return true;
			} 
		} 
		else
		{
			return true;
		}
		
		return false;
	}
	public void Merge(Lin lin) 
	{
		 list.addAll(lin.list);
	}
	public Boolean isContain(int mx, int my) 
	{
		int j = list.size();
		Rectangle rect=new Rectangle(order*100,200,71,j*30+100);
		return rect.contains(mx, my);
	}
	public Boolean canTake(int mx, int my) 
	{
		int cong=0;
		cong=(my-200)/30;
		if (cong+1!=list.size()) 
		{
			 return false;
		} 
		 return true;
	}
	public void removeLast()
	{
		list.removeLast();
	}
}