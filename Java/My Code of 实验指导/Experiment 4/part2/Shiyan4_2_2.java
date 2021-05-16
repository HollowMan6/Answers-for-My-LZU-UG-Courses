class Shiyan4_2_2 {
    public static void main(String[] args) {
        int n=100;
        Sieve s = new Sieve();                      //创建Sieve对象
        s.executeFilter(n);
        System.out.println("小于"+n+"的素数有：");
        s.outFilter();
    }
}
class Counter{                                      //数字产生器
    private int value;                              //数字产生器的初值
    Counter(int val){value=val;}
    public int getValue(){return value;}
    public void next(){value++;}                    //产生下一个数字
}
class Sieve{                                        //筛子
    final int Max = 100;                            //设定过滤器的最大值
    private int filterCount = 0;                    
    private int[] f;                                //存储过滤器数值的数组
    public Sieve(){f = new int[Max];filterCount=0;}
    public void executeFilter(int n){
        Counter c =new Counter(2);
        for(;c.getValue()<n;c.next()){
            passFilter(c);                          //实施过滤
        }
    }
    public void passFilter(Counter c){
        for(int i=2;i<=Math.sqrt(c.getValue());i++)//**原书中此处代码错误
            if(c.getValue()%i==0) return;           //若为合数则返回
        addElementIntoFilter(c.getValue());         //若为素数则加入过滤器
    }
    public void addElementIntoFilter(int x){
        f[filterCount]=x;
        filterCount++;
    }
    public void outFilter(){
        for(int i=0;i<filterCount;i++){
            System.out.printf("%4d",f[i]);
            if((i+1)%10==0)System.out.println();
        }
    }
}
