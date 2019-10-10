class DateTest
{
   public static void main(String[] args)
   {
     java.util.Date mydate=new java.util.Date();
     int year=mydate.getYear();
     int month=mydate.getMonth();
     int date=mydate.getDate();
     int day=mydate.getDay();
     System.out.println("今天是："+(year+1900)+"-"+(month+1)+"-"+date+" 星期 "+day);
     System.out.println("time: "+mydate.getHours()+":"+mydate.getMinutes()+":"+mydate.getSeconds());
   }
}