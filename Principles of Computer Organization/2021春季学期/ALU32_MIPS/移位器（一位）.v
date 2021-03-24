`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/03/23 20:05:59
// Design Name: 
// Module Name: shifter
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


module shifter(in0,c,out);
    input [31:0] in0;
    input c;
    output[31:0] out;
    //reg [31:0] out;
    wire [31:0]out;
            assign out[0]=in0[1]&c;
            assign out[1]=(in0[0]&~c)|(in0[2]&c);
            assign out[2]=(in0[1]&~c)|(in0[3]&c);
            assign out[3]=(in0[2]&~c)|(in0[4]&c);
            assign   out[4]=(in0[3]&~c)|(in0[5]&c);
            assign out[5]=(in0[4]&~c)|(in0[6]&c);
            assign  out[6]=(in0[5]&~c)|(in0[7]&c);
            assign out[7]=(in0[6]&~c)|(in0[8]&c);
            assign  out[8]=(in0[7]&~c)|(in0[9]&c);
            assign out[9]=(in0[8]&~c)|(in0[10]&c);
            assign out[10]=(in0[9]&~c)|(in0[11]&c);
            assign out[11]=(in0[10]&~c)|(in0[12]&c);
            assign  out[12]=(in0[11]&~c)|(in0[13]&c);
            assign  out[13]=(in0[12]&~c)|(in0[14]&c);
            assign out[14]=(in0[13]&~c)|(in0[15]&c);
            assign out[15]=(in0[14]&~c)|(in0[16]&c);
            assign out[16]=(in0[15]&~c)|(in0[17]&c);
            assign out[17]=(in0[16]&~c)|(in0[18]&c);
            assign out[18]=(in0[17]&~c)|(in0[19]&c);
            assign out[19]=(in0[18]&~c)|(in0[20]&c);
            assign out[20]=(in0[19]&~c)|(in0[21]&c);
            assign  out[21]=(in0[20]&~c)|(in0[22]&c);
            assign out[22]=(in0[21]&~c)|(in0[23]&c);
            assign  out[23]=(in0[22]&~c)|(in0[24]&c);
            assign out[24]=(in0[23]&~c)|(in0[25]&c);
            assign out[25]=(in0[24]&~c)|(in0[26]&c);
            assign out[26]=(in0[25]&~c)|(in0[27]&c);
            assign out[27]=(in0[26]&~c)|(in0[28]&c);
            assign out[28]=(in0[27]&~c)|(in0[29]&c);
            assign  out[29]=(in0[28]&~c)|(in0[30]&c);
            assign  out[30]=(in0[29]&~c)|(in0[31]&c);
            assign  out[31]=in0[30]&~c;
        /*assign out[0]=in0[1]&~c;
        assign out[1]=(in0[0]&c)|(in0[2]&~c);
        assign out[2]=(in0[1]&c)|(in0[3]&~c);
        assign out[3]=(in0[2]&c)|(in0[4]&~c);
     assign   out[4]=(in0[3]&c)|(in0[5]&~c);
      assign out[5]=(in0[4]&c)|(in0[6]&~c);
      assign  out[6]=(in0[5]&c)|(in0[7]&~c);
       assign out[7]=(in0[6]&c)|(in0[8]&~c);
      assign  out[8]=(in0[7]&c)|(in0[9]&~c);
       assign out[9]=(in0[8]&c)|(in0[10]&~c);
       assign out[10]=(in0[9]&c)|(in0[11]&~c);
       assign out[11]=(in0[10]&c)|(in0[12]&~c);
      assign  out[12]=(in0[11]&c)|(in0[13]&~c);
      assign  out[13]=(in0[12]&c)|(in0[14]&~c);
       assign out[14]=(in0[13]&c)|(in0[15]&~c);
       assign out[15]=(in0[14]&c)|(in0[16]&~c);
        assign out[16]=(in0[15]&c)|(in0[17]&~c);
       assign out[17]=(in0[16]&c)|(in0[18]&~c);
       assign out[18]=(in0[17]&c)|(in0[19]&~c);
        assign out[19]=(in0[18]&c)|(in0[20]&~c);
       assign out[20]=(in0[19]&c)|(in0[21]&~c);
      assign  out[21]=(in0[20]&c)|(in0[22]&~c);
       assign out[22]=(in0[21]&c)|(in0[23]&~c);
      assign  out[23]=(in0[22]&c)|(in0[24]&~c);
       assign out[24]=(in0[23]&c)|(in0[25]&~c);
       assign out[25]=(in0[24]&c)|(in0[26]&~c);
       assign out[26]=(in0[25]&c)|(in0[27]&~c);
       assign out[27]=(in0[26]&c)|(in0[28]&~c);
       assign out[28]=(in0[27]&c)|(in0[29]&~c);
      assign  out[29]=(in0[28]&c)|(in0[30]&~c);
      assign  out[30]=(in0[29]&c)|(in0[31]&~c);
      assign  out[31]=in0[30]&c;*/
    /*always@(*)
    begin
        out[0]<=in0[1]&~c;
        out[1]<=(in0[0]&c)|(in0[2]&~c);
        out[2]<=(in0[1]&c)|(in0[3]&~c);
        out[3]<=(in0[2]&c)|(in0[4]&~c);
        out[4]<=(in0[3]&c)|(in0[5]&~c);
        out[5]<=(in0[4]&c)|(in0[6]&~c);
        out[6]<=(in0[5]&c)|(in0[7]&~c);
        out[7]<=(in0[6]&c)|(in0[8]&~c);
        out[8]<=(in0[7]&c)|(in0[9]&~c);
        out[9]<=(in0[8]&c)|(in0[10]&~c);
        out[10]<=(in0[9]&c)|(in0[11]&~c);
        out[11]<=(in0[10]&c)|(in0[12]&~c);
        out[12]<=(in0[11]&c)|(in0[13]&~c);
        out[13]<=(in0[12]&c)|(in0[14]&~c);
        out[14]<=(in0[13]&c)|(in0[15]&~c);
        out[15]<=(in0[14]&c)|(in0[16]&~c);
        out[16]<=(in0[15]&c)|(in0[17]&~c);
        out[17]<=(in0[16]&c)|(in0[18]&~c);
        out[18]<=(in0[17]&c)|(in0[19]&~c);
        out[19]<=(in0[18]&c)|(in0[20]&~c);
        out[20]<=(in0[19]&c)|(in0[21]&~c);
        out[21]<=(in0[20]&c)|(in0[22]&~c);
        out[22]<=(in0[21]&c)|(in0[23]&~c);
        out[23]<=(in0[22]&c)|(in0[24]&~c);
        out[24]<=(in0[23]&c)|(in0[25]&~c);
        out[25]<=(in0[24]&c)|(in0[26]&~c);
        out[26]<=(in0[25]&c)|(in0[27]&~c);
        out[27]<=(in0[26]&c)|(in0[28]&~c);
        out[28]<=(in0[27]&c)|(in0[29]&~c);
        out[29]<=(in0[28]&c)|(in0[30]&~c);
        out[30]<=(in0[29]&c)|(in0[31]&~c);
        out[31]<=(in0[30]&c)|(in0[32]&~c);
        out[32]<=in0[31]&c;
    end*/
endmodule
