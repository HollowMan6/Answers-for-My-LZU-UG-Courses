import java.io.*;
import java.util.Scanner;

public class Matrix {
    private int r, c;
    private double[][] data;

    public Matrix(int x, int y) {
        this.r = x;
        this.c = y;
        data = new double[x][y];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                data[i][j] = 0;
    }

    public Matrix() {
        this.r = 3;
        this.c = 3;
        data = new double[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                data[i][j] = 0;
    }

    public Matrix(double[][] data) {
        r = data.length;
        c = data[0].length;
        this.data = new double[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                this.data[i][j] = data[i][j];
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        if (r > 0)
            this.r = r;
        else
            System.out.println("错误！行数应为正值！\n");
    }

    public int getC() {
        return c;
    }

    public void setC(int x) {
        if (r > 0)
            this.c = x;
        else
            System.out.println("错误！列数应为正值！\n");
    }

    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) throws myexception {
        if (data.length == r && data[0].length == c) {
            this.data = new double[r][c];
            for (int i = 0; i < r; i++)
                for (int j = 0; j < c; j++)
                    this.data[i][j] = data[i][j];
        } else {
            myexception e = new myexception("错误！数据值不符合矩阵大小！\n");
            throw e;
        }
    }

    public boolean equals(Matrix another) {
        if (r == another.r && c == another.c) {
            for (int i = 0; i < r; i++)
                for (int j = 0; j < c; j++)
                    if (data[i][j] == another.data[i][j])
                        continue;
                    else {
                        return false;
                    }
            return true;
        } else
            return false;
    }

    public String toString() {
        String info = " 行数：" + r + "\n 列数：" + c + "\n";
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++)
                info += (data[i][j] + " ");
            info += "\n";
        }
        return info;
    }

    public Matrix add(Matrix another) throws myexception {
        Matrix re = new Matrix(r, c);
        if (r == another.r && c == another.c) {
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    re.data[i][j] = data[i][j] + another.data[i][j];
                }
            }
        } else {
            myexception e = new myexception("提供的矩阵无法进行加法运算\n");
            throw e;
        }
        return re;
    }

    public Matrix sub(Matrix another) throws myexception {
        Matrix re = new Matrix(r, c);
        if (r == another.r && c == another.c) {
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    re.data[i][j] = data[i][j] - another.data[i][j];
                }
            }
        } else {
            myexception e = new myexception("提供的矩阵无法进行减法运算\n");
            throw e;
        }
        return re;
    }

    public Matrix dot(Matrix another) throws myexception {
        if (c == another.r) {
            Matrix re = new Matrix(r, another.c);
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    re.data[i][j] = 0;
                    for (int k = 0; k < c; k++) {
                        re.data[i][j] += data[i][k] * another.data[k][j];
                    }
                }
            }
            return re;
        } else {
            myexception e = new myexception("提供的矩阵无法进行矩阵乘法运算\n");
            throw e;
        }
    }

    public Matrix dot(int b) {
        Matrix re = new Matrix(r, c);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                re.data[i][j] = data[i][j] * b;
            }
        }
        return re;
    }

    public static void main(String[] args) throws IOException{
        FileOutputStream fos = new FileOutputStream(new File("out.txt"));
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw);
        int r=0;int c=0;
        File file=new File("in1.txt");
        Scanner sc=new Scanner(file);
        r=Integer.parseInt(sc.next());
        c= Integer.parseInt(sc.next());
        Matrix M = new Matrix(r, c);
        double[][] data = new double[r][c];
        for(int i=0;i<r;i++)
            for(int j=0;j<c;j++)
                data[i][j] = Integer.parseInt(sc.next());
        try {
            M.setData(data);
        } catch (myexception a) {
            System.out.println(a.getMessage());
            bw.write(a.getMessage()+ "\n");
        }
        bw.write(M + "\n");
        System.out.println(M.toString());
        bw.write("\n");
        System.out.println();
        sc.close();
        file = new File("in2.txt");
        sc = new Scanner(file);
        r = Integer.parseInt(sc.next());
        c = Integer.parseInt(sc.next());
        double[][] data1 = new double[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                data1[i][j] = Integer.parseInt(sc.next());
        Matrix N = new Matrix(data1);
        bw.write(N + "\n");
        System.out.println(N.toString());
        bw.write(N.equals(M) + "\n");
        System.out.println(N.equals(M));
        bw.write("\n");
        System.out.println();
        try{
        bw.write(N.add(M) + "\n");
        System.out.println(N.add(M));
        bw.write("\n");
        System.out.println();
        } catch (myexception a) {
            bw.write(a.getMessage()+"\n");
            System.out.println(a.getMessage());
        }
        try{
        bw.write(M.dot(N) + "\n");    
        System.out.println(M.dot(N));
        bw.write("\n");   
        System.out.println();
        } catch (myexception a) {
            bw.write(a.getMessage() + "\n");  
            System.out.println(a.getMessage());
        }
        try{
        bw.write(N.sub(M) + "\n"); 
        System.out.println(N.sub(M));
        bw.write("\n"); 
        System.out.println();
        }
        catch (myexception a) {
            bw.write(a.getMessage() + "\n"); 
            System.out.println(a.getMessage());
        }
        bw.write(N.dot(2) + "\n"); 
        System.out.println(N.dot(2));
        bw.write("\n"); 
        System.out.println();
        Matrix O = new Matrix();
        bw.write(O + "\n"); 
        System.out.println(O.toString());
        sc.close();
        bw.close();
        osw.close();
        fos.close();
    }
}

class myexception extends Exception {
    myexception() {
        super("错误！无法进行指定的矩阵运算！");
    }

    myexception(String msg) {
        super(msg);
    }
}