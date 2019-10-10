import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PopupMenuDemo extends JFrame
{
   private String[] colorNames={"Blue","Yellow","Red"};
   private Color[] colors={Color.BLUE,Color.YELLOW,Color.RED};
   private JRadioButtonMenuItem items[];
   private JPopupMenu popupMenu=new JPopupMenu();
   
   private ActionListener itemHandler=new ActionListener(){
     public void actionPerformed(ActionEvent e){
       for(int i=0;i<items.length;i++)
          if(e.getSource()==items[i]){
             getContentPane().setBackground(colors[i]);
             repaint();
             return;
           }
     }
   };
   public PopupMenuDemo(String title){
      super(title);
      ButtonGroup colorGroup=new ButtonGroup();
      items=new JRadioButtonMenuItem[3];
      for(int i=0;i<items.length;i++){
         items[i]=new JRadioButtonMenuItem(colorNames[i]);
         popupMenu.add(items[i]);
         colorGroup.add(items[i]);
         items[i].addActionListener(itemHandler);
     }
     getContentPane().setBackground(Color.WHITE);
     addMouseListener(new MouseAdapter(){
        public void mousePressed(MouseEvent e){checkForTriggerEvent(e);}
        public void mouseReleased(MouseEvent e){checkForTriggerEvent(e);}
        private void checkForTriggerEvent(MouseEvent e){
            if(e.isPopupTrigger())  
               popupMenu.show(e.getComponent(),e.getX(),e.getY());
        }
     });
     setSize(500,200);
     setVisible(true);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   public static void main(String args[]){
      new PopupMenuDemo("Hello");
   }
}