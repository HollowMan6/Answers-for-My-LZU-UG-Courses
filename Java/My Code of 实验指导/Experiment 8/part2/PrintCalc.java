import java.io.*;

public class PrintCalc {
    public static void main(String[] args) {
        PrintCalc app = new PrintCalc();
        try {
            app.readAndPrint();
        } catch (IOException e) {
            System.out.println("Error encountered during printing");
        }
    }

    private void readAndPrint() throws IOException{
        PushbackInputStream f=new PushbackInputStream(System.in,3);
        int c,c1,c2;
        while((c=f.read())!='q'){
            switch(c){
                case '.':
                    System.out.print((char)c);
                    if((c1=f.read())=='0'){
                        if((c2=f.read())=='0'){
                            System.out.print("**");
                        }else{
                            f.unread(c2);
                            f.unread(c1);
                        }
                    }else{
                        f.unread(c1);
                    }
                    break;
                default:
                    System.out.print((char)c);
                    break;
            }
        }
        f.close();
    }
}