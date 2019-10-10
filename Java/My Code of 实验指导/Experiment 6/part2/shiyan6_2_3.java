public class shiyan6_2_3{
    public static void main(String[] args) {
        StringBuilder s1 = new StringBuilder();
        System.out.println("StringBuilder对象的默认大小空间为："+s1.capacity());
        StringBuilder s2 = new StringBuilder("abcd1234");
        System.out.println("当前字符串为："+s2.toString()+", 容量为："+s2.capacity()+", 长度为："+s2.length());
        System.out.println("s2.delete(3,6) = "+s2.delete(3, 6).toString());
        System.out.println("s2.reverse() = "+s2.reverse().toString());
        System.out.println("s2.append(20.2) = "+s2.append(20.2).toString());
        s2=s2.insert(2, "1234567890");
        System.out.println("当前字符串："+s2.toString()+", 容量为："+s2.capacity());
        s2=s2.insert(3, "1234567890");
        System.out.println("当前字符串："+s2.toString()+", 容量为："+s2.capacity());
    }
}