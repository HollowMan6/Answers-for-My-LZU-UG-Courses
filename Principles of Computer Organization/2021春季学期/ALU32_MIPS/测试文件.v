module Test();
    reg [31:0] in0;
    reg [31:0] in1;
    reg [10:0] op;
    wire [31:0] out;
    wire overflow;
    wire zero;
    wire carryout;
    ALU ALUTest(
    .in0(in0),
    .in1(in1),
    .op(op),
    .out(out),
    .overflow(overflow),
    .zero(zero),
    .carryout(carryout)
    );
    initial
    begin
       //add
         op=11'b00000100000;        
         in0=32'hf2340000;
         in1=32'h80000000;
    #20  in0=32'h7fffffff;
         in1=32'h70000001;
    #20  in0=32'h7fffffff;
         in1=32'hf0000001;
    #20  in0=32'hffffffff;
         in1=32'h00000001;
    //addu          
    #20  op=11'b00000100001;   
         in0=32'hf2340000;
         in1=32'h80000000;
    #20  in0=32'h7fffffff;
         in1=32'h70000001;
    #20  in0=32'hffffffff;
         in1=32'h00000001;
    //sub    
    #20  op=11'b00000100010;        
         in0=32'h72340000;
         in1=32'h60000000;
    #20  in0=32'h7fffffff;
         in1=32'hf0000001;
    #20  in0=32'hf00fffff;
         in1=32'h7ffffff1;
    #20  in0=32'hffffffff;
         in1=32'hffffffff;
    #20  in0=32'hf0000000;
         in1=32'h0fffffff; 
    //subu
    #20  op=11'b00000100011;        
         in0=32'h72340000;
         in1=32'h60000000;
    #20  in0=32'h7fffffff;
         in1=32'hf0000001;
    #20  in0=32'hffffffff;
         in1=32'hffffffff;
    #20  in0=32'hf0000000;
         in1=32'h0fffffff; 
    //and
    #20  op=11'b00000100100;        
         in0=32'h72340000;
         in1=32'h60000000;
    #20  in0=32'h7fffffff;
         in1=32'h00000000; 
    //or
    #20  op=11'b00000100101;        
         in0=32'h00000000;
         in1=32'h00000000;
    #20  in0=32'h7fffffff;
         in1=32'hf0000001;
    //xor
    #20  op=11'b00000100110;        
         in0=32'ha0000000;
         in1=32'h50000000;
    #20  in0=32'h7fffffff;
         in1=32'hf0000001;
    //nor
    #20  op=11'b00000100111;        
         in0=32'h123451ff;
         in1=32'h60000000;
    #20  in0=32'h7fffffff;
         in1=32'hf0000001;
    //slt
    #20  op=11'b00000101010;        
         in0=32'h72340000;
         in1=32'hf0000000;
    #20  in0=32'h7000000f;
         in1=32'h7f000001;
    #20  in0=32'hf0001231;
         in1=32'h7ac34545;
    //sltu
    #20  op=11'b00000101011;        
         in0=32'h72340000;
         in1=32'hf0000000;
    #20  in0=32'h7000000f;
         in1=32'h7f000001;
    #20  in0=32'hf0001231;
         in1=32'h7ac34545;
    /*
        //add
        op=11'b00000100000;
        in0=32'h00001111;
        in1=32'h00000001;
        #20
        in0=32'hffffffff;
        in1=32'h00000001;
        //addu
        #20
        op=11'b00000100001;
        in0=32'h00001111;
        in1=32'h00000001;
        #20
        in0=32'hffffffff;
        in1=32'h00000001;
        //sub
        #20
        op=11'b00000100010;
        in0=32'h10001111;
        in1=32'h00011111;
        #20
        in0=32'h00000111;
        in1=32'h00000001;
        //subu
        #20
        op=11'b00000100011;
         in0=32'h10001111;
        in1=32'h00011111;
        #20
        in0=32'h00000111;
        in1=32'h00000001;
        //slt
        #20
        op=11'b00000101010;
        in0=32'hffffffff;
        in1=32'h00000001;
        #20
        in0=32'hf0001231;
        in1=32'h7ac34545;
        //sltu
        #20
        op=11'b00000101011;
        in0=32'hffffffff;
        in1=32'h00000001;
        #20
        in0=32'hf0001231;
        in1=32'h7ac34545;
        */
    end
endmodule