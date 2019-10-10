import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Menutest extends JFrame {
  JMenuItem mi1,mi2,mi3;
  Container con;
  public Menutest() {
  con=getContentPane();
  JMenuBar mb = new JMenuBar();
  JMenu fileMenu = new JMenu("显示");
  JMenu pullRightMenu = new JMenu("问好");
  JMenu colormenu=new JMenu("颜色");
   mi1=new JMenuItem("红色");
   mi2=new JMenuItem("蓝色");
   mi3=new JMenuItem("绿色");
  colormenu.add(mi1);colormenu.add(mi2);colormenu.add(mi3);
  myhandle ls=new myhandle();
  mi1.addActionListener(ls);
  mi2.addActionListener(ls);
  mi3.addActionListener(ls);
  fileMenu.add("欢迎");
  fileMenu.addSeparator();
  fileMenu.add(pullRightMenu);
  fileMenu.add("退出");
  pullRightMenu.add(new JCheckBoxMenuItem("早上好！"));
  pullRightMenu.add(new JCheckBoxMenuItem("下午好！"));
  pullRightMenu.add(new JCheckBoxMenuItem("晚安！再见！"));
  mb.add(fileMenu);mb.add(colormenu);   
  setJMenuBar(mb);
  }
  private class myhandle implements ActionListener
  {
     public void actionPerformed(ActionEvent e)
     {
        if(e.getSource()==mi1) con.setBackground(Color.red);
        if(e.getSource()==mi2) con.setBackground(Color.blue);
        if(e.getSource()==mi3) con.setBackground(Color.green);
     }
  }
  public static void main(String[] args)
  { 
    Menutest myapp=new Menutest();
    myapp.setSize(300,300);
    myapp.setVisible(true);
    myapp.addWindowListener(new WindowDestroyer());
  }
} 
