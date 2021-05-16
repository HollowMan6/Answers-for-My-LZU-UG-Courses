import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class DownMultiThread implements Runnable{
    private String sUrl="";
    private File desFile;
    private long startPos;
    private long endPos;
    public DownMultiThread(String sUrl,File desFile,long startPos,long endPos){
        this.sUrl=sUrl;this.desFile=desFile;
        this.startPos=startPos;this.endPos=endPos;
    }
    public void run(){
        try{
            URL url=new URL(sUrl);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestProperty("User-Agent", "NetFox");
            String rangeProperty="bytes = "+startPos+"-";
            if(endPos>0){
                rangeProperty="bytes = "+startPos+"-"+endPos;
            }
            conn.setRequestProperty("RANGE", rangeProperty);
            RandomAccessFile out=new RandomAccessFile(desFile, "rw");
            out.seek(startPos);
            InputStream in=conn.getInputStream();
            BufferedInputStream bin=new BufferedInputStream(in);
            byte[] buff=new byte[2048];
            int len=-1;
            len=bin.read(buff);
            while(len!=-1){
                out.write(buff,0,len);
                len=bin.read(buff);
            }
            out.close();
            bin.close();
            conn.disconnect();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
public class MultiThreadDownFrame extends JFrame{
    private JTextField tf_address,tf_save;
    public static void main(String[] args) {
        MultiThreadDownFrame frame=new MultiThreadDownFrame();
        frame.setBounds(100,100,400,220);
        frame.setVisible(true);
    }
    public MultiThreadDownFrame(){
        setTitle("网络资源的多线程下载");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container con=getContentPane();
        con.setLayout(new GridLayout(6,1));
        con.add(new JLabel("准备下载的网络资源地址"));
        tf_address=new JTextField();
        tf_address.setText("https://hollowman6.github.io/FreDuino/Instruction.pdf");
        con.add(tf_address);
        con.add(new JLabel("输入要保存的文件名："));
        tf_save=new JTextField();
        tf_save.setText("Instruction.pdf");
        con.add(tf_save);
        JButton button=new JButton("开 始 下 载");
        con.add(button);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(final ActionEvent e){
                try{
                    String address =tf_address.getText();
                    if(address==null||address.trim().equals(""))
                        address="https://hollowman6.github.io/FreDuino/Instruction.pdf";
                        String file=tf_save.getText();
                        if(file==null||file.trim().equals(""))
                            file="./download.dat";
                            download(address,file,2);
                }catch(Exception e1){
                    e1.printStackTrace();
                }
            }
        });
        JButton button2=new JButton("退 出 程 序");
        con.add(button2);
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(final ActionEvent e){
                System.exit(0);
            }
        });
    }
    public void download(String url,String dest,int ThreadNum) throws Exception{
        URL downURL=new URL(url);
        HttpURLConnection conn=(HttpURLConnection)downURL.openConnection();
        long fileLength=-1;
        int stateFlagCode=conn.getResponseCode();
        if(stateFlagCode==200){
            fileLength=conn.getContentLength();
            conn.disconnect();
        }
        if(fileLength>0){
            long byteCounts=fileLength/ThreadNum+1;
            File file=new File(dest);
            int i=0;
            while(i<ThreadNum){
                long startPosition=byteCounts*i;
                long endPosition=byteCounts*(i+1);
                if(i==ThreadNum-1){
                    DownMultiThread fileThread=new DownMultiThread(url, file, startPosition, 0);
                    new Thread(fileThread).start();
                }else{
                    DownMultiThread fileThread=new DownMultiThread(url, file, startPosition,endPosition);
                    new Thread(fileThread).start();
                }
                i++;
            }
            JOptionPane.showMessageDialog(null, "完成网络资源的下载");
        }
    }
}