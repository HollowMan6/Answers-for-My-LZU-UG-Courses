import java.util.*;
class student{
    String name;
    int number;
    float score;
    student(String name,int number,float score){
        this.name=name;
        this.number=number;
        this.score=score;
    }
    public String toString(){
        return "No.:"+number+"\nname:"+name+"\nscore:"+score+"\n";
    }
}
class HashTableTest {
    public static void main(String args[]) {
        Hashtable h = new Hashtable();
        Enumeration e;
        student stu;
        String str;
        h.put("10001", new student("马俊",10001,98));
        h.put("10002", new student("马健强",10002,88));
        h.put("10003", new student("李鹏",10003,77));
        e = h.keys();
        while (e.hasMoreElements()) {
            str = (String) e.nextElement();
            System.out.println("" + (student)h.get(str));
        }
        System.out.println();
        float score=((student)h.get("10003")).score;
        h.put("10003", new student("李鹏", 10003, score+15));
        System.out.println("李鹏修改后的信息: " + (student)h.get("10003"));
    }
}
