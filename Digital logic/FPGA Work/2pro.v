
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2019/11/30 18:24:47
// Design Name: 
// Module Name: test
// Project Name: 
// Target Devices: 
// Tool Versions: 
// Description: 
// 
// Dependencies: 
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
//////////////////////////////////////////////////////////////////////////////////


module test#(
    parameter CORE3X3_0 = 2'b10, //2*2卷积核
	parameter CORE3X3_1 = 2'b10,
	parameter CORE3X3_2 = 2'b10,
	parameter CORE3X3_3 = 2'b10
)
(	
	input CLK,
	input RSTn,
	input read,
	output [7:0]sumout,
	output [7:0] CONV_OUT
);

reg [3:0] ROM [15:0];                   //memory 存储被卷积矩阵2*2 -> 4*4
reg [5:0] rd;                           //memory address辅助卷积核进行移动
reg [7:0] CONV_OUT_REG;                //regsiter of conv_out卷积得到的3*3矩阵的每一项输出
reg [3:0] WIN0;                           //windows data在被卷积矩阵中取得2*2子阵
reg [3:0] WIN1;
reg [3:0] WIN2;
reg [3:0] WIN3;
reg [7:0] sum;


initial                                //被卷积矩阵的初始化（外面加一圈0）
begin
	ROM[ 0] = 4'b0000;
	ROM[ 1] = 4'b0000;
	ROM[ 2] = 4'b0000;
	ROM[ 3] = 4'b0000;
	ROM[ 4] = 4'b0000;
	ROM[ 5] = 4'b0001;
	ROM[ 6] = 4'b0010;
	ROM[ 7] = 4'b0000;
	ROM[ 8] = 4'b0000;
	ROM[ 9] = 4'b0011;
	ROM[10] = 4'b0100;
	ROM[11] = 4'b0000;
	ROM[12] = 4'b0000;
	ROM[13] = 4'b0000;
	ROM[14] = 4'b0000;
	ROM[15] = 4'b0000;
end

always @ (posedge CLK or negedge RSTn)
begin
	if (!RSTn)
		begin
		WIN0 <= 2'bx;
		WIN1 <= 2'bx;
		WIN2 <= 2'bx;
		WIN3 <= 2'bx;
		rd <= 0;
		end
	else if (read)
		begin
			WIN0 = ROM[rd];//在被卷积矩阵中读取2*2子阵
			WIN1 = ROM[rd + 4'b0001];
			WIN2 = ROM[rd + 4'b0100];
			WIN3 = ROM[rd + 4'b0101];
			rd <= rd + 1'b1;
		end
end

always @ ( posedge CLK or negedge RSTn )
begin//生成电路图
    if(!RSTn)
    begin
    end
	else
	begin
	case (rd)//辅助卷积核在卷积运算中换行
	6'd2:rd <= rd + 2'b10;
	6'd6:rd <= rd + 2'b10;
	6'd10:rd <= rd + 2'b10;
	endcase
	end
	
	
end	

always @ ( posedge CLK or negedge RSTn )
begin
	if (!RSTn)
		CONV_OUT_REG <= 0;
	else                       //输出卷积得到的3*3矩阵的每一位
		CONV_OUT_REG <= WIN0 * CORE3X3_3 + WIN1 * CORE3X3_2 + WIN2 * CORE3X3_1 + WIN3 * CORE3X3_0 ;
end

always @ ( posedge CLK or negedge RSTn )
begin
        if (!RSTn)
		sum <= 0;
		else              //卷积得到的3*3矩阵每一位累加
        sum<=sum+CONV_OUT_REG;
end

assign CONV_OUT = CONV_OUT_REG;//结果输出
assign sumout=sum;

endmodule

