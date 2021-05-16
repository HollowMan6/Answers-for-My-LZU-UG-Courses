import java.awt.*;
import java.awt.event.*;
import java.awt.Image.*;
import javax.swing.*; 
public class CardsTest extends JPanel{
    static JFrame myplay;
    private Cards cards; 
    Player [] p; //玩家
    public CardsTest(){
	cards = new Cards();//创建一副牌
	cards.shuffle(); //洗牌
	p=new Player[5];//四个玩家和底牌
        p[0]=new Player("东家","东",1);
        p[1]=new Player("南家","南",1);
        p[2]=new Player("西家","西",1);
        p[3]=new Player("北家","北",1);
        p[4]=new Player("底牌","中",1);
	deal();// 发牌
    }
    public void deal() {// 将牌依次发给玩家
	int suitmax=12;  //每人12张，底牌留6张 
	for (int index = 0,round=0; round<suitmax; round++) {
	    for(int i=0;i<4;i++){
	     	p[i].setCard(round,cards.getIndex(index++));
	    }
	}
	for(int a=48,round=0;a<54;){
	    p[4].setCard(round,cards.getIndex(a++));
	    round++;
	}
	for(int i=0;i<4;i++){
	      p[i].sort();
	}
    }
    public void showCardsInPlayer(){
	for(int i=0;i<4;i++){
	    p[i].showHand();
	}	
    }
    public  void showBottom(){
	System.out.println();
	System.out.print("底牌：");
        cards.displays(4*12,54);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int x=40,y=0;
        setBackground(Color.cyan);
        int w=80,h=123;
	for(int j=0;j<12;j++){
            p[0].getCard(j).drawme(g,x,y,w,h);x+=25;
         }
	x=350;y=30;
	for(int j=0;j<12;j++){
            p[1].getCard(j).drawme(g,x,y,w,h);y+=25;
         }
	x=0;y=30;
	for(int j=0;j<12;j++){
            p[3].getCard(j).drawme(g,x,y,w,h);y+=25;
        }
        x=40;y=330;
	for(int j=0;j<12;j++){
            p[2].getCard(j).drawme(g,x,y,w,h);x+=25;
        }
	x=110;y=170;
	for(int j=0;j<6;j++){
	    p[4].getCard(j).drawme(g,x,y,w,h);x+=25;
	}     
    }
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
	myplay=new JFrame("扑克牌建模");
	JMenuBar mb= new JMenuBar();
 	JMenu m1=new JMenu("游戏");
 	JMenu m2=new JMenu("帮助");
 	JMenuItem mi1 = new JMenuItem("新游戏");	
	JMenuItem mi2=new JMenuItem("关闭");
	mb.add(m1);mb.add(m2);
 	m1.add(mi1);
	m1.add(mi2);
        mi2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        mi1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
	        CardsTest cardpanel=new CardsTest();
	        myplay.getContentPane().add(cardpanel);
	        cardpanel.showCardsInPlayer();
	        cardpanel.showBottom();
                cardpanel.revalidate();
            }
        });
        myplay.setJMenuBar(mb);
        myplay.setSize(450,540);
	myplay.setVisible(true);
	myplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}