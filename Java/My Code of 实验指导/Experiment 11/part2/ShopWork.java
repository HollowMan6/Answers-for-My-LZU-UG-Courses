class ShopWorker implements Runnable {
    static Thread zhangSan, liSi, boss;

    ShopWorker() {
        boss = new Thread();
        zhangSan = new Thread();
        liSi = new Thread();
        zhangSan.setName("张三");
        liSi.setName("李四");
        boss.setName("老板");
    }

    public void run() {
        int i = 0;
        if (Thread.currentThread() == zhangSan) {
            while (true) {
                try {
                    i++;
                    System.out.println(Thread.currentThread().getName() + " 已搬了" + i + " 箱货物，休息一会儿");
                    if (i == 3)
                        return;
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println(boss.getName() + " 让" + Thread.currentThread().getName() + " 继续工作");
                }
            }
        } else if (Thread.currentThread() == liSi) {
            while (true) {
                try {
                    i++;
                    System.out.println(Thread.currentThread().getName() + " 已搬了" + i + " 箱货物，休息一会儿");
                    if (i == 3)
                        return;
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println(boss.getName() + " 让" + Thread.currentThread().getName() + " 继续工作");
                }
            }
        } else if (Thread.currentThread() == boss) {
            while (true) {
                zhangSan.interrupt();
                liSi.interrupt();
                if (!(zhangSan.isAlive() || liSi.isAlive())) {
                    System.out.println("下班了");
                    return;
                }
            }
        }
    }
}
class ShopWork{
    public static void main(String[] args) {
        ShopWorker shop=new ShopWorker();
        shop.zhangSan.start();
        shop.liSi.start();
        shop.boss.start(); 
    }
}