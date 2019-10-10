import java.util.*;
public class GuessNumber {
    public static void main(String[] args) {
        System.out.println("给你一个1至100之间的整数，请猜测这个数");
        int realNumber = (int)(Math.random()*100)+1;
        int myGuess=0;
        int guessCount=1;
        Scanner reader=new Scanner(System.in);
        System.out.println("输入你的猜测：");
        myGuess=reader.nextInt();
        while(realNumber!=myGuess){
            if(myGuess>realNumber){
                System.out.println("猜大了，请再猜：");
                myGuess=reader.nextInt();
            }
            else if(myGuess<realNumber){
                System.out.println("猜小了，请再猜：");
                myGuess=reader.nextInt();
            }
            guessCount++;
        }
        if(guessCount<4)
            System.out.println("你太聪明了，竟然这么快就猜对了！");
        else if(guessCount>8)
            System.out.println("要努力学习哦，希望下次猜的次数少一点");
        else
            System.out.println("正常智力");
    }
}