class innerouter{
  private class inner{
    private String name;
    private int age;
    public int step;
    inner(String s,int a)
    {
      name=s;
      age=a;
      step=0;
    }
    public void run()
    {
      step++;
    }
  }
  public static void main (String args[])
  {
    innerouter a=new innerouter();
    innerouter.inner d=a.new inner("Tom",3);
    d.step=25;
    d.run();
    System.out.println(d.step);
   }
}