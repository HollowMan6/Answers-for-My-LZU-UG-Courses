/* 囚徒困境_博弈　　
　　如果一名参与者背叛对方而对方不背叛，前者有5分，后者得0分；如果双方不背叛对方，双方各得3分；如果双方同时背叛对方，双方各得1分。
　　当以牙还牙者对背叛者，前者第一场比赛选择不背叛，而后者正好相反，后者获得5分。在余下的5场比赛，两位参与者背叛对方，每一场比赛各得1分。最后，背叛者得10分，以牙还牙者得5分。
　　当双方均为以牙还牙者，在所有6场比赛中彼此均不会背叛对方。双方每回合各得3分，最后每人各得18分。
　　当背叛者互相对赛，双方每次都会背叛对方。双方每回合各得1分，最后每人各得6分。
　　
尽管以牙还牙者从来没有赢得过一场比赛，而背叛者从未输过一场比赛，考虑到双方的最大共同利益，以牙还牙仍然是最好的策略。
*/
import java.util.Scanner;
public class Game_test {
public static void main(String[] args){
    System.out.print("此程序用来测试重复囚徒困境中的博弈策略：1---以牙还牙策略，2---随机策略，3---退出");
    Scanner myinputkey=new Scanner(System.in);
    int inputakey=myinputkey.nextInt();
    while(inputakey!=3){
       switch(inputakey){
          case 1:titfortatpolicy();break;
          case 2:randomizedpolicy();break;
       }
       System.out.print("此程序用来测试重复囚徒困境中的博弈策略：1---以牙还牙策略，2---随机策略，3---退出");
       inputakey=myinputkey.nextInt();
    }
}
    /*以牙还牙策略有四个特点：
      1.友善：以牙还牙者开始一定采取合作态度，不会背叛对方
      2.报复性：遭到对方背叛，以牙还牙者一定会还击作出报复
      3.宽恕：当对方停止背叛，以牙还牙者会原谅对方，继续合作
      4.不羡慕对手：以牙还牙者个人永远不会得到最大利益，整个策略以全体的最大利益为依归
      这一策略有两个步骤：
      1.第一个回合选择合作
      2.下一回合是否选合作要看上一回对方是否合作，若对方上一回背叛，此回合我亦背叛；若对方上一回合作，此回合继续合作
    */
    public static void titfortatpolicy(){
       int computerscores=0,userscores=0;
       StringBuffer computerselected=new StringBuffer();
       char computernowselected,usernowselected;
       int rounds=1;
       StringBuffer userselected=new StringBuffer();       
       Scanner myinputkey=new Scanner(System.in);
       computernowselected='A';
       while(true){
           System.out.print("A-合作，B-背叛，Q-结束，请输入你的第"+rounds+"轮次的选择：");
           usernowselected=myinputkey.nextLine().toUpperCase().charAt(0);
           if(usernowselected=='Q') break;
           if(usernowselected=='A'&computernowselected=='A') {
              computerscores+=3;
              userscores+=3;
           }else if(usernowselected=='A'&computernowselected=='B'){
              computerscores+=5;
              userscores+=0;
           }else if(usernowselected=='B'&computernowselected=='A'){
              computerscores+=0;
              userscores+=5;
           }else{
              computerscores+=1;
              userscores+=1;
           } 
           computerselected.append(computernowselected);  
           userselected.append(usernowselected);    
           computernowselected=usernowselected; 
           rounds++;    
       }
       System.out.println("Computer:"+computerselected+",the final score is:"+computerscores);
       System.out.println("User    :"+userselected+",the final score is:"+userscores);
       if(computerscores>userscores){ System.out.println("The computer is winner!");}
       else if(computerscores<userscores){ System.out.println("The user is the winner!");}
       else{System.out.println("We both are winners!");}
       System.out.println("The total scores is:"+(computerscores+userscores));
       
    } 
    /*随机策略没有什么特点，每次计算机随机选择合作或背叛。
    */
    public static void randomizedpolicy(){
       int computerscores=0,userscores=0;
       StringBuffer computerselected=new StringBuffer();
       char computernowselected,usernowselected;
       int rounds=1;
       StringBuffer userselected=new StringBuffer();   
       char preparedanswer[]={'A','B'};        
       Scanner myinputkey=new Scanner(System.in);
       computernowselected=preparedanswer[(int)(Math.random()*2)];
       while(true){
           System.out.print("A-合作，B-背叛，Q-结束，请输入你的第"+rounds+"轮次的选择：");
           usernowselected=myinputkey.nextLine().toUpperCase().charAt(0);
           if(usernowselected=='Q') break;          

           if(usernowselected=='A'&computernowselected=='A') {
              computerscores+=3;
              userscores+=3;
           }else if(usernowselected=='A'&computernowselected=='B'){
              computerscores+=5;
              userscores+=0;
           }else if(usernowselected=='B'&computernowselected=='A'){
              computerscores+=0;
              userscores+=5;
           }else{
              computerscores+=1;
              userscores+=1;
           } 
           computerselected.append(computernowselected);  
           userselected.append(usernowselected);    
           computernowselected=preparedanswer[(int)(Math.random()*2)];
           rounds++;    
       }
       System.out.println("Computer:"+computerselected+",the final score is:"+computerscores);
       System.out.println("User    :"+userselected+",the final score is:"+userscores);
       if(computerscores>userscores){ System.out.println("The computer is winner!");}
       else if(computerscores<userscores){ System.out.println("The user is the winner!");}
       else{System.out.println("We both are winners!");}
       System.out.println("The total scores is:"+(computerscores+userscores));
    }
}