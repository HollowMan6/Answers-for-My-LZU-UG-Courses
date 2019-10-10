import java.util.*;
class vector
{
public static void main(String []args)
{
   Vector MyVector=new Vector(100);
   for (int i=0;i<10;i++)
   {
      MyVector.addElement("welcome");
      MyVector.addElement("to");
      MyVector.addElement("beijing");
   }
   while (MyVector.removeElement("to"));
   System.out.println(MyVector);
}
}