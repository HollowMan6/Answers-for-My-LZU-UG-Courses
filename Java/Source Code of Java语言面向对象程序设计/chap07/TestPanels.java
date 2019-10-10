import javax.swing.*;
import java.awt.*;
public class TestPanels extends JFrame {
public TestPanels() {
setDefaultCloseOperation(EXIT_ON_CLOSE);
JPanel panel = new JPanel();
for (int i = 0; i < 2; i++) {
panel.add(new JButton("Button 00" + i));
}
JTextArea textArea = new JTextArea(5, 15);
textArea.setLineWrap(true);
JScrollPane scrollPane = new JScrollPane(textArea);
getContentPane().add(panel, BorderLayout.NORTH);
getContentPane().add(scrollPane, BorderLayout.CENTER);
pack();
}
public static void main(String[] args) {
TestPanels tp = new TestPanels();
tp.show();
}
}