import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
public class JFrameTest1{
    public static void constructGUI(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame=new JFrame();
        frame.setTitle("My First Swing Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label=new JLabel("Welcome");
        frame.add(label);
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args){
    //sun公司建议调用javax.swing.SwingUtilities类的静态方法invokeLater,以确保相应的调度线程创建GUI。
    //使用这个方法会使代码便得更加复杂一些，但是可以确保SWing应用程序能够正确的显示。
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                constructGUI();
            }
         });
     }
}