import java.util.*;
public class ListOper
{
    public static void main(String[] args)
    {
        if(args.length==0)
        {
             System.out.println("你必须提供列表元素，作为命令行参数。请重试！");
             System.exit(0);
        }
        System.out.println();
        List l=new ArrayList();
        for(int i=0;i<args.length;i++)
        {
            l.add(args[i]);
        }
        Collections.reverse(l);
         System.out.println("逆序的列表如下:");
         System.out.println(l);
         Collections.sort(l);
         System.out.println("排序的列表如下：");
         System.out.println(l);
         int index=Collections.binarySearch(l,"c");
         System.out.println("元素 “c”的位置为："+index);
         Collections.fill(l,"一");
System.out.println("用字“一”填充后的列表如下：");
         System.out.println(l);
         List ll=new ArrayList();
         ll.add("第一");
         ll.add("第二");
         ll.add("第三");
         Collections.copy(l,ll);
         System.out.println("用ll的元素替代后的列表如下：");
         System.out.println(l);
    }
}

         
