package mygame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Test
{
	FreeCell myfreecell=new FreeCell();
	MyWindow mywindow=null;
	Calculagraph calculagraph=null;
    Test()
	{
		myfreecell.initial();
		mywindow = new MyWindow();
		mywindow.setSize(5 * 2 + 900, 600 + 2 * 5 + 25);
		mywindow.setTitle("空当接龙");
		mywindow.setVisible(true);
		calculagraph = new Calculagraph(1000);
	}
    class MyWindow extends JFrame
	{
		Menu mymenu	= null;
		MyPanel	mypanel	= null;
		MyWindowListener exit= null;
	

		MyWindow()
		{
			mymenu= new Menu();
			this.setJMenuBar(mymenu);

			mypanel = new MyPanel();
			this.add(mypanel);

			exit = new MyWindowListener();
			this.addWindowListener(exit);

			this.repaint();
		}

		class Menu extends JMenuBar
		{
			JMenu		menu1,menu2;
			JMenuItem	option1,option2; 

			MenuListener menulistener;

			Menu()
			{
				menu1 = new JMenu("菜单");
                                menu2 = new JMenu("设计者");				
				option1 = new JMenuItem("新游戏"); 
				option2 = new JMenuItem("杨琳");

				this.add(menu1);
				this.add(menu2);
				menu1.add(option1);
				menu2.add(option2);

				menulistener = new MenuListener();
				option1.addActionListener(menulistener);
			}

			class MenuListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					
					if (e.getSource() == option1)
					{
						myfreecell.initial();
					}
				}
			}
		}

		class MyWindowListener extends WindowAdapter
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		}

		

		class MyPanel extends JPanel
		{
			MyMouseListener	mymouselistener	= null;

			MyPanel()
			{
				mymouselistener = new MyMouseListener();
				this.addMouseListener(mymouselistener);
				this.addMouseMotionListener(mymouselistener);
			}

			class MyMouseListener extends MouseAdapter implements MouseMotionListener
			{
				//public void mousePressed(MouseEvent e){}
				//public void mouseDragged(MouseEvent e){}
                                //public void mouseMoved(MouseEvent e)
				//{
				//	int mx = e.getX();
				//	int my = e.getY();
				//}
				//public void mouseReleased(MouseEvent e){}
                                public void mouseClicked(MouseEvent e)
				{

					int mx = e.getX();
					int my = e.getY();
					if ( e.getButton() ==MouseEvent.BUTTON3) 
					{			
						repaint();
					} 
					if (e.getButton() ==MouseEvent.BUTTON1)
					{
						if (e.getClickCount()==2) 
						{
							myfreecell.doubleClick(mx, my);
							for ( int i=0;i<10;i++)
							{
								myfreecell.Fly();
								if (myfreecell.isWon()) 
								{
									System.out.println("恭喜您赢了");
								} 
							}
						} 
						if(e.getClickCount()==1) 
						{
							if (myfreecell.linMe()) 
							{
								myfreecell.Put(mx, my);
								for ( int i=0;i<10;i++)
								{
									myfreecell.Fly();
								}
								if (myfreecell.isWon()) 
								{
									System.out.println("恭喜您赢了");
								} 
							} 
						
							else
							{
								myfreecell.HoldCard(mx, my);
							}
						}
					}
					repaint();
				
				}
				
			}

			public void paint(Graphics g)
			{
				if (myfreecell.isWon()) 
				{
					myfreecell.DisplayWon(g);
				} 
				else
				{
					myfreecell.Display(g);
				}

			}
		}
	}

	class Calculagraph implements Runnable
	{
		Thread	mythread	= null;
		long	time;

		Calculagraph(long time)
		{
			this.time = time;
			mythread = new Thread(this);
			mythread.start();
		}

		public void run()
		{
			while (true)
			{
				try
				{
					mythread.sleep(time);

					if (this == calculagraph)
					{
						mywindow.repaint();
					}
				
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args)
	{
		new Test();
	}
}

