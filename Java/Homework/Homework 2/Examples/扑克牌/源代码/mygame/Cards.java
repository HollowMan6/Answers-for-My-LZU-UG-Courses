package mygame;
import java.util.Collections;
import java.util.LinkedList;
public class Cards{
    public LinkedList<Card> list;
	Cards(){
		 list=new LinkedList<Card>();
		 for (int face=1;face<=13;face++)
			{
				for (int suit=1;suit<=4;suit++)
				{
					Card mycard=new Card(face,suit);
					list.add(mycard);
				}
			}						  
		   Collections.shuffle(list);
	}
	public Card deal()
	{
		 return list.removeFirst();
	}
}