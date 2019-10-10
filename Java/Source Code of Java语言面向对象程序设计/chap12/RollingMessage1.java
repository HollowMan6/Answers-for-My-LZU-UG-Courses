import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
public class RollingMessage1 extends java.applet.Applet implements Runnable {
Thread runThread;
String s;  // 要显示的字符串
int s_length;  // 字符串的长度
int x_character=0,  // 当前显示到第几个字符串
y_coord,  // 字符串的Y坐标位置
textcolor,  // 字符串的颜色值（16进制整数rrggbb）
backcolor,  // applet背景颜色值（16进制整数rrggbb）
delay;  //每帧画面的时延（毫秒）
int appletWidth, appletHeight;
String font_name;  // 字体的名称
int font_size;  // 字体的尺寸
Font wordFont;
FontMetrics wordMetrics;
public void init() {
String temp;
appletWidth = size().width;
appletHeight = size().height;
temp=getParameter("font");
font_name= (temp==null) ? "TimesRoman" : temp;
temp = getParameter("fontsize");
font_size= (temp==null) ? 12 : Integer.parseInt( temp ); //转换为10进制整数
wordFont = new Font(font_name, Font.PLAIN, font_size);
if (wordFont == null)
wordFont = getFont();
wordMetrics = getFontMetrics (wordFont);
temp = getParameter("text");
s= (temp==null) ? "Message goes here... " : temp;
s_length=s.length();
temp = getParameter("textcolor");
textcolor= (temp==null) ? 0 : Integer.parseInt( temp ,16 );  //转换为16进制整数
temp = getParameter("backcolor");
backcolor= (temp==null) ? 0xffffff : Integer.parseInt( temp ,16 );
temp = getParameter("delay");
delay= (temp==null) ? 100 : Integer.parseInt( temp );
y_coord = appletHeight/2 + (wordMetrics.getHeight()-wordMetrics.getDescent())/2;
}
public void start() {
if(runThread==null){
runThread = new Thread(this);
runThread.start();
}
}
public void stop() {
if(runThread!=null){
runThread.stop();
runThread=null;
}
}
public void run() {
while(true) {
if (++x_character>s_length)
x_character = 0;
repaint ();
try {
Thread.sleep( delay );
} catch (InterruptedException e) {}
}
}
public void paint (Graphics g) {
g.setFont (wordFont);
g.setColor (Color.red);
g.drawString (s.substring(0,x_character), 8, y_coord);
}
public void update(Graphics g){
if (x_character == 0){
g.setColor(getBackground());
g.fillRect(0,0,appletWidth,appletHeight);
g.setColor(getForeground());
}
paint(g);
}
}