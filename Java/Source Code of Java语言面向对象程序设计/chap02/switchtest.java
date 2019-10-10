class bankaccount
{
String name;
double benjin;
int years;
double rate;
public double getMoney()
{
switch(years)
{
    case 0:rate=0.81;break;
    case 1:rate=4.14;break;
    case 2:rate=4.68;break;
    case 3:
    case 4:rate=5.40;break;
    case 5:rate=5.85;break;
    default:rate=5.85;break;
}
double x;
if(years==0)
x=benjin*rate/100+benjin;
else
 x=benjin*rate*years/100+benjin;
 return x;
}  
}
class switchtest
{
   public static void main(String[] args)
   {
      bankaccount  zhangsan=new bankaccount();
      zhangsan.name="zhangsan";
      zhangsan.benjin=200000000;
      zhangsan.years=3;
      System.out.println("zhangsan's Money="+zhangsan.getMoney());
   }
}
