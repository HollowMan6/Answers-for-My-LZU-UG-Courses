public class SinTable{
    public static void main(String[] args) {
        double radian;
        for(int degree=0;degree<90;degree++){
            radian = degree * Math.PI/180;
            System.out.println("sin("+degree+")="+Math.sin(radian));
        }
    }
}