import java.applet.*;
import java.awt.*;
public class HelloApplet extends Applet
{
  public void paint(Graphics g)
  {
      g.drawString("Hello World, sqrt(2.0)="+Math.sqrt(2.0),30,40);
      g.drawLine(30,50,250,50);
  }
}
