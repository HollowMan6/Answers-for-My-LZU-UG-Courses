import java.applet.*;
import java.awt.*;
public class HelloApplet1 extends Applet{
    public void init()
    {
      Label   nml=new Label("姓名：");
      TextField nm=new TextField(8);
      Button  ok=new Button("确定");
      add(nml);add(nm);add(ok);
    }
    public void paint(Graphics g){
          g.drawString("Hello World!",20,60);
          g.drawLine(20,100,100,100);
          g.setColor(Color.red);
          g.fillOval(110,110,40,40);
    }
}
