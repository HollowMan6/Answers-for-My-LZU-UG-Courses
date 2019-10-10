package gomoku;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	int i=0,j=0;
	int row = 15;
//	数组下标
	int col = 15;
//	数组上标
	String board_name = "qipan1.jpg";
	String player_1_name = "c1.png";
	String player_2_name = "c2.png";
	java.net.URL board_URL = Board.class.getResource("/img/"+board_name);
	java.net.URL player_1_URL = Board.class.getResource("/img/"+player_1_name);
	java.net.URL player_2_URL = Board.class.getResource("/img/"+player_2_name);
	int num[][] = new int[row][col];
//	0：标示该位置为空，1：标示红棋子，2：标示黑棋子
	boolean available = true;
//	定义boolean值，用来判断该位置是否有子
	int step_count = 0;
//	定义记录落子数
	List<Rankings> rankinglist = new ArrayList<Rankings>();
//	定义集合，用来存储排行榜
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public String getPlayer_1_name() {
		return player_1_name;
	}
	public void setPlayer_1_name(String player_1_name) {
		this.player_1_name = player_1_name;
	}

	public String getPlayer_2_name() {
		return player_2_name;
	}
	public void setPlayer_2_name(String player_2_name) {
		this.player_2_name = player_2_name;
	}
	public void updateURL(){
		board_URL = Board.class.getResource("/img/"+board_name);
		player_1_URL = Board.class.getResource("/img/"+player_1_name);
		player_2_URL = Board.class.getResource("/img/"+player_2_name);
	}
	public void paint(Graphics g){
		super.paint(g);
		Image img= new ImageIcon(board_URL).getImage();
//		调入棋盘图片
		g.drawImage(img, 0, 0, 567, 567, this);
//		绘制棋盘
		Image c1= new ImageIcon(player_1_URL).getImage();
		Image c2= new ImageIcon(player_2_URL).getImage();
		for(int i = 0;i<num.length;i++){
			for(int j = 0;j<num[i].length;j++){
				if(num[i][j] == 1)
				{
					g.drawImage(c1, i*35+20, j*35+20, 35, 35, this);
				}
				else if(num[i][j] == 2)
				{
					g.drawImage(c2, i*35+20, j*35+20, 35, 35, this);
				}
			}
//			重绘棋子
		}
//		重载整个界面（防止最小化后原内容丢失）
	}
	public int[] getLoc(int x,int y) {
		int count = 1;
//		定义计数器，用于计算棋子数
		int[] wz1 = null;
		int[] wz2 = null;
//		定义数组，用来存储危险位置
		for(int i =x-1;i>=0;i--){
			if(num[i][y] == num[x][y])
			{
				count++;
			}
			else
			{
				if(num[i][y] == 0){
					wz1 = new int[]{i,y};
//					获取左边的危险位置坐标
				}
				break;
			}
		}
//		左
		for(int i =x+1;i<row;i++){
			if(num[i][y] == num[x][y])
			{
				count++;
			}else{
				if(num[i][y] == 0){
					wz2 = new int[]{i,y};
//					获取有变位置危险坐标
				}
				break;
			}
		}
//		右
		if(count>=3)
		{
			if(wz1 != null){
				return wz1;
//				判断返回左边危险位置
			}else if(wz2 != null){
				return wz2;
//				判断返回右边危险位置
			}else{
				return null;
			}
		}
//		左右
		count = 1;
		wz1 = null;
		wz2 = null;
//		初始化所有参数
		for(int j =y-1;j>=0;j--){
			if(num[x][j] == num[x][y])
			{
				count++;
			}
			else
			{
				if(num[x][j] == 0){
					wz1 = new int[]{x,j};
				}
				break;
			}
		}
//		上
		for(int j =y+1;j<col;j++){
			if(num[x][j] == num[x][y])
			{
				count++;
			}else{
				if(num[x][j] == 0){
					wz2 = new int[]{x,j};
				}
				break;
			}
		}
//		下
		if(count>=3)
		{
			if(wz1 != null){
				return wz1;
			}else if(wz2 != null){
				return wz2;
			}else{
				return null;
			}
		}
//		上下
		count = 1;
		wz1 = null;
		wz2 = null;
		for(int i =x-1,j =y-1;i>=0&&j>=0;i--,j--){
			if(num[i][j] == num[x][y])
			{
				count++;
			}
			else
			{
				if(num[i][j] == 0){
					wz1 = new int[]{i,j};
				}
				break;
			}
		}
//		左上
		for(int i =x+1,j =y+1;i<row&&j<col;i++,j++){
			if(num[i][j] == num[x][y])
			{
				count++;
			}else{
				if(num[i][j] == 0){
					wz2 = new int[]{i,j};
				}
				break;
			}
		}
//		右下
		if(count>=3)
		{
			if(wz1 != null){
				return wz1;
			}else if(wz2 != null){
				return wz2;
			}else{
				return null;
			}
		}
//		左上右下
		count = 1;
		wz1 = null;
		wz2 = null;
		for(int i =x-1,j =y+1;i>=0&&j<col;i--,j++){
			if(num[i][j] == num[x][y])
			{
				count++;
			}
			else
			{
				if(num[i][j] == 0){
					wz1 = new int[]{i,j};
				}
				break;
			}
		}
//		左下
		for(int i =x+1,j =y-1;i<row&&j>=0;i++,j--){
			if(num[i][j] == num[x][y])
			{
				count++;
			}else{
				if(num[i][j] == 0){
					wz2 = new int[]{i,j};
				}
				break;
			}
		}
//		右上
		if(count>=3)
		{
			if(wz1 != null){
				return wz1;
			}else if(wz2 != null){
				return wz2;
			}else{
				return null;
			}
		}
//		左下右上
		return null;
	}
	public boolean iswin(int x,int y){
		int count = 1;
		for(int i =x-1;i>=0;i--){
			if(num[i][y] == num[x][y])
			{
				count++;
			}
			else
			{
				break;
			}
		}
//		左
		for(int i =x+1;i<row;i++){
			if(num[i][y] == num[x][y])
			{
				count++;
			}else{
				break;
			}
		}
//		右
		if(count>=5)
		{
			return true;
		}
//		左右
		count = 1;
		for(int j =y-1;j>=0;j--){
			if(num[x][j] == num[x][y])
			{
				count++;
			}
			else
			{
				break;
			}
		}
//		上
		for(int j =y+1;j<col;j++){
			if(num[x][j] == num[x][y])
			{
				count++;
			}else{
				break;
			}
		}
//		下
		if(count>=5)
		{
			return true;
		}
//		上下
		count = 1;
		for(int i =x-1,j =y-1;i>=0&&j>=0;i--,j--){
			if(num[i][j] == num[x][y])
			{
				count++;
			}
			else
			{
				break;
			}
		}
//		左上
		for(int i =x+1,j =y+1;i<row&&j<col;i++,j++){
			if(num[i][j] == num[x][y])
			{
				count++;
			}else{
				break;
			}
		}
//		右下
		if(count>=5)
		{
			return true;
		}
//		左上右下
		count = 1;
		for(int i =x-1,j =y+1;i>=0&&j<col;i--,j++){
			if(num[i][j] == num[x][y])
			{
				count++;
			}
			else
			{
				break;
			}
		}
//		左下
		for(int i =x+1,j =y-1;i<row&&j>=0;i++,j--){
			if(num[i][j] == num[x][y])
			{
				count++;
			}else{
				break;
			}
		}
//		右上
		if(count>=5)
		{			
			return true;
		}
//		左下右上
		return false;
	}
//	判断输赢
	@Override
	public void mouseClicked(MouseEvent e) {
		if(available){
			Graphics g = this.getGraphics();
			int x = e.getX();
			int y = e.getY();
//			获取鼠标点击的位置
			Image c1= new ImageIcon(player_1_URL).getImage();
			int i = (x-25)/35;
			int j = (y-75)/35;
			if(num[i][j] != 0){
	             JOptionPane.showMessageDialog(null, "该位置有旗子，请重新落子");
	             return;
//	             判断有子，终止本次事件，进行下次事件触发
			}else{
				g.drawImage(c1, i*35+20, j*35+20, 35, 35, this);
//				画出玩家落子
				num[i][j] = 1;
//				标示量标示
				step_count++;
//				记录玩家落子步数
			}
			boolean b=iswin(i,j);
			if(b){
				JOptionPane.showMessageDialog(null, "你赢了！");
				available = false;
				Rankings ph = new Rankings();
				ph.setRound(rankinglist.size()+1);
				ph.setStep(step_count);
				ph.setResult("win");
				rankinglist.add(ph);
				return;
			}
//			调用boolean判断方法
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
//			时间间隔：玩家落子后的等待
			Random r = new Random();
			Image c2 = new ImageIcon(player_2_URL).getImage();
//			调入黑棋子图片
			do{
				int[] wz =getLoc(i, j);
				if(wz == null){
			i = r.nextInt(15);
			j = r.nextInt(15);
				}else{
					i=wz[0];
					j=wz[1];
				}
//			设置随机的坐标
			}while(num[i][j] != 0);
			g.drawImage(c2, i*35+20, j*35+20, 35, 35, this);
			num[i][j] = 2;
			boolean d=iswin(i,j);
			if(d){
				JOptionPane.showMessageDialog(null, "你输了！");
				available = false;
				Rankings ph = new Rankings();
				ph.setRound(rankinglist.size()+1);
				ph.setStep(step_count);
				ph.setResult("fail");
				rankinglist.add(ph);
				return;
			}
//			随机电脑落子位置;	
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
		
}
