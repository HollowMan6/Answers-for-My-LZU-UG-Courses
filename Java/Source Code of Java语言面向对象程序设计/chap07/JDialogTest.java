import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class JDialogTest extends JFrame{
    AddressDialog dialog=new AddressDialog(this,false);
    public JDialogTest(String title){
        super(title);
        init();
    }
    public JDialogTest(){
        super();
        init();
    }
    private void init(){
        this.getContentPane().setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AddressDialog dialog=new AddressDialog(this,false);
        JButton button=new JButton("Show Dialog");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                displayDialog();
            }
        });
        this.getContentPane().add(button);
    }
    private void displayDialog(){
        dialog.setSize(250,120);
        dialog.setVisible(true);
    }
    private static void constructGUI(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        JDialogTest frame=new JDialogTest();
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