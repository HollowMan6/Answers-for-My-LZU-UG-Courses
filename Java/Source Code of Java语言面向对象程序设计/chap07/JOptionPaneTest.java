import javax.swing.*;
class JOptionPaneTest
{
    public static void main(String[] args)
    {
         double x,y,z=0;
         String str;
         char op='+';
         int a;
         str=JOptionPane.showInputDialog("请输入你的x值：");
         x=Double.parseDouble(str);
         str=JOptionPane.showInputDialog("请输入你的y的值：");
         y=Double.parseDouble(str);
         str=JOptionPane.showInputDialog("请输入你的运算符(+-*/)：");
         op=str.charAt(0);
         a=JOptionPane.showConfirmDialog(null,"确认输入正确吗？","确认对话框",JOptionPane.YES_NO_OPTION);

         if(a==JOptionPane.YES_OPTION)
         {
            switch(op)
            {
               case '+': z=x+y;break;
               case '-': z=x-y;break;
               case '*': z=x*y;break;
               case '/': z=x/y;break;
            }
         JOptionPane.showMessageDialog(null,"两数之乘积："+z);
         }
         else
         {
            JOptionPane.showMessageDialog(null,"很遗憾！请重新运行程序。");           
         }
     }
}