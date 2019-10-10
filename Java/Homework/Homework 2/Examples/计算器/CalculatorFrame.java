package calculator;

import java.awt.*;
import javax.swing.*;

class CalculatorTest {
   public static void main(String[] args){
      EventQueue.invokeLater(new Runnable(){
         public void run(){
            JFrame frame=new CalculatorFrame();
            frame.setTitle("Calculator v1.0");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         }
      });
   }
}
class CalculatorFrame extends JFrame{
   public CalculatorFrame(){
      add(new CalculatorPanel());
      pack();
   }
}