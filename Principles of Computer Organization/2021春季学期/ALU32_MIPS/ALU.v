`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/03/04 09:28:34
// Design Name: 
// Module Name: ALU
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


module ALU(op,in0,in1,out,overflow,zero,carryout);
    
    input [31:0] in0;
    input [31:0] in1;
    input [10:0] op;
    output [31:0] out;
    output overflow;
    output zero;
    output carryout;
    reg [31:0] out;
    reg overflow;
    reg zero;
    reg carryout;
    
    wire [31:0]in0;
    wire [31:0]in1;
    wire [31:0] sub_in1;
    wire [31:0]add_out;
    wire [31:0]sub_out;
    wire [31:0]shift_out;
    wire add_carryout;
    wire sub_carryout;
    parameter cin=0;

    assign  sub_in1=(~in1+1);
    adder_32 add(
                .cin(cin),
                .in0(in0),
                .in1(in1),
                .carryout(add_carryout),
                .out(add_out)
                );
    adder_32 sub(
                .cin(cin),
                .in0(in0),
                .in1(sub_in1),
                .carryout(sub_carryout),
                .out(sub_out)
                );
     shifter_2 s(
                .in0(in0),
                .c(op[1]),
                .out(shift_out)
     );

    always @(*)
    begin 
        case(op)
        //add
        11'b00000100000:
            begin
                out=add_out;
                overflow=((in0[31]==in1[31])&&(~out[31]==in0[31]))?1:0;
                zero=(out==0)?1:0;
                carryout=0; //有符号加法最高位是符号位，故考虑溢出即可，进位赋值0
            end
        //addu
        11'b00000100001:
            begin
                carryout=add_carryout;
                out=add_out;
                zero=(out==0)?1:0;
                overflow=0;
            end
        //sub
        11'b00000100010:
            begin
                out=sub_out;
                overflow=((in0[31]==sub_in1[31])&&(~out[31]==in0[31]))?1:0;
                zero=(out==0)?1:0;
                carryout=0;
            end
        //subu
        11'b00000100011:    
            begin
                carryout=~sub_carryout;
                out=sub_out;
                zero=(out==0)?1:0;
                overflow=0;
            end
        //and
        11'b00000100100:
            begin
                out=in1&in0;
                zero=(out==0)?1:0;
                carryout=0;
                overflow=0;
            end
        //or
        11'b00000100101:
            begin
                out=in1|in0;
                zero=(out==0)?1:0;
                carryout=0;
                overflow=0;
            end
        //xor
        11'b00000100110:
            begin
                out=in1^in0;
                zero=(out==0)?1:0;
                carryout=0;
                overflow=0;
            end
        //nor
        11'b00000100111:
            begin
                out=~(in1|in0);
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
                zero=(out==0)?1:0;
                overflow=0;
                carryout=0;
            end
        //sltu
        11'b00000101011:
            begin
                out=(in0<in1)?1:0;
                zero=(out==0)?1:0;
                overflow=0;
                carryout=0;
            end
        //sll
            11'b00000000000:
            begin
            out=shift_out;
            carryout=0;
            overflow=in0[31]^in0[29];
            zero=(out==0)?1:0;
            end
        //srl
        11'b00000000010:
            begin
            out=shift_out;
            carryout=0;
            overflow=0;
            zero=(out==0)?1:0;
            end
        //sra
        11'b00000000011:
            begin
            out=$signed(in0)>>>2;
            carryout=0;
            overflow=0;
            zero=(out==0)?1:0;
            end
        //sllv
        11'b00000000100:
            begin
            out=in0<<in1;
            carryout=0;
            overflow=in0[31]^in0[31-in1];
            zero=(out==0)?1:0;
            end
        //srlv
        11'b00000000110:
            begin
            out=in0>>in1;
            carryout=0;
            overflow=0;
            zero=(out==0)?1:0;
            end
        //srav
        11'b00000000111:
            begin
            out=$signed(in0)>>>in1;
            carryout=0;
            overflow=0;
            zero=(out==0)?1:0;
            end
        endcase
    end
endmodule
