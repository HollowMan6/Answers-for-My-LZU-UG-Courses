import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

class mytuixiangzi extends JFrame{
	JMenuBar menuBar;					//声明一个菜单栏对象，用来添加主菜单
	JMenu menu,help,music;					//声明一个菜单对象
	JMenuItem exit,help1,guanyu,choice,next,last,renew;		//声明菜单项对象，以便往主菜单上添加菜单项
    InputStream in;							
    AudioStream as; 
	boolean hs=true;   //控制音乐播放，暂停
	zhupanel panel;
	public mytuixiangzi(){					//
		super("推箱子");						//标题
		setSize(600,600);
		setVisible(true);					//使框架可见
		setResizable(false);				//不可更改框架大小
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuBar=new JMenuBar();				
		setJMenuBar(menuBar);
		menu=new JMenu("菜单");
		help=new JMenu("帮助");
		menuBar.add(menu);
		menuBar.add(help);
		choice=new JMenuItem("选择关卡");
		renew=new JMenuItem("重新开始");
		last=new JMenuItem("上一关");
		next=new JMenuItem("下一关");
		music=new JMenu("音乐");
		exit=new JMenuItem("退出");
		help1=new JMenuItem("帮助");
		guanyu=new JMenuItem("关于");
		MenuItemListener bnListener=new MenuItemListener();		//定义菜单栏监听
		choice.addActionListener(bnListener);
		renew.addActionListener(bnListener);
		last.addActionListener(bnListener);
		next.addActionListener(bnListener);
		exit.addActionListener(bnListener);
		help1.addActionListener(bnListener);
		guanyu.addActionListener(bnListener);			//为各个按键添加监听
		 menu.add(choice); menu.add(renew); menu.add(last); menu.add(next); menu.add(music);menu.add(exit);
		help.add(help1);help.add(guanyu);
		JMenuItem kai=new JMenuItem("开");
		music.add(kai);
		JMenuItem guan=new JMenuItem("关");
		music.add(guan);
		kai.addActionListener(bnListener);
		guan.addActionListener(bnListener);
		panel=new zhupanel();				//新建一个panel面板
		add(panel);							//框架里添加面板
		panel.Tuixiangzi(panel.level);		//显示第几关的面板
		panel.requestFocus();			//焦点侦听器，接收发自此组件的焦点事件
		validate();						//确保组件具有有效的布局
		 try
		 {
		     in=new FileInputStream("music\\ailisi.wav");	//读取音频文件
		     as =new AudioStream(in);
		 }
		 catch(Exception e1)				//异常处理
		 {
		  e1.printStackTrace();
		 }
		 if(hs==true){
			AudioPlayer.player.start(as);		//音乐开始播放
			}	
	  	//这几行，定义音乐文件
	}
	class MenuItemListener implements ActionListener{		
		public void actionPerformed(ActionEvent e){						//点击表现
			JMenuItem source=(JMenuItem)(e.getSource());
			if(source.getText()=="选择关卡"){				//选择关卡
				String le=JOptionPane.showInputDialog(null,"请输入您要转到的关卡号：(1~5)",null);
				panel.level=Integer.parseInt(le);					//将字符串le转换为int型			
				if(panel.level>panel.maxlevel()||panel.level<1)			
				{JOptionPane.showMessageDialog(null, "没有这一关！！！");//输入的数字大于最大数或小于1
				panel.requestFocus();}
				else
					{
					panel.Tuixiangzi(panel.level);		//调到选择的关卡
					panel.requestFocus();
					}
			}
			else if(source.getText()=="重新开始"){		//重新开始
				panel.Tuixiangzi(panel.level);			//重新加载此关卡
				panel.requestFocus();
			}
			else if(source.getText()=="上一关"){				//上一关
				panel.level--;								//关卡减一
				if(panel.level<1)
				{panel.level++;JOptionPane.showMessageDialog(null,"本关是第一关");panel.requestFocus();}
				else 
				{
					panel.Tuixiangzi(panel.level);		//显示第几关的面板
					panel.requestFocus();
				}
			}
			else if(source.getText()=="下一关"){				//下一关
				panel.level++;								//关卡加一
				if(panel.level>panel.maxlevel())
				{panel.level--;
				JOptionPane.showMessageDialog(null,"本关已是最后一关");panel.requestFocus();}
				else 
				{
					panel.Tuixiangzi(panel.level);			//显示第几关的面板
					panel.requestFocus();
				}
			}
			else if(source.getText()=="开"){					//音乐开
					if(hs==false){
						AudioPlayer.player.start(as);	//音乐开始播放
						hs=true;
					}
			}
			else if(source.getText()=="关"){				//音乐关
					if(hs==true){
						AudioPlayer.player.stop(as);	//音乐停止播放
						hs=false;
					}
					
			}
			else if(source.getText()=="帮助"){			//帮助
				JOptionPane.showMessageDialog(null, "推箱子，通过控制小人上下左右移动，"+"\n"+"将箱子移动到指定的位置。");
			}
			else if(source.getText()=="关于"){			//关于
				JOptionPane.showMessageDialog(null, "推箱子，由兰州大学2011级信息科学与工程学院计算机科学与工程专业1班王学万编写。");
			}
			else if(source.getText()=="退出"){			//退出
				System.exit(0);
			}
		}
	}
}