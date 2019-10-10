class chengfa{
  public static void main(String[] args)
  {
    System.out.print("*");
    for(int i=1;i<=9;i++) System.out.print("\t"+i);
    System.out.println();

    for(int i=1;i<=9;i++)
    {
      System.out.print(i);
      for(int j=1;j<=i;j++)
      {
         System.out.print("\t"+i*j);
      }
      System.out.println();
    }
  }
}