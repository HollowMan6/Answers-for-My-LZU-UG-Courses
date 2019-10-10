import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
class MyInternalFrame extends
 JInternalFrame 
{
    JTextArea text;
    MyInternalFrame(String title)
    {
       super(title,true,true,true,true);
       text=new JTextArea();
       Container con=getContentPane();
       con.add(new JScrollPane(text),BorderLayout.CENTER);
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       addInternalFrameListener(new InternalFrameAdapter ()
                                {
                                  public void internalFrameActivated(InternalFrameEvent e)
                                   {
                                     setLayer(JDesktopPane.DRAG_LAYER);
                                   } 
                                  public  void internalFrameDeactivated(InternalFrameEvent e)
                                   {
                                      setLayer(JDesktopPane.DEFAULT_LAYER);
                               
                                   } 
                                });
    }
    public JTextArea getJTextArea()
    {
       return text;
    } 
}
class Mywindow extends JFrame implements ActionListener
{ 
    JDesktopPane  desk;      //用来添加内部窗体的桌面容器。
    JMenuBar menubar;
    JMenu menu;
    JMenuItem  itemNew,itemCopy,itemCut,itemPaste; 
     Container con;
    Mywindow()
    {
       setSize(360,360);
       setVisible(true); 
       con=getContentPane();
       desk=new JDesktopPane();
       desk.setDesktopManager(new DefaultDesktopManager());
       con.add(desk,BorderLayout.CENTER);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       menubar=new JMenuBar(); 
       menu=new JMenu("编辑");   
       itemNew=new JMenuItem("新建");
       itemCopy=new JMenuItem("拷贝");
       itemCut=new JMenuItem("剪切");
       itemPaste=new JMenuItem("粘贴");
       itemNew.addActionListener(this);
       itemCopy.addActionListener(this);
       itemCut.addActionListener(this);
       itemPaste.addActionListener(this);
       menu.add(itemNew);
       menu.add(itemCopy);
       menu.add(itemCut);
       menu.add(itemPaste);
       menubar.add(menu);
       setJMenuBar(menubar);
       validate();
    }
public void actionPerformed(ActionEvent e)
    {
       if(e.getSource()==itemNew)          //创建一个内部窗体。
       {
          JInternalFrame a[]=desk.getAllFrames();
          for(int i=0;i<a.length;i++)
           {
              desk.setLayer(a[i],JDesktopPane.DEFAULT_LAYER);
           }
          JInternalFrame newInternalFrame=new  MyInternalFrame("无标题");
          newInternalFrame.setBounds(10,10,300,300);
          newInternalFrame.setVisible(true);
          desk.add(newInternalFrame,JDesktopPane.DRAG_LAYER); 
          desk.validate();
          con.validate();
          this.validate();
       }
      if(e.getSource()==itemCopy)
       {
           MyInternalFrame internalFrame=(MyInternalFrame)desk.getSelectedFrame();
           JTextArea text=internalFrame.getJTextArea();
           text.copy();
       }   
       else if(e.getSource()==itemCut)
       {
           MyInternalFrame internalFrame=(MyInternalFrame)desk.getSelectedFrame();
           JTextArea text=internalFrame.getJTextArea();
           text.cut();
       }    
       else if(e.getSource()==itemPaste)
       {
           MyInternalFrame internalFrame=(MyInternalFrame)desk.getSelectedFrame();
           JTextArea text=internalFrame.getJTextArea();
           text.paste();
       }
    }
}
class InternalFrameTest
{ 
    public static void main(String args[])
    {
      Mywindow win=new Mywindow();
    }
}
