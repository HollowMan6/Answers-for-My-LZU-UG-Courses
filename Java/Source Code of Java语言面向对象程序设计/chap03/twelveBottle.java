public class twelveBottle {
	private static int bottles[] = null;
        static {
		bottles = new int[12];
		for(int i=0; i<12; i++)
			bottles[i] = 10;

		int specialNo = (int)(Math.random()*100)%12;
		double radom = Math.random();
		while(radom == 0.5) 
			radom = Math.random();
		int specialWeight = 10 + ((radom-0.5)>0?1:-1);
		//随机赋予一只瓶子的重量为11或者9
		bottles[specialNo] = specialWeight;
System.out.println("特殊的瓶子是"+specialNo+",重量为"+bottles[specialNo]);
	}
	
	public static void main(String[] args) {
		
		
		weighting("第一次称重: \t左边:0,1,2,3\t右边:4,5,6,7");
		if(bottles[0]+bottles[1]+bottles[2]+bottles[3]>
			bottles[4]+bottles[5]+bottles[6]+bottles[7]) { 
				weighting("结果：左边重！\t结论：特殊的瓶子在这八只里面。");
				
				weighting("第二次称重: \t左边:0,1,5\t右边:4,2,8");
				if(bottles[0]+bottles[1]+bottles[5] 
					> bottles[4]+bottles[2]+bottles[8]) {
						weighting("结果：左边重！\t结论：0号瓶，1号瓶有一个重，或者4号瓶轻。");
						
						weighting("第三次称重: \t左边:0\t右边:1");
						if(bottles[0] == bottles[1])
							weighting("结果：一样重！\t结论：特殊的瓶子是4号！\n称量结束！");
						else if(bottles[0] > bottles[1])
							weighting("结果：左边重！\t结论：特殊的瓶子是0号！\n称量结束！");
						else
							weighting("结果：右边重！\t结论：特殊的瓶子是1号！\n称量结束！");
								
				}
				else if(bottles[0]+bottles[1]+bottles[5] 
					< bottles[4]+bottles[2]+bottles[8]){ 
						weighting("结果：左边重！\t结论：5号瓶轻,或2号瓶重。");
						
						weighting("第三次称重: \t左边:2\t右边:1");
						if(bottles[2] == bottles[1])
							weighting("结果：一样重！\t结论：特殊的瓶子是5号！\n称量结束！");
						else
							weighting("结果：不一样重！\t结论：特殊的瓶子是2号！\n称量结束！");
				}
				else {
					weighting("结果：一样重！\t结论：6号瓶，7号瓶有一个轻，或者3号瓶重。");
					
					weighting("第三次称重: \t左边:6\t右边:7");
					if(bottles[6] == bottles[7])
						weighting("结果：一样重！\t结论：特殊的瓶子是3号！\n称量结束！");
					else if(bottles[6] > bottles[7])
						weighting("结果：左边重！\t结论：特殊的瓶子是7号！\n称量结束！");
					else
						weighting("结果：右边重！\t结论：特殊的瓶子是6号！\n称量结束！");						
				}
		}							 
	 
	 else if(bottles[0]+bottles[1]+bottles[2]+bottles[3] <
			bottles[4]+bottles[5]+bottles[6]+bottles[7]) { 
				weighting("结果：右边重！\t结论：特殊的瓶子在这八只里面。");
				
				weighting("第二次称重: \t左边:0,1,5\t右边:4,2,8");
				if(bottles[0]+bottles[1]+bottles[5] 
					< bottles[4]+bottles[2]+bottles[8]) {
						weighting("结果：右边重！\t结论：0号瓶，1号瓶有一个轻，或者4号瓶重。");
						
						weighting("第三次称重: \t左边:0\t右边:1");
						if(bottles[0] == bottles[1])
							weighting("结果：一样重！\t结论：特殊的瓶子是4号！\n称量结束！");
						else if(bottles[0] > bottles[1])
							weighting("结果：左边重！\t结论：特殊的瓶子是1号！\n称量结束！");
						else
							weighting("结果：右边重！\t结论：特殊的瓶子是0号！\n称量结束！");
								
				}
				else if(bottles[0]+bottles[1]+bottles[5] 
					> bottles[4]+bottles[2]+bottles[8]) {
						weighting("结果：左边重！\t结论：2号瓶轻,或5号瓶重。");
						
						weighting("第三次称重: \t左边:2\t右边:1");
						if(bottles[2] == bottles[1])
							weighting("结果：一样重！\t结论：特殊的瓶子是5号！\n称量结束！");
						else
							weighting("结果：不一样重！\t结论：特殊的瓶子是2号！\n称量结束！");
				}
				else {
					weighting("结果：一样重！\t结论：6号瓶，7号瓶有一个重，或者3号瓶轻。");
					
					weighting("第三次称重: \t左边:6\t右边:7");
					if(bottles[6] == bottles[7])
						weighting("结果：一样重！\t结论：特殊的瓶子是3号！\n称量结束！");
					else if(bottles[6] > bottles[7])
						weighting("结果：左边重！\t结论：特殊的瓶子是6号！\n称量结束！");
					else
						weighting("结果：右边重！\t结论：特殊的瓶子是7号！\n称量结束！");						
				}
	 }
		else {
			weighting("结果：一样重！\t结论：特殊的瓶子是8,9,10,11之中的一只！");
				
			weighting("第二次称重: \t左边:8\t右边:9");
			if(bottles[8] == bottles[9]) {
				weighting("结果：一样重！\t结论：特殊的瓶子是10,11之中的一只！");	
				
				weighting("第三次称重: \t左边:8\t右边:10");
				if(bottles[8] == bottles[10]) 
					weighting("结果：一样重！\t结论：特殊的瓶子是11号！\n称量结束！");
				else 
					weighting("结果：不一样重\t结论：特殊的瓶子是10号！\n称量结束！");
					
			} 
			else {
				weighting("结果：不一样重！\t结论：特殊的瓶子是8,9之中的一只！");
				
				weighting("第三次称重: \t左边:8\t右边:10");
				if(bottles[8] == bottles[10]) 
					weighting("结果：一样重！\t结论：特殊的瓶子是9号！\n称量结束！");
				else
					weighting("结果：不一样重！\t结论：特殊的瓶子是8号！\n称量结束！");
			}	
	
		}
	}
	private static void weighting(String s){
		System.out.println(s);
	}

}