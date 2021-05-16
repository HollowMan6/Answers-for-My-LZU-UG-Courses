import java.applet.*;
import java.awt.*;
public class Shiyan3_3_4 extends Applet  {
    public void paint(Graphics g) {
        double radian;
        for(int degree=30;degree<=60;degree++){
            radian = degree * Math.PI/180;
            g.drawString("cos("+degree+")="+Math.cos(radian),20,20+(degree-29)*20);
        }     
    }
}