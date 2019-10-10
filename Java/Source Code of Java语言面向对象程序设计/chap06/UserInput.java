import java.io.IOException;
class UserInput{
    public static String getUserInput(){
        StringBuilder sb=new StringBuilder();
        try {
            char c=(char) System.in.read();
            while(c!='\r'){
                sb.append(c);
                c=(char)System.in.read();
            }
        }catch(IOException e){
        }
        return sb.toString();
    }
    public static void main(String[] args){
        System.out.print("Please input a integer:");
        int a=Integer.parseInt(getUserInput());
        System.out.println("You input is "+a);
    }
} 