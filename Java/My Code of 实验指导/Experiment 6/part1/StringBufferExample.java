class StringBufferExample{
    public static void main(String[] args) {
        StringBuffer str = new StringBuffer("ABCDEFG");
        str.append("123456789");
        System.out.println(str);
        str.setCharAt(1,'b');
        System.out.println(str);
        str.insert(6, "Love");
        System.out.println(str);
        int index = str.indexOf("1");
        str.delete(index, index+4);
        int n = str.length();
        str.replace(n-3, n, "七八九");
        System.out.println(str);
        StringBuffer otherStr = new StringBuffer("we love you");
        int start = 0;
        char c = '\0';
        while(start!=-1){
            if(start!=0){
                start=start+1;
            }
            c=otherStr.charAt(start);
            if(Character.isLowerCase(c)){
                c=Character.toUpperCase(c);
                otherStr.setCharAt(start, c);
            }
            start=otherStr.indexOf(" ",start);
        }
        System.out.println(otherStr);
        StringBuffer yourStr = new StringBuffer("i love THIS GaME");
        for(int i=0;i<yourStr.length();i++){
            char c1=yourStr.charAt(i);
            if(Character.isLowerCase(c1)){
                c1=Character.toUpperCase(c1);
                yourStr.setCharAt(i, c1);
            }
            else if(Character.isUpperCase(c1)){
                c1=Character.toLowerCase(c1);
                yourStr.setCharAt(i, c1);
            }
        }
        System.out.println(yourStr);
    }
}