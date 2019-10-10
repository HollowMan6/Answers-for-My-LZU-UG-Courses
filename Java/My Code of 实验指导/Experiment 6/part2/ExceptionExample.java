class NoLowerLetter extends Exception{
    public void print(){
        System.out.printf(" %c",'#');
    }
}

class NoDigit extends Exception{
    public void print(){
        System.out.printf(" %c",'*');
    }
}

class Test {
    void printLetter(char c) throws NoLowerLetter{
        if(c<'a'||c>'z'){
            NoLowerLetter noLowerLetter = new NoLowerLetter();
            throw noLowerLetter;
        } else {
            System.out.print(c);
        }
    }
    void printDigit(char c) throws NoDigit{
        if(c<'1'||c>'9'){
            NoDigit noDigit = new NoDigit();
            throw noDigit;
        } else {
            System.out.print(c);
        }
    }
}
public class ExceptionExample{
    public static void main(String[] args) {
        Test t=new Test();
        for(int i=0;i<128;i++){
            try{
                t.printLetter((char)i);
            } catch(NoLowerLetter e){
                e.print();
            }
        }
        for(int i=0;i<128;i++){
            try{
                t.printDigit((char)i);
            } catch(NoDigit e){
                e.print();
            }
        }
    }
}