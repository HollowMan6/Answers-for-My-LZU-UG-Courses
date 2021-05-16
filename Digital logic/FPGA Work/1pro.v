`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2019/11/26 22:24:15
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


module test(
    a1,
    b1,
    c1,
    d1,
    a2,
    b2,
    c2,
    d2,
    sum
    );
    input [31:0] a1;
    input [31:0] b1;
    input [31:0] c1;
    input [31:0] d1;
    input [31:0] a2;
    input [31:0] b2;
    input [31:0] c2;
    input [31:0] d2;
    output sum;
    wire [31:0]a1; 
    wire [31:0]b1;
    wire [31:0]c1; 
    wire [31:0]d1;
    wire [31:0]a2; 
    wire [31:0]b2;
    wire [31:0]c2; 
    wire [31:0]d2;
    reg [31:0] sum;
    
    always @ (a1 or  b1 or c1 or d1 or a2 or b2 or c2 or d2)
        begin
            sum = a1*a2+a1*b2+a1*c2+a1*d2+b1*a2+b1*b2+b1*c2+b1*d2+c1*a2+c1*b2+c1*c2+c1*d2+d1*a2+d1*b2+d1*c2+d1*d2 ;
        end
endmodule

