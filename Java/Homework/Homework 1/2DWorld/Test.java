public class Test {
    public static void main(String[] args) {
        Partical a = new Partical(121, 34, 20);
        Partical b = new Partical(135, 37, 30, 6378137.0);
        System.out.println("测试 a.setlongitude():" + a.setlongitude(120));
        System.out.println("测试 a.getlongitude():" + a.getlongitude());
        System.out.println("测试 a.setlatitude():" + a.setlatitude(120));
        System.out.println("测试 a.getlatitude():" + a.getlatitude());
        System.out.println("测试 b.setmass():" + b.setmass(40));
        System.out.println("测试 b.getmass():" + b.getmass());
        System.out.println("测试 a.toString():" + a.toString());
        System.out.println("测试 a.hashCode():" + a.hashCode());
        System.out.println("测试 a.getDistance():"
                + a.getDistance(a.getlatitude(), a.getlongitude(), b.getlatitude(), b.getlongitude()));
        System.out.println("测试 a.getgravity():" + a.getgravity(b));
        Event m = new Event(a, 0);
        Event n = new Event(a, 1, "Creation");
        System.out.println("测试 n.settime():" + n.settime(2));
        System.out.println("测试 n.gettime():" + n.gettime());
        System.out.println("测试 n.setpartical():" + n.setpartical(b));
        System.out.println("测试 n.getpartical():" + n.getpartical());
        System.out.println("测试 m.setdescribe():" + m.setdescribe("Recreation"));
        System.out.println("测试 n.getdescribe():" + n.getdescribe());
        System.out.println("测试 n.timepass():" + n.timepass(2));
        System.out.println("测试 n.toString():" + n.toString());
        System.out.println("测试 n.hashCode():" + n.hashCode());
        System.out.println("测试 n.sort():");
        n.sort(m,n);
        System.out.println("测试 n.gettimediff():" + n.gettimediff(m));
    }
}