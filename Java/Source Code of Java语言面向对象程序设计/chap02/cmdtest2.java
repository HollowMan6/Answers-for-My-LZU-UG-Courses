class cmdtest2
{
   public static void main(String[] args)
   {
      double x,y,result=0;
      char op;
      x=Double.parseDouble(args[0]);
      y=Double.parseDouble(args[2]);
      op=args[1].charAt(0);

      switch(op)
      {
        case '+': result=x+y;break;
        case '-': result=x-y;break;
        case '*': result=x*y;break;
        case '/': result=x/y;break;
        default:System.out.println("Error op!");
      }
      System.out.println("result="+result);
   }
}