package mygame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
public class FreeCell 
{
	public Column[] columns;
	public Lin lin;
	public Cards cards;
	public Collection[] collections;
	public TransferStation[] transferstations;
	Column congChuan;
	TransferStation congQianChuan;
	TransferStation qian;
	public List<Card> newList;
	public FreeCell() {}
	public void initial() 
	{
		transferstations=new TransferStation[4];
		for (int i = 0; i < transferstations.length; i++)
		{
			transferstations[i]=new TransferStation(i);
			transferstations[i].x=0;
		}
		collections=new Collection[4];
		for (int i = 0; i < collections.length; i++)
		{
			collections[i]=new Collection(i);
		}
		columns=new Column[8];
		for (int i = 0; i < columns.length; i++)
		{
			columns[i]=new Column(i);
		}
		cards=new Cards();
		for (int i = 0; i < 52; i++)
		{
		   Card mycard =cards.deal();
		   columns[i%8].Next(mycard);
		}
	}
	
	//public void Move(int mx,int my){}
	public void Display(Graphics g) 
	{
		Image  myImage=(new ImageIcon("扑克图/扑克.jpg")).getImage();
		g.drawImage(myImage,0,0,null);
		for (int i = 0; i < 8; i++)
		{
			columns[i].Display(g); 
		}
		for (int i = 0; i < transferstations.length; i++)
		{
			transferstations[i].Display(g);
		}
		for (int i = 0; i < collections.length; i++)
		{
			collections[i].Display(g);
		}
		
	}
	public void DisplayWon(Graphics g)
        {
		Image  myImage=(new ImageIcon("扑克图/胜利_副本_副本.jpg")).getImage();	
		g.drawImage(myImage,0,0,null);	
	}
	public boolean linMe()
	{
		if (lin!=null) 
		{
			return true;
		} 
		else
		{
			return false;
		}
	}
	public void HoldCard(int mx,int my) 
	{
		lin=null;
		if (my<=100&&mx<370) 
		{
			int j=mx/100;
			congQianChuan=transferstations[j];
			if(congQianChuan.canTake(mx, my) && congQianChuan.isContain(mx, my))
			{
				lin=congQianChuan.newLin(mx,my);
				congQianChuan.x=1;
				lin.z=0;
				lin.x=j;
				lin.y=10;
			}
		} 
		else
		{
			int j=mx/100;
			congChuan=columns[j];
			if (congChuan.canTake(mx,my) && congChuan.isContain(mx, my) ) 
			{
				lin=congChuan.newLin(mx,my);
				lin.z=1;
				lin.x=j;
				lin.y=(my-200)/30;
			} 
		}
	}
	public void Put(int mx,int my) 
	{
		if(lin==null)
		{
			return;
		}
                if (my>=10&&my<=100&&mx<370) 
		{
			
			int j=mx/100;
			TransferStation daoChuan = transferstations[j];
			if(daoChuan.canPut() && daoChuan.isContain(mx, my))
			{
				daoChuan.Merge(lin);
				congChuan.removeLast();

			}
		} 
		if (my<=100&&mx<870&&mx>500) 
		{
			int j=(mx-500)/100;
			Collection daoChuan = collections[j];
			if( daoChuan.canCollect(lin) && daoChuan.isContain(mx, my))
			{
				daoChuan.Merge(lin);
				if (congQianChuan!=null) 
				{
						if(congQianChuan.x==1)
						{
							congQianChuan.removeLast();
						}
						else
						{
							congChuan.removeLast();
						}			
				} 
				else
				{
					congChuan.removeLast();
				}
			}
		} 
		else
		{
			int j=mx/100;
			Column daoChuan = columns[j];
			if (daoChuan.canPut(lin) && daoChuan.isContain(mx, my) ) 
			{
				daoChuan.Merge(lin);
				if (congQianChuan!=null) 
				{
						if(congQianChuan.x==1)
						{
							congQianChuan.removeLast();
							
						}
						else
						{
							congChuan.removeLast();
						}
									
				} 
				else
				{
					congChuan.removeLast();
				}
			} 	
		}
		lin=null;
	}
	public void doubleClick(int mx,int my)
	{

		int j=mx/100;
		Column congChuan = columns[j];
		LinkedList<Card> xinList=new LinkedList<Card>();
		xinList.add(columns[j].list.getLast());
		Lin l = new Lin(xinList);
		for (int i=0;i<4;i++)
		{
			if ( transferstations[i].canPut()&&columns[j].isContain(mx, my)) 
			{
				transferstations[i].Merge(l);
				columns[j].removeLast();
				i=4;
				l=null;
			} 		
		}	
	}
	public void Fly()
	{
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				if (columns[i].list.size()!=0) 
				{
					LinkedList<Card> xinList=new LinkedList<Card>();
					xinList.add(columns[i].list.getLast());
					Lin l = new Lin(xinList);
                                        if ( collections[j].canCollect(l)) 
					{
						collections[j].Merge(l);
						columns[i].removeLast();
						l=null;
					} 
				} 
			
			}
			
		}
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				if (transferstations[i].list.size()!=0) 
				{
					LinkedList<Card> xinList=new LinkedList<Card>();
					xinList.add(transferstations[i].list.getLast());
					Lin l = new Lin(xinList);
					if ( collections[j].canCollect(l)) 
					{
						collections[j].Merge(l);
						transferstations[i].removeLast();
						l=null;
					} 
				} 
				
			}
			
		}
	}
	public boolean isWon()
	{
		int a=0;
		for (int i = 0; i < 4; i++)
		{
			if (collections[i].is13()) 
			{
				a++;
			} 
			
		}
		if (a==4) 
		{
			return true;
		} 
		return false;
	}
	public int leftnumber()
	{
		int sum=0;
		for (int i = 0; i < 4; i++)
		{
			int a = collections[i].CollectedSize();
			sum=sum+a;
		}
		return 52-sum;
	}
}
