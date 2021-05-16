import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class Shiyan12_1_2{
    public Shiyan12_1_2(){
        MainFrame  frame=new MainFrame();
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize=frame.getSize();
        if(frameSize.height>screenSize.height){
            frameSize.height=screenSize.height;
        }
        if(frameSize.width>screenSize.width){
            frameSize.width=screenSize.width;
        }
        frame.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new Shiyan12_1_2();
    }
}
class MainFrame extends  JFrame{
    private JPanel contentPane;
    private GridLayout gridLayout1=new GridLayout(4,1);
    private JProgressBar jProgressBar1=new JProgressBar();
    private JProgressBar jProgressBar2=new JProgressBar();
    private JProgressBar jProgressBar3=new JProgressBar();
    private JButton jButton1=new JButton();
    private ProgressThread pThread1=null;
    private ProgressThread pThread2=null;
    private ProgressThread pThread3=null;
    public MainFrame(){
        setIconImage(Toolkit.getDefaultToolkit().createImage(MainFrame.class.getResource("icon.ico")));
        contentPane=(JPanel)this.getContentPane();
        contentPane.setLayout(gridLayout1);
        this.setSize(new Dimension(800,200));
        this.setTitle("线程优先级演示");
        jProgressBar1.setOrientation(JProgressBar.HORIZONTAL);
        jProgressBar1.setFont(new java.awt.Font("Dialog",0,14));
        jProgressBar1.setStringPainted(true);
        jProgressBar2.setOrientation(JProgressBar.HORIZONTAL);
        jProgressBar2.setFont(new java.awt.Font("Dialog",0,14));
        jProgressBar2.setStringPainted(true);
        jProgressBar3.setOrientation(JProgressBar.HORIZONTAL);
        jProgressBar3.setFont(new java.awt.Font("Dialog",0,14));
        jProgressBar3.setStringPainted(true);
        jButton1.setFont(new java.awt.Font("Dialog",0,14));
        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e ){
                jButton1_actionPerformed(e);
            }
        });
        contentPane.add(jProgressBar1);
        contentPane.add(jProgressBar2);
        contentPane.add(jProgressBar3);
        contentPane.add(jButton1);
        setDefaultCloseOperation(3);
    }
    void jButton1_actionPerformed(ActionEvent e){
        if(((JButton)e.getSource()).getText().equals("Start")){
            this.jButton1.setText("Stop");
            pThread1=new ProgressThread(this.jProgressBar1,Thread.MAX_PRIORITY);
            pThread1.start();
            pThread2=new ProgressThread(this.jProgressBar2,Thread.NORM_PRIORITY);
            pThread2.start();
            pThread3=new ProgressThread(this.jProgressBar3,Thread.MIN_PRIORITY);
            pThread3.start();
        }else{
            this.jButton1.setText("Start");
            this.pThread1.stopped=true;
        }
    }
}
class ProgressThread extends Thread{
    JProgressBar pbar;
    static boolean stopped;
    int min=0;
    int max =10000;
    public ProgressThread(JProgressBar pbar,int priority){
        this.pbar=pbar;
        this.pbar.setMinimum(min);
        this.pbar.setMaximum(max);
        this.stopped=false;
        this.setPriority(priority);
    }
    public void run(){
        for(int i=min;i<=max;i++){
            if(stopped) break;
            else{
                this.pbar.setValue(i);
                this.pbar.setString(String.valueOf(i));
                try{
                    Thread.sleep(1);
                }catch(Exception err){
                    err.printStackTrace();
                }
            }
        }
    }
}