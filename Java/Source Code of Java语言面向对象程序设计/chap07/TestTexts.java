import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class TestTexts extends JFrame {
private JLabel label1 = new JLabel("Username:");
private JLabel label2=new JLabel("Password:");
private JTextField textField;
private JPasswordField pwdField;
private JTextArea textArea;
public TestTexts() {
super("Test Texts");
setDefaultCloseOperation(EXIT_ON_CLOSE);
Container con=getContentPane();
textField = new JTextField(15);
/* 监听文本光标移动事件 */
textField.addCaretListener(new CaretListener() {
    public void caretUpdate(CaretEvent e) {
      // 如果改变了内容，就可以即时更新 label 显示的内容
    System.out.println(textField.getText());
  }
});
pwdField = new JPasswordField(15);
pwdField.addActionListener(new ActionListener(){
  public void actionPerformed(ActionEvent e)
  {
     textArea.append(pwdField.getText());
  }
});
textArea = new JTextArea(5, 15);
textArea.setLineWrap(true);
JPanel p=new JPanel();
p.setLayout(new GridLayout(2,2));
con.add(p,BorderLayout.NORTH);
p.add(label1);p.add(textField);
p.add(label2);p.add(pwdField);
con.add(textArea);
setSize(300, 200);
setVisible(true);
}
public static void main(String[] args) {
TestTexts tt = new TestTexts();
} 
}