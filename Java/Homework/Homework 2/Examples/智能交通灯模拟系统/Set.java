package java智能交通灯模拟系统;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
public class Set extends JDialog implements ActionListener{  
    JComboBox jcb,jcb2;  
    JPanel jp,jp2,jp3;  
    JLabel jl,jl2;  
    JButton jb,jb2;  
    public Set(Frame owner, String title, boolean modal){  
        super(owner,title,modal);  
        String speed[]={"5","6","7","8","9","10","11","12","13","14","15"};  
        jcb=new JComboBox(speed);  
        String CarSpeed=Car.speed+"";  
        jcb.setSelectedItem(CarSpeed);  
        jp=new JPanel();  
        jl=new JLabel("速度为：");  
        jp.add(jl);  
        jp.add(jcb);  
          
        jp2=new JPanel();  
        jb=new JButton("确定");  
        jb.addActionListener(this);  
        jb2=new JButton("取消");  
        jb2.addActionListener(this);  
        jp2.add(jb);  
        jp2.add(jb2);  
          
        jp3=new JPanel();  
        jl2=new JLabel("车辆数:");  
        String carNum[]={"20","25","30","35","40","45","50","55","60",  
                "65","70"};  
        jcb2=new JComboBox(carNum);  
        String num=HuPanel.carNum+"";  
        jcb2.setSelectedItem(num);  
        jp3.add(jl2);  
        jp3.add(jcb2);  
        this.add(jp,"North");  
        this.add(jp3);  
        this.add(jp2,"South");  
        this.setSize(200, 200);  
        this.setVisible(true);  
    }  
    @Override  
    public void actionPerformed(ActionEvent e) {  
        // TODO Auto-generated method stub  
        if(e.getSource()==jb){  
              
            Car.speed=Integer.parseInt((String) jcb.getSelectedItem());  
            HuPanel.carNum=Integer.parseInt((String) jcb2.getSelectedItem());  
              
            this.dispose();  
        }else{  
            this.dispose();  
        }  
    }  
}  

