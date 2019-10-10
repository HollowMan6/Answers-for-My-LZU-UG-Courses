import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class ImagePanel extends JPanel {
   private Image img;
   private String imageFiles[];
   private int current=0;
   private int counts=0;
   private File imageDir=null;
   Toolkit  mytool=Toolkit.getDefaultToolkit();  
   public ImagePanel(){
       imageDir=new File(".");
       changDir(imageDir);
   }
   public void changDir(File imageDir){
      current=0;
      counts=0;
      this.imageDir=imageDir;
      String [] filenames=imageDir.list();
      for(int i=0;i<filenames.length;i++){
         if(filenames[i].toLowerCase().endsWith(".jpg")||filenames[i].toLowerCase().endsWith(".gif")||filenames[i].toLowerCase().endsWith("jpeg") || 
              filenames[i].toLowerCase().endsWith("tiff") || filenames[i].toLowerCase().endsWith("png") || filenames[i].toLowerCase().endsWith("tif")) counts++;
         else  filenames[i]=null;
      }
      imageFiles=new String[counts];
      for(int i=0,j=0;i<filenames.length;i++){
          if(filenames[i]!=null) {
            imageFiles[j]=imageDir.getAbsolutePath()+"\\"+filenames[i];
            j++;
          } 
      }
      repaint();               
   }
   public void changDir(File imageDir,File selectedFile){
      this.imageDir=imageDir;
      counts=0;
      current=0;
      String[] filenames=imageDir.list();
      String selectedfilename=selectedFile.getName();
      for(int i=0;i<filenames.length;i++){         
         if(filenames[i].toLowerCase().endsWith(".jpg")||filenames[i].toLowerCase().endsWith(".gif")||filenames[i].toLowerCase().endsWith("jpeg") || 
             filenames[i].toLowerCase().endsWith("tiff") || filenames[i].toLowerCase().endsWith("png") || filenames[i].toLowerCase().endsWith("tif")){
                 counts++;
                 if(filenames[i].equals(selectedfilename))current=counts;
         }else{  
             filenames[i]=null;
         }
      }  
      System.out.println(filenames+","+filenames.length);
      System.out.println("counts="+counts);
      System.out.println(imageDir); 
      imageFiles=new  String[counts];
      for(int i=0,j=0;i<filenames.length;i++){
          if(filenames[i]!=null) {
              imageFiles[j]=imageDir.getAbsolutePath()+"\\"+filenames[i];
              j++;
          } 
      }         
      repaint();
   }
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      if(counts==0){
          g.drawString("该目录没有图像文件！",20,20);
      }else{
          img=mytool.getImage(imageFiles[current]);      
          g.drawImage(img,0,0,getSize().width,getSize().height,this);
      }
   }
   public int getCurrent(){return current;}  
   public void setCurrent(int a) {
      if(a>=0 && a<counts) current=a;
      else if(a<0) current=0;
      else current=counts-1;
   }
}
public class ImageViewer1 extends JFrame{
   private JButton selectdir,bt1,bt2;
   private ImagePanel mv;
   private ImageViewer1 outer=this;
   public ImageViewer1(){
      super("图像观查器 ver 1.1");
      bt1=new JButton("向前");
      bt2=new JButton("向后");
      selectdir=new JButton("选择目录");
      mv=new ImagePanel();
      Panel p1=new Panel();
      p1.add(selectdir);p1.add(bt1);p1.add(bt2);
      add(p1,BorderLayout.SOUTH);
      add(mv,BorderLayout.CENTER);

      Mylistener ls=new Mylistener();
      selectdir.addActionListener(ls);
      bt1.addActionListener(ls);
      bt2.addActionListener(ls);    
   }
   private class Mylistener implements ActionListener {
      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource()==bt1)
         {
            mv.setCurrent(mv.getCurrent()-1);
            mv.repaint();
         }else if(e.getSource()==bt2){
            mv.setCurrent(mv.getCurrent()+1);
            mv.repaint();
         }else{
              JFileChooser fileChooser=new JFileChooser();
              fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
              ImageFilter filter=new ImageFilter();
              fileChooser.setFileFilter(filter);
              int returnValue=fileChooser.showOpenDialog(outer);
              File selectedFile=null;
              if(returnValue==JFileChooser.APPROVE_OPTION){
                  selectedFile=fileChooser.getSelectedFile();
              }           
              if(selectedFile.isDirectory()){
                  mv.changDir(selectedFile);
              }else{
                  File parentdir=selectedFile.getParentFile();
                  mv.changDir(parentdir,selectedFile);
              }
          }
      } 
   }
   public static void main(String[] args)
   {
      ImageViewer1 myapp=new ImageViewer1();
      myapp.setSize(800,600);
      myapp.setVisible(true);
      myapp.addWindowListener(new WindowDestroyer());
   }
}