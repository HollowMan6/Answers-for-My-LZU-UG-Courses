/* <applet code="Walker.class" width=500 height=160> </applet>*/
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
public class Walker extends java.applet.Applet implements Runnable{
MediaTracker tracker;
Image walkerImgs[]=new Image[5]; //存放鸵鸟走路姿势图像
Image currentImg; //当前放映的鸵鸟动作图像
int xpos,ypos=0; //鸵鸟动作图像显示的位置
int walk_step=20; //鸵鸟图像每次移动的距离
int delay = 250; //每帧时延（毫秒）
Thread runThread;
Image bgImage; //存放草原背景图像
int applet_width,applet_height;
int birdImg_width; //鸵鸟图像宽度
public void init(){
tracker = new MediaTracker(this);
for (int i=0;i<walkerImgs.length;i++){ //获取鸵鸟动作图像
walkerImgs[i]=getImage(getCodeBase(),"dcimage/bird"+i+".gif");
tracker.addImage(walkerImgs[i], 0); //列入0组跟踪范围
}
bgImage = getImage(getCodeBase(),"dcimage/"+"bg.gif"); //获取草原背景图像
tracker.addImage(bgImage, 0); //列入0组跟踪范围
applet_width=size().width;
applet_height=size().height;
}
public void start (){
if (runThread==null){
runThread=new Thread(this);
runThread.start();
}
}
public void stop(){
if (runThread!=null){
runThread.stop();
runThread=null;
}
}
public void run(){
try{
tracker.waitForID(0);
}catch(InterruptedException e){
return;
}
birdImg_width=walkerImgs[0].getWidth(this);
int i=0;
while(true){
for(xpos=-birdImg_width;xpos<=applet_width;xpos+=walk_step){//计算位置
currentImg=walkerImgs[i];
repaint();
i=(i+1)%walkerImgs.length; //计算下一帧是哪幅图像
try{Thread.sleep(delay);}
catch(InterruptedException e){}
}
}
}
public void paint(Graphics g){
g.drawImage(bgImage,0,0,this);
g.drawImage(currentImg,xpos,ypos,this);
}
public void update(Graphics g){
paint(g);
}
}
