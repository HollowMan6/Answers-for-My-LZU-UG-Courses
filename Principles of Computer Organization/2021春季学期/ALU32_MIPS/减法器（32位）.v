module sub_32(cin,in0,in1,carryout,out);
    input [31:0] in0;
    input [31:0] in1;
    input cin;
    output [31:0] out;
    output carryout;
    wire [31:0] sub_in1;
    wire carryout1;
    wire carryout2;
    wire carryout3;
    
    
    assign  sub_in1=(~in1+1);
    adder_8 adder_8_0(cin,in0[7:0],sub_in1[7:0],carryout1,out[7:0]);
    adder_8 adder_8_1(carryout1,in0[15:8],sub_in1[15:8],carryout2,out[15:8]);
    adder_8 adder_8_2(carryout2,in0[23:16],sub_in1[23:16],carryout3,out[23:16]);
    adder_8 adder_8_3(carryout3,in0[31:24],sub_in1[31:24],carryout,out[31:24]);
endmodule