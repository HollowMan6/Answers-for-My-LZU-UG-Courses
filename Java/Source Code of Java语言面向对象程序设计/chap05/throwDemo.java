class throwDemo
{
    public static void main(String[] args)
    {
         try   {
            if(args.length!=1) 
                throw new Exception("参数过少或过多！");
            else  
                System.out.println(args[0]);
        }
        catch(Exception e){
              System.out.println("******参数个数不对！");
              System.out.println(e.getMessage());
              e.printStackTrace();
        }
    }
}