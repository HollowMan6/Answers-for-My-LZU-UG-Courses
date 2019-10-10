package test;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Calendar;
import java.util.TimeZone;
import java.text.DateFormat;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class Game2048 extends Thread {
	JPanel panel=new JPanel(null);
	JFrame frame=new JFrame();
	JLabel[]label_num={new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
	JLabel[]label_line={new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
	JLabel[]button={new JLabel("重新开始"),new JLabel("游戏示范"),new JLabel("帮助"),new JLabel("显示时间")};
	JLabel[]show={new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
	Demo demo=new Demo();
	Sound sound;
	Sound sound1;
	int n=0;
	int score=0;
	int highscore=0;
	public Game2048() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		Container contain=frame.getContentPane();
		sound=new Sound("src/voice/run.wav");
		sound1=new Sound("src/voice/add.wav");
		frame.setTitle("2048game_by-wangxu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(480,630);
		contain.add(panel);
		point();
		backcolor();
		opaque();
		font();
		listener();
		for(int index=0;index<label_num.length;index++)
		{
		panel.add(label_num[index]);
		}
		for(int index=0;index<label_line.length;index++)
		{
		panel.add(label_line[index]);
		}
		for(int index=0;index<show.length;index++)
		{
		panel.add(show[index]);
		}
		for(int index=0;index<button.length;index++)
		{
		panel.add(button[index]);
		}
		random();
		label_num[0].setText(String.valueOf((int)Math.pow(2,1)));
		blockcolor();
		frame.setVisible(true);
	}
	class SystTimeUpdateTimer implements ActionListener {//设置一个时间类实现监听者接口
		public final int ONE_SECOND=1000;//1秒钟更新一次
	    private JLabel timeLabel=null;//在此label上显示时间由外部传入
	    private Calendar calendar=null;//获取当前时间的日历类
	    private DateFormat dateFormat=null;//时间格式类 用来格式化时间用
	    private Timer timeTimer=null;//计时器
	    private TimeZone currentTimeZone;//当前的时区,用它是因为返回的时间可能跟时区有关而跟你机器上的相差几小时我就遇到过
	    public SystTimeUpdateTimer(JLabel jLabel) {
	    this.timeLabel=jLabel;    
	    this.currentTimeZone=TimeZone.getDefault();
	    this.dateFormat=DateFormat.getDateTimeInstance(2,2,java.util.Locale.getDefault());
	    this.timeTimer=new Timer(this.ONE_SECOND,this);
	    this.timeTimer.setRepeats(true);
	    this.timeTimer.start();
	    }
	    public void stopTimer()
	    {
	    	this.timeTimer.stop();
	    	}
	    public void reStartTimer()
	    {
	    	this.timeTimer.restart();
	    	}
	    public void actionPerformed(ActionEvent arg0)
	    {
	    	this.calendar=Calendar.getInstance(this.currentTimeZone);
	    	this.timeLabel.setText("当前系统时间："+this.dateFormat.format(this.calendar.getTime())); 
	    	}
	    }
	class Demo extends Thread{//示范线程的类
		Demo(){}
		public void run()//示范的run方法
		{
			for(int j=0;j<10;j++)
			{
				int i=(int)(4*Math.random());
				if(i==0)
				{
				moveup();
				random();
				blockcolor();
				}
				if(i==1)
				{
				movedown();
				random();
				blockcolor();
				}
				if(i==2)
				{
				moveleft();
				random();
				blockcolor();
				}
				if(i==3)
				{
				moveright();
				random();
				blockcolor();
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void listener()//设计的监听器类
	{
		frame.addKeyListener(new KeyAdapter()//对键盘操作进行监听
		   {
				   public void keyPressed(KeyEvent e)//对键盘上下左右操作监控
				   { 
					   if(e.getKeyCode()==KeyEvent.VK_UP)
					   {
						   moveup();
							random();
							score();
							blockcolor();
							if(iswin()==true)
						    JOptionPane.showMessageDialog(null, "你赢了");
					   }
					   if(e.getKeyCode()==KeyEvent.VK_LEFT)
					   {
						   moveleft();
							random();
							score();
							blockcolor();
							if(iswin()==true)
							    JOptionPane.showMessageDialog(null, "你赢了");
					   }
					   if(e.getKeyCode()==KeyEvent.VK_RIGHT)
					   {
						   moveright();
							random();
							score();
							blockcolor();
							if(iswin()==true)
							    JOptionPane.showMessageDialog(null, "你赢了");
					   }
					   if(e.getKeyCode()==KeyEvent.VK_DOWN)
					   {
						   movedown();
							random();
							score();
							blockcolor();
							if(iswin()==true)
							    JOptionPane.showMessageDialog(null, "你赢了");
					   }
				   }
		   });
		button[0].addMouseListener(new MouseListener(){//对鼠标进行监控
			public void mouseClicked(MouseEvent e) {//鼠标点击
				clear();
			}
			public void mouseEntered(MouseEvent e){}//鼠标移入
			public void mouseExited(MouseEvent e){}//鼠标移出
			public void mousePressed(MouseEvent e){//鼠标按下
				button[0].setBackground(new java.awt.Color(85,194,249));
			}
			public void mouseReleased(MouseEvent e){//鼠标释放
				button[0].setBackground(new java.awt.Color(210, 180,140));
			}
		});
		button[1].addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e){
				demo.start();
			}
			public void mouseEntered(MouseEvent e){}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				button[1].setBackground(new java.awt.Color(85,194,249));
			}
			public void mouseReleased(MouseEvent e) {
				button[1].setBackground(new java.awt.Color(210, 180,140));
			}
		});
		button[2].addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
					    JFrame frame = new JFrame("游戏规则");
					    JTextArea area=new JTextArea("1.每次可以选择键盘的上下左右其中一个方向去滑动。"
					    		+ "\n2.每滑动一次，所有的数字方块都会往滑动的方向靠拢外。"
					    		+ "\n3.系统也会在空白的地方乱数出现一个数字方块。"
					    		+ "\n4.相同数字的方块在靠拢、相撞时会相加。"
					    		+ "\n5.不断的叠加最终拼凑出2048这个数字就算成功。"
					    		+ "\n6.当16个格子布满并且无法移动的时候游戏失败，请点击重新开始或者退出。"
					    		+ "\n7.得分计算为每次碰撞相加的数字累加的和。"
					    		+ "\n8.可以通过游戏示范先行了解游戏操作。切记不能连续点击示范否则会报错！"
					    		+ "\n\n\n邮箱:wx2700317@163.com\n"+ "qq:702149386"
			    		        + "\n制作人:兰州大学2014级计算机2班王旭",25,20);
					    area.setEditable(false);//使其无法修改
					    frame.add(area);
					    frame.setBounds(25, 25, 450, 500);
					    frame.setVisible(true);
				}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				button[2].setBackground(new java.awt.Color(85,194,249));
			}
			public void mouseReleased(MouseEvent e) {
				button[2].setBackground(new java.awt.Color(210, 180,140));
			}
		});
		button[3].addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e){
				JFrame frame1= new JFrame("当前时间");
			    JLabel j=new JLabel();
			    SystTimeUpdateTimer time=new SystTimeUpdateTimer(j);
			    frame1.add(j);
			    frame1.setBounds(400,500,260,80);
			    frame1.setVisible(true);
			}
			public void mouseEntered(MouseEvent e){}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				button[3].setBackground(new java.awt.Color(85,194,249));
			}
			public void mouseReleased(MouseEvent e) {
				button[3].setBackground(new java.awt.Color(210, 180,140));
			}
		});
	}
	public void clear()//重新开始游戏
	{
		for(int index=0;index<label_num.length;index++)
		label_num[index].setText(String.valueOf(""));//将16块全清楚数字
		n=0;//步数与分数置0，否则再次游戏将以上次的n值累加
		score=0;
		show[2].setText(String.valueOf(0));
		show[3].setText(String.valueOf(0));
		random();
		label_num[0].setText(String.valueOf((int)Math.pow(2,1)));
		blockcolor();
	}
	public void score()
	{
		if(score>highscore)
		{
			highscore=score;
			show[5].setText(String.valueOf(highscore));
		}
	}
	public void random()//随机出现数字
	{
		int dom;
		int ran=(int)(Math.random());
		if(ran==0)
	    dom=2;
		else
		dom=4;
		ran=(int)(Math.random()*15);
		if(label_num[ran].getText().equals(""))
		{
		label_num[ran].setText(String.valueOf(dom));
		}
		else
		{
			for(int index=0;index<label_num.length;index++)
			if(label_num[index].getText().equals(""))
				{
				label_num[index].setText(String.valueOf(dom));
				break;
				}
		}
		blockcolor();
	}
	public void run(){sound.loop();}
	public boolean iswin()//用于判断游戏是否成功2048
	{
		for(int index=0;index<label_num.length;index++)
		{
			if(label_num[index].getText().equals("2048"))
			{
				return true;
			}
		}
		return false;
	}
	public void blockcolor()//用于游戏中方块碰撞产生的颜色变化以及字体位置的设置
	{
		for(int index=0;index<label_num.length;index++)
			{
				if(label_num[index].getText().equals("2"))
				{
					label_num[index].setFont(new Font("2048",Font.CENTER_BASELINE,45));
					label_num[index].setHorizontalAlignment(SwingConstants.CENTER);
					label_num[index].setBackground(new java.awt.Color(173,216,230));
					label_num[index].setForeground(Color.BLACK);
				}
			
				if(label_num[index].getText().equals("4"))
				{
					label_num[index].setFont(new Font("2048",Font.CENTER_BASELINE,45));
					label_num[index].setHorizontalAlignment(SwingConstants.CENTER);	
					label_num[index].setBackground(new java.awt.Color(124,252,0));	
					label_num[index].setForeground(Color.BLACK);
				}
			
				if(label_num[index].getText().equals("8"))
				{
					label_num[index].setFont(new Font("2048",Font.CENTER_BASELINE,45));
					label_num[index].setHorizontalAlignment(SwingConstants.CENTER);
					label_num[index].setBackground(new java.awt.Color(46,139,87));
					label_num[index].setForeground(Color.white);
				}
			
				if(label_num[index].getText().equals("16"))
				{
					label_num[index].setFont(new Font("2048",Font.CENTER_BASELINE,45));
					label_num[index].setHorizontalAlignment(SwingConstants.CENTER);//font center
					label_num[index].setBackground(new java.awt.Color(106,90,205));
					label_num[index].setForeground(Color.white);
				}	
			
				if(label_num[index].getText().equals("32"))
				{
					label_num[index].setFont(new Font("2048",Font.CENTER_BASELINE,45));
					label_num[index].setHorizontalAlignment(SwingConstants.CENTER);//font center
					label_num[index].setBackground(new java.awt.Color(75,0,130));	
					label_num[index].setForeground(Color.white);
				}
			
				if(label_num[index].getText().equals("64"))
				{
					label_num[index].setFont(new Font("2048",Font.CENTER_BASELINE,45));
					label_num[index].setHorizontalAlignment(SwingConstants.CENTER);
					label_num[index].setBackground(new java.awt.Color(253,99,71));
					label_num[index].setForeground(Color.white);
				}
			
				if(label_num[index].getText().equals("128"))
				{
					label_num[index].setFont(new Font("2048",Font.CENTER_BASELINE,45));
					label_num[index].setHorizontalAlignment(SwingConstants.CENTER);
					label_num[index].setBackground(new java.awt.Color(241, 200,128));
					label_num[index].setForeground(Color.white);
				}
			
				if(label_num[index].getText().equals("256"))
				{
					label_num[index].setFont(new Font("2048",Font.CENTER_BASELINE,45));
					label_num[index].setHorizontalAlignment(SwingConstants.CENTER);
					label_num[index].setBackground(new java.awt.Color(241, 200,108));
					label_num[index].setForeground(Color.white);
				}
				if(label_num[index].getText().equals("512"))
				{
					label_num[index].setFont(new Font("2048",Font.CENTER_BASELINE,45));
					label_num[index].setHorizontalAlignment(SwingConstants.CENTER);
					label_num[index].setBackground(new java.awt.Color(238, 198,84));
					label_num[index].setForeground(Color.white);
				}
				if(label_num[index].getText().equals("1024"))
				{
					label_num[index].setFont(new Font("2048",Font.CENTER_BASELINE,30));
					label_num[index].setHorizontalAlignment(SwingConstants.CENTER);
					label_num[index].setBackground(new java.awt.Color(240, 208,64));
					label_num[index].setForeground(Color.white);
				}
				if(label_num[index].getText().equals("2048"))
				{
					label_num[index].setFont(new Font("2048",Font.CENTER_BASELINE,30));
					label_num[index].setHorizontalAlignment(SwingConstants.CENTER);
					label_num[index].setBackground(new java.awt.Color(241, 190,40));
					label_num[index].setForeground(Color.white);
				}
				if(label_num[index].getText().equals(""))
				{
					label_num[index].setFont(new Font("2048",Font.CENTER_BASELINE,30));
					label_num[index].setHorizontalAlignment(SwingConstants.CENTER);
					label_num[index].setBackground(new java.awt.Color(204, 192,178));
					label_num[index].setForeground(Color.white);
				}
				else
				{
					continue;
				}
		}
	}
	public void backcolor()//用于设置背景色
	{
		panel.setBackground(new java.awt.Color(240,255,255));
		for(int index=0;index<label_num.length;index++)
		{
		label_num[index].setBackground(new java.awt.Color(204,192,178));
		}
		for(int index=0;index<label_line.length;index++)
		{
		label_line[index].setBackground(new java.awt.Color(238,232,170));
		}
		for(int index=0;index<show.length;index++)
		{
		show[index].setBackground(new java.awt.Color(135, 206,235));
		}
		for(int index=0;index<button.length;index++)
		{
		button[index].setBackground(new java.awt.Color(210, 180,140));
		button[index].setForeground(Color.BLACK);
		}
	}
	public void point()//块行按钮显示标签的排列方法
	{
		int x=30;
		for(int index=0;index<4;index++)
		{
		label_num[index].setBounds(x, 100, 90, 90);
		label_num[index+4].setBounds(x, 200, 90, 90);
		label_num[index+8].setBounds(x, 300, 90, 90);
		label_num[index+12].setBounds(x, 400, 90, 90);
		x=x+100;
		}
		x=20;
		for(int index=0;index<5;index++)
		{
		label_line[index].setBounds(x, 90, 10, 410);
		x=x+100;
		}
		int y=90;
		for(int index=5;index<10;index++)
		{
		label_line[index].setBounds(20, y, 400, 10);
		y=y+100;
		}
		show[0].setBounds(20, 18, 120, 26);
		show[1].setBounds(150, 18, 120, 26);
		show[2].setBounds(281, 18, 120, 26);
		show[3].setBounds(20, 50, 120, 26);
		show[4].setBounds(150, 50, 120, 26);
		show[5].setBounds(281, 50, 120, 26);
		button[0].setBounds(50, 520, 70, 50);
		button[1].setBounds(145, 520, 70, 50);
		button[2].setBounds(240, 520, 70, 50);
		button[3].setBounds(335, 520, 70, 50);
	}
	public void opaque()//设置组件透明的方法为了突出数值的操作
	{
		for(int index=0;index<label_num.length;index++)
		{
		label_num[index].setOpaque(true);
		}
		for(int index=0;index<label_line.length;index++)
		{
		label_line[index].setOpaque(true);
		}
		for(int index=0;index<show.length;index++)
		{
	     show[index].setOpaque(true);
		}
		for(int index=0;index<button.length;index++)
		{
	     button[index].setOpaque(true);
		}
	}
	public void font()
	{
		for(int index=0;index<6;index++)
		{
		show[index].setForeground(Color.WHITE);
		show[index].setFont(new Font("楷体",Font.BOLD,15));
		}
		for(int index=0;index<show.length;index++)
		{
		show[index].setHorizontalAlignment(SwingConstants.CENTER);
		}
		for(int index=0;index<label_num.length;index++)
		{
		label_num[index].setHorizontalAlignment(SwingConstants.CENTER);
		}
		for(int index=0;index<button.length;index++)
		{
		 button[index].setFont(new Font("楷体",Font.BOLD,15));
	     button[index].setHorizontalAlignment(SwingConstants.CENTER);
		}
		show[0].setText("行动次数");
		show[1].setText("当前分数");
		show[4].setText("最高分数");
		show[2].setText(String.valueOf(score));
		show[3].setText(String.valueOf(n));
		show[5].setText(String.valueOf(highscore));
	}
	public void moveup()//设置上方法
	{
		show[3].setText(String.valueOf(n=n+1));
		sound1.play();
		for(int loop=0;loop<10;loop++)
		{
		for(int index=4;index<13;)
		{
			for(int i=index;i<index+4;i++)
			{
				if(label_num[i-4].getText().equals(""))
				{
					label_num[i-4].setText(label_num[i].getText());
					label_num[i].setText("");
				}
				if(label_num[i-4].getText().equals(label_num[i].getText())&&label_num[i-4].getText()!="")//值的更新
				{
					label_num[i-4].setText(String.valueOf(Integer.parseInt(label_num[i].getText())+Integer.parseInt(label_num[i-4].getText())));
					label_num[i].setText("");
					try{
					show[2].setText(String.valueOf(score=score+(Integer.parseInt(label_num[i-4].getText()))));}catch(Exception e){}//对分数的计算
				}
				else
					continue;
			}
			index=index+4;
		}
		}
		blockcolor();
	}
	public void movedown()//下方法
	{
		show[3].setText(String.valueOf(n=n+1));
		sound1.play();
		for(int loop=0;loop<10;loop++)
		{
		for(int index=8;index>-1;)
		{
			for(int i=index;i<index+4;i++)
			{
				if(label_num[i+4].getText().equals(""))
				{
					label_num[i+4].setText(label_num[i].getText());
					label_num[i].setText("");
				}
				if(label_num[i+4].getText().equals(label_num[i].getText())&&label_num[i+4].getText()!="")
				{
					label_num[i+4].setText(String.valueOf(Integer.parseInt(label_num[i].getText())+Integer.parseInt(label_num[i+4].getText())));
					label_num[i].setText("");
					try{
						show[2].setText(String.valueOf(score=score+(Integer.parseInt(label_num[i+4].getText()))));}catch(Exception e){}
				}
				else
					continue;
			}
			index=index-4;
		}
		}
		blockcolor();
	}
	public void moveleft()//左方法
	{
		show[3].setText(String.valueOf(n=n+1));
		sound1.play();
		for(int loop=0;loop<10;loop++)
		{
			
			for(int index=1;index<index+1&&index<16;)
			{
				
				for(int i=index;i<index+3;i++)
				{
					if(label_num[i-1].getText().equals(""))
					{
						label_num[i-1].setText(label_num[i].getText());
						label_num[i].setText("");
					}
					if(label_num[i-1].getText().equals(label_num[i].getText())&&label_num[i-1].getText()!="")
					{
						label_num[i-1].setText(String.valueOf(Integer.parseInt(label_num[i-1].getText())+Integer.parseInt(label_num[i].getText())));
						label_num[i].setText("");
						try{
							show[2].setText(String.valueOf(score=score+(Integer.parseInt(label_num[i-1].getText()))));}catch(Exception e){}
					}
					else
					{
						continue;
						}
				}
				index=index+4;
			}
		}
		blockcolor();
	}
	public void moveright()//右方法
	{
		show[3].setText(String.valueOf(n=n+1));
		sound1.play();
		for(int loop=0;loop<10;loop++)
		{
			for(int index=2;index<index+1&&index<16;)
			{
				for(int i=index;i>index-3;i--)
				{
					if(label_num[i+1].getText().equals(""))
					{
						label_num[i+1].setText(label_num[i].getText());
						label_num[i].setText("");
						}
					if(label_num[i+1].getText().equals(label_num[i].getText())&&label_num[i+1].getText()!="")
					{
						label_num[i+1].setText(String.valueOf(Integer.parseInt(label_num[i].getText())+Integer.parseInt(label_num[i+1].getText())));
						label_num[i].setText("");
						try{
							show[2].setText(String.valueOf(score=score+(Integer.parseInt(label_num[i+1].getText()))));}catch(Exception e){}
					}
					else
					{	
						continue;
						}
				}
				index=index+4;		
			}
		}
		blockcolor();
	}
	public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		Thread thread=new Thread(new Game2048());
		thread.start();
	}
}

