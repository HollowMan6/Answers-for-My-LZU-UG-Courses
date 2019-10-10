import javax.swing.*; 
import java.awt.*;
import javax.swing.border.*;
class BoxLayoutTest extends JFrame 
{  
    Box baseBox,boxV1,boxV2; 
    BoxLayoutTest()
    { 
      boxV1=Box.createVerticalBox();
      boxV1.add(new JLabel("输入您的姓名"));
      boxV1.add(Box.createVerticalStrut(8));
      boxV1.add(new JLabel("输入email"));
      boxV1.add(Box.createVerticalStrut(8));
      boxV1.add(new JLabel("输入您的职业"));
      boxV2=Box.createVerticalBox();
      boxV2.add(new JTextField(16));
      boxV2.add(Box.createVerticalStrut(8));
      boxV2.add(new JTextField(16));
      boxV2.add(Box.createVerticalStrut(8));
      boxV2.add(new JTextField(16));
      baseBox=Box.createHorizontalBox();
      baseBox.add(boxV1);
      baseBox.add(Box.createHorizontalStrut(10));
      baseBox.add(boxV2);
      Container con=getContentPane();
      con.setLayout(new FlowLayout());
      con.add(baseBox); 
      con.validate();
      setBounds(120,100,300,130);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
   public static void main(String args[])
    {
      new BoxLayoutTest(); 
    }
}
