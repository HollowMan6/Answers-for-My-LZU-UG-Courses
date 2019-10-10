import java.awt.*;
class mycanvas extends Canvas
{
  private int flag;
  private int x,y;
  mycanvas(){flag=0;}

  public int getFlag(){return flag;}
  public void setFlag(int i){flag=i;}

  public void paint(Graphics g)
  {
     g.drawLine(1,1,400,1);

     g.drawLine(1,1,1,220);
     g.drawLine(1,110,400,110);

     switch(flag)
     {
        case 1:
          for(int i=0;i<360;i++)
          {
            x=i;
            y=110-(int)(100*Math.sin(x*3.1415926/180.0));
            g.drawLine(x,y,x,y);
          }
          break;
       case 2:
          for(int i=0;i<360;i++)
          {
            x=i;
            y=110-(int)(100*Math.cos(x*3.1415926/180.0));
            g.drawLine(x,y,x,y);
          }
          break;
       case 3:
          for(int i=0;i<360;i++)
          {
            x=i;
            y=110-(int)(4*Math.sqrt(x));
            g.drawLine(x,y,x,y);
          }
          break;
       default:break;
     }
  }
}