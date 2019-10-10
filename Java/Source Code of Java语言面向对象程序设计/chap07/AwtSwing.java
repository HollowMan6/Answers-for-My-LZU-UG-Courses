import java.awt.*;
import javax.swing.*;
public class AwtSwing {
    public static void main(String[] args) {
        AwtSwing as = new AwtSwing();
        as.show();
    }
    JFrame frame = new JFrame("Test AWT and SWING");
    JDesktopPane jdp = new JDesktopPane();
    JInternalFrame jif1 = new JInternalFrame("controls");
    JInternalFrame jif2 = new JInternalFrame("cover");
    public AwtSwing() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(jdp);
        jif1.setContentPane(new JPanel());
        jif2.setContentPane(new JPanel());
        jif1.getContentPane().setLayout(new BorderLayout());
        jif1.getContentPane().add(new Button("AWT Button"), BorderLayout.WEST);
        jif1.getContentPane().add(new JButton("Swing Button"),BorderLayout.EAST);
        jif1.setSize(200, 100);
        jif2.setSize(200, 100);
        jdp.add(jif1);jdp.add(jif2);
        frame.setSize(240, 140);
    }
    public void show() {
        frame.setVisible(true);
        jif1.setVisible(true);
        jif2.setVisible(true);
    }
}