import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
public class urldownload extends Frame implements ActionListener
{
      TextField  tf; TextArea ta; Button ok,save;Label  lb;
      TextField  filename;
       URL  url;
       BufferedReader  bin; 
       urldownload()
      {
            super("This is a net program!");
            lb=new Label("请输入网址：");
            tf=new TextField(30);
            ok=new Button("确定");
            save=new Button("保存为:");
            filename=new TextField(12);
            ta=new TextArea(40,60);
            Panel   p=new Panel();
            p.add(lb);p.add(tf);p.add(ok);p.add(save);p.add(filename);
            add(p,"North"); add(ta,"Center");
            ok.addActionListener(this);
            save.addActionListener(this);
            Font ft=new Font("宋体",Font.BOLD,24);
            lb.setFont(ft);
            ok.setFont(ft);
            save.setFont(ft);
            filename.setFont(ft);
            tf.setFont(ft);
            ta.setFont(ft);
      }
      public void actionPerformed(ActionEvent e)
      {
             String msg="";
              if(e.getSource()==ok)
             {
                      try{
                     url=new URL(tf.getText());
                     }catch(MalformedURLException e1){System.out.println("e1:");}
                     try{
                       bin=new BufferedReader(new InputStreamReader(url.openStream()));
                      while((msg=bin.readLine())!=null) 
                      {
                             ta.append("\n"+msg);
                       }       
                     }catch(IOException ee){System.out.println("ee");}     
             }
             if(e.getSource()==save)
             {
                 try{
                  PrintWriter out=new PrintWriter(new FileWriter(filename.getText().trim()));
                  out.print(ta.getText());
                  out.flush();
                  out.close();
                }catch(IOException e3){}
             }
      }
      public static void main(String args[])
      {
              urldownload myclient=new urldownload();
              myclient.setSize(800,600);
              myclient.setVisible(true);
              myclient.addWindowListener(new WindowDestroyer());
       }
}