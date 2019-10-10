import java.awt.*;
import java.util.*;
class myframe
{
   public static void main(String[] args)
   {
      Frame myapp=new Frame("图形界面测试!");
      Panel p1=new Panel();
      p1.setBackground(Color.yellow);

      myapp.add(p1,BorderLayout.NORTH);
      Label lb=new Label("姓名：");
      TextField tf=new TextField(10);
      TextArea ta=new TextArea();
      p1.add(lb);p1.add(tf);

      Button ok=new Button("提交");
      Button cc=new Button("取消");
      p1.add(ok);p1.add(cc);
      Panel p2=new Panel();
      p2.setBackground(Color.cyan);

      myapp.add(ta,BorderLayout.CENTER);
      Label lb1=new Label("状态行:         当前时间:"+new Date());
      CheckboxGroup cg=new CheckboxGroup();
      Checkbox male=new Checkbox("男",cg,true);
      Checkbox female=new Checkbox("女",cg,false);
      p2.add(male);p2.add(female);
      p2.add(lb1);
      myapp.add(p2,BorderLayout.SOUTH);
      Panel p3=new Panel();
      p3.setBackground(new Color(128,128,0));
      Label lb3=new Label("你喜欢的足球运动员是:");
      Choice moviestars = new Choice( ); 
      moviestars.addItem("安东尼奥.班德拉斯");
      moviestars.addItem("莱昂纳多.迪卡普尼奥");
      moviestars.addItem("桑德.布洛克");
      moviestars.addItem("休.葛兰特");
      moviestars.addItem("朱莉亚.罗萡茨"); 
      p3.add(lb3);p3.add(moviestars);
      myapp.add(p3,BorderLayout.EAST);
      Button btn=new Button("西部时空");
      myapp.add(btn,BorderLayout.WEST);
      myapp.setBounds(100,100,600,400);
      myapp.setVisible(true);
      myapp.setBackground(Color.red);
   }
}