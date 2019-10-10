import java.io.*;
import java.util.*;
class Student implements Serializable{
	private String num,name,sex;
	private double c,m,e;
	public Student(){
		Scanner reader=new Scanner(System.in);
		System.out.println("请输入该学生学号：");
		num=reader.nextLine();
		System.out.println("请输入该学生姓名：");
		name=reader.nextLine();
		System.out.println("请输入该学生性别：");
		sex=reader.nextLine();
		System.out.println("请输入该学生语文成绩：");
		c=reader.nextDouble();
		System.out.println("请输入该学生数学成绩：");
		m=reader.nextDouble();
		System.out.println("请输入该学生英语成绩：");
		e=reader.nextDouble();
	}
	public void setNum(String num){
		this.num=num;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setSex(String sex){
		this.sex=sex;
	}
	public void setC(Double c){
		if(c<0||c>100)
			System.out.println("学生语文成绩必须在0~100之间！");
		else
			this.c=c;
	}
	public void setM(Double m){
		if(m<0||m>100)
			System.out.println("学生数学成绩必须在0~100之间！");
		else
			this.m=m;
	}
	public void setE(Double e){
		if(e<0||e>100)
			System.out.println("学生英语成绩必须在0~100之间！");
		else
			this.e=e;
	}
	public String getNum(){
		return num;
	}
	public String getName(){
		return name;
	}
	public String getSex(){
		return sex;
	}
	public Double getC(){
		return c;
	}
	public Double getM(){
		return m;
	}
	public Double getE(){
		return e;
	}
	public String toString(){
		return "学号："+num+" 姓名："+name+" 性别："+sex+" 语文成绩："+c+" 数学成绩："+m+" 英语成绩："+e;
	}
	public boolean equals(Student another){
		return this.num.equals(another.num);
	}
	public void ShowMsg(){
		System.out.println(this.toString());
	}
}
class MyClass{
	private int count;
	private String name;
	private Student[] stu;
	public MyClass() throws IOException{
		Scanner reader=new Scanner(System.in);
		System.out.println("请输入班级名称：");
		name=reader.nextLine();
		System.out.println("请输入班级人数：");
		count=reader.nextInt();
		stu=new Student[count];
		for(int i=0;i<count;i++){
			System.out.println("录入第"+(i+1)+"个学生的个人信息：");
			stu[i]=new Student();
		}
		FileOutputStream fo=new FileOutputStream("yqc");
		ObjectOutputStream oo=new ObjectOutputStream(fo);
		oo.writeObject(new Integer(count));
		for(int i=0;i<count;i++){
			oo.writeObject(stu[i]);
		}
		oo.close();
		fo.close();
	}
	public void Output(String filename) throws IOException, ClassNotFoundException{
		FileInputStream fi=new FileInputStream(filename);
		ObjectInputStream oi=new ObjectInputStream(fi);
		count=((Integer) oi.readObject()).intValue();
		stu=new Student[count];
		for(int i=0;i<count;i++){
			stu[i]=(Student)oi.readObject();
			stu[i].ShowMsg();
		}
	}
}
public class MyClassTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		MyClass myclass=new MyClass();
		myclass.Output("yqc");
	}

}
