public class Rectangle extends Shape{
    private int l,c;
    public Rectangle(int x,int y,int l,int c){
        super(x, y); 
        this.setL(l);
        this.setC(c);
    }
    public void setL(int l){
        this.l=l;
    }
    public void setC(int c){
        this.c=c;
    }
    public void printme(Screen myscreen){
        int a=getX();
        int b=getY();
        for(int i=b; i<b+c; i++)
            for(int j=a; j<a+l; j++)
                myscreen.print(i, j, '*');
    }
}