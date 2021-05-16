import java.text.*;
import java.util.*;
class CalendarTest{
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String year = String.valueOf(calendar.get(Calendar.YEAR)),
               month = String.valueOf(calendar.get(Calendar.MONTH)+1),
               day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)),
               weekday = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK)-1);
        int hour = calendar.get(Calendar.HOUR_OF_DAY),
            minute = calendar.get(Calendar.MINUTE),
            second = calendar.get(Calendar.SECOND);
        System.out.println("现在的时间是：");
        System.out.println(year+"年"+month+"月"+day+"日"+"星期"+weekday);
        System.out.println(hour+"时"+minute+"分"+second+"秒");
        calendar.set(2008, 3, 8);
        long time2008 = calendar.getTimeInMillis();
        calendar.set(2009, 3, 8);
        long time2009 = calendar.getTimeInMillis();
        long days = (time2009-time2008)/(1000*60*60*24);
        System.out.println("2009年3月8日和2008年3月8日相隔"+days+"天");
        Date date = new Date();
        SimpleDateFormat dateFm1 = new SimpleDateFormat("EEEE-MMMM-dd-yyyy");
        System.out.println(dateFm1.format(date));
        DateFormat dateFm2 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
        System.out.println(dateFm2.format(date));
    }
}