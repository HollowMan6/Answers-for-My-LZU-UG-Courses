public class Operatortest {
  public static void main(String[] args) {
    int a,b,c,d,e;
    double v,w,x,y,z;
    boolean  flag1=true,flag2=false;
    a=3+4;
    b=4*5;
    c=67-23;
    d=67/23;
    e=67%23;
    System.out.println("a="+a);
    System.out.println("b="+b);
    System.out.println("c="+c);
    System.out.println("d="+d);
    System.out.println("e="+e);
    v=3.0+4.0;
    w=4.0*5.0;
    x=67.5-23.4;
    y=65.0/23.0;
    z=67.0%23.0;
    System.out.println("**************************");
    System.out.println("v="+v);
    System.out.println("w="+w);
    System.out.println("x="+x);
    System.out.println("y="+y);
    System.out.println("z="+z);
    System.out.println("**************************");
    System.out.println("a>b="+(a>b));
    System.out.println("(v==7.0)="+(v==7.0));
    System.out.println("(c!=d)="+(c!=d));
    System.out.println("**************************");
    System.out.println("a>b & c>d="+(a>b&c>d));
    System.out.println("w>=v | flag1="+(w>=v | flag1));
    System.out.println("!flag2="+(!flag2));
    System.out.println("flag1 ^ flag2="+(flag1 ^ flag2));
    System.out.println("++a>2 || ++b>2="+(++a>2 || ++b>2));
    System.out.println("a="+a+", b="+b);  //大家注意a加1而b没有加1
    System.out.println("**************************");
    a=142;
    b=186;
    c=a&b; d=a|b; e=a^b;
    System.out.println("~"+Integer.toBinaryString(65)+"="+Integer.toBinaryString(~65).substring(24));    
    System.out.println("a="+Integer.toBinaryString(a));
    System.out.println("b="+Integer.toBinaryString(b));
    System.out.println("a&b="+Integer.toBinaryString(c));
    System.out.println("a|b="+Integer.toBinaryString(d));
    System.out.println("a^b="+Integer.toBinaryString(e));   //注意前面0省略
    byte i=(byte)142;
    System.out.println("i="+Integer.toBinaryString(i).substring(24));
    System.out.println(""+i+">>3="+(i>>3));
    c=Integer.parseInt("01100110",2);
    System.out.println(""+c+">>3="+(c>>3));

  }
}