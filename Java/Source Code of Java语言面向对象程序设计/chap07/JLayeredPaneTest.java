import javax.swing.*;
import java.awt.*; 
class JLayeredPaneTest
{
    public static void main(String args[])
    {
       JFrame win=new JFrame("窗体"); 
       win.setBounds(100,100,300,300);
       win.setVisible(true);
       JButton b1=new JButton("我在DEFAULT_LAYER"),
              b2=new JButton("我在PALETTE_LAYER"),
               b3 =new JButton("我在MODAL_LAYER"),
               b4 =new JButton("我在POPUP_LAYER"),
               b5=new JButton("我在DRAG_LAYER");
       Container contenetPane=win.getContentPane();
       JLayeredPane pane=new JLayeredPane();
       pane.setLayout(null);
       pane.add(b5,JLayeredPane.DRAG_LAYER);
       pane.add(b4,JLayeredPane.POPUP_LAYER);
       pane.add(b3,JLayeredPane.MODAL_LAYER); 
       pane.add(b2,JLayeredPane.PALETTE_LAYER); 
       pane.add(b1,JLayeredPane.DEFAULT_LAYER);
       b5.setBounds(50,50,200,100);         
       b4.setBounds(40,40,200,100);
       b3.setBounds(30,30,200,100);
       b2.setBounds(20,20,200,100);
       b1.setBounds(10,10,200,100);
       b1.setBackground(Color.red);
       b2.setBackground(Color.pink);
       b3.setBackground(Color.green);
       b4.setBackground(Color.orange);
       b5.setBackground(Color.yellow);
       contenetPane.add(pane,BorderLayout.CENTER);
       contenetPane.validate();
       win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
