import java.io.*;
class test {
   public static void main(String[] args){
       File mp3file=null;
       Mp3Player mp3player=new Mp3Player();
       try {
           mp3file=new File(args[0]);
           if(mp3file.exists())
               mp3player.play(mp3file);
           else
               System.out.println("MP3文件不存在!");
       }catch(ArrayIndexOutOfBoundsException e1){
          e1.printStackTrace();
       }
   }
}