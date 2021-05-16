public class LianLianKan {
	private int[][] m;
    	private int num;
    	private int NULL = -1;
    	private int width;
    	private int height;
    	private int firstX;
    	private int firstY;
    	private int secondX;
    	private int secondY;
    	public LianLianKan(int wid,int hei,int typeNum){
        	width = wid;
        	height = hei;
        	m = new int [width+2][height+2];
        	num = typeNum;
        	for(int i=0;i <= width+1;i++){
            		m[i][0] = NULL;
            		m[i][height+1] = NULL;
        	}
        	for(int j=0;j<=height+1;j++){
            		m[0][j] = NULL;
            		m[width+1][j] = NULL;
        	}
    	}
    	public void setImageArray(int x,int y,int type){
        	m[x][y]  = type;
    	}
    	public int getImageArray(int x,int y){
        	return m[x][y];
    	}
    	public boolean isNullImage(int x,int y){
        	if(m[x][y] == NULL)
            		return true;
        	else 
			return false;
    	}
    	public int getFirstX(){
        	return firstX;
    	}
    	public int getFirstY(){
        	return firstY;
    	}
    	public int getSecondX(){
        	return secondX;
    	}
    	public int getSecondY(){
        	return secondY;
    	}
    	public boolean ifCanConnect(int x1,int y1,int x2,int y2){
        	if(ifCanOneLineConnect(x1,y1,x2,y2))
            		return true;
        	else if(ifCanTwoLinesConnect(x1,y1,x2,y2))
            		return true;
        	else if(ifCanThreeLinesConnect(x1,y1,x2,y2))
            		return true;
        	else 
			return false;
    	} 
    	public void setNULL(int x,int y){
        	m[x][y] = NULL;
    	}
    	public boolean ifImageSame(int x1,int y1,int x2,int y2){
        	if(m[x1][y1] == m[x2][y2] && !(x1 == x2 && y1 == y2))
            		return true;
        	else 
			return false;
    	}
    	public boolean ifCanOneLineConnect(int x1,int y1,int x2,int y2){
        	if( x1 == x2 || y1 == y2){
            		if(x1 == x2){
                		if(y1 - y2 == 1 || y2 - y1 == 1)
                    			return true;
                		if(y1 < y2){
                    			for(int i=y1+1;i<=y2-1;i++){
                       				if(m[x1][i] != NULL)
                           				return false;
                    			}
                    			return true;
                    
                		}else{
                    			for(int i= y2+1;i <= y1-1;i++){
                        			if(m[x1][i] != NULL)
                            				return false;
                    			}
                    			return true;
                    
                		}
            		}else{  
                		if(x1 - x2 == 1 || x2 - x1 == 1){
                    			return true;
                		}
                		if(x1 < x2){
                    			for(int i=x1+1;i <= x2-1;i++){
                        			if(m[i][y1] != NULL)
                            				return false;
                    			}
                    			return true;                                        
                		}else{
                    			for(int i=x2+1;i <= x1-1;i++){
                        			if(m[i][y1] != NULL){
                            				return false;
                        			}
                    			}
                    			return true;                    
                		}
            		}
        	}    
        	return false;
    	}
    	public boolean ifCanTwoLinesConnect(int x1,int y1,int x2,int y2){
       		if(x1 - x2 == 0 || y1 - y2 ==0)
            		return false;
        	else{
            		ReachBorder border = new ReachBorder(x1,y1);
            		int i;
            		for(i = y1-1;i>=border.up;i--){
                		if(ifCanOneLineConnect(x1,i,x2,y2)){
                    			firstX = x1;
                    			firstY = i;
                    			return true;
                		}
            		}
            		for(i = y1+1;i<=border.down;i++){
                		if(ifCanOneLineConnect(x1,i,x2,y2)){
                    			firstX = x1;
                    			firstY = i;
                    			return true;                        
                		}
            		}
            		for(i = x1-1;i>=border.left;i--){
                		if(ifCanOneLineConnect(i,y1,x2,y2)){
                    			firstX = i;
                    			firstY = y1;
                    			return true;                        
                		}
            		}
            		for(i = x1+1;i<=border.right;i++){
                		if(ifCanOneLineConnect(i,y1,x2,y2)){
                    			firstX = i;
                    			firstY = y1;
                    			return true;                        
                		}                    
            		}
            		return false;
        	}
    	}
    	public boolean ifCanThreeLinesConnect(int x1,int y1,int x2,int y2){
        	ReachBorder border = new ReachBorder(x1,y1);
        	int i;
        	for(i = y1-1;i>=border.up;i--){
            		if(ifCanTwoLinesConnect(x1,i,x2,y2)){
                		secondX = x1;
                		secondY = i;
                		return true;
            		}
        	}
        	for(i = y1+1;i<=border.down;i++){
            		if(ifCanTwoLinesConnect(x1,i,x2,y2)){
                		secondX = x1;
                		secondY = i;
                		return true;                        
            		}
        	}
        	for(i = x1-1;i>=border.left;i--){
            		if(ifCanTwoLinesConnect(i,y1,x2,y2)){
                		secondX = i;
                		secondY = y1;
                		return true;                        
            		}
        	}
        	for(i = x1+1;i<=border.right;i++){
            		if(ifCanTwoLinesConnect(i,y1,x2,y2)){
                		secondX = i;
                		secondY = y1;
                		return true;                        
            		}                    
        	}
        	return false;
    	}
    	public class ReachBorder {
        	private int up;
        	private int down;
        	private int left;
        	private int right;
        	public ReachBorder(int x,int y){
            		int i;
            		for( i = y-1;i >= 0; i--){
                		if(m[x][i] != NULL)
                    			break;
            		}
            		up = i+1;
            		for( i = y+1; i <= height+1; i++){
                		if(m[x][i] != NULL)
                    			break;
            		}
            		down = i-1;
            		for( i = x-1;i >= 0;i--){
                		if(m[i][y] != NULL)
                    			break;
            		}
            		left = i+1;
            		for( i = x+1;i<= width+1;i++){
                		if(m[i][y] != NULL)
                    			break;
            		}
            		right = i-1;
        	} 
    	}
    	void reArrange(){
        	for(int i = 1; i <= width; i++)
            		for(int j = 1;j <= height; j++){
                		int tmp,ii,jj;
                		tmp = m[i][j];
                		ii = (i*3) % width + 1;
                		jj = (j*2) % height + 1;   
                		m[i][j] = m[ii][jj];
                		m[ii][jj] = tmp;
            		}
    	}
}
