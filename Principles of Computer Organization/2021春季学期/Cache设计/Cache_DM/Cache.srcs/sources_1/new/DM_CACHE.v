`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 01.06.2021 11:32:42
// Design Name: 
// Module Name: DM_CACHE
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


module DM_CACHE(
input clk,                //时钟
input rst,                //复位信号，低电平有效
input [31:0] address
    );    

reg[15:0] miss;			//未命中
reg[15:0] hit;			//命中
reg[15:0]     w_addr;      //RAM PORT A 写地址
reg[14:0]    w_data;     //RAM PORT  A 写数据
reg          ena;        //RAM使能，高电平有效
reg          wea;        //RAM PORT  A 写使能，高电平有效
reg          enb;        //RAM PORT  B 读使能，高电平有效
reg[15:0]     r_addr;     //RAM PORT  B 读地址
wire[14:0]   r_data;    //RAM PORT   B 读数据

//全相联映射缓存
always@(posedge clk or negedge rst) begin
      if(!rst) begin  //重设相关数值
		  ena <= 1'b0;
		  enb <= 1'b0;
		  r_addr <= 16'd0;
		  wea <= 1'b0;
	      w_addr <= 16'd0;
		  w_data <= 15'd0;
		  miss <= 16'd0;
		  hit <= 16'd0;
	  end
      else begin
		    ena <= 1'b1;
		  	enb <= 1'b1;
			r_addr <= address[17:2];			//根据Index读出缓存中数据
			if(r_data[14]==0)					//数据无效，自动模拟从内存装入数据
			begin
				w_data[14]<=1;					//有效位置为1
				w_data[13:0]<=address[31:18];	//Tag
				w_addr<=address[17:2];		//Index
				miss<=miss+1'b1;					//未命中
				wea <= 1'b1;
			end
			else
			begin
				if(r_data[13:0]==address[31:18]) //数据有效并且tag号相同
				begin	
					hit<=hit+1'b1;					//命中
				end
				else
				begin							//数据有效但未装入，直接替换装入
					w_data[14]<=1;					//有效位
					w_data[13:0]<=address[31:18];	//Tag
					w_addr <= address[17:2];		//Index
					miss<=miss+1'b1;				//未命中
					wea <= 1'b1;
				end
			end
	  end 
end 

////////////////////////////////////////////////
//实例化RAM 
blk_mem_gen_0 ram_ip_test ( 
  .clka      (clk          ),            // input clka 
  .ena       (ena          ),            // input [1 : 0] ena 
  .wea       (wea          ),            // input [1 : 0] wea 
  .addra     (w_addr       ),            // input [16 : 0] addra 
  .dina      (w_data       ),            // input [15 : 0] dina 
   .clkb     (clk          ),            // input clkb 
   .enb      (enb          ),			 // input [1 : 0] ena 
   .addrb    (r_addr       ),            // input [16 : 0] addrb 
   .doutb    (r_data       )             // output [15 : 0] doutb 
  ); 

endmodule
