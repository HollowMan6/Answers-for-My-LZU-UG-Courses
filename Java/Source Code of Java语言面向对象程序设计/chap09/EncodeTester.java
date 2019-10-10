import java.io.*;
import java.nio.charset.*;
public class EncodeTester {
  private static void readBuff(byte[] buff)throws IOException{
    ByteArrayInputStream in=new ByteArrayInputStream(buff);
    int data;
    while((data=in.read())!=-1)
       System.out.print(data+" ");
    System.out.println();
in.close();
  }

  public static void main(String agrs[])throws IOException{
    System.out.println("在内存中采用Unicode字符编码：");
    char c='好';
    int lowBit=c & 0xFF; //获得二进制的低8位
    int highBit=(c & 0xFF00) >>8; //获得二进制的高8位
    System.out.println (highBit+" "+lowBit);
    
    String s="好";
    System.out.println("采用本地操作系统的默认字符编码：");
    readBuff(s.getBytes());

    System.out.println("采用GBK字符编码：");
    readBuff(s.getBytes("GBK"));

    System.out.println("采用标准UTF-8字符编码：");
    readBuff(s.getBytes("UTF-8"));
    Charset cs=Charset.defaultCharset();
    System.out.println(cs);
  }
}


