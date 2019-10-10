import java.awt.*;
import javax.swing.*;
public class JComboBoxTest {
    private static void constructGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame=new JFrame("JComboBox Test");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] selections={"green","red","orange","dark blue"};
        JComboBox comboBox=new JComboBox(selections);
        comboBox.setSelectedIndex(1);
        System.out.println(comboBox.getSelectedItem());
        frame.add(comboBox);
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