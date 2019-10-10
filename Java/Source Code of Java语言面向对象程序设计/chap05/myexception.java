class myexception extends Exception
{
     String mymsg="我自己定义的异常!";
     double mynum=2.0;
     myexception(){super("首字母不能为A!");}
     myexception(String msg){super(msg);}
     public void displayme(){System.out.println(mymsg);}
     public double mymethod(){return Math.sqrt(mynum);}
}

class exceptiontest
{
      public static void main(String[] args)
      {
         try
         {
           if(args[0].charAt(0)=='A') 
           {
                myexception e=new myexception();
                System.out.println("kkkk:"+e.mymethod());
                e.displayme();
                System.out.println("**********in try*********");
                throw e;
           }
           else if(args[0].charAt(0)=='B')
           {
                throw new myexception("第一个字符不应是B!");
            }else{System.out.println(args[0]);}

          }
          catch(myexception aaaa)
          {
                System.out.println(aaaa.getMessage());
                aaaa.displayme();
                System.out.println(""+aaaa.mymethod());
          }
          catch(ArrayIndexOutOfBoundsException e)
          {
              System.out.println("命令行参数个数错！");
          }
      }
}