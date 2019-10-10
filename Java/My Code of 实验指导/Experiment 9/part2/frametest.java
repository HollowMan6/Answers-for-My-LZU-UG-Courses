import java.awt.*;
import java.awt.event.*;

class frametest{
    public static void main(String[] args) {
        Frame myframe=new Frame("测试窗口");
        Panel p1=new Panel();
        Panel p11 = new Panel();
        p1.setBackground(Color.red);
        p1.setLayout(new BorderLayout());
        myframe.add(p1);
        Label lbl =new Label("名称：");
        TextField txt=new TextField();
        p11.add(lbl);p11.add(txt);
        p1.add(p11,BorderLayout.NORTH);
        final TextArea ta =new TextArea(5,20);
        Font myfont=new Font("TimesNewRome",Font.BOLD,24);
        ta.setFont(myfont);
        p1.add(ta,BorderLayout.CENTER);
        Panel p12=new Panel();
        Button btn=new Button("确定");
        Button btn2 = new Button("取消");
        p12.add(btn);p12.add(btn2);
        p1.add(p12,BorderLayout.SOUTH);
        p1.add(new Button("东"),BorderLayout.EAST);
        p1.add(new Button("西"), BorderLayout.WEST);
        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ta.setText("");
            }
        });
        Panel p2 =new Panel();
        Checkbox b1=new Checkbox("老虎");
        Checkbox b2 =new Checkbox("狮子");
        Checkbox b3=new Checkbox("大象",true);
        p2.add(b1);p2.add(b2);p2.add(b3);
        p2.setBackground(Color.blue);
        myframe.add("South",p2);
        Choice moviestars=new Choice();
        moviestars.addItem("安东尼奥·班德拉斯");
        moviestars.addItem("莱昂纳多·迪卡普尼奥");
        moviestars.addItem("马拉多纳");
        moviestars.addItem("休·葛兰特");
        moviestars.addItem("茱莉亚·罗伯茨");
        p2.add(moviestars);
        myframe.add(new Button("myframe north"),BorderLayout.NORTH);
        myframe.add(new Button("myframe east"), BorderLayout.EAST);
        myframe.add(new Button("myframe west"), BorderLayout.WEST);
        myframe.setSize(360,450);
        myframe.setVisible(true);
        myframe.addWindowListener(new WindowCloser());
    }
}