import java.lang.*;
import java.io.*;
import java.util.*;

public class StudentBean implements Serializable
{
  ObjectInputStream fin;
  ObjectOutputStream fout;
  ArrayList<Student> alist;
  Student tmpstu;
  public StudentBean(){
    try 
    {
       alist=new ArrayList<Student>();
       fin=new ObjectInputStream(new BufferedInputStream(new FileInputStream("student.dat")));
    }
    catch(FileNotFoundException f)
    {
       try{
         File tmp=new File("student.dat");
         tmp.createNewFile();
         fin=new ObjectInputStream(new BufferedInputStream(new FileInputStream("student.dat")));
       }catch(IOException ee){}
    }
    catch(Exception e) 
    {
      System.err.println(e.getMessage());
    }
    load();
  }
public void load(){
    try{
      Student stu=(Student)fin.readObject(); 
      while(stu!=null)
      {
        alist.add(stu); 
        stu=(Student)fin.readObject(); 
      }
    }catch(Exception e){}
    if(alist.size()==0) alist.add(new Student());
}

public void close()
{
   try{
     if(fin!=null)  fin.close();
     if(fout!=null) fout.close();
     alist=null;
   }catch(Exception e){}
}
public int find(Student stu)
{
   for(int i=0;i<alist.size();i++) {
     tmpstu=alist.get(i);
     if(stu.getId().equals(tmpstu.getId())) return i;
   }
   return -1;
   
}
public void insertOrUpdate(Student stu) 
{
   int i=find(stu);
   if(i!=-1) alist.set(i,stu);
   else alist.add(stu);
}
public boolean delStudent(Student stu) 
{
   int i=find(stu);
   if(i!=-1){
      alist.remove(i); return  true;
   }else
      return false;  
}
public Student getStudent(String id)  
{
   for(int i=0;i<alist.size();i++) {
     tmpstu=alist.get(i);
     if(id.equals(tmpstu.getId())) return tmpstu;
   }    
    return null;  
}

public ArrayList<Student> getAll() 
{
    return alist;
}
public void saveAll(){
   try{
     if(fin!=null) fin.close();
     fout=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("Student.dat")));
     for(int i=0;i<alist.size();i++)   fout.writeObject(alist.get(i));
    }catch(Exception e){}  
}
}
