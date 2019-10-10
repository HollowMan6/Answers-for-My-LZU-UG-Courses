import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.io.IOException;
import javax.swing.Timer;   //计时器包

public class Tetris extends JFrame {
    public static Tetrisblok a = new Tetrisblok();
    public Tetris(Tetrisblok b) {
        addKeyListener(b);
        add(b);   //由于方块类是继承的面板类 因此则可将其直接添加进
    }

    public static void main(String[] args) {
        Tetris frame = new Tetris(a);     
        JMenuBar menu = new JMenuBar();       //JMenuBar类为菜单栏类  可添加JMenu菜单对象
        frame.setJMenuBar(menu);              //在框架中添加菜单栏menu
        JMenu menu1 = new JMenu("游戏");      //使用JMenu类菜单
        JMenuItem newgame = menu1.add("新游戏"); 
        JMenuItem pause = menu1.add("暂停游戏");
        JMenuItem conti = menu1.add("继续游戏");
        JMenuItem exit = menu1.add("退出游戏");
        JMenu menu2 = new JMenu("帮助");
        JMenuItem about = menu2.add("关于");
        menu.add(menu1);
        menu.add(menu2);
        //监听菜单事件并调用相关函数
        exit.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
        JOptionPane.showMessageDialog(null, "Goodbye!"); 
        System.exit(0);
        }});
        newgame.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
        a.newbegin();
        }});
        about.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
        a.read();
        }});
        pause.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
        a.pause();
        }});
        conti.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
        a.conti();
        }});
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(400,500);         //设置边框的大小
        frame.setTitle("俄罗斯方块");   //设置边框名字
        frame.setVisible(true);         //设置可见
        frame.setResizable(false);
        a.start();

    }
}

// 创建一个俄罗斯方块类
class Tetrisblok extends JPanel implements KeyListener,Runnable{ 

    private int blockType;           //方块类型
    private int score = 0;           //所得分数  一个方块的一分  消去一行得10分
    private int[] next=new int[2];   //用于存放下一个方块的类型及状态
    private int f=0;                 //记录是否是第一次产生块  若为第一次产生则需要产生两组未知数来确定两个块

    private int turnState;           //方块状态
    
    private Timer timer;             //Timer计时器对象
    private int x;                   //整个界面的x坐标

    private int y;                   //整个界面的y坐标用于定位每个块的位置

    private int i = 0;

    int j = 0;
    int flag = 0;
  
    int[][] map = new int[13][23];                //定义已经放下的方块x=0~11,y=0~21;
    Thread thread1=new Thread(this,"");           //创建好一个线程 使其具有该类中的run方法

    // 方块的形状 第一层代表方块类型有S、Z、L、J、I、O、T 7种 第二层 代表旋转几次 第三层为 方块矩阵的数字表示  每一个方块都有一个4*4的矩阵来表示
    private final int shapes[][][] = new int[][][] {
            // i  一字型
            { { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
                    { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 } },
            // s  反z型
            { { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                    { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } },
            // z   z型
            { { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 } },
            // j    J型
            { { 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                    { 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
            // o     正方形
            { { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
            // l    L型
            { { 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
            // t    T型
            { { 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 } } };

    // 生成新方块的方法
    public void newblock() {
        if(f==0){                                       //表明是第一次产生块
        blockType = (int) (Math.random() * 1000) % 7;   //产生随机数 随机一种类型的块出来
        turnState = (int) (Math.random() * 1000) % 4;   //随机一种类型的 状态出来
        f=1;
        }
        else{  
        blockType = next[0];                  
        turnState = next[1];  
	}
        next[0]=(int) (Math.random() * 1000) % 7;      //next数组中存放的是下一个块的类型及状态以便于提前显示通知玩家
        next[1]=(int) (Math.random() * 1000) % 4;
        
        x = 4;                                         //横坐标值初始化为4
        y = 0;					       //纵坐标值初始化为0
        if (gameover(x, y) == 1) {                  

            newmap();
            drawwall();
            score = 0;
            JOptionPane.showMessageDialog(null, "GAME OVER");  //调用JOptionPane类的方法来显示游戏结束窗口
        }
    }

    // 画围墙
    public void drawwall() {
        for (i = 0; i < 12; i++) {
            map[i][21] = 2;
        }
        for (j = 0; j < 22; j++) {
            map[11][j] = 2;
            map[0][j] = 2;
        }
    }

    // 初始化地图
    public void newmap() {                               //地图大小为12*22  其中10个放方块的地方两个左右边框  22为高度 最下面为边框
        for (i = 0; i < 12; i++) {
            for (j = 0; j < 22; j++) {
                map[i][j] = 0;
            }
        }
    }

    // 初始化构造方法
    Tetrisblok() {
        newblock();    //产生新的块
        newmap();      //画新地图
        drawwall();    //画墙
    }
   
    public void newbegin(){
       newblock();                         
       newmap();
       drawwall();                                       //新游戏要重置并重新画墙和地图
       timer = new Timer(1000, new TimerListener());     //创建一个Timer类  即创建一个新的计时器 开始计时控制下落 1000ms为刷新速率即下落速率
       timer.start();
       score=0;
       repaint();
    }

    public void pause(){
    timer.stop();
    }
    public void conti(){
    timer.start();
    }
    
    public void read(){                                  //用于菜单监听"关于"按钮时用到  调用cmd命令打开Tetris.txt文件
    try{String command="notepad 俄罗斯方块游戏规则";
    Runtime.getRuntime().exec(command);}
    catch(IOException e){}
    }

    public void start(){               
    thread1.start();
    }
    public void run(){                                   //在run方法中创建计时器类  开始按固定时长下落方块
        timer = new Timer(1000, new TimerListener());    //创建一个Timer类  即创建一个新的计时器 开始计时控制下落 1000ms为刷新速率即下落速率
        timer.start();
    }



    // 旋转的方法
    public void turn() {
        int tempturnState = turnState;      //保存原来状态
        turnState = (turnState + 1) % 4;    //将状态值加一  然后返回  取模四  判断旋转后的下一个状态是否可以满足条件
        if (blow(x, y, blockType, turnState) == 1) {
        }
        if (blow(x, y, blockType, turnState) == 0) {
            turnState = tempturnState;      //否则恢复原来的状态值turnState  不改变状态
        }
        repaint();                          //重新绘制组件  调用paint()方法  该方法在component类中 该类为所有基本组件类的祖先
    }

    // 左移的方法
    public void left() {
        if (blow(x - 1, y, blockType, turnState) == 1) {
            x = x - 1;
        }
        repaint();
    }

    // 右移的方法
    public void right() {
        if (blow(x + 1, y, blockType, turnState) == 1) {
            x = x + 1;
        }
        repaint();
    }

    // 下落的方法
    public void down() {
        if (blow(x, y + 1, blockType, turnState) == 1) {
            y = y + 1;
        }
        if (blow(x, y + 1, blockType, turnState) == 0) {   //若向下走一行不合法的话则将其添加到map中固定
            add(x, y, blockType, turnState);               //将这个方块添加到已经好的部分中
            newblock();                                    //建立新的方块
            delline();                                     //看是否满足删行状态  若满足则删行
        }
        repaint();
    }

    // 是否合法的方法
    public int blow(int x, int y, int blockType, int turnState) {
        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 4; b++) {
                if (((shapes[blockType][turnState][a * 4 + b] == 1) && (map[x + b + 1][y + a] == 1))          //判断其状态是否与map中有重合 若有重合则不合法
                     || ((shapes[blockType][turnState][a * 4 + b] == 1) && (map[x + b + 1][y + a] == 2)))     //或与墙重合则不合法
		 { return 0;}               //不合法返回0
            }
        }
        return 1;                           //合法返回1
    }
    



    // 消行的方法
    public void delline() {                  //检查并删除已经满的一行
        int c = 0;
        for (int b = 0; b < 22; b++) {       //从上向下检查
            for (int a = 0; a < 12; a++) {
                if (map[a][b] == 1) {
                    c = c + 1;               //计数 若满了十个则说明一行已经满了则删除  
                    if (c == 10) {
                        score += 10;         //加分数
                        for (int d = b; d > 0; d--) {       //从这行开始向上依次设为上一行状态
                            for (int e = 0; e < 11; e++) {  //将这一行的每一个都换为上一行的状态
                                map[e][d] = map[e][d - 1];

                            }
                        }
                    }
                }
            }
            c = 0;
        }
    }

    // 判断游戏结束的方法
    public int gameover(int x, int y) {
        if (blow(x, y, blockType, turnState) == 0) {
            return 1;
        }
        return 0;
    }

    // 把当前已下落完成的块添加到map中
    public void add(int x, int y, int blockType, int turnState) {    //用于将已经落到底的块添加到地图中  x，y为当前坐标
        int j = 0;
        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 4; b++) {
                if (map[x + b + 1][y + a] == 0) {
                    map[x + b + 1][y + a] = shapes[blockType][turnState][j];  //将该块以当前状态加到map地图中去
                }
          
                j++;
            }
        }
        score++;     //每次下落一个则分数加1
    }

    // 画方块的的方法
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 画当前方块
        for (j = 0; j < 16; j++) {
            if (shapes[blockType][turnState][j] == 1) {
                switch(blockType){                       //画之前先将画的颜色设置好 事不同的块显示不同的颜色
		case 0:g.setColor(Color.magenta);break;
                case 1:g.setColor(Color.cyan);break;
		case 2:g.setColor(Color.lightGray);break;
		case 3:g.setColor(Color.red);break;
		case 4:g.setColor(Color.green);break;
		case 5:g.setColor(Color.yellow);break;
		case 6:g.setColor(Color.pink);break;
		}
                g.fill3DRect((j % 4 + x + 1) * 20, (j / 4 + y) * 20, 20, 20,true);    //Fill3DRect使用当前颜色填充矩形参数分别为x坐标 y坐标 宽度 高度
                g.draw3DRect((j % 4 + x + 1) * 20, (j / 4 + y) * 20, 20, 20,true);    //使矩形有边框向上凸出
		}
              if(shapes[next[0]][next[1]][j] == 1){                                   //显示next数组中存的下一个方块
                switch(next[0]){                       
		case 0:g.setColor(Color.magenta);break;
                case 1:g.setColor(Color.cyan);break;
		case 2:g.setColor(Color.lightGray);break;
		case 3:g.setColor(Color.red);break;
		case 4:g.setColor(Color.green);break;
		case 5:g.setColor(Color.yellow);break;
		case 6:g.setColor(Color.pink);break;
		}
                  g.fill3DRect((j % 4 + 13 + 1) * 20, (j / 4 + 5) * 20, 20, 20,true);
                  g.draw3DRect((j % 4 + 13 + 1) * 20, (j / 4 + 5) * 20, 20, 20,true); 
	        }   
            
 
        }
        // 画已经固定的方块
        for (j = 0; j < 22; j++) {
            for (i = 0; i < 12; i++) {
                if (map[i][j] == 1) {                      //等于1代表已经下落完成的方块
                    g.setColor(Color.blue);
                    g.fill3DRect(i * 20, j * 20, 20, 20,true);
                    g.draw3DRect(i * 20, j * 20, 20, 20,false);

                }
                if (map[i][j] == 2) {                      //等于2代表为边框部分
                    g.setColor(Color.orange);
                    //g.drawRect(i * 20, j * 20, 20, 20);  //drawRect方法用来以当前颜色画边框
		    g.draw3DRect(i * 20, j * 20, 20, 20,false);
		    g.fill3DRect(i * 20, j * 20, 20, 20,true);
		     //g.fillRect(i * 20, j * 20, 20, 20);
                }
            }
        }
        g.setColor(Color.black);                   //写之前将颜色设置为黑色
        g.drawString("您的得分为:", 250, 10);      //显示当前分数提示语句
        g.drawString("           "+score,250,40);  //显示当前分数
        g.drawString("下一个方块是:\n",250, 80);   //提示下一个方块的信息
 
    }

    // 键盘监听
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_DOWN:   //键盘下键
            down();
            break;
        case KeyEvent.VK_UP:     //键盘上键
            turn();
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
    class TimerListener implements ActionListener {   //由于方块需要不断计时下落 因此需要定时器监听并不断刷新
        public void actionPerformed(ActionEvent e) {  //创建timer类时  设置参数每多少毫秒执行该actionPerformed方法

            repaint();
            if (blow(x, y + 1, blockType, turnState) == 1) {
                y = y + 1;
            }
  
            if (blow(x, y + 1, blockType, turnState) == 0) {

                if (flag == 1) {
                    add(x, y, blockType, turnState);
                    delline();
                    newblock();
                    flag = 0;
                }
                flag = 1;
            }
           
        }
    }
}