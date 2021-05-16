import java.awt.*;
import javax.swing.*;
public class nihongdeng{
    public static void main(String[] args) {
        JFrame f=new JFrame("步步高升");
        f.setSize(200,300);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        NeonSign na=new NeonSign(10,Color.red);
        f.add(na,BorderLayout.CENTER);
        f.setVisible(true);
    }
}
class NeonSign extends Canvas{
    private int num,cNum,x,y,w,h,vGap,offset;
    class ComputerThread extends Thread{
        public void run(){
            while(true){
                repaint();
                try{sleep(1000);}
                catch(InterruptedException e){}
            }
        }
    }
    public NeonSign(int n,Color c){
        num=n;
        setForeground(c);
        new ComputerThread().start();
    }
    public void init(){
        Dimension size=getSize();
        cNum=0;
        h=size.height/num;
        vGap=h/5;
        w=size.width*9/10;
        offset=w/num;
        x=1;
        y=size.height-h;
    }
    public void update(Graphics g){
        g.fill3DRect(x, y, w, h-vGap, true);
        y=y-h;
        w=w-offset;
        cNum++;
        if(cNum>num){
            g.clearRect(0, 0, getWidth(), getHeight());
            init();
        }
    }
    public void paint(Graphics g){
        init();
    }
} 