public class MulTable {
    public static void main(String[] args) {
        for(int i=1;i<=9;i++)
        {
            for(int j=1;j<=i;j++)
                System.out.printf("%2d *%2d = %2d  ",i,j,i*j);
            System.out.printf("\n");
        }
    }
}