import javax.swing.*;
import java.awt.*;
public class RunnableTest extends JFrame implements Runnable 
{
    JLabel prompt1 = new JLabel("第一个子线程");
    JLabel prompt2 = new JLabel("第二个子线程");     
    JTextField threadFirst = new JTextField(14);           
    JTextField threadSecond = new JTextField(14);    
    Thread thread1,thread2; //两个Thread的线程对象
    int count1=0,count2=0; //两个计数器
    public RunnableTest( )
    {
        super("线程测试");
        setLayout(new FlowLayout());
        add(prompt1);
        add(threadFirst);
        add(prompt2);
        add(threadSecond);
    }
    public void start( )
    {      //创建线程对象，具有当前类的run( )方法，并用字符串指定线程对象的名字 
        thread1 = new Thread(this,"FirstThread");
        thread2 = new Thread(this,"SecondThread");
        thread1.start( );//启动线程对象，进入就绪状态
        thread2.start( );
    }
    public void run( ) //实现Runnable接口的run( )方法，在该线程启动时自动执行
    {
        String currentRunning;
        while(true)    //无限循环
        {
           try
           {              //使当前活动线程休眠0到3秒
               Thread.sleep((int)(Math.random( ) * 3000));
           }
           catch(InterruptedException e){}
           currentRunning = Thread.currentThread( ).getName( );
           if(currentRunning.equals("FirstThread"))
           {    count1++;
                threadFirst.setText("线程1第"+count1+"次被调度");
           }
           else if(currentRunning.equals("SecondThread"))
           {
                count2++;
                threadSecond.setText("线程2第"+count2+"次被调度");
           }
        }   //while循环结束
    }         //run( )方法结束
    public static void main(String[] args) {
        RunnableTest myapp=new RunnableTest();
        myapp.setSize(300,100);
        myapp.setVisible(true);
        myapp.start();
        myapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
