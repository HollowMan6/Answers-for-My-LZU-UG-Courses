import java.awt.*;
import javax.swing.*;
public class JTextFieldTest {
    private static void constructGUI(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("JTextField Test");
        frame.setLayout(new GridLayout(2,2));
        JLabel label1=new JLabel("User Name:",SwingConstants.RIGHT);
        JLabel label2=new JLabel("Password:",SwingConstants.RIGHT);
        JTextField userNameField=new JTextField(20);
        JPasswordField passwordField=new JPasswordField();
        frame.add(label1);
        frame.add(userNameField);
        frame.add(label2);
        frame.add(passwordField);
        frame.setSize(200,70);
        frame.setVisible(true);
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                constructGUI();
            }
        });
    }
}