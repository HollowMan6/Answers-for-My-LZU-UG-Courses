import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class StudentFrame extends JFrame
{
   JLabel id_label,name_label,english_label,chinese_label,math_label;
   JTextField id_txt,name_txt,english_txt,chinese_txt,math_txt;
   JButton first,last,next,previous,save,load,saveall,delete;
   StudentBean mybean;
   ArrayList<Student> stulist;
   int row=0;
   public StudentFrame()
   {
      super("学生成绩管理系统 version 1.0");
      mybean=new StudentBean();
      JPanel p1=new JPanel();
      JPanel p2=new JPanel();
      id_label=new JLabel("学号:");
      name_label=new JLabel("姓名:");
      english_label=new JLabel("英语:");
      chinese_label=new JLabel("语文:");
      math_label=new JLabel("数学:");
      id_txt=new JTextField(8);
      name_txt=new JTextField(8);
      english_txt=new JTextField(8);
      chinese_txt=new JTextField(8);
      math_txt=new JTextField(8);
      p1.setLayout(new GridLayout(5,2));
      p1.add(id_label);p1.add(id_txt);
      p1.add(name_label);p1.add(name_txt);
      p1.add(english_label);p1.add(english_txt);
      p1.add(chinese_label);p1.add(chinese_txt);
      p1.add(math_label);p1.add(math_txt);

      first=new JButton("第一条");
      first.setEnabled(false);
      first.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            savetolist();
            row=0;
            refreshdata();   
         }
      });

      next=new JButton("下一条");
      next.setEnabled(false);
      next.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            savetolist();
            if(row<stulist.size()-1)
            {
               row++;
            }
            else
            {  
               row=stulist.size()-1;
            }
            refreshdata();   
         }
      });
      last=new JButton("最后一条");
      last.setEnabled(false);
      last.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            savetolist();
            row=stulist.size()-1;
            refreshdata();   
         }
      });
      previous=new JButton("上一条");
      previous.setEnabled(false);
      previous.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            savetolist();
            if(row>0)
            {
               row--;
            }
            else
            {  
               row=0;
            }
            refreshdata(); 
         }
      });

      save=new JButton("保存");
      save.setEnabled(false);
      save.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            Student stu=new Student();
            stu.setId(id_txt.getText());
            stu.setName(name_txt.getText());
            try{
              stu.setEnglish(Float.parseFloat(english_txt.getText()));
              stu.setChinese(Float.parseFloat(chinese_txt.getText()));
              stu.setMath(Float.parseFloat(math_txt.getText()));
              mybean.insertOrUpdate(stu);
              stulist=mybean.getAll();
            }
            catch(NumberFormatException ne)
            {
               JOptionPane.showMessageDialog(null,"你输入的成绩格式不对！" , "错误信息", JOptionPane.ERROR_MESSAGE);
            }
            catch(Exception e2){}
         }
      });

      saveall=new JButton("保存全部");
      saveall.setEnabled(false);
      saveall.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            try{
              for(int i=0;i<stulist.size();i++)
              {
                 mybean.insertOrUpdate(stulist.get(i));
              }
              stulist=mybean.getAll();
            }
            catch(NumberFormatException ne)
            {
               JOptionPane.showMessageDialog(null,"你输入的成绩格式不对！" , "错误信息", JOptionPane.ERROR_MESSAGE);
            }
            catch(Exception e2){}
         }
      });

      delete=new JButton("删除");
      delete.setEnabled(false);
      delete.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            Student stu=new Student();
            stu.setId(id_txt.getText());
            try{
              mybean.delStudent(stu);
              stulist=mybean.getAll();
              if(row>stulist.size()-1)
              {
                 row=stulist.size()-1;
              }
              refreshdata();
            }
           catch(Exception e2){}
         }
      });

      load=new JButton("装入");
      load.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
             try
             {
                stulist=mybean.getAll();
             }
             catch(Exception e1){}
             first.setEnabled(true);
             next.setEnabled(true);
             last.setEnabled(true);
             previous.setEnabled(true);
             saveall.setEnabled(true);
             delete.setEnabled(true);
             save.setEnabled(true);
             refreshdata();   
         }
      });
      p2.setLayout(new GridLayout(2,4));
      p2.add(first);p2.add(previous);p2.add(next);p2.add(last);
      p2.add(load);p2.add(save);p2.add(delete);p2.add(saveall);
      Container con=getContentPane();
      con.add(p1,BorderLayout.CENTER);
      con.add(p2,BorderLayout.SOUTH);
   }
   public void refreshdata()
   {
      id_txt.setText(stulist.get(row).getId());
      name_txt.setText(stulist.get(row).getName());
      english_txt.setText(""+stulist.get(row).getEnglish());
      chinese_txt.setText(""+stulist.get(row).getChinese());
      math_txt.setText(""+stulist.get(row).getMath());
   }
   public void savetolist()
   {
      try
      {
        stulist.get(row).setId(id_txt.getText());
        stulist.get(row).setName(name_txt.getText());
        stulist.get(row).setEnglish(Float.parseFloat(english_txt.getText()));
        stulist.get(row).setChinese(Float.parseFloat(chinese_txt.getText()));
        stulist.get(row).setMath(Float.parseFloat(math_txt.getText()));
      }
      catch(NumberFormatException ne)
      {
          JOptionPane.showMessageDialog(null,"你输入的成绩格式不对！" , "错误信息", JOptionPane.ERROR_MESSAGE);
      }
      
   }
   public static void main(String[] args)
   {
      StudentFrame myapp=new StudentFrame();
      myapp.setSize(500,300);
      myapp.setVisible(true);
      myapp.addWindowListener(new WindowDestroyer());
   }
}