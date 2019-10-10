import javax.swing.*;
public class JButtonTest extends JFrame {
    private static void constructGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("JButton Test");
        ImageIcon imageIcon=new ImageIcon("./images/triangle.jpg");
        JButton loginButton=new JButton("Login",imageIcon);
        frame.add(loginButton);
        frame.pack();
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