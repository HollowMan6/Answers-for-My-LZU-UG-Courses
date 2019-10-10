public class Account {
    private String name; // 姓名
    private String ID; // ID
    private double balance; // 余额
    private int overdraftNum; // 消费次数
    private String[] withdrawAndSave;// 交易明细，假定最多存存1000笔
    private double overdraftLimit = 200;// 透支额度
    private int index; // 交易索引号

    public Account(String Name,String id) {// 构造方法
        index=0;
        name=Name;
        ID=id;
        balance=0.0;
        overdraftNum=0;
        withdrawAndSave = new String[1000];
    }

    public Account() {// 构造方法
        index = 0;
        name = "";
        ID = "";
        balance = 0.0;
        overdraftNum = 0;
        withdrawAndSave = new String[1000];
    }

    public boolean compareID(String ID1, String ID2) {// 比较ID是否相等
        return ID1.equals(ID2);
    }

    public boolean equalsID(Account accounta, Account accountb) {// 比较账户是否信息相同
        return accounta.equals(accountb);
    }

    public boolean saveMoney(double money) {// 存钱
        if (index >= 2000)// 存满之后覆盖最旧的记录重新存
            index = 0;
        if (money < 0) {
            System.out.println("错误！请输入一个正数");
            return false;
        } else {
            this.balance += money;
            this.withdrawAndSave = new String[1000];
            this.withdrawAndSave[index] = "Save " + money;
            this.index += 1;
            return true;
        }
    }

    public boolean getMoney(double balance) {// 取钱
        if (index >= 2000)// 存满之后覆盖最旧的记录重新存
            index = 0;
        if (this.balance - balance <= 0) {
            System.out.println("错误！取款大于余额！");
            return false;
        } else {
            this.balance = this.balance - balance;
            this.withdrawAndSave = new String[1000];
            this.withdrawAndSave[index] = "Withdraw " + balance;
            this.index += 1;
            return true;
        }
    }

    public boolean consumed(double balance) {// 消费
        if (index >= 2000)// 存满之后覆盖最旧的记录重新存
            index = 0;
        if (this.balance - balance < -1 * overdraftLimit) {
            System.out.println("错误！此卡消费超过所能透支额度！");
            return false;
        } else {
            this.balance = this.balance - balance;
            this.overdraftNum += 1;
            this.withdrawAndSave = new String[1000];
            this.withdrawAndSave[index] = "Consume " + balance;
            this.index += 1;
            return true;
        }
    }

    public String getName() {// 返回姓名
        return this.name;
    }

    public void setName(String n) {
        this.name=n;
    }
    
    public void setID(String id) {
        this.ID = id;
    }
    
    public void setBalance(double b) {
        this.balance = b;
    }

    public void setOverdraftNum(int o) {
        this.overdraftNum = o;
    }

    public void setIndex(int i) {
        this.index = i;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public String[] getWithdrawAndSave() {
        return this.withdrawAndSave;
    }

    public void setWithdrawAndSave(String[] str) {
        this.withdrawAndSave=str;
    }

    public double getBalance() {// 返回余额
        return this.balance;
    }

    public String getID() {// 返回ID
        return this.ID;
    }

    public int getOverdraftNum() {// 返回消费刷卡次数
        return this.overdraftNum;
    }

    public void display() {// 显示账号信息
        System.out.println("姓名:" + this.getName());
        System.out.println("账户ID:" + this.getID());
        System.out.println("账户余额:" + this.getBalance());
        System.out.println("消费刷卡次数:" + this.getOverdraftNum());
    }

    public String toString() {
        String info="";
        info+="\n姓名:" + this.getName();
        info+="\n账户ID:" + this.getID();
        info+="\n账户余额:" + this.getBalance();
        info+="\n消费刷卡次数:" + this.getOverdraftNum();
        return info;
    }
}