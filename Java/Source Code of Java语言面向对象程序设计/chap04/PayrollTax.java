import java.util.Scanner;
/**
*个人所得税计税说明(2013)
*应纳税额 = （工薪税收入 - 扣除基数）* 适用税率 - 速算扣除数
*
*备注：工薪税收入 = 财政工资实发数 + 其他收入
*
*个人所得税税率表(基数3500)
*级数 	全月应纳税所得额 	税率（%） 	速算扣除数
*1 	不超过1500元 			3 	0
*2 	超过1500元至4500元的部分 	10 	105
*3 	超过4500元至9000元的部分 	20 	555
*4 	超过9000元至35000元的部分 	25 	1005
*5 	超过35000元至55000元的部分 	30 	2755
*6 	超过55000元至80000元的部分 	35 	5505
*7 	超过80000元的部分 		45 	13505
*/
class TaxPolicy { //国家税收政策抽象和封装
    public static int getDeduct(double income){
        income=income-3500;
        if(income<=1500) return 0;
        else if(income<=4500) return 105;
        else if(income<=9000) return 555;
        else if(income<35000) return 1005;
        else if(income<55000) return 2755;
        else if(income<80000) return 5505;
        else return 13505;
    }
    public static int taxRate(double income){
        income=income-3500;
        if(income<=1500) return 3;
        else if(income<=4500) return 10;
        else if(income<=9000) return 20;
        else if(income<35000) return 25;
        else if(income<55000) return 30;
        else if(income<80000) return 35;
        else return 45;
    } 
}
/**
* 雇员类抽象，抽象出工资号、姓名、工资以及三险一金
*/
class Employee { // 雇员类  
    private String staff_no;       //工资号  
    private String name;     
    private double salary;      // 应发工资  
    private double insurancesandfund;  // "三险一金"数额  
    Employee(String ID,String name){ //带2参数的构造方法  
        this.staff_no = ID;  
        this.name = name;  
    }     
    Employee(String ID,String name,double salary,double insurancesandfund){  //带4参数的构造方法  
        this.staff_no = ID;  
        this.name = name;  
        this.salary = salary;  
        this.insurancesandfund = insurancesandfund;  
    }  
    public String getStaff_no() {  
        return staff_no;  
    }  
    public String getName() {  
        return name;  
    }  
    public double getSalary() {  
        return salary;  
    }  
    public double getInsurancesandfund() {  
        return insurancesandfund;  
    }  
    public void setStaff_no(String iD) {  
        staff_no = iD;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public void setSalary(double salary) {  
        this.salary = salary;  
    }  
    public void setInsurancesandfund(double insure) {  
        this.insurancesandfund = insure;  
    }  
    public double getTax(){
        return (salary-insurancesandfund-3500)*TaxPolicy.taxRate(salary)/100.0-TaxPolicy.getDeduct(salary);
        
    }
    public double getRealIncome(){
        return salary-getTax()-insurancesandfund; 
    }
}
/**
* 主控类抽象
*/
public class PayrollTax{ 
    public static void main(String[] args){
        Employee emp1=new Employee("1001","张三",6581,812.82);
        Employee emp2=new Employee("1002","李四");
        System.out.println("编号:"+emp1.getStaff_no()+"，姓名:"+emp1.getName()+" 的应纳税额是:"+emp1.getTax()+",税后收入是："+emp1.getRealIncome());  
        Scanner mykey=new Scanner(System.in);
        System.out.print("请输入李四的应发工资：");
        double s=mykey.nextDouble();
        System.out.print("请输入李四的三险一金：");
        double insurance=mykey.nextDouble();
        emp2.setSalary(s);
        emp2.setInsurancesandfund(insurance);
        System.out.println("编号:"+emp2.getStaff_no()+"，姓名:"+emp2.getName()+" 的应纳税额是:"+emp2.getTax()+",税后收入是："+emp2.getRealIncome());  
     }  
}  