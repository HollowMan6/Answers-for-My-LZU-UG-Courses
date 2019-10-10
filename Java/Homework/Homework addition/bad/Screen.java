public class Screen{
    private int r,c;
    private char[][] myscreen;
    public Screen(int x,int y){
        this.r=x;
        this.c=y;
        myscreen=new char[r][c];
    }
    public void cls(){
        for(int i=0;i<r;i++)
            for(int j=0;j<c;j++)
                myscreen[i][j]=' ';
    }
    public void print(int x,int y,char c){
        myscreen[x][y]=c;
    }
    public void scroll(){
        for(int i=1;i<r;i++)
            myscreen[i-1]=myscreen[i];
        myscreen[r-1]=new char[c];
        for(int i=0;i<c;i++)
            myscreen[r-1][i]=' ';
    }
    public void display(){
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++)
                System.out.print(myscreen[i][j]);
            System.out.println();
        }
    }
}