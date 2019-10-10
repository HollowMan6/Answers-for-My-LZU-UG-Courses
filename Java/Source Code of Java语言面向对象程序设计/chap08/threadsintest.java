class threadsintest
{
  public static void main(String []args)
  {
    ThreadSin[] t=new ThreadSin[3];
    t[0]=new ThreadSin(0,30);
    t[1]=new ThreadSin(30,60);
    t[2]=new ThreadSin(60,90);
    for(int i=0;i<t.length;i++)  t[i].start();
  }
}