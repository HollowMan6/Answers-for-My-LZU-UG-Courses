import java.io.*;

public class Bank {
    private String name; // 银行名称
    private String bincode; // BIN编码
    private String telNum; // 服务电话
    //private Account[] bankAccount; 因为我都是以ID为索引，每次遍历文件查找，所以认为没有必要，删去了这个功能

    public Bank(String name,String tel) { // 构造方法
        this.name = name;
        telNum=tel;
        //this.bankAccount = new Account[2000];
    }

    public Bank(String name, String bincode, String tel) {// 构造方法
        this.name = name;
        this.bincode = bincode;
        telNum = tel;
        //this.bankAccount = new Account[2000];
    }
    
    public String getName(){
        return name;
    }

    public String getBincode() {
        return bincode;
    }

    public String getTelNum() {
        return telNum;
    }


    public boolean addAccount(Account account) throws IOException {// 增加新账号
        if (this.store(account, "info") && this.store(account, "record"))
            return true;
        else
            return false;
    }

    public boolean checkID(String ID) {// 检查ID输入(16位)是否符合要求
        if (ID.length() == 16 && ID.startsWith(this.bincode))
            return true;
        else
            return false;
    }

    public Account searchAccount(String ID) throws IOException {// 根据ID账号返回对象
        return this.load(ID);
    }

    public boolean store(Account account, String kind) throws IOException {// 写入文件(第二个参数为info时写入账户信息，为record时写入交易记录)
        String ID = account.getID();
        // 存储账号信息
        if (kind == "info") {
            File file = new File("data/info/" + ID + ".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            bw.write(account.getName() + "\n" + account.getID() + "\n" + account.getBalance() + "\n" + account.getOverdraftNum() + "\n"
                    + account.getIndex() + "\n");
            bw.close();
            return true;
        }
        // 存储交易记录
        else if (kind == "record") {
            File file = new File("data/record/" + ID + ".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            for (int i = 0; i < account.getIndex(); i++)
                if (account.getWithdrawAndSave()[i] == null)
                    continue;
                else
                    bw.write(account.getWithdrawAndSave()[i] + "\n");
            bw.close();
            return true;
        } else
            return false;
    }

    public Account load(String ID) throws IOException {// 从文件读入
        Account account = new Account();
        account.setWithdrawAndSave(new String[1000]);
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null; // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
        fis = new FileInputStream("data/info/" + ID + ".txt");// FileInputStream
        // 从文件系统中的某个文件中获取字节
        isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
        br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
        account.setName(br.readLine());
        account.setID(br.readLine());
        account.setBalance(Double.parseDouble(br.readLine()));
        account.setOverdraftNum(Integer.parseInt(br.readLine()));
        account.setIndex(Integer.parseInt(br.readLine()));
        br.close();
        isr.close();
        fis.close();
        FileInputStream fiss = null;
        InputStreamReader isrr = null;
        BufferedReader brr = null; // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
        fiss = new FileInputStream("data/record/" + ID + ".txt");// FileInputStream
        // 从文件系统中的某个文件中获取字节
        isrr = new InputStreamReader(fiss);// InputStreamReader 是字节流通向字符流的桥梁,
        brr = new BufferedReader(isrr);// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
        String data = new String();
        while ((data = brr.readLine()) != null) {
            account.getWithdrawAndSave()[account.getIndex()] = data;
            account.setIndex(account.getIndex()+1);
        }
        brr.close();
        isrr.close();
        fiss.close();
        return account;
    }

    public boolean save(String ID, double money) throws IOException {// 存钱
        Account account = this.load(ID);
        if (account.saveMoney(money)) {
            this.store(account, "record");
            this.store(account, "info");
            return true;
        } else
            return false;
    }

    public boolean get(String ID, double money) throws IOException {// 取钱
        Account account = this.load(ID);
        if (account.getMoney(money)) {
            this.store(account, "record");
            this.store(account, "info");
            return true;
        } else
            return false;
    }

    public boolean consume(String ID, double balance) throws IOException {// 消费
        Account account = this.load(ID);
        if (account.consumed(balance)) {
            this.store(account, "record");
            this.store(account, "info");
            return true;
        } else
            return false;
    }

    public boolean deleteAccount(String ID) {// 删除账号
        File files = new File("data/info/" + ID + ".txt");
        File filer = new File("data/record/" + ID + ".txt");
        return files.delete() && filer.delete();
    }

    public double checkBalance(String ID) throws IOException {// 检查账号ID余额
        Account account = this.load(ID);
        return account.getBalance();
    }

    public void ShowindividualAccount(String ID) throws IOException {// 显示个人账号信息
        Account account = this.load(ID);
        account.display();
    }

    public void ShowAccount() throws IOException {// 显示所有账号信息
        File file = new File("data/info");
        File[] fs = file.listFiles(); // 遍历path下的文件和目录，放在File数组中
        for (File f : fs) { // 遍历File[]数组
            if (!f.isDirectory()) // 非目录(即文件)
            {
                String ID = f.getName().substring(0, 16);
                this.ShowindividualAccount(ID);
            }
        }
    }

    public int ShowindividualOverdraftTime(String ID) throws IOException {// 显示账号ID的消费次数
        Account account = this.load(ID);
        return account.getOverdraftNum();
    }

    public void checkOverdraftAccount() throws IOException {// 显示有欠款的账号信息
        File file = new File("data/info");
        File[] fs = file.listFiles(); // 遍历path下的文件和目录，放在File数组中
        for (File f : fs) { // 遍历File[]数组
            if (!f.isDirectory()) // 非目录(即文件)
            {
                String ID = f.getName().substring(0, 16);
                Account account = this.load(ID);
                if (account.getBalance() < 0)
                    account.display();
            }
        }
    }

    public double getTotalBalance() throws IOException {// 银行总的余额
        double TotalBalance = 0;
        File file = new File("data/info");
        File[] fs = file.listFiles(); // 遍历path下的文件和目录，放在File数组中
        for (File f : fs) { // 遍历File[]数组
            if (!f.isDirectory()) // 非目录(即文件)
            {
                String ID = f.getName().substring(0, 16);
                Account account = this.load(ID);
                TotalBalance += account.getBalance();
            }
        }
        return TotalBalance;
    }
}