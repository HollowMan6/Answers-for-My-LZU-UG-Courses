class Student extends Person{
    private int sc;
    private String id;
    public Student(String n,String id,int a,char s,int sc){
        super(n,a,s);
        this.sc=sc;
        this.id=id;
    }
    public String getID(){return id;}
    public void setID(String n){id=n;}
    public int getScore(){return sc;}
    public void setScore(int n){sc=n;}
    void display(){
        System.out.println("姓名："+name);
        System.out.println("年龄："+age);
        System.out.println("性别："+sex);
        System.out.println("ID："+id);
        System.out.println("成绩："+sc);
    }
}
class Test{
    public static void main(String[] args) {
        Student me = new Student("Steven", "20188866", 18, 'M', 100);
        me.display();
    }
}