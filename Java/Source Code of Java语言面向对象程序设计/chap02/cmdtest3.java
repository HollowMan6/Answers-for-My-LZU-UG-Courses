class cmdtest3
{
  public static void main(String[] bb)
  {
    double sum=0.0;
    for(int i=0;i<bb.length;i++)
    {
       sum=sum+Double.parseDouble(bb[i]);
    }
    System.out.println("sum="+sum);
  }
}