package gomoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Gomoku extends JFrame{
	private static final long serialVersionUID = 1L;
	Board p = null;
	JMenuBar menu = new JMenuBar();
	JMenu option = new JMenu("选项");
	JMenu setting = new JMenu("设置");
	JMenu help = new JMenu("帮助");
	JMenuItem replay = new JMenuItem("重新开始");
	JMenuItem rankinglist = new JMenuItem("排行榜");
	JMenuItem exit = new JMenuItem("退出游戏");
	JMenuItem changeboard = new JMenuItem("更换棋盘");
	JMenuItem changepiece = new JMenuItem("更换棋子");
	JMenuItem about = new JMenuItem("关于");
	public Gomoku()
	{
		   p =new Board();
		   this.setSize(585,600);
		   this.setLocation(200,100);
		   this.add(p);
		   this.setResizable(false);
		   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   option.add(replay);
		   option.add(rankinglist);
		   option.add(exit);
		   setting.add(changeboard);
		   setting.add(changepiece);
		   help.add(about);
		   menu.add(option);
		   menu.add(setting);
		   menu.add(help); 
		   this.setJMenuBar(menu);
		   this.addMouseListener(p);
		   exit.addActionListener(new ActionListener() {
//			匿名虚构类
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		   replay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<p.row;i++){
					for(int j=0;j<p.col;j++){
						p.num[i][j] = 0;
					}
				}
				p.available = true;
				p.step_count = 0;
				repaint();
			}
		});
		   changeboard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Random r = new Random();
				int n = r.nextInt(8);
				String board_name = "qipan"+n+".jpg";
				p.setBoard_name(board_name);
				p.updateURL();
				repaint();
			}
		});
		   changepiece.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Random r = new Random();
				int n = r.nextInt(8);
				String qizi1_name = "c"+n+".png";
				String qizi2_name = "c"+(n+1)+".png";
				p.setPlayer_1_name(qizi1_name);
				p.setPlayer_2_name(qizi2_name);
				p.updateURL();
				repaint();
			}
		});
		   about.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg ="关于\n" +
						"1、玩家先落子;\n" +
						"2、形成5颗同色连子即为赢;\n\n\n" +
						"制作人：詹佳伟\n" +
						"年级：2014级\n" +
						"班级：计算机基地班\n";
				JOptionPane.showMessageDialog(null, msg);
				
			}
		});
		   rankinglist.addActionListener(new ActionListener() {
		
			   public void actionPerformed(ActionEvent e) {
			String msg ="排行榜\n" +
					"局数      步数      结果\n";
			for(int i=0;i<p.rankinglist.size();i++)
			{
				Rankings ph = p.rankinglist.get(i);
			      msg = msg+"   "+ph.getRound()
			    		  +"          "+ph.getStep()
			    		  +"          "+ph.getResult()+"\n";
			}
				JOptionPane.showMessageDialog(null, msg);
			   }
		});
		   this.setVisible(true);
	}
	public static void main(String[] args){
		new Gomoku();
	}
}
