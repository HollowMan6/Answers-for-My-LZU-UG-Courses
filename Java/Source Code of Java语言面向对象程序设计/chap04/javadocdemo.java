import java.io.*;
/**
*Title:JavaDocdemo类<br>
*Descripte:这是一使用javadoc程序的例子<br>
*Copyright:(c)2004年9月20日 www.majun.com.cn<br>
*Company:flyhorsespace(飞马研究中心)<br>
*@author 马俊
*@version 1.00
*/
public class javadocdemo
{
     public String name;
/**
* 这是person对象的构造函数
*@param name javadocdemo的名字 
*/
public javadocdemo(String str)
{
            name=str;
}
/**
*这是setwork方法的说明
*@param workarea 工作领域
*@param workage   工龄
*@return  返回修改成功否
*/
public boolean setwork(String workarea,int workage)
{
    return true;
}
}