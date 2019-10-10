import java.applet.*;
import java.awt.*;
import java.awt.event.*;
public class HelloApplet extends Applet{
    TextField nm;
    Button ok;
    public void init()
    {
      Label   nml=new Label("姓名：");
      nm=new TextField(20);
      ok=new Button("确定");
      add(nml);add(nm);add(ok);
      MyListener ml=new MyListener();
      nm.addActionListener(ml);
      ok.addActionListener(ml);
    }
    private class MyListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         String xm=nm.getText();
         if(xm.trim().equals("")) nm.setText("请输入姓名！");
         else nm.setText("欢迎"+xm.trim()+"来使用此程序！"); 
      }
    }
    public void paint(Graphics g){
          g.drawString("Hello World!",20,60);
          g.drawLine(20,100,200,100);
          g.setColor(Color.red);
          g.fillOval(110,110,40,40);
    }
}
