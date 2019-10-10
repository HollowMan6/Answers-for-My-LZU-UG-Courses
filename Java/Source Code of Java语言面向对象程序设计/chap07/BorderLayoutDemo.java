import java.awt.*;
public class BorderLayoutDemo{
   public static void main(String[] args)
   {
      Frame myapp=new Frame("BorderLayoutDemo");  //默认就是BorderLayout
      Button btnEast=new Button("东");
      Button btnWest=new Button("西");
      Button btnNorth=new Button("北");
      Button btnSouth=new Button("南");
      Button btnCenter=new Button("中");
      myapp.add(btnEast,BorderLayout.EAST);
      myapp.add(btnWest,BorderLayout.WEST);
      myapp.add(btnNorth,BorderLayout.NORTH);
      myapp.add(btnSouth,BorderLayout.SOUTH);
      myapp.add(btnCenter,BorderLayout.CENTER);
      myapp.setSize(300,300);
      myapp.setVisible(true);
   }
}