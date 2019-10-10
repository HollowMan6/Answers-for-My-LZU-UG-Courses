import java.awt.event.*; 
import java.awt.*;
import javax.swing.*;
class MyDialog extends JDialog implements ActionListener 
{  
    static final int YES=1,NO=0,CLOSE=-1;
    int message=10; 
    Button yes,no;
    MyDialog(JFrame f,String s,boolean b)  //构造方法。	
    {  
      super(f,s,b);
      Container con=getContentPane();
      con.setLayout(new FlowLayout());
      yes=new Button("Yes"); 
      yes.addActionListener(this);
      no=new Button("No");  
      no.addActionListener(this);
      con.add(yes); 
      con.add(no);
      setBounds(60,60,100,100);
      addWindowListener(new WindowAdapter()
                      {   
                         public void windowClosing(WindowEvent e)
                           { 
                              message=CLOSE;
                              setVisible(false);
                           }
                      }
                   );
    }
    public void actionPerformed(ActionEvent e)
    {  
      if(e.getSource()==yes) 
      {
          message=YES;
          setVisible(false);
      }
      else if(e.getSource()==no)
      {
          message=NO;
          setVisible(false);
      }
    }
    public int getMessage()
    { 
          return message;
    }
}
class MyDialogTest extends JFrame implements ActionListener 
{  
    JTextArea text; 
    JButton button; 
    MyDialog dialog;
    MyDialogTest(String s)
    {  
      super(s);
      Container con=getContentPane();
      con.setLayout(new FlowLayout());
      text=new JTextArea(12,20); 
      button=new JButton("打开对话框"); 
      button.addActionListener(this);
      con.add(button); 
      con.add(new JScrollPane(text)); 
      dialog=new MyDialog(this,"我有模式",true);
      setBounds(60,60,300,300); 
      setVisible(true);
      validate();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e)
    { 
        if(e.getSource()==button)
        { 
            dialog.setVisible(true); //对话框激活状态时,堵塞下面的语句。
            //对话框消失后下面的语句继续执行：
           if(dialog.getMessage()==MyDialog.YES)   //如果单击了对话框的"yes"按钮。
             { 
                 text.append("\n你单击了对话框的yes按钮");
             }
           else if(dialog.getMessage()==MyDialog.NO)   
            {  
                 text.append("\n你单击了对话框的No按钮");
            }
          else if(dialog.getMessage()==MyDialog.CLOSE)  
            {  
                 text.append("\n你单击了对话框的关闭图标");
            }
        }
    }
    public static void main(String args[])
    { 
       new MyDialogTest("带对话框的窗口");
    }
}
