import java.awt.*;
public class Card {	// 一张扑克牌的抽象
	private int suit;	// 花色（取值范围为0至4，分别代表方块、梅花、红桃、黑桃、王）
	private int face;	// 点数（取值范围为0至12，分别代表2、3、...、10、J、Q、K、A，王取值0，1）
        private Image image;
        static Toolkit  mytool=Toolkit.getDefaultToolkit();
	// 构造方法，参数s和f分别表示牌的花色与点数
	public Card(int s, int f,String imagefile) {
		suit = s;
		face = f;
		image=mytool.getImage(imagefile);
	}
        public void drawme(Graphics g,int x,int y,int w,int h){
             g.drawImage(image,x,y,w,h,null);
        }
	public String display() {// 返回用字符串描述的牌面
		String suitString = "";
		if (suit == 0)  suitString = "方块";
		else if (suit == 1)  suitString = "梅花";
		else if (suit == 2)  suitString = "红桃";
		else if (suit==3) suitString = "黑桃";
		else suitString="王";
		String faceString = "";
		if(suit!=4){
			if (face >= 0 && face <= 8)  faceString = "" + (face + 2);
			else if (face == 9)  faceString = "J";
			else if (face == 10)  faceString = "Q";
			else  if (face == 11)  faceString = "K";
			else  faceString = "A";
			return  (suitString + faceString);
		}else{
		    	if(face==0) return "小"+suitString;
		    	else return "大"+suitString;
		}
	}
        public String toString(){return display();}
	public int getIndex() {	// 返回在所有牌中排序的次序
		return  (suit * 13 + face);
	}
}