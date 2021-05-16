
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
    parameter CORE2X2_0 = 4'b0010, 
	parameter CORE2X2_1 = 4'b0010,
	parameter CORE2X2_2 = 4'b0010,
	parameter CORE2X2_3 = 4'b0010
)
(	
	input CLK,
	input RSTn,
	input read,
	output [7:0]sumout,
	output [7:0] CONV_OUT
);

reg [3:0] ROM [15:0];                   
reg [5:0] rd;                          
reg [7:0] CONV_OUT_REG;                
reg [3:0] WIN0;                          
reg [3:0] WIN1;
reg [3:0] WIN2;
reg [3:0] WIN3;
reg [7:0] sum;


initial                              
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

function [7:0] mult;
    input[3:0] ina,inb;
    reg[7:0] temp,ci;
    integer i,j;
 begin   
    mult=8'h00;
    for(i=0;i<4;i=i+1)
    begin            
        if(inb&(1<<i))
            begin
                temp=ina<<i;
            end
        else 
            begin
                temp=8'h00;
            end
    begin
        for(j=0;j<8;j=j+1)
            begin
                if(j==0)
                    begin
                        ci[j]=mult[j]&temp[j];
                        mult[j]=mult[j]^temp[j];
                    end
                else
                    begin
                        ci[j]=(mult[j]&temp[j])|(mult[j]&ci[j-1])|(temp[j]&ci[j-1]);
                        mult[j]=mult[j]^temp[j]^ci[j-1];
                    end
            end
         end
     end
  end
 endfunction

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
			WIN0 = ROM[rd];
			WIN1 = ROM[rd + 4'b0001];
			WIN2 = ROM[rd + 4'b0100];
			WIN3 = ROM[rd + 4'b0101];
			rd <= rd + 1'b1;
		end
end

always @ ( posedge CLK or negedge RSTn )
begin
    if(!RSTn)
        begin
        end
    else
	   begin
	       case (rd)
	       6'd2:rd <= rd + 2'b10;
	       6'd6:rd <= rd + 2'b10;
	       6'd10:rd <= rd + 2'b10;
	endcase
	
end	


always @ ( posedge CLK or negedge RSTn )
begin
	if (!RSTn)
		CONV_OUT_REG <= 0;
		sum<=0;
	else                       
		CONV_OUT_REG <= WIN0 * CORE2X2_3 + WIN1 * CORE2X2_2 
                       + WIN2 * CORE2X2_1 + WIN3 * CORE2X2_0 ;
		 sum<=sum+CONV_OUT_REG;
end


assign CONV_OUT = CONV_OUT_REG;
assign sumout=sum;

endmodule

