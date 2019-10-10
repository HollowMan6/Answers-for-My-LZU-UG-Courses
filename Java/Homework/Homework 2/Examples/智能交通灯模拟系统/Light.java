package java智能交通灯模拟系统;

public class Light implements Runnable{  
    int x;  
    int y; 
    int tm;
    long e;
    long s;
    boolean redLight=true;
    boolean yellowLight=false;
    boolean greenLight=false;  
    int direct;  
    //红绿灯来回切换  
    static int kaiGuan=0;  
    static boolean zanTing=true;  
    public Light(){}   
    public Light(int x,int y,int direct){  
        this.x=x;    
      this.y=y;    
      this.direct=direct;   
    }  
  
    @Override
	public void run() {  
        // TODO Auto-generated method stub  
        while(true){  
            if(kaiGuan==0){  
                for(int i=0;i<HuPanel.vtLight.size();i++){  
                    Light light=HuPanel.vtLight.get(i); 
                    if(light.direct>3){
                    	if(light.direct%2==0){
                        	light.greenLight=false;
                        	light.yellowLight=true;
                    	}
                    }
                }
                //切换灯  
                Light.kaiGuan=2;
            }else if(kaiGuan==1){  
                for (int i = 0; i < HuPanel.vtLight.size(); i++) {  
                    Light light = HuPanel.vtLight.get(i); 
                    if(light.direct>3){
                    	if (light.direct%2 != 0) {
                    		light.greenLight = false;
                    		light.yellowLight=true;  
                    	}  
                    }
                }  
                //切换灯  
                Light.kaiGuan=3;  
            } else if(kaiGuan==2){  
                for (int i = 0; i < HuPanel.vtLight.size(); i++) {  
                    Light light = HuPanel.vtLight.get(i); 
                    if(light.direct<=3){
                    	if (light.direct%2 == 0) {
                    		light.greenLight = false;
                    		light.yellowLight=true;  
                    	}  
                    }
                }  
                //切换灯  
                Light.kaiGuan=1;  
            } else if(kaiGuan==3){  
                for (int i = 0; i < HuPanel.vtLight.size(); i++) {  
                    Light light = HuPanel.vtLight.get(i); 
                    if(light.direct<=3){
                    	if (light.direct%2 != 0) {
                    		light.greenLight = false;
                    		light.yellowLight=true;  
                    	}  
                    }
                }  
                //切换灯  
                Light.kaiGuan=0;  
            } 
            try {  
                Thread.sleep(60/Car.speed*MyCar.t0);  
            } catch (InterruptedException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }
            Hufan.count=-1;
            for(int i=0;i<HuPanel.vtLight.size();i++){  
                Light light=HuPanel.vtLight.get(i);   
                if(light.yellowLight==true){
                    light.yellowLight=false;
                    light.redLight=true;
                    Hufan.count=light.direct;
                } 
            }
            for(int i=0;i<HuPanel.vtLight.size();i++){  
                Light light=HuPanel.vtLight.get(i); 
                if(Hufan.count!=-1)
                {
                	switch(Hufan.count){
                		case 0:
                			if(light.direct==5||light.direct==7){
                				light.redLight=false;
                				light.greenLight=true;
                			}
                			break;
                		case 1:
                			if(light.direct==4||light.direct==6){
                				light.redLight=false;
                				light.greenLight=true;
                			}
                			break;
                		case 2:
                			if(light.direct==5||light.direct==7){
                				light.redLight=false;
                				light.greenLight=true;
                			}
                			break;
                		case 3:
                			if(light.direct==4||light.direct==6){
                				light.redLight=false;
                				light.greenLight=true;
                			}
                			break;
                		case 4:
                			if(light.direct==0||light.direct==2){
                				light.redLight=false;
                				light.greenLight=true;
                			}
                			break;
                		case 5:
                			if(light.direct==1||light.direct==3){
                				light.redLight=false;
                				light.greenLight=true;
                			}
                			break;
                		case 6:
                			if(light.direct==0||light.direct==2){
                				light.redLight=false;
                				light.greenLight=true;
                			}
                			break;
                		case 7:
                			if(light.direct==1||light.direct==3){
                				light.redLight=false;  
                				light.greenLight=true;
                			}
                			break;
                		
                	}
                }
            }
            
            tm=40/Car.speed*MyCar.t0;
            if(kaiGuan==2){//南北直道
        		s=tm*(MyCar.carNUMBER0*3/5);
        		if(s>12000)s=12000;
        		if(s<2000)s=2000;
        	}else if(kaiGuan==1){//东西转弯
        		s=tm*(MyCar.carNUMBER3*3/5);
        		if(s>12000)s=12000;
        		if(s<2000)s=2000;
        	}else if(kaiGuan==3){//东西直道
        		s=tm*(MyCar.carNUMBER1*3/5);
        		if(s>12000)s=12000;
        		if(s<2000)s=2000;
        	}else if(kaiGuan==0){//南北转弯
        		s=tm*(MyCar.carNUMBER2*3/5);
        		if(s>12000)s=12000;
        		if(s<2000)s=2000;
        	}
            try {  
            		Thread.sleep(s);
            } catch (InterruptedException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }
            
        }  
    }  
}  