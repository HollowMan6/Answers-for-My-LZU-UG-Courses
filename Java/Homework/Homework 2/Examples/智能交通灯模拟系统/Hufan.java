package java智能交通灯模拟系统;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.util.*;  
public class Hufan extends JFrame implements ActionListener{  
    static HuPanel hp;  
    static int count;
    JMenuBar jmb;  
    JMenu jm1,jm2;  
    JMenuItem jmi1,jmi2,jmi3,jmi4;  
    StartPanel sp;  
    public static void main(String[] args) {  
        new Hufan();  
    }  
    public Hufan(){  
        jmb=new JMenuBar();  
        jm1=new JMenu("模拟");  
        jmi1=new JMenuItem("开始模拟");  
        jmi1.addActionListener(this);  
        jmi2=new JMenuItem("暂停");  
        jmi2.addActionListener(this);  
        jmi3=new JMenuItem("退出");  
        jmi3.addActionListener(this);  
        jm1.add(jmi1);  
        jm1.add(jmi2);  
        jm1.add(jmi3);  
        jm2=new JMenu("控制");  
        jmi4=new JMenuItem("设置");  
        jmi4.addActionListener(this);  
        jm2.add(jmi4);  
        jmb.add(jm1);  
        jmb.add(jm2);  
        this.setJMenuBar(jmb);  
          
        sp=new StartPanel();  
        new Thread(sp).start();  
        this.add(sp); 
       
        this.setTitle("模拟交通灯");  
        this.setSize(700, 700);  
        this.setResizable(false);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setVisible(true);  
    }  
    
    @Override
	public void actionPerformed(ActionEvent e) {  
  
        if(e.getSource()==jmi1){  
            sp.isLive=false;  
            this.remove(sp);  
            hp=new HuPanel();  
            this.add(hp);  
            this.setVisible(true);  
        }else if(e.getSource()==jmi2){  
            if(jmi2.getText().equals("暂停")){  
                Car.zanTing=false;  
                hp.zanTing=false;  
                Light.kaiGuan=3;  
                jmi2.setText("继续");  
            }else if(jmi2.getText().equals("继续")){  
                Car.zanTing=true;  
                hp.zanTing=true;  
                Light.kaiGuan=3;  
                jmi2.setText("暂停");  
            }  
        }else if(e.getSource()==jmi3){  
            System.exit(0);  
        }else if(e.getSource()==jmi4){  
            new Set(this,"设置",true);  
        }  
    }  
}  
class StartPanel extends JPanel implements Runnable{  
	int info=0;
	boolean isLive=true;
	@Override
	public void paint(Graphics g) { 
		
		super.paint(g);
        g.fillRect(0, 0, 700, 700);  
        g.setColor(new Color(250,205,0));  
        g.setFont(new Font("宋体",Font.BOLD,30));  
        if(info%2==0){  
            g.drawString("Java模拟智能交通灯系统", 180,300);  
        }
    }  
	
    @Override
	public void run() {  
       
        while(true){  
            try {  
                Thread.sleep(100);  
            } catch (InterruptedException e) {  
                
                e.printStackTrace();  
            }  
            info++;  
            this.repaint();  
            if(isLive==false){  
                break;  
            }  
        }  
    }  
}  
class HuPanel extends JPanel implements Runnable{  
    static Vector<MyCar> vtCar=new Vector<MyCar>();  
    static Vector<Light> vtLight=new Vector<Light>();  
    Random r=new Random(); 
    static Date starttime=new Date();
    boolean zanTing=true;  
    static int carNum=40; 
    public HuPanel(){  
        vtLight.add(new Light(291,274,0));  
        vtLight.add(new Light(274,381,1));    
        vtLight.add(new Light(381,411,2));    
        vtLight.add(new Light(411,291,3));
        vtLight.add(new Light(321,274,4));  
        vtLight.add(new Light(274,351,5));    
        vtLight.add(new Light(351,411,6));    
        vtLight.add(new Light(411,321,7));
        //启动灯线程  
        Thread t=new Thread(new Light());  
        t.start();  
        //启动生成对象车线程  
        new Thread(this).start();  
    }  
    //JFrame自动调用paint，paint是JPanel父类的方法，  
    //所以先super调用父类的paint方法  
    @Override
	public void paint(Graphics g){ 
    	String str="总    车    数",str1="当前总车数",str2="时            间";
        super.paint(g);  
        //画十字车道  
        g.setColor(Color.darkGray);    
        g.fillRect(0, 289, 700, 2);    
        g.fillRect(0, 409, 700, 2);    
        g.fillRect(289, 0, 2, 700);    
        g.fillRect(409, 0, 2, 700);    
        g.setColor(Color.gray);    
        g.fillRect(0, 349, 700, 2);    
        g.fillRect(349, 0, 2, 700);
        g.setColor(Color.orange);
        g.fillRect(0, 319, 700, 2);    
        g.fillRect(319, 0, 2, 700);
        g.fillRect(0, 379, 700, 2);    
        g.fillRect(379, 0, 2, 700);
        
        g.setColor(new Color(250,160,0));   
         //画文字
        String str3=String.valueOf((new Date().getTime() - starttime.getTime())/1000+"秒");
        g.drawString(str,80,20);
        g.drawString(String.valueOf(MyCar.carNU),160,20);
        g.drawString(str1,80,40);
        g.drawString(String.valueOf(MyCar.carNUMBER),160,40);
        g.drawString(str2,80,60);
        g.drawString(str3,160,60);
        
        
      //画灯 
        for(int i=0;i<vtLight.size();i++){    
            Light light=vtLight.get(i);    
            g.setColor(Color.black);   
            if(light.direct==0){    
            	g.fillRect(light.x, light.y, 58, 15);    
            }else if(light.direct==1){    
            	g.fillRect(light.x, light.y-30, 15, 58);    
            }else if(light.direct==2){    
            	g.fillRect(light.x-30, light.y, 58, 15);    
            }else if(light.direct==3){    
            	g.fillRect(light.x, light.y, 15, 58);    
            } 
            
            //绿灯  
            g.setColor(Color.green);    
            if(light.greenLight==true){
            	if(light.direct>3){
            		if(light.direct%2==0){    
            			g.fillOval(light.x+7, light.y+2, 12, 12);    
            		}else{    
            			g.fillOval(light.x+2, light.y+7, 12, 12);    
            		}   
            	}else{
            		if(light.direct%2==0){    
            			g.fillOval(light.x+7, light.y+2, 12, 12);    
            		}else{    
            			g.fillOval(light.x+2, light.y+7, 12, 12);    
            		}
            		
            	}
            }
          //黄灯  
            g.setColor(Color.yellow);    
            if(light.yellowLight==true){    
            	if(light.direct>3){
            		if(light.direct%2==0){    
            			g.fillOval(light.x+7, light.y+2, 12, 12);    
            		}else{    
            			g.fillOval(light.x+2, light.y+7, 12, 12);    
            		}   
            	}else{
            		if(light.direct%2==0){    
            			g.fillOval(light.x+7, light.y+2, 12, 12);    
            		}else{    
            			g.fillOval(light.x+2, light.y+7, 12, 12);    
            		}
            		
            	}    
            }
            //红灯  
            g.setColor(Color.red);    
            if(light.redLight==true){    
            	if(light.direct>3){
            		if(light.direct%2==0){    
            			g.fillOval(light.x+7, light.y+2, 12, 12);    
            		}else{    
            			g.fillOval(light.x+2, light.y+7, 12, 12);    
            		}   
            	}else{
            		if(light.direct%2==0){    
            			g.fillOval(light.x+7, light.y+2, 12, 12);    
            		}else{    
            			g.fillOval(light.x+2, light.y+7, 12, 12);    
            		}
            		
            	}    
            }    
        }  
      //画车     
        for(int i=0;i<vtCar.size();i++){  
            MyCar mc=vtCar.get(i);  
                g.setColor(mc.colorCar);   
            if(mc.direct<=3){
                if(mc.direct%2==0){    
                	g.fill3DRect(vtCar.get(i).x, vtCar.get(i).y, 20, 25,true);    
                }else{    
                	g.fill3DRect(vtCar.get(i).x, vtCar.get(i).y, 25, 20,true);    
                }    
            }else {
            	if(mc.direct%2==0&&mc.newDirect==false||mc.direct%2!=0&&mc.newDirect==true){    
            		g.fill3DRect(vtCar.get(i).x, vtCar.get(i).y, 20, 25,true);    
            	}else{    
            		g.fill3DRect(vtCar.get(i).x, vtCar.get(i).y, 25, 20,true);    
            	}
            }
        }  
    }  
    //得到到十字路口要转的方向  
    public int getDirect(int d){  
        int i=r.nextInt(8);  
        while(true){
            if(i==d){  
                i=r.nextInt(8);  
            }else{  
                break;  
            }  
        }  
        return i;  
    }  
  
    @Override
	public synchronized void run() {  
        MyCar myCar=null;
        int last=-1;
        boolean flag;  
        // TODO Auto-generated method stub  
        while(true){  
            if(zanTing){  
                if(vtCar.size()<carNum){  
                    switch(last=getDirect(last)){  
                    case 0:
                        myCar=new MyCar(294, -25, 0,Color.blue);
                        flag=true;
                        for(int j=0;j<HuPanel.vtCar.size();j++){    
                            try{
                            	Car car=HuPanel.vtCar.get(j);
                            	if(car.equals(myCar))
                            		flag=false;
                            }catch(ArrayIndexOutOfBoundsException e){}
                    	}
                        if(!flag)break;
                        vtCar.add(myCar);    
                        break;  
                    case 1:
                        myCar=new MyCar(-25, 386, 1,Color.green); 
                        flag=true;
                        for(int j=0;j<HuPanel.vtCar.size();j++){    
                            try{
                            	Car car=HuPanel.vtCar.get(j);
                            	if(car.equals(myCar))
                            		flag=false;
                            }catch(ArrayIndexOutOfBoundsException e){}
                    	}
                        if(!flag)break;
                        vtCar.add(myCar);    
                        break;  
                    case 2: 
                        	myCar=new MyCar(386, 700, 2,Color.cyan); 
                        	flag=true;
                            for(int j=0;j<HuPanel.vtCar.size();j++){    
                                try{
                                	Car car=HuPanel.vtCar.get(j);
                                	if(car.equals(myCar))
                                		flag=false;
                                }catch(ArrayIndexOutOfBoundsException e){}
                        	}
                            if(!flag)break;
                            vtCar.add(myCar);    
                            break;  
                    case 3: 
                        myCar=new MyCar(700, 294, 3,Color.orange);
                        flag=true;
                        for(int j=0;j<HuPanel.vtCar.size();j++){    
                            try{
                            	Car car=HuPanel.vtCar.get(j);
                            	if(car.equals(myCar))
                            		flag=false;
                            }catch(ArrayIndexOutOfBoundsException e){}
                    	}
                        if(!flag)break;
                        vtCar.add(myCar);    
                        break;
                    case 4:  
                    	myCar=new MyCar(324,-25, 4,Color.blue);
                    	flag=true;
                        for(int j=0;j<HuPanel.vtCar.size();j++){    
                            try{
                            	Car car=HuPanel.vtCar.get(j);
                            	if(car.equals(myCar))
                            		flag=false;
                            }catch(ArrayIndexOutOfBoundsException e){}
                    	}
                        if(!flag)break;
                        vtCar.add(myCar);    
                        break;
                    case 5:  
                    	myCar=new MyCar(-25, 354, 5,Color.green);
                    	flag=true;
                        for(int j=0;j<HuPanel.vtCar.size();j++){    
                            try{
                            	Car car=HuPanel.vtCar.get(j);
                            	if(car.equals(myCar))
                            		flag=false;
                            }catch(ArrayIndexOutOfBoundsException e){}
                    	}
                        if(!flag)break;
                        vtCar.add(myCar);    
                        break;
                    case 6:  
                    	myCar=new MyCar(354, 700,6,Color.cyan);
                    	flag=true;
                        for(int j=0;j<HuPanel.vtCar.size();j++){    
                            try{
                            	Car car=HuPanel.vtCar.get(j);
                            	if(car.equals(myCar))
                            		flag=false;
                            }catch(ArrayIndexOutOfBoundsException e){}
                    	}
                        if(!flag)break;
                        vtCar.add(myCar);    
                        break;
                    case 7:  
                    	myCar=new MyCar(700, 324,7,Color.orange);
                    	flag=true;
                        for(int j=0;j<HuPanel.vtCar.size();j++){    
                            try{
                            	Car car=HuPanel.vtCar.get(j);
                            	if(car.equals(myCar))
                            		flag=false;
                            }catch(ArrayIndexOutOfBoundsException e){}
                    	}
                        if(!flag)break;
                        vtCar.add(myCar);    
                        break;
                    }  
                    new Thread(myCar).start();  
                }  
            }  
            try {  
                Thread.sleep(40/Car.speed*MyCar.t0);  
            } catch (InterruptedException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
    }  
}  