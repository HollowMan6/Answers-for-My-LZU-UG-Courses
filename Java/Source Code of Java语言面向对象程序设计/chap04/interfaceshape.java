import java.awt.Graphics;
interface shape 
{
        void drawme(Graphics g);
        double area();
        double length();
        String getName();
}
class point implements shape
{
     int x,y;
     point(int x,int y){this.x=x;this.y=y;}
     public void drawme(Graphics g){g.fillOval(x,y,5,5);}
     public double area(){return 0;}
     public double length(){return 0;}
     public String getName(){return "Point";}
}
class triangle implements shape
{
      point a,b,c;
      triangle(point aa,point bb,point cc){a=aa;b=bb;c=cc;}
       public void  drawme(Graphics g)
      {
             g.drawLine(a.x,a.y,b.x,b.y);
             g.drawLine(b.x,b.y,c.x,c.y);
             g.drawLine(c.x,c.y,a.x,a.y);
      }
      public double area()
      {
           double a_b=Math.sqrt((b.x-a.x)*(b.x-a.x)+(b.y-a.y)*(b.y-a.y));
           double a_c=Math.sqrt((c.x-a.x)*(c.x-a.x)+(c.y-a.y)*(c.y-a.y));
           double c_b=Math.sqrt((b.x-c.x)*(b.x-c.x)+(b.y-c.y)*(b.y-c.y));
           double l=(a_b+a_c+c_b)/2;
           double s=Math.sqrt((l-a_b)*(l-a_c)*(l-c_b)*l);
           return s;
      }
      public double length()
      {
           double a_b=Math.sqrt((b.x-a.x)*(b.x-a.x)+(b.y-a.y)*(b.y-a.y));
           double a_c=Math.sqrt((c.x-a.x)*(c.x-a.x)+(c.y-a.y)*(c.y-a.y));
           double c_b=Math.sqrt((b.x-c.x)*(b.x-c.x)+(b.y-c.y)*(b.y-c.y));
           return a_b+a_c+c_b;
      }
      public String getName(){return "Triangle";}      
}
class circle implements shape
{
      point c;
      int r;
      circle(point cc,int rr){c=cc;r=rr;}
      public void drawme(Graphics g)
      {
             g.drawOval(c.x-r,c.y-r,2*r,2*r);
       }
      public double area(){return 3.14159*r*r;}
      public double length(){return 2*3.14159*r;}
      public String getName(){return "circle";}
}
class rectangle implements shape
{
        point a,b;
        rectangle(point aa,point bb){a=aa;b=bb;}
        public void drawme(Graphics g)
        {
               g.drawRect(a.x,a.y,b.x-a.x,b.y-a.y);
        }
        public double area(){return (b.x-a.x)*(b.y-a.y);}
        public double length(){return 2*(b.x-a.x+b.y-a.y);}
        public String getName(){return "rectangle";}
}
