class StringBuffertest{
  public static void main(String[] args){
    String str1="从来富贵都是梦";  
    str1=str1+"未有圣贤不读书";  //注意此str1的存储的地址已发生变化
    System.out.println(str1);
    StringBuffer sb1=new StringBuffer("精思生智慧，");
    StringBuffer sb2=sb1.append("慧可解怨！");  //注意sb1和sb2指向同一个地址
    sb2.insert(6,"识可转智，");
    System.out.println("sb1="+sb1);
    System.out.println("sb2="+sb2);
    sb1.append(Math.E);
    System.out.println(sb1);
    System.out.println(sb1.charAt(2));
    System.out.println("sb1的长度="+sb1.length());
    System.out.println("sb1的容量="+sb1.capacity());
  }
}