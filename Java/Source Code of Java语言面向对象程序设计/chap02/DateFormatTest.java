import java.util.Date;
import java.text.SimpleDateFormat;
class DateFormatTest
{  
    public static void main(String args[ ])
    { 
      Date nowTime=new Date();
      System.out.println("现在的时间:"+nowTime);
      SimpleDateFormat matter1=new SimpleDateFormat(" 'time':yyyy-MM-dd");
      System.out.println("现在的时间:"+matter1.format(nowTime));
      SimpleDateFormat matter2=
      new SimpleDateFormat(" 'goodTime':yyyy年-MM月-Edd日-HH:时mm:分ss:秒 北京时间");
      System.out.println(matter2.format(nowTime));
      SimpleDateFormat matter3=
      new SimpleDateFormat("北京时间dd日HH时MMM ss秒mm分EE");
      System.out.println("现在的时间:"+matter3.format(nowTime));
      long time=-3600;
      Date date=new Date(time);
      System.out.println("-3600秒表示的日期时间是："+date);
    }
}
