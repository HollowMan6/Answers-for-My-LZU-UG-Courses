class Calculation {
  public void add( int a, int b) 
 {
   int c = a + b;
   System.out.println("两个整数相加得 "+ c);
  }
  public void add( float a, float b)
  {
    float c = a + b;
    System.out.println("两个浮点数相加得"+c);
  }
  public void add( String a, String b) 
  {
    String c = a + b;
    System.out.println("两个字符串相加得 "+ c);
  }
  public void add(fushu a,fushu b)
  {
       fushu f=new fushu(a.getReal()+b.getReal(),a.getImag()+b.getImag());
       System.out.println("两个复数相加得 "+f);
  }
} 
