public class Voter {
  private static int MAX_COUNT=100; //静态变量，最大投票数
  private static int count;  //静态变量，已投票数
  private static Voter[] voters=new Voter[MAX_COUNT];
  private String name;
  
  public Voter(String name){this.name=name;}
  public String getName(){return name;}
  public void voteFor(){
    if(count==MAX_COUNT){
       System.out.println("投票活动已经结束");
       return;
    }
    if(isExisted(this))
       System.out.println(name+" :你不允许重复投票");
    else {
       votersadd(this); 
       System.out.println(name+" :感谢你投票");
    }
  }
  public static boolean isExisted(Voter obj) {   //判断是否已投过票了
    for(int i=0;i<count;i++)
    {
       if(voters[i].getName().equals(obj.getName())) {
          return true;
       }
    }
    return false;
  }
  public static void votersadd(Voter obj){   //将已投票人加入数组
     voters[count++]=obj;
  }
  public static void printVoteResult(){
     System.out.println("当前投票数为："+count);
     System.out.println("参与投票的选民如下：");
     for(int i=0;i<count;i++)
       System.out.println(voters[i].getName());
  }
  public static void main(String[] args)
  {
     Voter majun=new Voter("马俊");
     Voter flyhorse=new Voter("云中飞马");
     Voter mike=new Voter("Mike");
     Voter majian=new Voter("马健");

     majun.voteFor();
     flyhorse.voteFor();
     mike.voteFor();
     mike.voteFor();
     majian.voteFor();

     Voter.printVoteResult();
  }
}