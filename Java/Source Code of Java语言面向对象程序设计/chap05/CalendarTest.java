import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarTest {
    
    public static void main(String[] args) {
        GregorianCalendar d=new GregorianCalendar();
        for(int i=0;i<=Calendar.DECEMBER;i++){
            d.set(Calendar.MONTH, i);
            System.out.println("\t\t\t  "+(d.get(Calendar.MONTH)+1)+"月");
            System.out.println("======================================================");
            showMonth(d);
            System.out.println("******************************************************");
        }
    }

    private static void showMonth(GregorianCalendar d) {
        //construct d as current date
        int today=d.get(Calendar.DAY_OF_MONTH);
        int month=d.get(Calendar.MONTH);
        //set d to start date of the month
        d.set(Calendar.DAY_OF_MONTH, 1);
        int weekday=d.get(Calendar.DAY_OF_WEEK);
        //get first day of week(Sunday )
        int firstDayOfWeek=d.getFirstDayOfWeek();
        //determine the required indentation for the first line
        int indenct=0;
        while(weekday!=firstDayOfWeek){
            indenct++;
            d.add(Calendar.DAY_OF_MONTH,-1);
            weekday=d.get(Calendar.DAY_OF_WEEK);
        }
        //print weekday name
        String[] weekdayNames=new DateFormatSymbols().getShortWeekdays();
        do{
            System.out.printf("%4s",weekdayNames[weekday]+"\t");
            d.add(Calendar.DAY_OF_WEEK,1);
            weekday=d.get(Calendar.DAY_OF_WEEK);
        }while(weekday!=firstDayOfWeek);
         System.out.println();
         //print space 
        for(int i=0;i<indenct;i++){
            System.out.print("\t");
        }
        d.set(Calendar.DAY_OF_MONTH, 1);
        do{
            //print day
            int day=d.get(Calendar.DAY_OF_MONTH);
            System.out.printf("%3d\t",day);
            //advance d to the next day
            d.add(Calendar.DAY_OF_MONTH, 1);
            weekday=d.get(Calendar.DAY_OF_WEEK);
            //start a new line at the start of the week
            if(weekday==firstDayOfWeek){
                System.out.println();
            }
            
        }while(d.get(Calendar.MONTH)==month);
        //the loop exists when d id day 1 of the next money
        
        //print final end of line if necessary
        if(weekday!=firstDayOfWeek){
            System.out.println();
        }
    }
    
}
