`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/03/24 14:09:46
// Design Name: 
// Module Name: shifter_2
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


module shifter_2(in0,c,out);
    input [31:0] in0;
    input c;
    output [31:0] out;
    wire [31:0] temp;
    shifter s1(in0,c,temp);
    shifter s2(temp,c,out);
endmodule
