public class Test1 {
    public static void main(String[] args) {
        Screen myscreen=new Screen(25,80);
        myscreen.cls();
        Shape shapes[]=new Shape[5];
        shapes[0]=new Lingxing(0,0,9);
        shapes[1]=new Lingxing(20,1,12);
        shapes[2]=new Rectangle(14,1,5,7);
        shapes[3]=new Triangle(56,2,7);
        shapes[4]=new Circle(34,0,10);
        for(int i=0;i<shapes.length;i++) {
            shapes[i].printme(myscreen);
        }
        //myscreen.scroll();
        myscreen.display();
    }
}