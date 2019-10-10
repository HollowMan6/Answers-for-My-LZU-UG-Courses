import java.util.*;
import java.util.Calendar;

class Month{  //设计一个月份类
    private String[] date=new String[42];

    Month(){  //构造函数
	for (int i=0; i<42; i++)
	    date[i]="    ";
    }

    public void setMonth(int year,int m){  //set函数，运算出该月的日历
	Calendar cal=Calendar.getInstance();
	cal.set(year,m,1);
	int first=cal.get(Calendar.DAY_OF_WEEK);
	int total=cal.getActualMaximum(Calendar.DATE);

        for (int i=first,j=1; j<=total; i++,j++){
	    if (j<10) this.date[i-1]="   "+j;
	    else this.date[i-1]="  "+j;
	    if (i % 7==0){
		if (m % 2==0){date[i-1]+="        ";}
		else date[i-1]+="\n";
	    }
	}

	for (int j=total+first; j<=42; j++){
	    if (j % 7==0){
		if (m % 2==0){date[j-1]+="        ";}
	    	else {date[j-1]+="\n";}
	    }
	}
    }

    public String getDate(int i){
	return this.date[i];
    }
}

public class Cal{
    private Month[] month=new Month[12];

    Cal(){
  	for (int i=0; i<12; i++)
            {month[i]=new Month();}
    }

    public void setYear(int year){
  	for (int i=0; i<12; i++)
            {this.month[i].setMonth(year,i);}
    }

    public String toString(){  //toString 函数
	String calendar="";
	for (int i=1; i<=6; i++){
	    switch(i){
		case 1: calendar+="\n         January                             February\n"; break;
		case 2: calendar+="\n          March                               April\n"; break;
		case 3: calendar+="\n           May                                June\n"; break;
		case 4: calendar+="\n          July                               August\n"; break;
		case 5: calendar+="\n        September                            October\n"; break;
		case 6: calendar+="\n        November                             December\n"; break;
	    }
	    calendar+="  Sun Mon Tue Wed Thu Fri Sat         Sun Mon Tue Wed Thu Fri Sat\n";
	    int flag=0;
	    for (int j=0; j<42; j++){
	        calendar+=month[2*i-2].getDate(j);
		if ((j+1)%7==0){
		    for (int k=flag; k<42; k++){
		    	calendar+=month[2*i-1].getDate(k);
			if ((k+1)%7==0){ 
			    flag=k+1; 
			    break;
			}
		    }
		}
	    }
	}
	return calendar;
    }

    public static void main(String[] args){
    	int year;
	Cal calendar=new Cal();
    	Scanner keyin=new Scanner(System.in);
	System.out.println("Please enter the YEAR:");
	year=keyin.nextInt();
	calendar.setYear(year);
        System.out.println("Calendar:\n"+calendar);
    }
}
