import java.util.Scanner;
import java.io.*;
public class Myclass
{
   private String name;
   private int counts;
   private Student[] stus;

   public Myclass(){name="unknown name";counts=0;stus=null;}
   public Myclass(String n,int c)
   {
       name=n;  counts=c>=0?c:0;
       stus=new Student[c];
   }
   public String getName(){return name;}
   public void setName(String n){name=n;}
   
   public int getCounts(){return counts;}
   public void setCounts(int a){counts=a>=0?a:0;}
   
   public Student[] getStudents(){return stus;}
   public Student getStudent(int i){return stus[i];}
   public void setStudents(Student[] ss){stus=ss;}
   public void setStudent(Student s,int i){ stus[i]=s;}

   public void inputData()
   {
      for(int i=0;i<counts;i++) 
      {
        Scanner in=new Scanner(System.in);
        System.out.println("请输入学生姓名:");
        String n=in.nextLine();
        System.out.println("请输入学生性别:");
        char C=in.nextLine().charAt(0);
        System.out.println("请输入学生年龄:");
        int a=in.nextInt();
        stus[i]=new Student(n,C,a);
        stus[i].inputData();
      }
   }
   public void outputData()
   {
       double[] avg=new double[3];
       avg[0]=avg[1]=avg[2]=0;
       System.out.println("班级名："+name+"\t人数："+counts);
       System.out.println("==============================================");
       System.out.print("姓名");
       for(int i=0;i<stus[0].getCoursenames().length;i++) System.out.print("\t"+stus[0].getCoursename(i));
       System.out.println("\t总分");
       System.out.println("--------------------------------------------------");
       for(int i=0;i<counts;i++)
       {
          System.out.print(stus[i].getName());
          for(int j=0;j<stus[i].getCoursescores().length;j++)
          { 
             System.out.print("\t  "+stus[i].getCoursescore(j));
             avg[j]=avg[j]+stus[i].getCoursescore(j);
          }
          System.out.println("  "+stus[i].total());
       }
       System.out.println("=============================================");
       System.out.print("平   均：");
       for(int k=0;k<3;k++) System.out.print("\t  "+avg[k]/counts);
       System.out.println("\n===========================================");
       
   }
   public void outputToFile() throws Exception
   {
       FileOutputStream fout=new FileOutputStream(""+name+".dat");
       ObjectOutputStream o=new ObjectOutputStream(fout);
       o.writeObject(new Integer(counts));
       for(int i=0;i<counts;i++)
          o.writeObject(stus[i]);
       o.close();
       fout.close();
   }
   public void inputData(String fname) throws Exception
   {
       FileInputStream fin=new FileInputStream(""+fname+".dat");
       ObjectInputStream in=new ObjectInputStream(fin);
       counts=((Integer)in.readObject()).intValue();
       stus=new Student[counts];
       for(int i=0;i<counts;i++)
          stus[i]=(Student)in.readObject();
       in.close();
       fin.close();
   }
}




