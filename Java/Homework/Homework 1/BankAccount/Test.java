import java.io.IOException;

public class Test {// 测试类以及测试数据
    //因为绝大部分Account类中的方法都在Bank类中调用了，所以对于部分Account不进行额外测试了
    public static void main(String[] args) throws IOException {
        Bank bank = new Bank("Hbank","32","0931-8888888");
        Account account = new Account("Steven","3201808909028098");
        Account accounta = new Account("Steve", "3201808909028099");
        System.out.println("测试 bank.checkID："+bank.checkID(account.getID()));
        System.out.println();
        System.out.println("测试 bank.addAccount："+bank.addAccount(account));
        System.out.println();
        System.out.println("测试 bank.addAccount：" + bank.addAccount(accounta));
        System.out.println();
        account=bank.searchAccount(account.getID());
        System.out.println("测试 bank.searchAccount："+account);
        System.out.println();
        System.out.println("测试 bank.save："+bank.save(account.getID(),20));
        System.out.println();
        System.out.println("测试 bank.save(accountb)：" + bank.save(accounta.getID(), 20));
        System.out.println();
        System.out.println("测试 bank.get："+bank.get(account.getID(),10));
        System.out.println();
        System.out.println("测试 bank.get(accountb超出取款测试)：" + bank.get(accounta.getID(), 30));
        System.out.println();
        System.out.println("测试 bank.consume："+bank.consume(account.getID(), 205));
        System.out.println();
        System.out.println("测试 bank.checkBalance："+bank.checkBalance(account.getID()));
        System.out.println();
        System.out.println("测试 bank.ShowAccount：");
        bank.ShowAccount();
        System.out.println();
        System.out.println("测试 bank.ShowindividualOverdraftTime："+bank.ShowindividualOverdraftTime(account.getID()));
        System.out.println();
        System.out.println("测试 bank.consume(超额消费失败测试)：" + bank.consume(account.getID(), 7));
        System.out.println();
        System.out.println("测试 bank.consume(再次超额消费)：" + bank.consume(account.getID(), 4));
        System.out.println();
        System.out.println("测试 bank.ShowindividualOverdraftTime（显示2次超额次数）：" + bank.ShowindividualOverdraftTime(account.getID()));
        System.out.println();
        System.out.println("测试 bank.checkOverdraftAccount：");
        bank.checkOverdraftAccount();
        System.out.println();
        System.out.println("测试 bank.getTotalBalance："+bank.getTotalBalance());
        System.out.println();
        System.out.println("测试 bank.deleteAccount："+bank.deleteAccount(account.getID()));
        System.out.println();
        System.out.println("显示bank.deleteAccount效果：");
        bank.ShowAccount();
        System.out.println("测试 account.equalsID(account和accounta):"+account.equalsID(account, accounta));
        System.out.println();
        System.out.println("测试 account.compareID："+account.compareID(account.getID(), account.getID()));
        System.out.println();
    }
}