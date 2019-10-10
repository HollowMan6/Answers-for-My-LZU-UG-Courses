class CalculationDemo {
 public static void main(String args[]) {
	Calculation c = new Calculation();
	c.add(10,20);
	c.add(40.0F, 35.65F);
        c.add("早上", "好");
        fushu f1=new fushu(3.4,2.8);
        fushu f2=new fushu(1.6,-7.8);
        System.out.println("f1="+f1);
        System.out.println("f2="+f2);
        c.add(f1,f2);
 }
} 
