import java.awt.*;
import java.awt.event.*;

class Greet{
    public static void main(String[] args) {
        Frame myframe=new Frame("测试窗口");
        Panel p1=new Panel();
        Panel p11 = new Panel();
        p1.setLayout(new BorderLayout());
        myframe.add(p1);
        Label lbl =new Label("姓名：");
        TextField txt=new TextField(35);
        p11.add(lbl);p11.add(txt);
        p1.add(p11,BorderLayout.WEST);
        Panel p12=new Panel();
        Button btn=new Button("确定");
        Button btn2 = new Button("清除");
        p12.add(btn);p12.add(btn2);
        p1.add(p12,BorderLayout.SOUTH);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!(txt.getText().startsWith("你好,")||txt.getText().equals("")))
                    txt.setText("你好, "+txt.getText()+", 欢迎你使用Java编程!");
            }
        });
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txt.setText("");
            }
        });
        myframe.setSize(360,110);
        myframe.setVisible(true);
        myframe.addWindowListener(new WindowCloser());
    }
}