import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class MyTable extends JFrame implements ActionListener
{  
    JTable table;
    Object a[][];
    Object name[]={"产品名称","单价","销售量","销售额"};
    JButton computerRows,computerColums;
    JTextField inputRowsNumber;
    int initRows=1;
    JPanel pSouth,pNorth;
    int count=0,rowsNumber=0;
    MyTable()
    { 
      computerRows=new JButton("每件产品销售额");
      computerColums=new JButton("总销售额");
      inputRowsNumber=new JTextField(10);
      computerRows.addActionListener(this);
      computerColums.addActionListener(this);
      inputRowsNumber.addActionListener(this);
      pSouth=new JPanel();
      pNorth=new JPanel();
      pNorth.add(new JLabel("输入表格行数,回车确认"));
      pNorth.add(inputRowsNumber);
      pSouth.add(computerRows);
      pSouth.add(computerColums);
      getContentPane().add(pSouth,BorderLayout.SOUTH);
      getContentPane().add(pNorth,BorderLayout.NORTH);
      getContentPane().add(new JScrollPane(table),BorderLayout.CENTER);
      setSize(370,250);
      setVisible(true);
      getContentPane().validate();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 
    public void actionPerformed(ActionEvent e)
   { 
     if(e.getSource()==inputRowsNumber)
      {
        count=0;
        initRows=Integer.parseInt(inputRowsNumber.getText());
        a=new Object[initRows][5];
        for(int i=0;i<initRows;i++)
          {
            for(int j=0;j<5;j++)
             {
                a[i][j]="0";
             }    
          }  
        table=new JTable(a,name);
        table.setRowHeight(20);
        getContentPane().removeAll();
        getContentPane().add(new JScrollPane(table),BorderLayout.CENTER);
        getContentPane().add(pSouth,BorderLayout.SOUTH);
        getContentPane().add(pNorth,BorderLayout.NORTH);
        validate();
      }
     else if(e.getSource()==computerRows)
      {
        int rows=table.getRowCount();     //获取现有表格的行数。
        for(int i=0;i<rows;i++)
        { 
          double sum=1;
          boolean boo=true;
          for(int j=1;j<=2;j++)
            { 
               try{
                     sum=sum*Double.parseDouble(a[i][j].toString());
                  }
               catch(Exception ee)
                  {
                      boo=false;
                      table.repaint();       //表格更新显示。
                  }
               if(boo==true)
                  {
                     a[i][3]=""+sum;         //修改数组中的数据。
                     table.repaint();
                  }
             }
         }  
      }
     else if(e.getSource()==computerColums)
      {  
         if(count==0)
           {
              rowsNumber=table.getRowCount();     //获取表格的目前的行数。
              count++;
           }
         else
           {
              rowsNumber=table.getRowCount();     ///获取表格的目前的行数。
              rowsNumber=rowsNumber-1;            //不要最后一行。  
           }
         double totalSum=0;
         for(int j=0;j<rowsNumber;j++)
          {
             totalSum=totalSum+Double.parseDouble(a[j][3].toString());
          }
         Object b[][]=new Object[rowsNumber+1][4];//比数组a多一行的数组。
         for(int i=0;i<rowsNumber;i++)            //将数组a的数据拷贝到数组b中。
          {
             for(int j=0;j<4;j++) 
                 b[i][j]=a[i][j];
          }
         b[rowsNumber][0]="一共有"+rowsNumber+"件产品";
         b[rowsNumber][3]="总销售额："+totalSum;
         a=b;                                   // 重新初始化a。  
         table=new JTable(a,name);
         getContentPane().removeAll();
         getContentPane().add(new JScrollPane(table),BorderLayout.CENTER);
         getContentPane().add(pSouth,BorderLayout.SOUTH);
         getContentPane().add(pNorth,BorderLayout.NORTH);
         validate();
      }
    }
}
public class TableDemo
{
    public static void main(String args[ ])
    {  
       MyTable win=new MyTable();  
    }
}
