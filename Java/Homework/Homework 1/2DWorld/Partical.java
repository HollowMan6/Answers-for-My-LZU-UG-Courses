public class Partical {
    private double longitude;// 经度(以0——360度计)
    private double latitude;// 纬度(以0——360度计)
    private double mass; // 质量
    private final double pi = 3.1415926535;
    private double radio = 6378137.0;// 定义地球半径

    public Partical(double longitude, double latitude, double mass) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.mass = mass;
    }

    public Partical(double longitude, double latitude, double mass, double radio) {// 重载以修改球面半径
        this.longitude = longitude;
        this.latitude = latitude;
        this.mass = mass;
        this.radio = radio;
    }

    public double getlongitude() {
        return this.longitude;
    }

    public boolean setlongitude(double newvalue) {
        this.longitude = newvalue;
        return true;
    }

    public double getlatitude() {
        return this.latitude;
    }

    public boolean setlatitude(double newvalue) {
        this.latitude = newvalue;
        return true;
    }

    public double getmass() {
        return this.mass;
    }

    public boolean setmass(double newvalue) {
        this.mass = newvalue;
        return true;
    }

    public String toString() {
        String str="";
        str=str+"\nlongitude: "+longitude+"\nlatitude: "+latitude+"\nmass: "+mass;
        return str;
    }

    public int hashCode() {
        int r = 20;
        r = r * 11 + (int) (longitude * 10);
        r = r * 11 + (int) (latitude * 10);
        r = r * 11 + (int) (mass * 10);
        r = r * 11 + (int) (radio * 10);
        return r;
    }

    public double getDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        double dlat, dlon;
        double a, c, distance;
        dlon = Math.abs((longitude2 - longitude1)) * pi / 180;
        dlat = Math.abs((latitude2 - latitude1)) * pi / 180;
        a = (Math.sin(dlat / 2) * Math.sin(dlat / 2)) + Math.cos(latitude1 * pi / 180) * Math.cos(latitude2 * pi / 180)
                * (Math.sin(dlon / 2) * Math.sin(dlon / 2));
        if (a == 1.0)
            c = pi;
        else
            c = 2 * Math.atan(Math.sqrt(a) / Math.sqrt(1 - a));
        distance = radio * c;
        return distance;
    }

    public double getgravity(Partical b) {
        return Math.abs(mass - b.getmass()) / Math.abs(getDistance(latitude, longitude, b.getlatitude(), b.getlongitude()));
    }
}