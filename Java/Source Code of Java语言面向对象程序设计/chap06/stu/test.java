class test
{
  public static void main(String args[])
  {
     SingleStudent s1=SingleStudent.getInstance();
     SingleStudent s2=SingleStudent.getInstance();
     
     s2.setName("bbbb");
     s2.setAge(34);
     s2.setSex('F');

     s1.inputData();
     s2.inputData();
     System.out.println("s1:"+s1);
     System.out.println("s2:"+s2);
  }
}