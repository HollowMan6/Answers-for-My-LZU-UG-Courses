import javax.swing.*; 
import java.awt.Container;   
public class SpringLayoutTest {     
    public static void main(String[] args) {  
        JFrame frame = new JFrame("SpringDemo1");         
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         
        Container contentPane = frame.getContentPane(); 
        SpringLayout layout = new SpringLayout();         
        contentPane.setLayout(layout); 
        contentPane.add(new JLabel("Label: "));         
        contentPane.add(new JTextField("Text field", 15));     
        frame.setSize(100,200);         
        frame.setVisible(true);       
    }
}