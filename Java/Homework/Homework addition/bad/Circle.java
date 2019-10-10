public class Circle extends Shape{
    private int r;
    public Circle(int x,int y,int r){
        super(x, y); 
        this.setR(r);
    }
    public void setR(int r){
        this.r=r;
    }
    public void printme(Screen myscreen){
        int a=getX();
        int b=getY();
        for(int i=a+r-r/2+5; i<a+r/2+8; i++)
            myscreen.print(b, i, '*'); 
        for(int i=a+r-r/2-(r-1)/2*2+7; i<a+r-r/2+(r-2)/2*2+6; i++)
            myscreen.print(b+1, i, '*');
        for(int i=b+2; i<b+r/2; i++)
            for(int j=a+r-r/2-(r-1)/2*2+5+b+2-i; j<a+r-r/2+(r-2)/2*2+8+i-b-2; j++)
                myscreen.print(i, j, '*'); 
        for(int j=a+r-r/2-(r-1)/2*2+5+2-r/2+1; j<a+r-r/2+(r-2)/2*2+8+r/2-b-3; j++)
            myscreen.print(b+r/2, j, '*');
        for(int i=b+r/2+1; i<b+r-1; i++)
            for(int j=a+r-r/2-(r-1)/2*2+3+i-(b+r/2+1); j<a+r-r/2+(r-2)/2*2+10+(b+r/2+1)-i; j++)
                myscreen.print(i, j, '*'); 
        for(int i=a+r-r/2-(r-1)/2*2+7; i<a+r-r/2+(r-2)/2*2+6; i++)
            myscreen.print(b+r-1, i, '*');
        for(int i=a+r-r/2+5; i<a+r/2+8; i++)
            myscreen.print(b+r, i, '*'); 
    }
}