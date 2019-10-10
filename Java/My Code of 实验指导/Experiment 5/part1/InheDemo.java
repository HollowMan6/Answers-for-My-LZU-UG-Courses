import java.util.Locale.Category;

abstract class Employee{                //抽象类
    String EmpName;
    char sex;
    double EmpSal;
    Employee(String en,char s,double es){
        EmpName=en; sex=s; EmpSal =es;
    }
    public String getName(){
        return EmpName;
    }
    public char getSex(){
        return sex;
    }
    public abstract double getSal();
    public void setSal(int basSal){
        EmpSal = basSal;
    }
}
/************************************************* */
class Worker extends Employee{
    char category;
    boolean dressAllowance;
    Worker(String en,char s,double es,char c,boolean d){
    super(en, s, es);
        category = c;
        dressAllowance = d;
    }
    public char getCategory(){
        return category;
    }
    public boolean getDressAll(){
        return dressAllowance;
    }
    public double getSal(){
        return EmpSal;
    }
}
/************************************************* */
class Superior extends Employee{
    int experience;
    boolean vehicle;
    double medicalAllowance;
    Superior(String en,char s,double es, int e,boolean v,double ma){
        super(en, s, es);
        experience = e;
        vehicle = v;
        medicalAllowance =ma;
    }
    public int getExp(){
        return experience;
    }
    public boolean getVehicle(){
        return vehicle;
    }
    public double getMedicalAll(){
        return medicalAllowance;
    }
    public double getSal(){
        return EmpSal*4+1000+medicalAllowance;
    }
}
/************************************************* */
class Officer extends Superior{
    double travelAllowance;
    Officer(String en, char s,double es,int e,boolean v,double ma,double ta){
        super(en,s,es,e,v,ma);
        travelAllowance=ta;
    }
    public double getTravelAll(){
        return travelAllowance;
    }
    public double getSal(){
        return EmpSal*2+200+travelAllowance+medicalAllowance;
    }
}
/************************************************* */
class Manager extends Superior{
    double clubAllowance;
    Manager(String en, char s,double es,int e,boolean v,double ma,double ca){
        super(en,s,es,e,v,ma);
        clubAllowance=ca;
    }
    public double getClubAll(){
        return clubAllowance;
    }
    public double getSal(){
        return EmpSal*5+2000+medicalAllowance+clubAllowance;
    }
}
/************************************************* */
class InheDemo{
    public static void main(String[] args) {
        Worker w=new Worker("M.John", 'M', 1200.50, 'B', true);
        System.out.println("工人信息：");
        System.out.println("姓名："+w.getName());
        System.out.println("性别："+w.getSex());
        System.out.println("薪资："+w.getSal());
        System.out.println("类别："+w.getCategory());
        if(w.getDressAll()) System.out.println("提供服装津贴");
        else System.out.println("未提供服装津贴");
        Officer o=new Officer("S.David", 'F', 2500.70, 15, true, 345.60, 200);
        System.out.println("\n 主任信息：");
        System.out.println("姓名："+o.getName());
        System.out.println("性别："+o.getSex());
        System.out.println("薪资："+o.getSal());
        System.out.println("工作经验："+o.getExp()+"年");
        if(o.getVehicle())  System.out.println("提供交通工具");
        else System.out.println("未提供交通工具");
        System.out.println("医疗津贴："+o.getMedicalAll());
        System.out.println("出差津贴："+o.getTravelAll());
        Manager m=new Manager("ArnoldShwaz",'M', 4500.70, 15, true, 345.60, 300);
        System.out.println("\n 经理信息：");
        System.out.println("姓名："+m.getName());
        System.out.println("性别："+m.getSex());
        System.out.println("薪资："+m.getSal());
        System.out.println("工作经验："+m.getExp()+"年");
        if(m.getVehicle())  System.out.println("提供交通工具");
        else System.out.println("未提供交通工具");
        System.out.println("医疗津贴："+m.getMedicalAll());
        System.out.println("会员津贴："+m.getClubAll());
    }
}
