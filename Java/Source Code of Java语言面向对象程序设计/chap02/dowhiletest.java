class dowhiletest
{
  public static void main(String[] args)
  {
    int i=1;
    int sum=0;
    do 
    {
       sum=sum+i*i;
       i++;
    }while(i<=100);
    System.out.println("sum="+sum);
  }
}
