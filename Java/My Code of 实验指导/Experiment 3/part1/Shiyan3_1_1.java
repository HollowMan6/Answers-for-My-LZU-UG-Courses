public class Shiyan3_1_1 {
    public static void main(String[] args) {
        System.out.println("我们已经知道地球的平均半径为6370.856km");
        System.out.println("也知道公认的地球质量为5.98 x 10^24kg");
        System.out.println("我们就可以使用数学公式计算地球的平均密度为：");
        double radius =6.370856E6;//定义储存半径的浮点型变量，用科学计数法
        double mass = 5.98E24;    //定义储存质量的浮点型变量
        double volume = 4*Math.PI*Math.pow(radius,3)/3;
                //球的体积公式，注意数学公式在程序中的变换，用到了Math类中的常量和方法
        double density = mass/volume;//计算平均密度
        System.out.print(density+"(千克/立方米)");
    }
}