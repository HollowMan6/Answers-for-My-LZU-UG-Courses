import java.awt.event.*;  
import javax.swing.*;  
public class SpringDemo extends JFrame {  
    private JPanel jp = new JPanel();  
    private JLabel jl = new JLabel("请将备注写在这里:");  
    private JTextArea jta = new JTextArea();  
    private JScrollPane jsp = new JScrollPane(jta);  
    private SpringLayout sl = new SpringLayout();  
    public SpringDemo() {  
    jp.setLayout(sl);   
    Spring jlx = Spring.constant(20);  
    Spring jly = Spring.constant(10);  
    Spring jlw = Spring.constant(150);   
    Spring jlh = Spring.constant(15);   
    jp.add(jl, new SpringLayout.Constraints(jlx, jly, jlw, jlh)); 
    Spring jlx1 = Spring.constant(140);  
    Spring jly1 = Spring.constant(10);  
    Spring jlw1 = Spring.constant(100);   
    Spring jlh1 = Spring.constant(15);        
    jp.add(new JTextField(10),new SpringLayout.Constraints(jlx1, jly1, jlw1, jlh1));
    Spring jpw = sl.getConstraint(SpringLayout.EAST, jp);  
    Spring jph = sl.getConstraint(SpringLayout.SOUTH, jp);   
    Spring jls = sl.getConstraint(SpringLayout.SOUTH, jl);   
    Spring jspx = Spring.constant(5);    
    Spring jspy = Spring.sum(Spring.constant(5), jls);  
    Spring jspw = Spring.sum(jpw, Spring.minus(Spring.scale(jspx, 2.0f)));  
    Spring jsph = Spring.sum(jph, Spring.minus(Spring.sum(jspx, jspy)));    
    jp.add(jsp, new SpringLayout.Constraints(jspx, jspy, jspw, jsph));  
    this.add(jp);    
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    this.setTitle("弹簧布局示例");  
    this.setBounds(100, 100, 300, 200);  
    this.setVisible(true);  
    }  
    public static void main(String[] args) {  
    new SpringDemo();  
    }  
}  
