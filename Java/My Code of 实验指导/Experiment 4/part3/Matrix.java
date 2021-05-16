public class Matrix{
    private int r,c;
    private double[][] data;
    public Matrix(int x,int y){
        this.r=x;
        this.c=y;
        data=new double[x][y];
    }
    public Matrix(){
        this.r=3;
        this.c=3;
        data=new double[r][c];
        for(int i=0;i<r;i++)
            for(int j=0;j<c;j++)
                data[i][j]=0;
    }
    public Matrix(double[][] data){
        r=data.length;
        c=data[0].length;
        this.data=new double[r][c];
        for(int i=0;i<r;i++)
            for(int j=0;j<c;j++)
                this.data[i][j]=data[i][j];
    }
    public int getR(){
        return r;
    }
    public void setR(int r){
        if(r>0)
            this.r=r;
        else
            System.out.println("错误！行数应为正值！");
    }
    public int getC(){
        return c;
    }
    public void setC(int x){
        if(r>0)
            this.c=x;  
        else
            System.out.println("错误！列数应为正值！"); 
    }
    public double[][] getData(){
        return data;
    }
    public void setData(double[][] data){
        if(data.length==r&&data[0].length==c){
            this.data=new double[r][c];
            for(int i=0;i<r;i++)
                for(int j=0;j<c;j++)
                    this.data[i][j]=data[i][j];
        }
        else
            System.out.println("错误！数据值不符合矩阵大小！");
    }
    public boolean equals(Matrix another){
        if(r==another.r&&c==another.c)
        {
            for(int i=0;i<r;i++)
                for(int j=0;j<c;j++)
                    if(data[i][j]==another.data[i][j])
                        continue;
                    else{
                        return false;
                    }
            return true;
                }
        else
            return false;
    } 
    public String toString(){
        String info=" 行数："+r+"\n 列数："+c+"\n";
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
                info+=(data[i][j]+" ");
            info+="\n";
        }
        return info;
    }
    public static void main(String[] args) {
        Matrix M=new Matrix(2, 3);
        double[][] data=new double[2][3];
        for(int i=0;i<2;i++)
            for(int j=0;j<3;j++)
                data[i][j]=1;
        M.setData(data);
        System.out.println(M.toString());
        System.out.println();
        double[][] data1=new double[3][4];
        for(int i=0;i<3;i++)
            for(int j=0;j<4;j++)
                data1[i][j]=2;
        Matrix N=new Matrix(data1);
        System.out.println(N.toString());
        System.out.println(N.equals(M));
        System.out.println();
        Matrix O=new Matrix();
        System.out.println(O.toString());
    }
}