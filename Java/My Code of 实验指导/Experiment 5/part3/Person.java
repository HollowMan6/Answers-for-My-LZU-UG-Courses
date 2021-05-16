public class Person{
	protected int age=0;
	protected String name = "noname";
	protected char sex ='M';
	public Person(){}
	public Person(String n,int a,char s){
		name=n;
		if(a>=0&&a<140)	age=a;		//数据过滤
		else age = 0;
		if(s=='M')sex=s;			//数据过滤
		else sex='F';	
	}
	public String getName(){return name;}
	public void setName(String n){name=n;}
	public int getAge(){return age;}
	public void setAge(int a){		//注意数据过滤
		if(a>=0&&a<140) age=a;
		else age=0;
	}
	public char getSex(){return sex;}
	public void setSex(char s){		//注意数据过滤
		if(s=='M')sex='M';
		else sex='F';
	}
}