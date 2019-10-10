class MyComplex{
    private double real,image;
    public MyComplex(double a,double b){
        real=a;
        image=b;
    }
    public MyComplex(){
        real=0;
        image=0;
    }
    public boolean equals(MyComplex a){
        if(a.getReal()==real&&a.getImage()==image)
            return true;
        else
            return false;
    }
    public String toString(){
        String info="";
        info+=real+" + "+image+"i";
        return info;
    }
    public void setImage(double n){image=n;}
    public double getImage(){return image;}
    public void setReal(double n){real=n;}
    public double getReal(){return real;}
    public static MyComplex add(MyComplex m,MyComplex n){
        MyComplex a=new MyComplex();
        a.setImage(m.getImage()+n.getImage());
        a.setReal(m.getReal()+n.getReal());
        return a;
    }
    public static MyComplex sub(MyComplex m,MyComplex n){
        MyComplex a=new MyComplex();
        a.setImage(m.getImage()-n.getImage());
        a.setReal(m.getReal()-n.getReal());
        return a;
    }
    public static MyComplex mul(MyComplex m,MyComplex n){
        MyComplex a=new MyComplex();
        a.setImage(m.getImage()*n.getReal()+n.getImage()*m.getReal());
        a.setReal(m.getReal()*n.getReal()-m.getImage()*n.getImage());
        return a;
    }
    public static MyComplex div(MyComplex m,MyComplex n){
        MyComplex a=new MyComplex();
        a.setImage((m.getImage()*n.getReal()-n.getImage()*m.getReal())/(n.getImage()*n.getImage()+n.getReal()*n.getReal()));
        a.setReal((m.getReal()*n.getReal()-m.getImage()*n.getImage())/(n.getImage()*n.getImage()+n.getReal()*n.getReal()));
        return a;
    }
    public static void main(String[] args) {
        MyComplex m1=new MyComplex(3.4,8.0);
        MyComplex m2=new MyComplex(3.4,8.0);
        System.out.println("m1 = "+m1);
        System.out.println("m2 = "+m2);
        System.out.println("m1 == m2 = "+(m1==m2));
        System.out.println("m1.equals(m2) = "+m1.equals(m2));
        MyComplex m3 = new MyComplex(4.4,-8.9);
        System.out.println("m3 = "+m3);
        MyComplex m4 = MyComplex.add(m1,m3);
        MyComplex m5 = MyComplex.sub(m2,m3);
        MyComplex m6 = MyComplex.mul(m1,m2);
        MyComplex m7 = MyComplex.div(m1,m2);
        System.out.println("m1 + m3 = "+m4);
        System.out.println("m2 - m3 = "+m5);
        System.out.println("m1 * m2 = "+m6);
        System.out.println("m1 / m2 = "+m7);
    }
}