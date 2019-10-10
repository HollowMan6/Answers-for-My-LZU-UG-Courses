import java.util.*;
public class  EfficiencyTest{
  private static final int COUNTS=100000;
  public static abstract class Tester{
    private String operation;
    public Tester(String operation){this.operation=operation;}
    public abstract void test(List<String> list);
    public String getOperation(){return operation;}
  }
  static Tester iterateTester=new Tester("iterate"){
      public void test(List<String> list){  //迭代操作
       for(int i=0;i<10;i++){
          Iterator<String> it=list.iterator();
          while(it.hasNext()){
            it.next();
          }
        }
      }
  };
  static Tester getTester=new Tester("get"){
      public void test(List<String> list){  //随机访问操作
         for(int i=0;i<list.size();i++)
           for(int j=0;j<10;j++)
              list.get(j);
      }
  };
  static Tester insertTester=new Tester("insert"){
    public void test(List<String> list){  //插入操作
      ListIterator<String> it=list.listIterator(list.size()/2);  //从中间开始
      for(int i=0;i<COUNTS/2;i++)
        it.add("hello");
    }};
  static Tester deleteTester=new Tester("delete"){
    public void test(List<String> list){   //删除操作
      ListIterator<String> it=list.listIterator();
      while(it.hasNext()){
        it.next();
        it.delete();
      }
    }};
  static public void testJavaArray(List<String> list){
     Tester[] testers={iterateTester,getTester};
     test(testers,list);
  }
  static public void testList(List<String> list){
    Tester[] testers={insertTester,iterateTester,getTester,removeTester};
    test(testers,list);
  }
  static public void test(Tester[] testers,List<String> list){
    for(int i=0;i<testers.length;i++){
      System.out.print(testers[i].getOperation()+"操作：");
      long t1=System.currentTimeMillis();
      testers[i].test(list);
      long t2=System.currentTimeMillis();
      System.out.print(t2-t1+" ms");
      System.out.println();
    }
  }
  public static void main(String args[]){
    List<String> list=null;
    System.out.println("----测试Java数组----");
    String[] ss=new String[COUNTS];
    Arrays.fill(ss,"hello");
    list=Arrays.asList(ss);
    testJavaArray(list);
    ss=new String[COUNTS/2];
    Collection<String> col=Arrays.asList(ss);
    System.out.println("----测试Java向量----");
    list=new Vector<String>();
    list.addAll(col);
    testList(list);
    System.out.println("----测试Java链表----");
    list=new LinkedList<String>();
    list.addAll(col); 
    testList(list);
    System.out.println("----测试Java中ArrayList----");
    list=new ArrayList<String>();
    list.addAll(col);
    testList(list);
  }
}
