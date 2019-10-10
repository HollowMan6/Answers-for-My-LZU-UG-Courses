import java.awt.*;
import java.applet.*;
public class paramtest extends Applet
{
   Color c;
   String msg;
   public void init()
   {
      int r=Integer.parseInt(getParameter("red"));
      int g=Integer.parseInt(getParameter("green"));
      int b=Integer.parseInt(getParameter("blue"));
      msg=getParameter("msg");
      c=new Color(r,g,b);
   }
   public void paint(Graphics g)
   {
      setBackground(c);
      g.setFont(new Font("宋体",Font.BOLD,26));
      g.setColor(Color.red);
      g.drawString(msg,20,100);
   }
}


