import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
public class JFileChooserTest extends JFrame {
    private static void constructGUI(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        JFrame frame=new JFrame("JComboBox Test");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button=new JButton("Select File");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                JFileChooser fileChooser=new JFileChooser();
                int returnValue=fileChooser.showOpenDialog(null);
                if(returnValue==JFileChooser.APPROVE_OPTION){
                    File selectedFile=fileChooser.getSelectedFile();
                    System.out.println(selectedFile.getName());
                }
            }
        });
        frame.add(button);
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