import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*此为面板类，为游戏创建面板*/
class zhupanel extends JPanel implements KeyListener{
	int max=5;				//定义地图个数
	int[][] map,mapt;		//定义地图
	int renX,renY,boxnum;		//小人的位置，箱子数
	Image[] myImage;			//定义图片
	Readmap Levelmap;			
	Readmap Levelmapt;	
	int len=30;
	public int level=1;
	zhupanel()
	{	
		setSize(600,600);			//面板尺寸
		addKeyListener(this);		//面板增加监听
		myImage=new Image[10];		
		for(int i=0; i<10; i++)
		{
		    myImage[i] = Toolkit.getDefaultToolkit().getImage("pic\\"+i+".gif");
		}
		//此把图片赋给 myImage[i]
		setVisible(true);		//使面板可见
	}

	void Tuixiangzi(int i)		//Tuixiangzi（）方法
	{
		Levelmap=new Readmap(i);	//读取第i个地图
		Levelmapt=new Readmap(i);		
		map=Levelmap.getmap();			//得到地图
		renX=Levelmap.getrenX();			
		renY=Levelmap.getrenY();		//得到小人的位置
		mapt=Levelmapt.getmap();
		repaint();
	}
	int maxlevel(){return max;}		//最大关卡

	public void paint(Graphics g)		//绘图
	{
		for(int i=0; i<20; i++)
			for(int j=0; j<20; j++)
		    {
			    g.drawImage(myImage[map[j][i]],i*len,j*len,this);
			}		
		g.setColor(new Color(0,0,0));
		g.setFont(new Font("楷体_2312",Font.BOLD,30));
		g.drawString("第",180,520);
		g.drawString(String.valueOf(level),250,520);
		g.drawString("关",300,520);
		//在面板底部显示出当前处于第几关
	}

	public void keyPressed(KeyEvent e)		//键盘按压处理
	{
		if(e.getKeyCode()==KeyEvent.VK_UP){moveup();}		//按压上键
		if(e.getKeyCode()==KeyEvent.VK_DOWN){movedown();}	//按压下键
		if(e.getKeyCode()==KeyEvent.VK_LEFT){moveleft();}	//按压左键
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){moveright();}	//按压右键
		if(iswin())
		{
			if(level==max){JOptionPane.showMessageDialog(this, "恭喜您通过最后一关！！！");}
			else
			{
				String msg="恭喜您通过第"+level+"关!!!\n是否要进入下一关？";
				int type=JOptionPane.YES_NO_OPTION;
				String title="过关";
				int choice=0;
				choice=JOptionPane.showConfirmDialog(null,msg,title,type);
				if(choice==1)		//选择否，仍为此关
					Tuixiangzi(level);
				else if(choice==0)		//选择是，跳到下一关
				{
					level++;
					Tuixiangzi(level);
				}
			}
		}
	}
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	/*
	 * 以下四个方法为小人移动的规则
	 * moveup()为小人向上移动的规则
	 * movedown()为小人向下移动的规则
	 * moveleft()为小人向左移动的规则
	 * moveright()为小人向右移动的规则
	 * */
	void moveup()			//上移方法
	{
		if(map[renY-1][renX]==2||map[renY-1][renX]==4)		
		{
			if(mapt[renY][renX]==4||mapt[renY][renX]==9) 
				map[renY][renX]=4;
			else map[renY][renX]=2;
			map[renY-1][renX]=8;
			repaint();
			renY--;
		}
		else if(map[renY-1][renX]==3)			

		{
			if(map[renY-2][renX]==4)
			{
				if(mapt[renY][renX]==4||mapt[renY][renX]==9)
					map[renY][renX]=4;
				else map[renY][renX]=2;
				map[renY-1][renX]=8;
				map[renY-2][renX]=9;
				repaint();renY--;
			}
			else if(map[renY-2][renX]==2)
			{
				if(mapt[renY][renX]==4||mapt[renY][renX]==9)
					map[renY][renX]=4;
				else map[renY][renX]=2;
				map[renY-1][renX]=8;
				map[renY-2][renX]=3;
				repaint();renY--;
			}
			else {map[renY][renX]=8;repaint();}
		}
		else if(map[renY-1][renX]==9)
		{
			if(map[renY-2][renX]==4)
			{
				if(mapt[renY][renX]==4||mapt[renY][renX]==9)
					map[renY][renX]=4;
				else map[renY][renX]=2;
				map[renY-1][renX]=8;
				map[renY-2][renX]=9;
				repaint();renY--;
			}
			else if(map[renY-2][renX]==2)
			{
				if(mapt[renY][renX]==4||mapt[renY][renX]==9)
					map[renY][renX]=4;
				else map[renY][renX]=2;
				map[renY-1][renX]=8;
				map[renY-2][renX]=3;
				repaint();renY--;
			}
			else {map[renY][renX]=8;repaint();}
		}
		if(map[renY-1][renX]==1)
		{
			map[renY][renX]=8;repaint();
		}
	}
	void movedown()			//下移
	{
		if(map[renY+1][renX]==2||map[renY+1][renX]==4)
		{
			if(mapt[renY][renX]==4||mapt[renY][renX]==9)
				map[renY][renX]=4;
			else map[renY][renX]=2;
			map[renY+1][renX]=5;
			repaint();renY++;
		}
		else if(map[renY+1][renX]==3)
		{
			if(map[renY+2][renX]==4)
			{
				if(mapt[renY][renX]==4||mapt[renY][renX]==9)
					map[renY][renX]=4;
				else map[renY][renX]=2;
				map[renY+1][renX]=5;
				map[renY+2][renX]=9;
				repaint();renY++;
			}
			else if(map[renY+2][renX]==2)
			{
				if(mapt[renY][renX]==4||mapt[renY][renX]==9)
					map[renY][renX]=4;
				else map[renY][renX]=2;
				map[renY+1][renX]=5;
				map[renY+2][renX]=3;
				repaint();renY++;
			}
			else {map[renY][renX]=5;repaint();}
		}
		else if(map[renY+1][renX]==9)
		{
			if(map[renY+2][renX]==4)
			{
				if(mapt[renY][renX]==4||mapt[renY][renX]==9)
					map[renY][renX]=4;
				else map[renY][renX]=2;
				map[renY+1][renX]=5;
				map[renY+2][renX]=9;
				repaint();renY++;
			}
			else if(map[renY+2][renX]==2)
			{
				if(mapt[renY][renX]==4||mapt[renY][renX]==9)
					map[renY][renX]=4;
				else map[renY][renX]=2;
				map[renY+1][renX]=5;
				map[renY+2][renX]=3;
				repaint();renY++;
			}
			else {map[renY][renX]=5;repaint();}
		}
		else if(map[renY+1][renX]==1)
		{
			map[renY][renX]=5;repaint();
		}
	}

	void moveleft()						//左移
	{
		if(map[renY][renX-1]==2||map[renY][renX-1]==4)
		{
			if(mapt[renY][renX]==4||mapt[renY][renX]==9)
				map[renY][renX]=4;
			else map[renY][renX]=2;
			map[renY][renX-1]=6;			
			repaint();renX--;
		}
		else if(map[renY][renX-1]==3)
		{
			if(map[renY][renX-2]==4)
			{
				if(mapt[renY][renX]==4||mapt[renY][renX]==9)
					map[renY][renX]=4;
				else map[renY][renX]=2;
				map[renY][renX-1]=6;
				map[renY][renX-2]=9;
				repaint();renX--;
			}
			else if(map[renY][renX-2]==2)
			{
				if(mapt[renY][renX]==4||mapt[renY][renX]==9)
					map[renY][renX]=4;
				else map[renY][renX]=2;
				map[renY][renX-1]=6;
				map[renY][renX-2]=3;
				repaint();renX--;
			}
			else {map[renY][renX]=6;repaint();}
		}
		else if(map[renY][renX-1]==9)
		{
			if(map[renY][renX-2]==4)
			{
				if(mapt[renY][renX]==4||mapt[renY][renX]==9)
					map[renY][renX]=4;
				else map[renY][renX]=2;
				map[renY][renX-1]=6;
				map[renY][renX-2]=9;
				repaint();renX--;
			}
			else if(map[renY][renX-2]==2)
			{
				if(mapt[renY][renX]==4||mapt[renY][renX]==9)
					map[renY][renX]=4;
				else map[renY][renX]=2;
				map[renY][renX-1]=6;
				map[renY][renX-2]=3;
				repaint();renX--;
			}
			else {map[renY][renX]=6;repaint();}
		}
		else if(map[renY][renX-1]==1)
		{
			map[renY][renX]=6;repaint();
		}
	}

	void moveright()								//右移
	{
		if(map[renY][renX+1]==2||map[renY][renX+1]==4)
		{			
			if(mapt[renY][renX]==4||mapt[renY][renX]==9)
				map[renY][renX]=4;
			else map[renY][renX]=2;
			map[renY][renX+1]=7;			
			repaint();renX++;
		}
		else if(map[renY][renX+1]==3)
		{
			if(map[renY][renX+2]==4)
			{
				if(mapt[renY][renX]==4)
					map[renY][renX]=4;
				else map[renY][renX]=2;
				map[renY][renX+1]=7;
				map[renY][renX+2]=9;
				repaint();renX++;
			}
			else if(map[renY][renX+2]==2)
			{
				if(mapt[renY][renX]==4)
					map[renY][renX]=4;
				else map[renY][renX]=2;
				map[renY][renX+1]=7;
				map[renY][renX+2]=3;
				repaint();renX++;
			}
			else {map[renY][renX]=7;repaint();}
		}
		else if(map[renY][renX+1]==9)
		{
			if(map[renY][renX+2]==4)
			{
				if(mapt[renY][renX]==4||mapt[renY][renX]==9)
					map[renY][renX]=4;
				else map[renY][renX]=2;
				map[renY][renX+1]=7;
				map[renY][renX+2]=9;
				repaint();renX++;
			}
			else if(map[renY][renX+2]==2)
			{
				if(mapt[renY][renX]==4||mapt[renY][renX]==9)
					map[renY][renX]=4;
				else map[renY][renX]=2;
				map[renY][renX+1]=7;
				map[renY][renX+2]=3;
				repaint();renX++;
			}
			else {map[renY][renX]=7;repaint();}
		}
		else if(map[renY][renX+1]==1)
		{
			map[renY][renX]=7;repaint();
		}
	}

	boolean iswin()  //iswin()函数，起判断作用
	{
		boolean num=false;
		out:for(int i=0; i<20; i++)
			for(int j=0; j<20; j++)
		{
			if(mapt[i][j]==4||mapt[i][j]==9)
				if(map[i][j]==9)num=true;
			    else {num=false;break out;}
		}
		return num;
	}
}

