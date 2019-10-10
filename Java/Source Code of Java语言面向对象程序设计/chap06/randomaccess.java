import java.io.*;
public class randomaccess
{
    public static void main(String args[])
    {
         int data_arr[]={65,66,56,23,27,1,43,65,4,99};
         try
         {
            RandomAccessFile randf=new RandomAccessFile("temp.dat","rw");
            for (int i=0;i<data_arr.length;i++)
                 randf.writeInt(data_arr[i]);
            randf.writeUTF("中国");
            for(int i=data_arr.length-1;i>=0;i=i-1)
            {
                  randf.seek(i*4);
                  System.out.println(randf.readInt());
            }
            randf.seek(40);
            System.out.println(randf.readUTF());
            randf.close();
         }catch (IOException e){
            System.out.println("File access error: "+e);
         }
    }
}
