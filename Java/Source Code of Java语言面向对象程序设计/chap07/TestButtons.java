import javax.swing.*;
import java.awt.event.*;
public class TestButtons {
JFrame frame = new JFrame("Test Buttons");
JButton jButton = new JButton("JButton"); //按钮
JToggleButton toggle = new JToggleButton("Toggle Button"); //切换按钮
JCheckBox checkBox = new JCheckBox("Check Box"); //复选按钮
JRadioButton radio1 = new JRadioButton("Radio Button 1"); //单选按钮
JRadioButton radio2 = new JRadioButton("Radio Button 2");
JRadioButton radio3 = new JRadioButton("Radio Button 3");
JLabel label = new JLabel("Here is Status, look here."); //不是按钮，是静态文本
public TestButtons() {
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.getContentPane().setLayout(new java.awt.FlowLayout());
/* 为一般按钮添加动作监听器 */
jButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
label.setText("You clicked jButton");
}
});
/* 为切换按钮添加动作监听器 */
toggle.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
JToggleButton toggle = (JToggleButton) ae.getSource();
if (toggle.isSelected()) {
label.setText("You selected Toggle Button");
} else {
label.setText("You deselected Toggle Button");
}
}
});
/* 为复选按钮添加条目监听器 */
checkBox.addItemListener(new ItemListener() {
public void itemStateChanged(ItemEvent e) {
JCheckBox cb = (JCheckBox) e.getSource();
label.setText("Selected Check Box is " + cb.isSelected());
}
});
/* 用一个按钮组对象包容一组单选按钮 */
ButtonGroup group = new ButtonGroup();
/* 生成一个新的动作监听器对象，备用 */
ActionListener al = new ActionListener() {
public void actionPerformed(ActionEvent ae) {
JRadioButton radio = (JRadioButton) ae.getSource();
if (radio == radio1) {
label.setText("You selected Radio Button 1");
} else if (radio == radio2) {
label.setText("You selected Radio Button 2");
} else {
label.setText("You selected Radio Button 3");
}
}
};
/* 为各单选按钮添加动作监听器 */
radio1.addActionListener(al);
radio2.addActionListener(al);
radio3.addActionListener(al);
/* 将单选按钮添加到按钮组中 */
group.add(radio1);
group.add(radio2);
group.add(radio3);
frame.getContentPane().add(jButton);
frame.getContentPane().add(toggle);
frame.getContentPane().add(checkBox);
frame.getContentPane().add(radio1);
frame.getContentPane().add(radio2);
frame.getContentPane().add(radio3);
frame.getContentPane().add(label);
frame.setSize(200, 250);
}
public void show() {
frame.show();
}
public static void main(String[] args) {
TestButtons tb = new TestButtons();
tb.show();
}
}