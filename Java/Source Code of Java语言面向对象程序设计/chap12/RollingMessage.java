/* <applet code="RollingMessage.class" width=500 height=100></applet> */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
public class RollingMessage extends java.applet.Applet implements Runnable {
  Thread runThread;
  String s = "Welcome to here!";
  int s_length = s.length();
  int x_character = 0;
  Font wordFont=new Font("TimesRoman",Font.BOLD,50);
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
    if(++x_character>s_length)
      x_character = 0;
    repaint ();
    try {
      Thread.sleep(300);
    } catch (InterruptedException e) {}
    }
  }
  public void paint (Graphics g) {
    g.setFont (wordFont);
    g.setColor (Color.red);
    g.drawString (s.substring(0,x_character), 8, 50);
  }
}