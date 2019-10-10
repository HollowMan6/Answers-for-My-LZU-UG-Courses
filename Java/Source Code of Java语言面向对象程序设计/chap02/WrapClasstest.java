class WrapClasstest
{
   public static void main(String[] args)
   {
      Boolean bln=new Boolean(true);
      Byte b=new Byte((byte)1);
      Character c=new Character('c');
      Short s=new Short((short)32);
      Integer i=new Integer(45);
      Long l=new Long(20L);
      Float f=new Float(1.0f);
      Double d=new Double(1.0);
      Integer ii=new Integer("22");
      Double dd=new Double("3.14D");
      int a=Integer.parseInt("123");
      float f1=Float.valueOf("22.3f");
      int myint=a+i-3;   //在JDK1.5后，允许基本类型和包装类型进行混合数学运算。
      System.out.println("That's OK!");
   }
}