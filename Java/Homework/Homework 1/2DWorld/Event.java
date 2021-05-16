public class Event {
    private Partical partical;// 质点
    private String describe = "Creation";// 事件描述
    private double time; // 时间

    public Event(Partical partical, double time) {
        this.partical = partical;
        this.time = time;
    }

    public Event(Partical partical, double time, String describe) {// 重载增加事件描述
        this.partical = partical;
        this.time = time;
        this.describe = describe;
    }

    public double gettime() {
        return this.time;
    }

    public boolean settime(double newvalue) {
        this.time = newvalue;
        return true;
    }

    public Partical getpartical() {
        return this.partical;
    }

    public boolean setpartical(Partical newvalue) {
        this.partical = newvalue;
        return true;
    }

    public String getdescribe() {
        return this.describe;
    }

    public boolean setdescribe(String newvalue) {
        this.describe = newvalue;
        return true;
    }

    public double timepass(int mul) {//设定事件流逝(倍数)
        return this.time = (this.time + 1) * mul;
    }

    public String toString() {
        String str = "";
        str = partical.toString()+"\ndescribe: "+describe+"\ntime: "+time;
        return str;
    }

    public int hashCode() {
        int r = 17;
        r = r * 31 + partical.hashCode();
        r = r * 31 + describe.hashCode();
        r = r * 31 + (int) (time * 10);
        return r;
    }

    public void sort(Event... s) { //按时间排序，输出事件描述
        Event[] ss = new Event[s.length];
        int i = 0;
        for (Event a : s) {
            ss[i++] = a;
        }
        for (int i2 = 0; i2 < ss.length - 1; i2++)
            for (int j = i2 + 1; j < ss.length; j++) {
                if (ss[i2].gettime() > ss[j].gettime()) {
                    Event tmp = ss[i2];
                    ss[i2] = ss[j];
                    ss[j] = tmp;
                }
            }
        for (int j=0;j<ss.length;j++){
            System.out.print(ss[j].getdescribe());
            if(j==ss.length-1)
                System.out.println();
            else
                System.out.print(" > ");

        }
    }

    public double gettimediff(Event b) {
        return time - b.gettime();
    }
}