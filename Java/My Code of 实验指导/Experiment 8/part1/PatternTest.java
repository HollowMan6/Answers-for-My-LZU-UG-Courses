import java.util.regex.*;
class PatternTest{
    public static void main(String[] args) {
        Pattern p;
        Matcher m;
        String s1="0A1A2A3A4A5A6A7A8A9";
        p=Pattern.compile("\\dA\\d");
        m=p.matcher(s1);
        while(m.find()){
            String str=m.group();
            System.out.print("从"+m.start()+"到"+m.end()+"匹配模式子序列");
            System.out.println(str);
        }
        String temp=m.replaceAll("***");
        System.out.println(temp);
        System.out.println(s1);
        m=p.matcher("9A00A3");
        if(m.matches()){
            String str=m.group();
            System.out.println(str);
        }else{
            System.out.println("不完全匹配");
        }
        if(m.lookingAt()){
            String str=m.group();
            System.out.println(str);
        }
    }
}