import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitMail {
 public SplitMail() {
 }

 public Map<String, String> splitMail(List<String> mails) {
  String userName;
  String mailUrl;
  Map<String, String> hp = new HashMap<String, String>();
  for (String mail : mails) {
   int first = mail.indexOf("@");
   userName = mail.substring(0, first);
   mailUrl = mail.substring(first, mail.length());
   System.out.println(" 分离后的用户名:" + userName + "\n" + "分离后的邮箱地址:"
     + mailUrl);
   hp.put(userName, mailUrl);
  }

  return hp;
 }

 public static void main(String[] args) {
  List<String> mails = new ArrayList<String>();
  mails.add("aa@sina.com");
  mails.add("bb@163.com");
  mails.add("cc@sohu.com");
  Map<String, String> map = new SplitMail().splitMail(mails);
 }
}