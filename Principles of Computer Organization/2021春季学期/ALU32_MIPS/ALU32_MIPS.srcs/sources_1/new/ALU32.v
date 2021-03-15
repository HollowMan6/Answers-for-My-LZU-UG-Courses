`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 15.03.2021 20:27:51
// Design Name: 
// Module Name: ALU32
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


module ALU32(
    op,in0,in1,
    carryout,overflow,zero,out
    );

input [31:0] in0,in1;
input [10:0] op;
output reg[31:0] out;
output reg carryout,overflow,zero;
   
always@(*)
begin
    case(op)
        //add
        11'b00000100000:
            begin
            out=in0+in1;
            overflow=((in0[31]==in1[31])&&(~out[31]==in0[31]))?1:0;
            zero=(out==0)?1:0;
            carryout=0;
            end
        //addu
        11'b00000100001:
            begin
            {carryout,out}=in0+in1;
            zero=(out==0)?1:0;
            overflow=0;
            end
        //sub
        11'b00000100010:
            begin
            out=in0-in1;
            overflow=((in0[31]==0&&in1[31]==1&&out[31]==1)||			      		                          (in0[31]==1&&in1[31]==0&&out[31]==0))?1:0;
            zero=(in0==in1)?1:0;
            carryout=0;
            end
        //subu
        11'b00000100011:
            begin
            {carryout,out}=in0-in1;
            zero=(out==0)?1:0;
            overflow=0;
            end
        //and
        11'b00000100100:
            begin
            out=in0&in1;
            zero=(out==0)?1:0;
            carryout=0;
            overflow=0;
            end
        //or
        11'b00000100101:
            begin
            out=in0|in1;
            zero=(out==0)?1:0;
            carryout=0;
            overflow=0;
            end
        //xor
        11'b00000100110:
            begin
            out=in0^in1;
            zero=(out==0)?1:0;
            carryout=0;
            overflow=0;
            end
        //nor
        11'b00000100111:
            begin
            out=~(in0|in1);
            zero=(out==0)?1:0;
            carryout=0;
            overflow=0;
            end
        //slt
        11'b00000101010:
            begin                        
            if(in0[31]==1&&in1[31]==0)
                out=1;
            else if(in0[31]==0&&in1[31]==1)
                out=0;
            else 
                out=(in0<in1)?1:0;
           overflow=out; 
           zero=(out==0)?1:0;
           carryout=0;              
           end
        //sltu
        11'b00000101011:
            begin
                out=(in0<in1)?1:0;
                carryout=out;
                zero=(out==0)?1:0;
                overflow=0;
            end
        //shl
        11'b00000000100:
            begin
            {carryout,out}=in0<<in1;
            overflow=0;
            zero=(out==0)?1:0;
            end
        //shr
        11'b00000000110:
            begin
            out=in0>>in1;
            carryout=in0[in1-1];
            overflow=0;
            zero=(out==0)?1:0;
            end
        //sar
        11'b00000000111:
            begin
            out=($signed(in0))>>>in1;
            carryout=in0[in1-1];
            overflow=0;
            zero=(out==0)?1:0;
            end
        
    endcase
end
endmodule