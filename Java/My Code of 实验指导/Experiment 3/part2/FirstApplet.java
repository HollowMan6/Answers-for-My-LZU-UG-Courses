import java.applet.*;
import java.awt.*;
public class FirstApplet extends Applet  {
    public void paint(Graphics g) {
        for(int I=1;I<=20;I++){
            g.drawString("sqrt("+I+")="+Math.sqrt(I),20,20+I*20);
        }     
    }
}