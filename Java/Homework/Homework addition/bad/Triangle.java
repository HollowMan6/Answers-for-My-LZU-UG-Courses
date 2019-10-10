public class Triangle extends Shape{
    private int l;
    public Triangle(int x,int y,int l){
        super(x, y); 
        this.setL(l);
    }
    public void setL(int l){
        this.l=l;
    }
    public void printme(Screen myscreen){
        int a=getX();
        int b=getY();
        for(int i=b; i<b+l; i++)
            for(int j=a+b+l/2-i+l/2; j<a+b+l/2-i+(i-b)*2+1+l/2; j++)
                myscreen.print(i, j, '*');    
    }
}