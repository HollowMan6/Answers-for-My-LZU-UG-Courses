public class Lingxing extends Shape{
    private int l;
    public Lingxing(int x,int y,int l){
        super(x, y); 
        this.setL(l);
    }
    public void setL(int l){
        this.l=l;
    }
    public void printme(Screen myscreen){
        int a=getX();
        int b=getY();
        for(int i=b; i<b+l/2+l-l/2*2; i++)
            for(int j=a+b+l/2-i; j<a+b+l/2-i+(i-b)*2+1; j++)
                myscreen.print(i, j, '*');
        for(int i=b+l/2+l-l/2*2; i<b+l; i++)
            for(int j=a+i-l+l/2*2+2-(b+l/2+1); j<a+i-(b+l/2+1)+(b+l-i)*2-l+l/2*2+1; j++)
                myscreen.print(i, j, '*');
    }
}