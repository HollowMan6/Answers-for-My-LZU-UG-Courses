public class trycatch
{
  public static void main(String[] args)
  {
    int x=12;int y=0;
    try{
      x=x/y;
      System.out.println("You won't see this!");
    }catch(Exception e){
       System.out.println("Catch a Exception!");
       e.printStackTrace();  //打印调用堆栈
    }
  }
}