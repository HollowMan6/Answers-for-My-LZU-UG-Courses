import java.awt.*;
import javax.swing.JFrame;
public class SineWaveAnimator extends JFrame implements Runnable{
    private int frame=0;
    public SineWaveAnimator(){
        setTitle("Sine Wave Animator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,200);
        setVisible(true);
    }
    public static void main(String[] args){
        SineWaveAnimator app=new SineWaveAnimator();
        Thread animator=new Thread(app);
        animator.setDaemon(true);
        animator.start();
    }
    public void run(){
        while(true){
            repaint();
            try {
                Thread.sleep(100);
            }catch(InterruptedException e){
            }
            frame++;
        }
    }
    @Override
    public void paint(Graphics g){
        Rectangle d=getBounds();
        g.clearRect(0,0,d.width,d.height);
        int h=d.height/2;
        for(int x=0;x<d.width;x++){
            int y1=(int) ((1.0+Math.sin((x-frame)*0.09))*h);
            int y2=(int) ((1.0+Math.sin((x+frame)*0.01))*h);
            g.drawLine(x,y1,x,y2);
        }
    }
}