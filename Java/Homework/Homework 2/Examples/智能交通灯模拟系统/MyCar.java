package java智能交通灯模拟系统;

import java.awt.Color;  

public class MyCar extends Car implements Runnable{  
    //到十字路口，往哪个方向  
	static int carNU, carNUMBER=0,carNUMBER0=0,carNUMBER1=0,carNUMBER2=0,carNUMBER3=0;
    boolean newDirect=false;  
    static int t0=50;
    Color colorCar;  
    public MyCar(int x, int y, int direct, Color colorCar) {  
        // TODO Auto-generated constructor stub  
        super(x, y, direct);
        carNUMBER++;
        carNU++;
        if(direct>3){
    		if(direct%2==0) carNUMBER2++;
    		else  carNUMBER3++;  
    	}else {
    		if(direct%2==0) carNUMBER0++;
    		else  carNUMBER1++;
    	}
       // this.newDirect=newDirect;  
        this.colorCar=colorCar;  
    }  
    //换方向  
  
    @Override
	public synchronized void run() {  
        // TODO Auto-generated method stub  
        while(true){  
            if(zanTing){  
                switch(direct){    
                case 0:   
                    panDuan(x, y+25+Car.speed, direct);
                    if(stopCar){   
                        y+=speed;     
                    }    
                    break;    
                case 1: 
                    ////////  
                    panDuan(x+25+Car.speed,y,direct);
                    if(stopCar){  
                        x+=speed;   
                    }  
                       
                    break;    
                case 2:
                    ////////
                    panDuan(x, y-Car.speed, direct);
                    if(stopCar){  
                        y-=speed;    
                    }      
                    break;    
                case 3:    
                    
                    ////////  
                    panDuan(x-Car.speed, y, direct);
                    if(stopCar){  
                        x-=speed;    
                    }      
                    break; 
                case 4:    
                    
                    ////////  
                    panDuan(x, y+25+Car.speed, direct);
                    if(y<294){
                    	if(stopCar){  
                    		y+=speed;    
                    	}  
                    }else if(y<354){
                    	if(stopCar){  
                    		y+=speed;
                    		x+=speed;
                    		newDirect=true;
                    	}
                    }else  if(stopCar){  
                		x+=speed;    
                	}
                    break; 
                    
                case 5:    
                    
                    ////////  
                    panDuan(x+25+Car.speed, y, direct);
                    if(x<294){
                    	if(stopCar){  
                    		x+=speed;    
                    	}  
                    }else if(x<354){
                    	if(stopCar){  
                    		y-=speed;
                    		x+=speed;
                    		newDirect=true;
                    	}
                    }else  if(stopCar){  
                		y-=speed;    
                	} 
                    break;
                    
                case 6:    
                    
                    ////////  
                    panDuan(x, y-Car.speed, direct);
                    if(y>386){
                    	if(stopCar){  
                    		y-=speed;    
                    	}  
                    }else if(y>328){
                    	if(stopCar){  
                    		y-=speed;
                    		x-=speed;
                    		newDirect=true;
                    	}
                    }else  if(stopCar){  
                		x-=speed;    
                	}      
                    break;
                    
                case 7:    
                    
                    ////////  
                    panDuan(x-Car.speed, y, direct);
                    if(x>386){
                    	if(stopCar){  
                    		x-=speed;    
                    	}  
                    }else if(x>328){
                    	if(stopCar){  
                    		y+=speed;
                    		x-=speed;
                    		newDirect=true;
                    	}
                    } else  if(stopCar){  
                		y+=speed;    
                	}     
                    break;
 
                }  
            }  
            //如果死亡，退出线程  
            if(isStop==false){  
                break;  
            }
            Hufan.hp.repaint();  
            try {  
                Thread.sleep(t0);  
            } catch (InterruptedException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
  
    }  
    public void panDuan(int x,int y,int direct){  
        //保存前面车  
        Car startCar=null;
        int qCar=0;
        // 碰到边界，从arrAcr删除  
        if(x<0&&(direct==3||direct==6)||x>700&&(direct==1||direct==4)||y<0&&(direct==2||direct==5)||y>700&&(direct==0||direct==7)){     
            HuPanel.vtCar.remove(this);
            if(this.isLive==true){
            	carNUMBER--;
            	if(direct>3){
            		if(direct%2==0) carNUMBER2--;
            		else  carNUMBER3--;  
            	}else {
            		if(direct%2==0) carNUMBER0--;
            		else  carNUMBER1--;
            	}
            }
            this.isStop=false;  
            this.isLive=false;
        }   
        //车碰车     
        for(int i=0;i<HuPanel.vtCar.size();i++){    
            try{
            	Car car=HuPanel.vtCar.get(i);  
            if(isLive&&!car.equals(this)){  
            	switch(direct){  
                case 0:  
                	if(direct==car.direct && y>=car.y-1&&y<=car.y+25){  
                       qCar++ ;
                    }
                    break;  
                case 1:  
                	if(x>=car.x-1&&x<=car.x+15&&direct==car.direct){   
                		qCar++ ;  
                    }  
                    break; 
                case 2:  
                	if(direct==car.direct&&y>=car.y&&y<=car.y+26){  
                		qCar++ ;  
                    }
                    break;  
                case 3:  
                	if(x>=car.x&&x<=car.x+26&&direct==car.direct){   
                		qCar++ ;  
                    }  
                    break;
                case 4:  
                    	if(direct==car.direct&& y>=car.y-1&&y<=car.y+15&&newDirect==false){  
                    		qCar++ ;  
                        }
                        break;  
                case 5:  
                    	if(x>=car.x-1&&x<=car.x+15&&direct==car.direct&&newDirect==false){   
                    		qCar++ ;  
                        }  
                        break; 
                case 6:  
                    	if(direct==car.direct&&y>=car.y&&y<=car.y+26&&newDirect==false){  
                    		qCar++ ;  
                        }
                        break;  
                case 7:  
                    	if(x>=car.x&&x<=car.x+26&&direct==car.direct&&newDirect==false){   
                    		qCar++ ; 
                        }  
                        break;
                }  
            	
            	
                   
            } 
            }catch(ArrayIndexOutOfBoundsException e){}
        }if(qCar==0)  stopCar=true;
        else stopCar=false;
        //是否碰到灯     
        for(int i=0;i<HuPanel.vtLight.size();i++){    
            Light light=HuPanel.vtLight.get(i);    
            if(direct%2==0){    
                if(light.direct==direct&&y>=light.y&&y<=light.y+15){  
                    if(light.redLight==true&&light.direct==direct||light.yellowLight==true&&light.direct==direct){    
                        stopCar=false;    
                    }else{    
                        stopCar=true;    
                    }    
                }    
            }else{    
                if(x>=light.x&&x<=light.x+15&&light.direct==direct){    
                    if(light.redLight==true&&light.direct==direct||light.yellowLight==true&&light.direct==direct){    
                        stopCar=false;    
                    }else{    
                        stopCar=true;    
                    }    
                }    
            }    
        }  
    }  
}  


