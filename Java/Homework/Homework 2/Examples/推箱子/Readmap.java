import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class Readmap
{
	private int level,mx,my;
	private int[][] map=new int[20][20];		//定义地图二维数组
	FileReader fi;
	BufferedReader bu;
	String st="";
	int[] x;
	int c=0;
	Readmap(int l)
	{
		level=l;
		String s;
		try
		{
			File f=new File("maps\\"+level+".map");			//读取地图文件
			fi=new FileReader(f);
			bu=new BufferedReader(fi);
		}catch (IOException e){
			System.out.println(e);
		}
		try
		{
			while ((s=bu.readLine())!=null)					//整行读取
			{
				st=st+s;									//将字符串全部赋给st
				
			}
		}catch (IOException g){
			System.out.println(g);
		}
		byte[] d=st.getBytes();
		int len=st.length();			//len得到st字符串长度为400
		int[] x=new int[len];				
		for(int i=0;i<st.length();i++)
			x[i]=d[i]-48;
		for(int i=0;i<20;i++)
		{
			for(int j=0;j<20;j++)
		    {
				map[i][j]=x[c];
		        if(map[i][j]==5)		//此时位置为小人
		        {
					mx=j;my=i;			//坐标赋给mx，my
		        }
		        c++;
		    }
	    }
	}
	int[][] getmap(){return map;}		//getmap方法
	int getrenX(){return mx;}			//getrenX方法
	int getrenY(){return my;}			//getrenY方法
}
