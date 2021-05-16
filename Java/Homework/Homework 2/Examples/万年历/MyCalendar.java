/*MyCalendar.java*/

import java.io.*;
import java.util.*;
import java.text.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class MyCalendar{
	public static void main(String[] args){
		new CalendarWindow();
	}
}

class CalendarWindow extends JFrame implements ChangeListener, Runnable{
	Thread time=new Thread(this);
	JTextArea area=new JTextArea(7,10);
	JTextArea file=new JTextArea(5,10);
	Calendar cal=Calendar.getInstance();
	Lunar lunar=new Lunar(cal);
	JSpinner yearjsp=null;
	JSpinner monthjsp=null;
	CalPanel calPane=null;
	JPanel panel=null;
	JPanel change=null;
	Container con=null;
	CalendarWindow(){
		setTitle("郑恒冰万年历");
		time.start();
		panel=currentPanel();
		SpinnerModel yearmodel=new SpinnerNumberModel(cal.get(Calendar.YEAR),1990,2090,1);
		SpinnerModel monthmodel=new SpinnerNumberModel(cal.get(Calendar.MONTH)+1,1,12,1);
		yearjsp=new JSpinner(yearmodel);
		yearjsp.addChangeListener(this);
		yearjsp.setEditor(new JSpinner.NumberEditor(yearjsp,"#"));
		monthjsp=new JSpinner(monthmodel);
		monthjsp.addChangeListener(this);
		monthjsp.setEditor(new JSpinner.NumberEditor(monthjsp,"#"));
		change=changePanel();
		con=this.getContentPane();
		con.add(change,BorderLayout.NORTH);
		calPane=new CalPanel();
		con.add(calPane,BorderLayout.CENTER);
		con.add(panel,BorderLayout.EAST);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100,100);
		setSize(600,320);
		setVisible(true);
		validate();
	}
	public JPanel changePanel(){
		JPanel panel=new JPanel();
		panel.setBackground(Color.orange);
		JLabel space1=new JLabel("=================");
		JLabel timelabel=new JLabel("现在是：",JLabel.CENTER);
		JLabel yearlabel=new JLabel("年",JLabel.CENTER);
		JLabel monthlabel=new JLabel("月",JLabel.CENTER);
		JLabel space2=new JLabel("=================");
		JLabel lan=new JLabel("把握时机，珍惜光阴",JLabel.CENTER);
		panel.setLayout(new FlowLayout());
		panel.add(space1);
		panel.add(timelabel);
		panel.add(yearjsp);
		panel.add(yearlabel);
		panel.add(monthjsp);
		panel.add(monthlabel);
		panel.add(space2);
		panel.add(lan);
		return panel;
	}
	public JPanel currentPanel(){
		JPanel lPanel=new JPanel();
		lPanel.setLayout(new BorderLayout());
		area.setBackground(Color.yellow);
		lPanel.add(area,BorderLayout.NORTH);
		file.setLineWrap(true);
		if(daytoString().length()!=0)
			file.setText(daytoString());
		else
			file.setText("今日无特别提醒");
		file.setBackground(Color.cyan);
		lPanel.add(file,BorderLayout.CENTER);
		return lPanel;
	}
	public String daytoString(){
		String m="";
		String m2="";
		String m3="";
		String m4="";
		int i=0;
		String temp="";
		String s[]=new String[80];
		for(i=0; i<s.length; i++)
			s[i]="";
		try{
			i=0;
			FileReader in=new FileReader("message.txt");
			BufferedReader read=new BufferedReader(in);
			while((temp=read.readLine())!=null){
				s[i]=temp;
				i++;
			}
			in.close();
			read.close();
		}
		
		catch(FileNotFoundException e){System.out.println("记录文件不存在");return "";}
		catch(IOException e1){System.out.println("流错误");return "";}
		catch(ArrayIndexOutOfBoundsException e2){System.out.println("数组下标越界异常");return "";}
		for(i=0,temp=""; i<s.length; i++){
			if(s[i].startsWith(String.valueOf(cal.get(Calendar.YEAR))+String.valueOf(cal.get(Calendar.MONTH)+1)+String.valueOf(cal.get(Calendar.DAY_OF_MONTH))))
			{
				temp+=s[i];
			}
		}
		if(temp.length()>6){
			m=temp;
			m4=m.substring(0,6);
			m3=m.substring(0,7);
			m2=m.substring(0,8);
			if(isInt(m2)){
				return temp.substring(8);
			}
			else if(isInt(m3)){
				return temp.substring(7);
			}
			else if(isInt(m3)){
				return temp.substring(6);
			}
		}
		return null;
	}
	public boolean isInt(String str){
		if(str.matches("[1-9]\\d*"))
	        	return true;
		return false;
	}
	public void stateChanged(ChangeEvent e){
		con.remove(calPane);
		if(e.getSource()==yearjsp){
			int year=(Integer)yearjsp.getValue();
			calPane=new CalPanel(year);
			con.add(calPane,BorderLayout.CENTER);
		}
		else if(e.getSource()==monthjsp){
			int year=(Integer)yearjsp.getValue();
			int month=(Integer)monthjsp.getValue();
			calPane=new CalPanel(year,month);
			con.add(calPane,BorderLayout.CENTER);
		}
		con.validate();
		validate();
	}
	public void run(){
		while(true){
			try{
				Calendar cal2=Calendar.getInstance();
				String text="             今天是：\n"
				+"              公历：\n "+String.valueOf(cal2.get(Calendar.YEAR))+" 年 "+String.valueOf(cal2.get(Calendar.MONTH)+1)+" 月"
				+String.valueOf(cal2.get(Calendar.DAY_OF_MONTH))+" 日\n"
				+"              农历：\n         "+lunar.toString()+"\n"
				+"        现在时间是：\n        "
				+String.valueOf(cal2.get(Calendar.HOUR_OF_DAY))+" : "
				+String.valueOf(cal2.get(Calendar.MINUTE))+" : "
				+String.valueOf(cal2.get(Calendar.SECOND));
				area.setText(text);
				Thread.sleep(1000);
			}
			catch(InterruptedException e){}
		}
	}
}
class CalPanel extends JPanel implements MouseListener{
	remind rd=null;
	int day0=0;
	JLabel dates[][]=new JLabel[6][7];
	JLabel week[]=new JLabel[7];
	String a[][]=new String[6][7];
	String colName[]={"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
	int year, month;
	int todayYear, todayMonth, todayDay;	//今天的年月日
	CalendarBean calendar;
	Color currentColor;
	
	public CalPanel(){	//默认为今天
		setLayout(new GridLayout(7, 7));
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());	//设置为当前时间	
		calendar=new CalendarBean();
		
		todayYear=cal.get(Calendar.YEAR);
		todayMonth=cal.get(Calendar.MONTH)+1;
		todayDay=cal.get(Calendar.DAY_OF_MONTH);	
		
		cal.set(todayYear, todayMonth-1, 1);
		int week=cal.get(Calendar.DAY_OF_WEEK)-1;	//本月第一天的位置
		todayDay+=week-1;	//今天在数组中的位置
		
		year=todayYear;
		month=todayMonth;
			
		setLabelForm(calendar);
	}
	public CalPanel(int Year){	//修改年
		setLayout(new GridLayout(7, 7));
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());	//设置为当前时间	
		calendar=new CalendarBean();
		
		todayYear=cal.get(Calendar.YEAR);
		todayMonth=cal.get(Calendar.MONTH)+1;
		todayDay=cal.get(Calendar.DAY_OF_MONTH);	
		
		cal.set(Year, todayMonth-1, 1);
		int week=cal.get(Calendar.DAY_OF_WEEK)-1;	//本月第一天的位置
		todayDay+=week - 1;	//今天在数组中的位置
		
		year=Year;
		month=todayMonth;
			
		setLabelForm(calendar);
	}
	public CalPanel(int Year, int Month){	//修改年和月
		setLayout(new GridLayout(7, 7));
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());	//设置为当前时间	
		calendar=new CalendarBean();
		
		todayYear=cal.get(Calendar.YEAR);
		todayMonth=cal.get(Calendar.MONTH)+1;
		todayDay=cal.get(Calendar.DAY_OF_MONTH);	
		
		cal.set(Year, Month-1, 1);
		int week=cal.get(Calendar.DAY_OF_WEEK)-1;	//本月第一天的位置
		todayDay+=week - 1;	//今天在数组中的位置
		
		year=Year;
		month=Month;
			
		setLabelForm(calendar);
	}
	
	public int getTodayYear(){return todayYear;}
	public void setYear(int year){this.year = year;}
	public int getTodayMonth(){return todayMonth;}
	public void setMonth(int month){this.month = month;}
	
	public void clearLabel(){
		removeAll();
	}
	public void setLabelForm(CalendarBean calendar){
		JLabel label;
		calendar.setMonth(month);
		calendar.setYear(year);
		String days[]=calendar.getCalendar();
		setTable(days);
		for(int i=0; i<7; i++){
			label = new JLabel("", JLabel.CENTER);
			label.setOpaque(true);
			label.setText(colName[i]);
			label.setBackground(Color.cyan);
			week[i] = label;
			add(week[i]);
		}
		for(int i=0; i<6; i++){
			for(int j=0; j<7; j++){
				label = new JLabel("", JLabel.CENTER);
				label.setOpaque(true);	//JLabel设置为不透明的
				label.setBackground(Color.yellow);
				label.setText(a[i][j]);
				label.addMouseListener(this);
				dates[i][j] = label;
				add(dates[i][j]);
			}
		}
		if(year==todayYear && month==todayMonth){	//若是今年、今月，则设置今天的前景色和背景色
			int row=todayDay/7;	//今天所在行
			int column=todayDay%7;	//今天所在列
			dates[row][column].setForeground(Color.WHITE);
			dates[row][column].setBackground(Color.GREEN);
			
			dates[row][column].setText("<html><center>" + String.valueOf(todayDay) + "<br/><font size = \"2\"> 今天 </center></html>");
		} 
		for(int i=0; i<6; i++){	//周六周天为红色
			dates[i][6].setForeground(Color.RED);
		}
		for(int i=0; i<6; i++){
			dates[i][0].setForeground(Color.RED);
		}
		validate();
	}
	public void setTable(String day[]){	//设置表格单元格中的数据
		int n=0;
		for(int i=0; i<6; i++){
			for(int j=0; j<7; j++){
				a[i][j]=day[n];
				Calendar cal = Calendar.getInstance();
				if(day[n]!=null){
					cal.set(year,month-1,Integer.parseInt(day[n]));
					Lunar lunar=new Lunar(cal);
					a[i][j]="<html><center>"+a[i][j]+"<br/><font size=\"2\">"+lunar+"</center></html>";
				} 
				n++;
			}
		}
	}	
	public void mouseClicked(MouseEvent e){
		if (e.getSource() instanceof JLabel){
			JLabel temp=(JLabel)e.getSource();
			if(temp.getText()!=null){
				if(e.getClickCount()==2)	//点击两次
				{
					String m=temp.getText();
					String m2=m.substring(14,16);
					if(isInt(m2)){
						day0=Integer.parseInt(m2);
					}
					else{
						m2=m.substring(14,15);
						day0=Integer.parseInt(m2);
					}
					rd=new remind(year,month,day0);
				}
			}
		}
	}
	public boolean isInt(String str){
		if(str.matches("[1-9]\\d*"))
	        	return true;
		return false;
	}
	public void mouseEntered(MouseEvent e){
		if(e.getSource() instanceof JLabel){
			JLabel temp=(JLabel)e.getSource();
			if (temp.getText()!=null){
				currentColor=temp.getBackground();
				temp.setBackground(Color.cyan);
			}
		}
	}
	public void mouseExited(MouseEvent e){
		if(e.getSource() instanceof JLabel){
			JLabel temp=(JLabel)e.getSource();
			if (temp.getText()!=null){
				temp.setBackground(currentColor);
			}
		}
	}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
}
class remind extends JFrame implements ActionListener{
	String m="";
	String m2="";
	String m3="";
	String m4="";	
	int changeyear,changemonth,changeday;
	JFrame rframe=null;
	JLabel label=null;
	JPanel rpanel0=new JPanel();	
	JPanel rpanel1=new JPanel();
	JTextArea rtext=new JTextArea();
	JButton rsure=null;
	int i=0;
	int n=0;
	String[] s=new String[80];
	FileReader in=null;
	BufferedReader read=null;
	String temp="";
	BufferedWriter write=null;
	public remind(int year, int month, int day){
		label=new JLabel("提醒",JLabel.CENTER);
		changeyear=year;
		changemonth=month;
		changeday=day;
		rframe=new JFrame("提醒");
		rtext.setText("无特别提醒");
		for (i=0; i<s.length;i++)
		{
			s[i]="";
		}
		if(judge(year,month,day)){
			rtext.setText(toString(year,month,day));
		}
		rpanel0.setLayout(new BorderLayout());
		rpanel0.add(label,BorderLayout.NORTH);
		rpanel0.add(rtext,BorderLayout.CENTER);
		rsure=new JButton("添加提醒");
		rsure.addActionListener(this);
		rpanel1.add(rsure);
		rframe.add(rpanel0,BorderLayout.CENTER);
		rframe.add(rpanel1,BorderLayout.SOUTH);
		rframe.setVisible(true);
		rframe.setSize(400,300);
		rframe.setLocation(100,100);
	}
	public boolean judge(int year, int month, int day){
		i=0;
		try{
			in=new FileReader("message.txt");
			read=new BufferedReader(in);
			while((temp=read.readLine())!=null){
				s[i]=temp;
				i++;
			}
			in.close();
			read.close();
		}
		catch(FileNotFoundException e){}
		catch(IOException e1){}
		for(i=0; i<s.length; i++){
			if(s[i].startsWith(String.valueOf(year)+String.valueOf(month)+String.valueOf(day)))
				n++;
		}
		if(n==0)
			return false;
		else 
			return true;
	}
	public String toString(int year, int month, int day){
		i=0;
		try{
			in=new FileReader("message.txt");
			read=new BufferedReader(in);
			while((temp=read.readLine())!=null){
				s[i]=temp;
				i++;
			}
			in.close();
			read.close();
		}
		catch(FileNotFoundException e){return "";}
		catch(IOException e1){return "";}
		for(i=0,temp=""; i<s.length; i++){
			if(s[i].startsWith(String.valueOf(year)+String.valueOf(month)+String.valueOf(day)))
				temp+=s[i];
		}
		if(temp.length()>7){
			m=temp;
			m4=m.substring(0,6);
			m3=m.substring(0,7);
			m2=m.substring(0,8);
			if(isInt(m2)){
				return temp.substring(8);
			}
			else if(isInt(m3)){
				return temp.substring(7);
			}
			else if(isInt(m3)){
				return temp.substring(6);
			}
		}
		return null;
	}
	public boolean isInt(String str){
		if(str.matches("[1-9]\\d*"))
	        	return true;
		return false;
	}
	public void actionPerformed(ActionEvent e1){
		try{
			RandomAccessFile out=new RandomAccessFile("message.txt","rw");
			out.seek(out.length());
			String msg=new String((String.valueOf(changeyear)+
					String.valueOf(changemonth)+
					String.valueOf(changeday)+
					rtext.getText()+"\n").getBytes("GBK"),"iso8859-1");
			out.writeBytes(msg);
			out.close();
			rframe.setVisible(false);
		}
		catch(IOException ee){}
	}
}
class Lunar{ 
	private int year;
	private int month;
	private int day;
	private boolean  leap;
	final static String chineseNumber[]={"一","二","三","四","五","六","七","八","九","十","十一","十二"};
	final static String Big_Or_Small[]={"大","小","大","小","大","小","大","大","小","大","小","大"};

	static SimpleDateFormat chineseDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
	final static long[] lunarInfo=new long[]{
		0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 
		0x056a0, 0x09ad0, 0x055d2, 0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 
		0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0, 0x14977, 0x04970, 
		0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 
		0x052f2, 0x04970, 0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 
		0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950, 0x0d4a0, 0x1d8a6, 
		0x0b550, 0x056a0, 0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 
		0x0b557, 0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5d0, 0x14573, 
		0x052d0, 0x0a9a8, 0x0e950, 0x06aa0, 0x0aea6, 0x0ab50, 0x04b60, 
		0x0aae4, 0x0a570, 0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0, 
		0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 
		0x0b540, 0x0b5a0, 0x195a6, 0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 
		0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570, 0x04af5, 
		0x04970, 0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 
		0x096d5, 0x092e0, 0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 
		0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5, 0x0a950, 0x0b4a0, 
		0x0baa4, 0x0ad50, 0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0,
		0x0a930, 0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6,
		0x0a4e0, 0x0d260, 0x0ea65, 0x0d530, 0x05aa0, 0x076a3, 0x096d0,  
		0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45,
		0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50,  
		0x1b255, 0x06d20, 0x0ada0};
      
          // ====== 传回农历 y年的总天数 
	final private static int yearDays(int y){
		int i,sum=348;
		for(i=0x8000; i>0x8; i>>=1){
			if((lunarInfo[y-1900]&i)!=0)
				sum+=1 ;
		} 
		return(sum+leapDays(y));
	} 

     // ====== 传回农历 y年闰月的天数 
	final private static int leapDays(int y){
		if(leapMonth(y)!=0){
			if((lunarInfo[y-1900]&0x10000)!=0)
				return 30;
			else 
				return 29;
			}
			else return 0;
	}
          
     // ====== 传回农历 y年闰哪个月 1-12 , 没闰传回 0 
	final private static int leapMonth(int y){
		return (int)(lunarInfo[y-1900]&0xf);
	} 
		
     // ====== 传回农历 y年m月的总天数 
	final private static int monthDays(int y, int m){
		if((lunarInfo[y-1900]&(0x10000>>m))==0)
			return 29;
		else 
			return 30;
	} 
     /** */ /** 
     * 传出y年m月d日对应的农历.
     * yearCyl3:农历年与1864的相差数              ?
     * monCyl4:从1900年1月31日以来,闰月数
     * dayCyl5:与1900年1月31日相差的天数,再加40      ? 
      */ 
	public Lunar(Calendar cal){
		int yearCyl, monCyl, dayCyl;
		int leapMonth=0;
		Date baseDate=null;
		try{
			baseDate=chineseDateFormat.parse("1900年1月31日");
		}
		catch(ParseException e){
			e.printStackTrace();   // To change body of catch statement use Options | File Templates. 
		} 
              	
        // 求出和1900年1月31日相差的天数 
        int offset=(int)((cal.getTime().getTime()-baseDate.getTime())/86400000L);
        dayCyl=offset+40;
        monCyl=14;
         
        //用offset减去每农历年的天数
        //计算当天是农历第几天
        //i最终结果是农历的年份
        //offset是当年的第几天 
        int iYear, daysOfYear=0;
        for(iYear=1900; iYear<2050&&offset>0; iYear++){
         	daysOfYear=yearDays(iYear);
            offset-=daysOfYear;
            monCyl+=12;
        } 
        if(offset<0){
            offset+=daysOfYear;
            iYear--;
            monCyl-=12;
        } 
         // 农历年份 
        year=iYear;
        yearCyl=iYear-1864 ;
        leapMonth=leapMonth(iYear);  // 闰哪个月,1-12 
        leap=false;

         // 用当年的天数offset,逐个减去每月（农历）的天数，求出当天是本月的第几天 
        int iMonth,daysOfMonth=0;
        for(iMonth=1; iMonth<13&&offset>0; iMonth++){
             // 闰月 
            if(leapMonth>0&&iMonth==(leapMonth+1)&&!leap){
                --iMonth;
                leap=true;
                daysOfMonth=leapDays(year);
            }   
			else 
                daysOfMonth=monthDays(year,iMonth);
            offset-=daysOfMonth;
             // 解除闰月 
            if(leap&&iMonth==(leapMonth+1)) leap=false;
            if(!leap) monCyl++;
        } 
          // offset为0时，并且刚才计算的月份是闰月，要校正 
        if(offset==0&&leapMonth>0&&iMonth==leapMonth+1){
            if(leap){
                leap=false;
            }  
			else{
                leap=true;
                --iMonth;
                --monCyl;
            } 
        } 
         // offset小于0时，也要校正 
        if(offset<0){
            offset+=daysOfMonth;
            --iMonth;
            --monCyl;
        } 
        month=iMonth;
        day=offset+1;
    }   
	public static String getChinaDayString(int day){
		String chineseTen[]={"初","十","廿","卅"};
		int n=day%10==0?9:day%10-1;
		if(day>30)
			return "";
		if(day==10)
			return "初十";
		else 
			return chineseTen[day/10]+chineseNumber[n];
	} 	
	public String toString(){
		return (leap?"闰":"")+chineseNumber[month-1]+"月"+getChinaDayString(day);
	} 
	public String numeric_md(){//返回阿拉伯数字的阴历日期
	    String temp_day;
	    String temp_mon;
	    temp_mon=month<10?"0"+month:""+month;
	    temp_day=day<10?"0"+day:""+day;
	    return temp_mon+temp_day;
	}
	public String get_month(){//返回阴历的月份
	    return chineseNumber[month-1];
	}
	public String get_date(){//返回阴历的天
	    return getChinaDayString(day);
	}
	public String get_Big_Or_Small(){//返回的月份的大或小
	    return Big_Or_Small[month-1];
	}
}
class CalendarBean{
	Calendar rili=Calendar.getInstance();
	Lunar lunar=new Lunar(rili);
	String day[];
	int year=rili.get(Calendar.YEAR),month=rili.get(Calendar.MONTH)+1;
	public void setYear(int Year){
		this.year=Year;
	}
	public int getYear(){
		return year;
	}
	public void setMonth(int month){
		this.month=month;
	}
	public int getMonth(){
		return month;
	}
	public String[] getCalendar(){
		String a[]=new String[42];
		rili.set(year,month-1,1);
		int week=rili.get(Calendar.DAY_OF_WEEK)-1;
		int day=0;
		if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
			day=31;
		}
		if(month==4||month==6||month==9||month==11){
			day=30;
		}
		if(month==2){
			if(((year%4==0)&&(year%100!=0))||(year%400==0)){
				day=29;
			}
			else{
				day=28;
			}
		}
		for(int i=week,n=1;i<week+day;i++){
			a[i]=String.valueOf(n);
			n++;
		}
		return a;
	}
}