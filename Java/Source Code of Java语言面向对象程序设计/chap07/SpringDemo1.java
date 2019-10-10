import java.awt.Container;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JTextField;  
import javax.swing.Spring;  
import javax.swing.SpringLayout;  
  
/** 
 * 演示SpringLayout布局管理器的使用。例子，演示有三组由左为表签、右为文本框）的组件<br/> 
 * 将随着窗口宽度变化 而相应变化。 
 * 特别说明：当用putConstraint函数设定组件，且对宽与高都不要求随窗口变化时，则会<br/> 
 * 遇着一些问题。怀疑是Bug。如果要让宽或高 要随窗口变化而变化时，最好用用容器的<br/> 
 * add方法.把组件及对该组件的"Spring.constraint"约束添加进去。<br/> 
 * 其约束的设定，是从与相邻组件的四个方向进行设置。另外如果水平有两个及两个以的组件<br> 
 * 的宽度都要随父容器变化而时，每个文本框的右边约束建议按父容器的宽度的百分比进行设定。 
 *  
 * @author cloud 
 */  
public class SpringDemo1 {  
    public static void main(String args[]) {  
        JFrame frame = new JFrame("SpringLayout");  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        Container contentPane = frame.getContentPane();  
        SpringLayout layout = new SpringLayout();  
        contentPane.setLayout(layout);  
  
        JLabel  lbl1 = new JLabel("Left1");  
        JTextField txt3 = new JTextField("text3");  
          
        JLabel  lbl4 = new JLabel("Left4");  
        JTextField txt4 = new JTextField("text4");  
          
        JLabel  lbl5 = new JLabel("Left5");  
        JTextField txt5 = new JTextField("text5");  
  
    
        contentPane.add(lbl3);  
        contentPane.add(lbl4);  
        contentPane.add(lbl5);  
        contentPane.add(txt3);  
        contentPane.add(txt4);  
        contentPane.add(txt5);  
          
        //得到父容器当前最右边（东方）的宽度，并除以2. 目的让两组各占一半窗口宽度。  
        Spring rightC=layout.getConstraint (SpringLayout.EAST, contentPane);  
        Spring rightC1=Spring.scale(rightC, 0.3f);  
        Spring rightC2=Spring.scale(rightC, 0.6f);  
          
        // 让lbl3标签上边与父容器的上边相距20。即第一个标签 上边距为20  
        layout.putConstraint(SpringLayout.NORTH, lbl3, 20,   
                SpringLayout.NORTH, contentPane);  
        //让lbl3标签的左边与父容器的左边相距10。即第一个标签左边间隔为10  
        layout.putConstraint(SpringLayout.WEST, lbl3, 10,   
                SpringLayout.WEST, contentPane);   
          
        //获得一个约束对象，以便对组件的四个方向的间隔进行设置。这个约束设定后好  
        //将由父容器的add方法，把该约束与要束的组件一起添加进去，从而完成组件间隔设置  
        SpringLayout.Constraints constraint = new SpringLayout.Constraints();    
        //设定约束上边间距为20  
        constraint.setConstraint(SpringLayout.NORTH, Spring.constant(20));          
        //得lbl3标签右边的约束，并增加间隔10  
        Spring pad=Spring.sum(  
                layout.getConstraint(SpringLayout.EAST, lbl3),   
                Spring.constant(10)  
                );            
        //设左边的约束为lbl3约束，并间隔10.即设与lbl3标签的水平间隔为10  
        constraint.setConstraint(SpringLayout.WEST, pad);       
        //设右边的约束为30%。  
        constraint.setConstraint(SpringLayout.EAST, rightC1);   
        //把txt3按前面设好的约束，添加到父容器中。  
        contentPane.add(txt3, constraint);  
          
        //让lbl4标签上边与父容器的上边相距20。即第二个标签 上边距为20  
        layout.putConstraint(SpringLayout.NORTH, lbl4, 20,   
                SpringLayout.NORTH, contentPane);    
        //让lbl4标签左边与txt3的左边相距20。即第一个标签左边与第一组间隔30  
        layout.putConstraint(SpringLayout.WEST, lbl4, 30,  
                SpringLayout.EAST, txt3);  
          
        //获得一个约束对象，以便对组件的四个方向的间隔进行设置。这个约束设定后好  
        //将由父容器的add方法，把该约束与要束的组件一起添加进去，从而完成组件间隔设置  
        constraint = new SpringLayout.Constraints();    
        //设定约束上边间距为20  
        constraint.setConstraint(SpringLayout.NORTH, Spring.constant(20));          
        //得lbl4标签右边的约束，并增加间隔10  
        pad=Spring.sum(  
                layout.getConstraint(SpringLayout.EAST, lbl4),   
                Spring.constant(10)  
                );            
        //设左边的约束为lbl4约束，并间隔10.即设与lbl4标签的水平间隔为10  
        constraint.setConstraint(SpringLayout.WEST, pad);       
        //设右边的约束为父容器右边的60%，并增加10.  
        constraint.setConstraint(SpringLayout.EAST,  
                Spring.sum( rightC2,Spring.constant(10))  
                );   
        //把txt4按前面设好的约束，添加到父容器中。  
        contentPane.add(txt4, constraint);  
          
        //设lbl5的上边距为20  
        layout.putConstraint(SpringLayout.NORTH, lbl5, 20,   
                SpringLayout.NORTH, contentPane);    
        //设lbl5的左边与txt4的右边相隔30,即lbl5与txt4的水平间隔为30  
        layout.putConstraint(SpringLayout.WEST, lbl5, 30,  
                SpringLayout.EAST, txt4);  
     
        //获得一个约束对象，以便对组件的四个方向的间隔进行设置。这个约束设定后好  
        //将由父容器的add方法，把该约束与要束的组件一起添加进去，从而完成组件间隔设置  
        constraint = new SpringLayout.Constraints();    
        //设定约束上边间距为20  
        constraint.setConstraint(SpringLayout.NORTH, Spring.constant(20));          
        //得lbl5标签右边的约束，并增加间隔10  
        pad=Spring.sum(  
                layout.getConstraint(SpringLayout.EAST, lbl5),   
                Spring.constant(10)  
                );            
        //设左边的约束为lbl5约束，并间隔10.即设与lbl5标签的水平间隔为10  
        constraint.setConstraint(SpringLayout.WEST, pad);       
        //设右边的约束为容器的右边约束并减去20，即设成与父容器右边间隔为20.  
        constraint.setConstraint(SpringLayout.EAST,  
                Spring.sum(rightC, Spring.constant(-10))  
                );   
        //把txt5按前面设好的约束，添加到父容器中。  
        contentPane.add(txt5, constraint);  
          
  
        frame.setSize(300,100);  
        frame.setVisible(true);  
    }  
          
  
}  
