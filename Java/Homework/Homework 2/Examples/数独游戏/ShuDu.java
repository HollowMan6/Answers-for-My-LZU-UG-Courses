import java.awt.*;   
import java.awt.event.*;   
import java.io.*;   
import javax.swing.*;   
import java.util.*;   
  
public class ShuDu extends JFrame implements ActionListener,ItemListener{   
  
  private MenuBar menubar=new MenuBar();  //创建菜单栏 
	
  //分别创建 文件 结果  帮助 菜单
  private Menu menu_file=new Menu("文件");   
  private Menu menu_edit=new Menu("结果");   
  private Menu menu_help=new Menu("帮助");  
  
  //分别创建 答案 提交  重来 开局 作者  退出  菜单项
  private MenuItem item_ans=new MenuItem("答案");   
  private MenuItem item_sol=new MenuItem("提交");   
  private MenuItem item_rem=new MenuItem("重来");   
  private MenuItem item_next=new MenuItem("开局");   
  private MenuItem item_auther=new MenuItem("作者");   
  private MenuItem item_exit=new MenuItem("退出");   
  
  //分别创建 答案 提交  重来 开局 作者  退出  按钮
  private JButton button_sol=new JButton("提交");   
  private JButton button_ans=new JButton("答案");   
  private JButton button_rem=new JButton("重来");   
  private JButton button_ext=new JButton("退出");   
  private JButton button_aur=new JButton("作者");   
  private JButton button_next=new JButton("开局");  
  
  //创建具有默认数据模型的复选框组件box
  private JComboBox box=new JComboBox();   
  
  //创建静态整形二维数组data\ansdata\row\col和三维数组sq1
  static int data[][]=new int[10][10];   
  static int ansdata[][]=new int[10][10];   
  static int row[][]=new int[10][10];   
  static int col[][]=new int[10][10];   
  static int sql[][][]=new int[4][4][10];  
  
  //创建二维数组文本框组件text
  static JTextField text[][]=new JTextField[10][10];   
  static String atext[][]=new String[10][10];   
  static int hard=2;   
  static int datahard[]={2,3,5}; // 不同的难度   
    
  class ShuDuAns extends JFrame{ 
    private JTextField atext[][]=new JTextField[10][10];   
    public ShuDuAns(){   
      super("答案");   
      //JPanel apanel_but=new JPanel(new FlowLayout(FlowLayout.LEFT)); //流程布局  
      JPanel apanel_txt=new JPanel(new GridLayout(3,3,2,2));   //栏格布局
      JPanel apanel[]=new JPanel[10];  //创建面板数组apanel 
       
      for(int i=1;i<=9;i++){   
 	apanel[i]=new JPanel(new GridLayout(3,3)); 	//给面板数组apanel添加GridLayout布局管理器  
	apanel_txt.add(apanel[i]);   			//将面板数组apanel添加到有GridLayout布局管理器的面板apanel_txt中
        int m=(i+2)/3*3-2;   
        int n=((i-1)%3+1)*3-2;   
        for(int j=m;j<=m+2;j++){   
           for(int k=n;k<=n+2;k++){   
             atext[j][k]=new JTextField(Integer.toString(data[j][k]));  	//文本框 
             atext[j][k].setHorizontalAlignment(JTextField.CENTER);  		//横向对齐 
             atext[j][k].setEditable(false);   					//可编辑的
             apanel[i].add(atext[j][k]);   					//将二维数组文本框atext添加到数组面板apanel中
         
           }   
        }   
      } 
  
      this.add(apanel_txt);  
      this.setSize(300,300);   		//大小
      this.setLocation(200,200);  	//位置 
      this.setVisible(true);   		//可见性
      this.setResizable(false);   	//重设大小    
      }  
  }   
  
 static int DFS(){   
     for(int i=1;i<=9;i++){   
         for(int j=1;j<=9;j++){   
             if(data[i][j]==0){   
                 for(int k=1;k<=9;k++){   
                     if( row[i][k]==0 && col[j][k]==0 && sql[(i+2)/3][(j+2)/3][k]==0 ){   
                         data[i][j]=k;   
                         row[i][k]=1;   
                         col[j][k]=1;   
                         sql[(i+2)/3][(j+2)/3][k]=1;   
                         if( DFS()==1 )   
                             return 1;   
                         else{   
                             data[i][j]=0;   
                             row[i][k]=0;   
                             col[j][k]=0;   
                             sql[(i+2)/3][(j+2)/3][k]=0;   
                         }   
                     }   
                     if(k==9)   
                         return 0;   
                 }   
             }   
         }   
     }   
     return 1;   
 }   
    
 static void set_data_zero(){   
     for(int i=0;i<=9;i++){   
         for(int j=0;j<=9;j++){   
             data[i][j]=0;   
         }   
     }   
 }   
    
 static void setnum(){   
     setzero();   
     set_data_zero();   
     for(int i=1;i<=9;i++){   
         int n =(int)(Math.random()*100)+1; //产生随机的数字  
         int j=i*3-((i+2)/3*8-6);   
         data[i][j]=n%9+1;    
         int k=data[i][j];   
         row[i][k]=1;   
         col[j][k]=1;   
         sql[(i+2)/3][(j+2)/3][k]=1;   
      
     }   
     DFS();   
 }   
    
 static void setzero(){   
    for(int i=0;i<=9;i++){   
         for(int k=0;k<=9;k++){   
             row[i][k]=0;   
             col[i][k]=0;   
         }   
      
         for(int j=0;j<=9;j++){   
             for(int k=0;k<=9;k++){   
                 sql[(i+2)/3][(j+2)/3][k]=0;   
             }   
          }   
     }   
 }   
    
 static void settext(){   
     for(int i=1;i<=9;i++){   //hard代表难度   
         for(int j=1;j<=9;j++){
             int n=(int)(Math.random()*100)+1;   
             if(n%hard==0){   
                  text[i][j]=new JTextField(Integer.toString(data[i][j]));   
                  text[i][j].setEditable(false);   //可编辑的
              }   
              else{   
                   text[i][j]=new JTextField();   
               }   
              text[i][j].setHorizontalAlignment(JTextField.CENTER); //横向对齐  
              atext[i][j]=text[i][j].getText();   
         }   
     }   
 }   
    
 static int gettext(){   //判断输入的是否是数字
     for(int i=1;i<=9;i++){   
         for(int j=1;j<=9;j++){   
             try{   
                 int k=Integer.parseInt(text[i][j].getText());   
                 ansdata[i][j]=k;   
             }   
             catch(NumberFormatException nfe){   
                 JOptionPane.showMessageDialog(null,"数据中包括非数字，请重新输入！");    
                 return 0;   
             }   
         }   
      }   
      return 1;   
 }   
    
 static int ans(){   //判断输入的数字是否重复
     setzero();   
     for(int i=1;i<=9;i++){   
         for(int j=1;j<=9;j++){   
             int k=ansdata[i][j];   
             if( k>9 || k<1 ){   
                 return 0;   
             }   
             if( row[i][k]==1 || col[j][k]==1 || sql[(i+2)/3][(j+2)/3][k]==1 ){   
                 return 0;   
             }   
             row[i][k]=1;   
             col[j][k]=1;   
             sql[(i+2)/3][(j+2)/3][k]=1;       
         }   
     }   
     return 1;   
 }   
    
  
  public ShuDu(){   
      super("数独游戏");   
      this.setSize(470,500);  //设置当前组件大小
      this.setLocation(260,130);   //设置当前组件的新位置
      this.setVisible(true);   //设置当前组件可见
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);   
      this.setResizable(false);  //设置窗体不可以由用户调整大小
      JPanel panel_but=new JPanel(new FlowLayout(FlowLayout.LEFT));  //定义一个面板对象panel_but并为该对象配制了FlowLayout布局管理器
      JPanel panel_txt=new JPanel(new GridLayout(3,3,2,2));          //定义一个面板对象panel_txt并为该对象配制了GridLayout布局管理器   
      JPanel panel[]=new JPanel[10]; //定义了一个面板数组 
      for(int i=1;i<=9;i++){ 
          panel[i]=new JPanel(new GridLayout(3,3));   
          panel_txt.add(panel[i]);   
          int m=(i+2)/3*3-2;   
          int n=((i-1)%3+1)*3-2;   
          for(int j=m;j<=m+2;j++){   
              for(int k=n;k<=n+2;k++){   
                  panel[i].add(text[j][k]);   
              }   
          }   
      }   
      this.add(panel_but,"South");   
      this.add(panel_txt);   
      menubar.add(menu_file);                      //建立菜单栏
      menubar.add(menu_edit);   
      menubar.add(menu_help);       
      menu_file.add(item_next);   
      menu_file.add(item_ans);   
      menu_file.add(item_exit);   
      menu_edit.add(item_sol);   
      menu_edit.add(item_rem);   
      menu_help.add(item_auther);   
      item_exit.addActionListener(this);           //为菜单项添加动作监听器 
      item_next.addActionListener(this);   
      item_ans.addActionListener(this);   
      item_sol.addActionListener(this);   
      item_auther.addActionListener(this);   
      item_rem.addActionListener(this);     
      this.setMenuBar(menubar);                   //将当前对象的菜单栏设置为指定的menubar菜单栏
      Object pro[]={" 简单 "," 一般 "," 困难 "};  //选择游戏的难度 ，建立一个数组
      box=new JComboBox(pro);   
      if(hard==datahard[0]){   
          box.setSelectedIndex(0);   
      }   
      if(hard==datahard[1]){   
          box.setSelectedIndex(1);   
      }   
      if(hard==datahard[2]){   
          box.setSelectedIndex(2);   
      }   
      panel_but.add(box);   
      box.addItemListener(this);
      panel_but.add(button_next);            //将按钮button_next添加到面板panel_but中 
      button_next.addActionListener(this);   //给按钮添加动作监听器 
      panel_but.add(button_ans);             //将按钮button_ans添加到面板panel_but中
      button_ans.addActionListener(this);    //给按钮添加动作监听器 
      panel_but.add(button_sol);             //将按钮button_sol添加到面板panel_but中
      button_sol.addActionListener(this);    //给按钮添加动作监听器
      panel_but.add(button_rem);             //将按钮button_rem添加到面板panel_but中
      button_rem.addActionListener(this);    //给按钮添加动作监听器      
      panel_but.add(button_ext);             //将按钮button_ext添加到面板panel_but中
      button_ext.addActionListener(this);    //给按钮添加动作监听器
      panel_but.add(button_aur);             //将按钮button_aur添加到面板panel_but中
      button_aur.addActionListener(this);    //给按钮添加动作监听器
  }   
        
  public void actionPerformed(ActionEvent e){    //按钮点击方法 动作事件
      if( e.getSource()==item_auther || e.getSource()==button_aur ){   
          JOptionPane.showMessageDialog(null,"作者:14计基郭涵宁 \n");       
      }   
     
      if( e.getSource()==item_exit || e.getSource()==button_ext ){   
          System.exit(0);   
      }   

      if( e.getSource()==item_sol || e.getSource()==button_sol ){   
          if(gettext()==1){   
              if(ans()==1){   
                  JOptionPane.showMessageDialog(null,"答案正确，恭喜！");     //展示信息
              }   
              else{   
                  JOptionPane.showMessageDialog(null,"答案错误，请再接再厉！");     
              }   
          }   
       }   
     
       if( e.getSource()==item_rem || e.getSource()==button_rem ){   //重来
           for(int i=1;i<=9;i++){   
               for(int j=1;j<=9;j++){   
                   text[i][j].setText(atext[i][j]);   
               }   
           }   
        }   
     
        if( e.getSource()==item_ans || e.getSource()==button_ans ){   //答案 
            new ShuDuAns();   
        }   
     
        if( e.getSource()==item_next || e.getSource()==button_next ){   //开局
            setnum();   
            settext();   
            this.setVisible(false);   
            new ShuDu();   
      }   
  }   
     
  public void itemStateChanged(ItemEvent e){   //项目的状态改变，选择游戏难度
      if(box.getSelectedIndex()==0){   // easy容易
          hard=datahard[0];   
      }   
      if(box.getSelectedIndex()==1){   // normal一般
          hard=datahard[1];   
      }   
      if(box.getSelectedIndex()==2){   // hard困难
          hard=datahard[2];   
      }   
  }    
  
  public static void main(String args[]){   
      setnum();   
      settext();   
      new ShuDu();   
 }   
}  