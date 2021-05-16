import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
class MyJTextArea extends JTextArea{
    private Image image;
    public MyJTextArea(String path){
        ImageIcon imageIcon=new ImageIcon(path);
        image=imageIcon.getImage();
        setOpaque(false);
    }
    public void paint(Graphics g){
        g.drawImage(image, 0, 0, getWidth(),getHeight(),this);
        super.paint(g);
    }
}
public class MyJTextAreaTest extends JFrame{
    private JPanel contentPane;
    public static void main(String[] args) {
        MyJTextAreaTest frame=new MyJTextAreaTest();
        frame.setSize(800,300);
        frame.setVisible(true);
    }
    public MyJTextAreaTest(){
        setTitle("自定义背景的文本区域测试");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane=new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(new BorderLayout(0,0));
        setContentPane(contentPane);
        JScrollPane scrollPane=new JScrollPane();
        contentPane.add(scrollPane,BorderLayout.CENTER);
        JTextArea textArea=new MyJTextArea("b.jpg");
        textArea.setFont(new Font("微软雅黑",Font.PLAIN,16));
        scrollPane.setViewportView(textArea);
    }
}