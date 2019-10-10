import java.awt.*;
import javax.swing.*;
public class JListTest {
    private static void constructGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame=new JFrame("JList Test");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] selections={"green","red","orange","dark blue"};
        JList list=new JList(selections);
        list.setSelectedIndex(1);
        System.out.println(list.getSelectedValue());
        frame.add(list);
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                constructGUI();
            }
        });
    }
}