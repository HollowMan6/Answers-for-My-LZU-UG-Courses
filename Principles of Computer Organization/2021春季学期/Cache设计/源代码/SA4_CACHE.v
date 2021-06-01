`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 01.06.2021 19:26:13
// Design Name: 
// Module Name: SA4_CACHE
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


module SA4_CACHE(
input clk,                //时钟
input rst,                //复位信号，低电平有效
input [31:0] address
    );    

reg not_valid;			//记录当前记录的组号是否存放着无效数据
reg[1:0] least_set;		//LRU算法完成后最远的组号
reg[7:0] temp;			//运行LRU算法时的临时寄存器
reg[7:0] count;			//当前查询的编号，用于LRU算法
reg[15:0] miss;			//未命中
reg[15:0] hit;			//命中
reg[15:0]     w_addr;      //RAM PORT A 写地址
reg[91:0]    w_data;     //RAM PORT  A 写数据
reg          ena;        //RAM使能，高电平有效
reg          wea;        //RAM PORT  A 写使能，高电平有效
reg          enb;        //RAM PORT  B 读使能，高电平有效
reg[15:0]     r_addr;     //RAM PORT  B 读地址
wire[91:0]   r_data;    //RAM PORT   B 读数据

//4路组相联映射缓存
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
		  count <= 8'd0;
		  temp <= 8'd0;
		  least_set <= 2'b0;
		  not_valid <= 1'b0;
	  end
      else begin
		  	least_set <= 2'b0;
		    ena <= 1'b1;
		  	enb <= 1'b1;
			r_addr <= address[17:2];			//根据Index读出缓存中数据
			if(r_data[91]==1 && r_data[82:69]==address[31:18])	//匹配到第一组数据有效并且tag号相同
			begin
				hit<=hit+1'b1;
				w_data<=r_data;
				w_data[90:83]<=count;		//更新被使用
				w_addr<=address[17:2];		//Index
				wea <= 1'b1;
			end
			else
			begin
				if (r_data[91]==0)			//如果是无效位直接替换
				begin
					not_valid <= 1'b1;
				end
				else
				begin
					temp <= r_data[90:83];
				end

				if(r_data[68]==1 && r_data[59:46]==address[31:18])	//匹配到第二组数据有效并且tag号相同
				begin
					hit<=hit+1'b1;
					w_data<=r_data;
					w_data[67:60]<=count;		//更新被使用
					w_addr<=address[17:2];		//Index
					wea <= 1'b1;
				end
				else
				begin
					if (r_data[68]==0)			//如果是无效位直接替换
					begin
						not_valid <= 1'b1;
						least_set <= 2'b1;
					end
					else
					begin
						if(not_valid == 0 && temp > r_data[67:60])	// LRU寻找最远访问过的组
						begin
							least_set <= 2'b1;
							temp <= r_data[67:60];
						end
					end

					if(r_data[45]==1 && r_data[36:23]==address[31:18])	//匹配到第三组数据有效并且tag号相同
					begin
						hit<=hit+1'b1;
						w_data<=r_data;
						w_data[44:37]<=count;		//更新被使用
						w_addr<=address[17:2];		//Index
						wea <= 1'b1;
					end
					else
					begin
						if (r_data[45]==0)			//如果是无效位直接替换
						begin
							not_valid <= 1'b1;
							least_set <= 2'b10;
						end
						else
						begin
							if(not_valid == 0 && temp > r_data[44:37])	// LRU寻找最远访问过的组
							begin
								least_set <= 2'b10;
								temp <= r_data[44:37];
							end
						end

						if(r_data[22]==1 && r_data[13:0]==address[31:18])	//匹配到第四组数据有效并且tag号相同
						begin
							hit<=hit+1'b1;
							w_data<=r_data;
							w_data[21:14]<=count;		//更新被使用
							w_addr<=address[17:2];		//Index
							wea <= 1'b1;
						end
						else
						begin
							if (r_data[22]==0)			//如果是无效位直接替换
							begin
								not_valid <= 1'b1;
								least_set <= 2'b11;
							end
							else
							begin
								if(not_valid == 0 && temp > r_data[21:14])	// LRU寻找最远访问过的组
								begin
									least_set <= 2'b11;
								end
							end
							w_data<=r_data;
							//全部未命中，进行替换
							case(least_set)
								2'b0:
								begin
									w_data[91]<=1;				//有效位置为1
									w_data[90:83]<=count;		//更新被使用
									w_data[82:69]<=address[31:18];//Tag
								end
								2'b1:
								begin
									w_data[68]<=1;				//有效位置为1
									w_data[67:60]<=count;		//更新被使用
									w_data[59:46]<=address[31:18];//Tag
								end
								2'b10:
								begin
									w_data[45]<=1;				//有效位置为1
									w_data[44:37]<=count;		//更新被使用
									w_data[36:23]<=address[31:18];//Tag
								end
								2'b11:
								begin
									w_data[22]<=1;				//有效位置为1
									w_data[21:14]<=count;		//更新被使用
									w_data[13:0]<=address[31:18];//Tag
								end
							endcase
							w_addr<=address[17:2];		//Index
							wea <= 1'b1;
							miss<=miss+1'b1;
						end
					end
				end
			end
			count<=count+1'b1;					//LRU计数
	  end 
end 

////////////////////////////////////////////////
//实例化RAM 
blk_mem_gen_0 ram_ip_test ( 
  .clka      (clk          ),            // input clka 
  .ena       (ena          ),            // input [1 : 0] ena 
  .wea       (wea          ),            // input [1 : 0] wea 
  .addra     (w_addr       ),            // input [16 : 0] addra 
  .dina      (w_data       ),            // input [92 : 0] dina 
   .clkb     (clk          ),            // input clkb 
   .enb      (enb          ),			 // input [1 : 0] ena 
   .addrb    (r_addr       ),            // input [16 : 0] addrb 
   .doutb    (r_data       )             // output [92 : 0] doutb 
  ); 

endmodule
