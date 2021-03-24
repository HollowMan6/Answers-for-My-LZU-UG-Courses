module adder_8(cin,a,b,cout,s);
    input [7:0] a,b;
    input cin;
    output [7:0] s;
    output cout;
    wire [7:0] G,P;
    wire [7:0] C;
    
    assign G[0]=a[0]&b[0];
    assign P[0]=a[0]|b[0];
    assign C[0]=cin;
    assign s[0]=a[0]^b[0]^C[0];
    
    assign G[1]=a[1]&b[1];
    assign P[1]=a[1]|b[1];
    assign C[1]=G[0]|(P[0]&cin);
    assign s[1]=a[1]^b[1]^C[1];
    
    assign G[2]=a[2]&b[2];
    assign P[2]=a[2]|b[2];
    assign C[2]=G[1]|(P[1]&G[0])|(P[1]&P[0]&cin);
    assign s[2]=a[2]^b[2]^C[2];
    
    assign G[3]=a[3]&b[3];
    assign P[3]=a[3]|b[3];
    assign C[3]=G[2]|(P[2]&G[1])|(P[2]&P[1]&G[0])|(P[2]&P[1]&P[0]&cin);
    assign s[3]=a[3]^b[3]^C[3];
    
    assign G[4]=a[4]&b[4];
    assign P[4]=a[4]|b[4];
    assign C[4]=G[3]|(P[3]&G[2])|(P[3]&P[2]&G[1])|(P[3]&P[2]&P[1]&G[0])|(P[3]&P[2]&P[1]&P[0]&cin);
    assign s[4]=a[4]^b[4]^C[4];
    
    assign G[5]=a[5]&b[5];
    assign P[5]=a[5]|b[5];
    assign C[5]=G[4]|(P[4]&G[3])|(P[4]&P[3]&G[2])|(P[4]&P[3]&P[2]&G[1])|(P[4]&P[3]&P[2]&P[1]&G[0])|(P[4]&P[3]&P[2]&P[1]&P[0]&cin);
    assign s[5]=a[5]^b[5]^C[5];
    
    assign G[6]=a[6]&b[6];
    assign P[6]=a[6]|b[6];
    assign C[6]=G[5]|(P[5]&G[4])|(P[5]&P[4]&G[3])|(P[5]&P[4]&P[3]&G[2])|(P[5]&P[4]&P[3]&P[2]&G[1])|(P[5]&P[4]&P[3]&P[2]&P[1]&G[0])|(P[5]&P[4]&P[3]&P[2]&P[1]&P[0]&cin);
    assign s[6]=a[6]^b[6]^C[6];
    
    assign G[7]=a[7]&b[7];
    assign P[7]=a[7]|b[7];
    assign C[7]=G[6]|(P[6]&G[5])|(P[6]&P[5]&G[4])|(P[6]&P[5]&P[4]&G[3])|(P[6]&P[5]&P[4]&P[3]&G[2])|(P[6]&P[5]&P[4]&P[3]&P[2]&G[1])|(P[6]&P[5]&P[4]&P[3]&P[2]&P[1]&G[0])|(P[6]&P[5]&P[4]&P[3]&P[2]&P[1]&P[0]&cin);
    assign s[7]=a[7]^b[7]^C[7];
    
    assign cout=G[7]|(P[7]&G[6])|(P[7]&P[6]&G[5])|(P[7]&P[6]&P[5]&G[4])|(P[7]&P[6]&P[5]&P[4]&G[3])|(P[7]&P[6]&P[5]&P[4]&P[3]&G[2])|(P[7]&P[6]&P[5]&P[4]&P[3]&P[2]&G[1])|(P[7]&P[6]&P[5]&P[4]&P[3]&P[2]&P[1]&G[0])|(P[7]&P[6]&P[5]&P[4]&P[3]&P[2]&P[1]&P[0]&cin);
endmodule
