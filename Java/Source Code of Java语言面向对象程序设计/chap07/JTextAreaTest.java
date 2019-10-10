import java.awt.*;
import javax.swing.*;
public class JTextAreaTest {
    private static void constructGUI(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame=new JFrame("JTextArea Test");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String text="A JTextArea Object represents"+
             "a multiline area for displaying text. "+
             "You can change the number of lines "+
             "that can be displayed at a time, "+
             "as well as the number of columns. "+
             "You can wrap lines and words too. "+
             "You can also put your JTextArea in a "+
             "JScrollPane to make it scrollable.";
        JTextArea textArea1=new JTextArea(text,5,10);
        textArea1.setPreferredSize(new Dimension(100,100));
        JTextArea textArea2=new JTextArea(text,5,10);
        textArea2.setPreferredSize(new Dimension(100,100));
        JScrollPane scrollpane=new JScrollPane(textArea2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        textArea1.setLineWrap(true);
        textArea2.setLineWrap(true);
        frame.add(textArea1);
        frame.add(scrollpane);
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