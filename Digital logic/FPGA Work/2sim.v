
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2019/11/30 18:25:09
// Design Name: 
// Module Name: sim
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


module sim();
	reg CLK;
	reg RSTn;
	reg read;
	
	wire [7:0] CONV_OUT;
	wire [7:0] sumout;
	
initial 
begin
	CLK = 0;
	forever #100 CLK = ~CLK;
end

initial 
begin
	RSTn = 0;
	#50 RSTn = 1;
	read = 1;
end

   test test(.CLK(CLK),
		.RSTn(RSTn),
		.read(read),
		.CONV_OUT(CONV_OUT),
		.sumout(sumout)
		);
			 
endmodule

