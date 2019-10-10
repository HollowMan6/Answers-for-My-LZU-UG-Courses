public class CatchException{
    public static void main(String[] args) {
        int[] myarr=new int[10];
        try{
            myarr[10]=10;
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("数组下标越界异常！");
        }
    }   
}