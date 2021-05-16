import java.io.*;
public class Shiyan8_3_2{
    public static void main(String[] args) throws Exception{
        FileReader fin=new FileReader("phone.txt");
        BufferedReader bin=new BufferedReader(fin);
        String s=bin.readLine();
        System.out.print("\n以;作为分隔符来提取字符串\n");
        String[] sa=s.split(";");
        for(String ss:sa)System.out.print("=="+ss);
        System.out.print("\n以正则表达式[,;]作为分隔符来提取字符串\n");
        sa=s.split("[,;]");
        for (String ss : sa)System.out.print("==" + ss);
        System.out.print("\n\n用正则表达式[\\D*&&[^- ]]提取出所有的电话号码\n");
        sa=s.split("[\\D*&&[^- ]]");
        for (String ss : sa)System.out.print(ss+"#");
        System.out.println("\n\n将兰州的电话号码改为0931-xxxxxxx");
        String s1=s.replaceAll("0931[ -]\\d{7}","0931-x{7}");
        String s2 = s.replaceAll("0931[ -]\\d{7}", "0931-xxxxxxx");
        System.out.println("s="+s);
        System.out.println("s1="+s1);
        System.out.println("s2="+s2+"\n\n");
        String s3="a99+0X55f-55/0xAF+33";
        String[] sd=s3.split("0[Xx]([0-9a-fA-F]) + ");
        for(String ss:sd)System.out.print("=="+ss);
    }
}