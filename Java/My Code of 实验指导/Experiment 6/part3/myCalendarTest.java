import java.util.*;
import java.text.DateFormatSymbols;

class MyMonth {
	private int month;
	private int start_of_week;
	private int days_in_month;
	public static String[] weekdayNames = new DateFormatSymbols().getShortWeekdays();
	private String[][] data = new String[7][8];

	public MyMonth(int m,int s,int d) {
		month=m;
		days_in_month=d;
		start_of_week=s;
		for(int j=1;j<8;j++) {
			data[0][j]=new String(MyMonth.weekdayNames[j]);
		}
		int days =1, day_of_week = start_of_week,r=1;
		do {
			data[r][day_of_week]=String.valueOf(days);
			days++;
			day_of_week++;
			if(day_of_week==8) {
				day_of_week=1;
				r++;
			}
		}while(days<=days_in_month);
	}

	public void display() {
		System.out.println("\t\t\t" + (month + 1) + "月");

		System.out.println("========================================================");
		for (int i = 0; i < 7; i++) {
			for (int j = 1; j < 8; j++) {
				if (data[i][j] == null)
					System.out.print("\t");
				else {
					System.out.print(data[i][j] + "\t");
				}
			}
			System.out.println();
		}
		System.out.println("*********************************************************");

	}

	public int getMonth() {
		return month + 1;
	}

	public int getDaysInMonth() {
		return days_in_month;
	}

	public String[][] getData() {
		return data;
	}
}

class myCalendarTest {
	public static void main(String[] args) {
		Calendar d = Calendar.getInstance();
		Scanner keyin = new Scanner(System.in);
		System.out.print("请输入要显示万年历的年份：");
		int year = keyin.nextInt();
		d.set(Calendar.YEAR, year);
		MyMonth[] mymonths = new MyMonth[12];
		for (int i = 0; i <= Calendar.DECEMBER; i++) {
			d.set(Calendar.MONTH, i);
			d.set(Calendar.DAY_OF_MONTH, 1);
			mymonths[i] = new MyMonth(i, d.get(Calendar.DAY_OF_WEEK), d.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
		for (int i = 0; i <= Calendar.DECEMBER; i += 2) {
			displayTwoMonth(mymonths[i], mymonths[i + 1]);
		}
	}

	public static void displayTwoMonth(MyMonth mon1, MyMonth mon2) {
		System.out.print("\t\t\t" + mon1.getMonth() + "月");
		System.out.println("\t\t\t\t\t\t\t" + mon2.getMonth() + "月");

		System.out.print("======================================================");
		System.out.println("  ======================================================");
		String[][] d1 = mon1.getData();
		String[][] d2 = mon2.getData();
		for (int i = 0; i < 7; i++) {
			for (int j = 1; j < 8; j++) {
				if (d1[i][j] == null)
					System.out.print("\t");
				else
					System.out.print(d1[i][j] + "\t");
			}
			for (int j = 1; j < 8; j++) {
				if (d2[i][j] == null)
					System.out.print("\t");
				else
					System.out.print(d2[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.print("******************************************************");
		System.out.println("  ******************************************************");
	}
}

class MonthTest {
	public static void main(String[] args) {
		Calendar d = Calendar.getInstance();
		Scanner keyin = new Scanner(System.in);
		System.out.print("请输入要显示年历的年份：");
		int year = keyin.nextInt();
		System.out.print("请输入要显示年历的月份：");
		int month=keyin.nextInt()-1;
		d.set(Calendar.YEAR, year);
		MyMonth[] mymonths = new MyMonth[12];
		d.set(Calendar.MONTH, month);
		d.set(Calendar.DAY_OF_MONTH, 1);
		mymonths[month] = new MyMonth(month, d.get(Calendar.DAY_OF_WEEK), d.getActualMaximum(Calendar.DAY_OF_MONTH));
		displayMonth(mymonths[month]);
	}

	public static void displayMonth(MyMonth mon1) {
		System.out.print("\t\t\t" + mon1.getMonth() + "月");
		System.out.println();
		System.out.print("======================================================");
		String[][] d1 = mon1.getData();
		System.out.println();
		for (int i = 0; i < 7; i++) {
			for (int j = 1; j < 8; j++) {
				if (d1[i][j] == null)
					System.out.print("\t");
				else
					System.out.print(d1[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.print("******************************************************");
	}
}