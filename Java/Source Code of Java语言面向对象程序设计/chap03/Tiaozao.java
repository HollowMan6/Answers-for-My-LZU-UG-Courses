class House{
  private int m;
  private int n;
  private int[][] a;
  public House(){
    m=10;n=10;
    a=new int[m][n];
    for(int i=0;i<m;i++)
      for(int j=0;j<n;j++) a[i][j]=0;
  }
  public House(int m,int n){
    this.m=m;this.n=n;
    a=new int[m][n];
    for(int i=0;i<m;i++)
      for(int j=0;j<n;j++) a[i][j]=0;
  }
  public int getM(){return m;}
  public int getN(){return n;}
  public int[][] getA(){return a;}
  public int getElement(int i,int j){return a[i][j];}
  public void setElement(int i,int j,int v){ a[i][j]=v; }
  public boolean checkZero(){
    for(int i=0;i<m;i++)
      for(int j=0;j<n;j++) {
        if(a[i][j]==0) return true;
      }   
    return false;
  }
  public void display()
  { 
    for(int i=0;i<m;i++){
       for(int j=0;j<n;j++) {
         System.out.printf("%4d",a[i][j]);
      }        
      System.out.println();
    }
  }
}
public class Tiaozao{
   private static final int UP=0;
   private static final int DOWN=1;
   private static final int RIGHT=2;
   private static final int LEFT=3;
   private int x,y;
   private int totals;
   private House ahouse;
   public Tiaozao(House h){
      ahouse=h;
      totals=0;
      x=(int)(Math.random()*ahouse.getM());
      y=(int)(Math.random()*ahouse.getN());


   } 
   public int getTotals(){return totals;}
   public boolean walk(int direction)
   {
      switch(direction)
      {
        case UP: if(y==0) return false;
                 else {
                   ahouse.setElement(x,y,ahouse.getElement(x,y)+1);
                   y=y-1;
                 }
                 return true;
        case DOWN: if(y==ahouse.getN()-1) return false;
                 else {
                   ahouse.setElement(x,y,ahouse.getElement(x,y)+1);
                   y=y+1;
                 }
                 return true;
        case LEFT: if(x==0) return false;
                 else {
                   ahouse.setElement(x,y,ahouse.getElement(x,y)+1);
                   x=x-1;
                 }
                 return true;
        case RIGHT: if(x==ahouse.getM()-1) return false;
                 else {
                   ahouse.setElement(x,y,ahouse.getElement(x,y)+1);
                   x=x+1;
                 }
                 return true;
        default: System.out.println("非法移动!");return false;
      }                
   } 
   public void move()
   {
       int nextdirection;
       boolean success;
       do{
         nextdirection=(int)(Math.random()*4);
         success=walk(nextdirection); 
         if(success) totals++;
       }while(ahouse.checkZero());
   }
   public static void main(String[] args)
   {
      House ahouse=new House(6,6);
      Tiaozao  atiaozao=new Tiaozao(ahouse);
      atiaozao.move();
      ahouse.display();
      System.out.println("Totals="+atiaozao.getTotals());
   }
}