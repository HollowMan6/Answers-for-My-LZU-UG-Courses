class Stringtest{
  public static void main(String[] args){
     String str1="书到用时方恨少，事非经过不知难";
     String str2=new String("若论成道本来易, 欲除妄想真个难");
     String str3="The heart of the wise is in the house of mourning; but the heart of fools is in the house of mirth.";
     String str4=new String("Whatsoever thy hand findeth to do, do it with thy might; for there is no work, nor device, nor knowledge, nor wisdom, in the grave, whither thou goest.");
     String str5=null;
     
     System.out.println("str1 的长度是 "+str1.length());
     System.out.println("str3 的长度是 "+str3.length());
     str5=str1+str2;
     System.out.println("str5="+str5);
     System.out.println(str3.concat(str4));
     System.out.println(str1.substring(8));
     String str6="理可顿悟,事须渐修";
     String str7=new String("理可顿悟,事须渐修");
     System.out.println(str6.equals(str7));
     System.out.println(str1.compareTo(str2));
     System.out.println(str2.charAt(2));
     System.out.println(str3.indexOf("wise"));
     System.out.println(str4.replace('h','*'));
     System.out.println(str3.toLowerCase());
     System.out.println(str4.toUpperCase());
     String str8="  闲观扑纸蝇，笑痴人自生障碍，静觇竞巢鹊，叹杰士空逞英雄。  ";
     System.out.println(str8.trim());
  }
}