class Array1
{
   public static void main(String[] args)
   {
      int[] a;
      String[] b;
      a=new int[10];
      b=new String[3];
      for(int i=0;i<10;i++)
      {
         a[i]=(int)(100*Math.random());
      }
      b[0]=new String(" China!");
      b[1]="Good morning!";
      b[2]=b[0]+b[1];
      for(int i=0;i<10;i++)
      {
         System.out.println("a["+i+"]="+a[i]);
      }
      System.out.println(b[0]+"\n"+b[1]+"\n"+b[2]);
      System.out.println("a中元素个数："+a.length);
      System.out.println("b中元素个数："+b.length);
   }
}