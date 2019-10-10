import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.Timer;   


public class MyPintu extends JFrame {
    
    public static Pintu a = new Pintu();      //由于Pintu类是继承的面板类 因此则可将其直接添加进
    public MyPintu(Pintu b){
    addKeyListener(b);
    add(b);
    }

    public static void test(){
        MyPintu frame = new MyPintu(a);     
        JMenuBar menu = new JMenuBar();        //JMenuBar类为菜单栏类  可添加JMenu菜单对象
        frame.setJMenuBar(menu);               //在框架中添加菜单栏menu
        JMenu menu1 = new JMenu("游戏");       //使用JMenu类菜单
        JMenu newgame = new JMenu("新游戏"); 
        menu1.add(newgame);
        JMenuItem exit = menu1.add("退出");
        JMenu menu2 = new JMenu("帮助");
        JMenuItem about = menu2.add("关于");
        JMenuItem b=new JMenuItem("小鹿");
        JMenuItem c=new JMenuItem("小猪");
        newgame.add(b);
        newgame.add(c);
        menu.add(menu1);
        menu.add(menu2);
        //监听各个菜单项的动作并调用相应函数
        exit.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
        JOptionPane.showMessageDialog(null, "Goodbye!"); 
        System.exit(0);
        }});
        b.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
        a.setPhoto(1);a.newb();a.repaint();
        }});
        c.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
        a.setPhoto(2);a.newb();a.repaint();
        }});
        about.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
        a.read();
        }});
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //关闭窗口
	frame.setSize(500,500);         //设置边框的大小
        frame.setTitle("拼图");         //设置边框名字
        frame.setVisible(true);         //设置可见
        frame.setResizable(false);
      }

    public static void main(String[] args){test();}
}

// 创建一个拼图类
class Pintu extends JPanel implements KeyListener {  //使用1到8代表8个块 0代表没有 10代表墙

    
    
    private int f = 0;               

    private int i = 0;
    private int photo=0;               //记录选择哪个图片

    int j = 0;
    int flag = 0;
    int map[][]= new int[5][5];
    int kk[]=new int[2];               //记录当前白色块的位置
    int a[]=new int[8];                //在移动时用来暂时存储某一行一列的去0数据
    
    public void newb(){                //打乱方块序列
    int n,k;
    n=(int)(Math.random()*6+5);        //随机5到10次
    for(k=0;k<n;k++){
    for(i=0;i<4;i++)
       for(j=0;j<(int)(Math.random()*2+1);j++)  //上下左右每种操作随机移动1~2次
             {
              switch(i){
              case 0:right();break;
              case 1:down();break;
	      case 2:left();break;
 	      case 3:up();break;
              }
             }
     }
     for(i=0;i<2;i++){left();up();}     //保证将0放到最右下角
     kk[0]=3;kk[1]=3;
     }
     
   

    // 初始化地图
    public void newmap(){       
        for (i = 0; i < 5; i++) {
            for(j = 0; j < 5; j++){
                if(i==3&&j==3)map[i][j]=0;
                else map[i][j]=(i-1)*3+j;
            }
        }

        //墙
        for (i = 0; i < 5; i++) {
            map[i][0] = 10;
            map[i][4] = 10;
        }
        for (j = 0; j < 5; j++) {
            map[4][j] = 10;
            map[0][j] = 10;
        }
        kk[0]=3;kk[1]=3;
    }

    // 初始化构造方法
    Pintu() { 
        newmap();      //画新地图
        newb();        //打乱块
        Timer timer = new Timer(100, new TimerListener()); //创建一个Timer类 即创建一个新的计时器便于快速及时刷新
        timer.start();
    }
    public void setPhoto(int a){photo=a;}
   

   //用于菜单监听"关于"按钮时用到  调用cmd命令打开拼图游戏规则.txt文件
    public void read(){
    try{String command="notepad 拼图游戏规则";
    Runtime.getRuntime().exec(command);}
    catch(IOException e){}
    }
  

    // 上移的方法
    public void up(){
       if(map[kk[0]+1][kk[1]]<9)                //代表下面有块  否则代表下面为墙 则可执行上移操作
       { 
        map[kk[0]][kk[1]]=map[kk[0]+1][kk[1]];  //将方块上移
        map[kk[0]+1][kk[1]]=0;                  //将块原来位置置为0
        kk[0]++;
       }
       else  {} //下面没有块则什么都不做
        repaint();
        if (gameover() == 1) { 
            
            JOptionPane.showMessageDialog(null, "You Win!");  //调用JOptionPane类的方法来显示游戏结束窗口
        }
    }

    // 下移的方法
    public void down() {
       if(map[kk[0]-1][kk[1]]<9)                //代表上面有块  否则代表上面为墙 则可执行下移操作
       { 
        map[kk[0]][kk[1]]=map[kk[0]-1][kk[1]];  //将方块下移
        map[kk[0]-1][kk[1]]=0;                  //将块原来位置置为0
        kk[0]--;
       }
       else  {} //下面没有块则什么都不做
        repaint();
       if (gameover() == 1) { 
            
            JOptionPane.showMessageDialog(null, "You Win!");  //调用JOptionPane类的方法来显示游戏结束窗口
        }
    }

    // 右移的方法
    public void right() {
        if(map[kk[0]][kk[1]-1]<9)               //代表左面有块  否则代表左面为墙 则可执行右移操作
       { 
        map[kk[0]][kk[1]]=map[kk[0]][kk[1]-1];  //将方块右移
        map[kk[0]][kk[1]-1]=0;                  //将块原来位置置为0
        kk[1]--;
       }
       else  {} //下面没有块则什么都不做
        repaint();
        if (gameover() == 1) { 
             
            JOptionPane.showMessageDialog(null, "You Win!");  //调用JOptionPane类的方法来显示游戏结束窗口
        }
    }
    // 左移的方法
    public void left(){
        if(map[kk[0]][kk[1]+1]<9)               //代表右面有块  否则代表右面为墙 则可执行左移操作
       { 
        map[kk[0]][kk[1]]=map[kk[0]][kk[1]+1];  //将方块左移
        map[kk[0]][kk[1]+1]=0;                  //将块原来位置置为0
        kk[1]++;
       }
       else  {} //下面没有块则什么都不做
        repaint();
         if (gameover() == 1) { 
              
            JOptionPane.showMessageDialog(null, "You Win!");  //调用JOptionPane类的方法来显示游戏结束窗口
        }
    }

    // 判断游戏结束的方法
    public int gameover() { 
    int n=0;
    if(map[3][3]!=0){return 0;}
   else{
    for(i=1;i<4;i++){
    for(j=1;j<4;j++)
     { 
       if(i!=3||j!=3){if(map[i][j]!=((i-1)*3+j))n=1;}
      } }                      //判断是否都在位置上除了最后一个空格
    }
    if(n==0) return 1;
    return 0;
    }                         //返回值1代表游戏完成结束0代表没有结束
       


    // 画方块的的方法
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //画不同数字用不同的颜色
        String a="";
        switch(photo){    //由photo值来决定从哪个文件夹读取图片
        case 0:break;
        case 1:a="21";break;
        case 2:a="22";break;
        } 
        g.setColor(Color.white);
        Toolkit mytool=Toolkit.getDefaultToolkit();

        Image im=mytool.getImage(a+"\\"+a+"_00.gif");
        g.drawImage(im,350,0,150,150,null);
        for (i=1;i<4;i++)  {
		for(j=1;j<4;j++){
                switch(map[i][j]){                       //不同数字的块设置为不同的颜色
		case 0:g.setColor(Color.white);break;
                case 1:im=mytool.getImage(a+"\\"+a+"_01.gif");break;
		case 2:im=mytool.getImage(a+"\\"+a+"_02.gif");break;
		case 3:im=mytool.getImage(a+"\\"+a+"_03.gif");break;
		case 4:im=mytool.getImage(a+"\\"+a+"_04.gif");break;
		case 5:im=mytool.getImage(a+"\\"+a+"_05.gif");break;
		case 6:im=mytool.getImage(a+"\\"+a+"_06.gif");break;
                case 7:im=mytool.getImage(a+"\\"+a+"_07.gif");break;
		case 8:im=mytool.getImage(a+"\\"+a+"_08.gif");break;

		}
                g.fill3DRect(j * 70, i * 70, 70, 70,true);    //Fill3DRect使用当前颜色填充矩形参数分别为x坐标 y坐标 宽度 高度
                g.draw3DRect(j * 70, i * 70, 70, 70,true);    //使矩形有边框向上凸出
                if(map[i][j]!=0)g.drawImage(im,j*70,i*70,70,70,null);
	
		} }
 
        //画墙
        for (i= 0; i< 5; i++) {
            for (j = 0; j < 5; j++) {
                if (map[i][j] == 10) {    //等于2代表为边框部分
                    g.setColor(Color.orange);
		    g.draw3DRect(j * 70, i * 70, 70, 70,false);
		    g.fill3DRect(j * 70, i * 70, 70, 70,true);
                }
            }
        }
 
    }

    // 键盘监听
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_DOWN:   //键盘下键
            down();
            break;
        case KeyEvent.VK_UP:     //键盘上键
            up();
            break;
        case KeyEvent.VK_RIGHT:  //键盘右键
            right();
            break;
        case KeyEvent.VK_LEFT:   //键盘左键
            left();
            break;
        }

    }


    // 无用
    public void keyReleased(KeyEvent e) {
    }

    // 无用
    public void keyTyped(KeyEvent e) {
    }
   // 定时器监听
    class TimerListener implements ActionListener {   //定时刷新执行repaint
        public void actionPerformed(ActionEvent e) {
                repaint();
        }
    }

}