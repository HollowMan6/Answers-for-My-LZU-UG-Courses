import java.util.*;
class Student 
{
   String name;
   int score;
   Student(String name,int score)
   {
      this.name=name;
      this.score=score;
   }
      }
public class LinkedListTest
{
    public  static void main(String args[])
    { 
      LinkedList<Student> mylist=new LinkedList<Student>();
      mylist.add(new Student("张小一",78)); 
      mylist.add(new Student("王小二",98));
      mylist.add(new Student("李大山",67));
      int number=mylist.size();
      System.out.println("现在链表中有"+number+"个节点:");
      for(int i=0;i<number;i++)
        {
           Student temp=mylist.get(i);
           System.out.printf("第"+i+"节点中的数据,学生:%s,分数:%d\n",temp.name,temp.score);
        }
      Student removeSTU=mylist.remove(1);
      System.out.printf("被删除的节点中的数据是:%s,%d\n",removeSTU.name,removeSTU.score);
      Student replaceSTU=mylist.set(1,new Student("赵钩林",68));
      System.out.printf("被替换的节点中的数据是:%s,%d\n",replaceSTU.name,replaceSTU.score);
      number=mylist.size();
      System.out.println("现在链表中有"+number+"个节点:");
      for(int i=0;i<number;i++)
        {
           Student temp=mylist.get(i);
           System.out.printf("第"+i+"节点中的数据,学生:%s,分数:%d\n",temp.name,temp.score);
        }
       if(mylist.contains("Open"))
        {
           System.out.println("链表包含字符串数据");
        }
      else
        {
           System.out.println("链表没有节点含有字符串数据");
        }  
    }
}
