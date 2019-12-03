`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2019/11/26 22:24:43
// Design Name: 
// Module Name: simu
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


module simu;
reg [31:0] a1,b1,c1,d1,a2,b2,c2,d2;
wire [31:0] sum;
//reg clk;
//parameter STEP=100.0000;

//always#(STEP/2) begin
//    clk <= -clk;
//end

initial
begin
    #0      a1= 32'd2;
            b1= 32'd2;
            c1= 32'd2;
            d1= 32'd2;
            a2= 32'd2;
            b2= 32'd2;
            c2= 32'd2;
            d2= 32'd2;
     #10    $stop;
 end
 
 test regfile(
    .a1(a1),
    .b1(b1),
    .c1(c1),
    .d1(d1),
    .a2(a2),
    .b2(b2),
    .c2(c2),
    .d2(d2),
    .sum(sum)
//    .clk(clk)
 );
endmodule
