package mygame;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
public class Card{
	int x,y;
	int face,suit;
	
	Card(int f,int s){
		this.face=f;
		this.suit=s;
	}
	public void DisplayCard(Graphics g) 
	{
		Image CardImage=new ImageIcon("扑克图/1/"+face+"-"+suit+".gif").getImage();	
		g.drawImage(CardImage,x,y,null);	
	}
}