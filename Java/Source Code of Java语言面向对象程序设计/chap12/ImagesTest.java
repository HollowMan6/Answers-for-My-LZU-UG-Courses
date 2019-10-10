/* <applet code="ImagesTest.class" width=450 height=300>
</applet>
*/
import java.awt.Graphics;
import java.awt.Image;
public class ImagesTest extends java.applet.Applet{
Image img;
public void init(){
   img=getImage(getCodeBase(),"boxwave.gif");
}
public void paint(Graphics g){
  int w=img.getWidth(this);
  int h=img.getHeight(this);
  g.drawImage(img,20,10,this); //原图
  g.drawImage(img,200,10,w/2,h/2,this); //缩小一半
  g.drawImage(img,20,200,w*2,h/3,this); //宽扁图
  g.drawImage(img,350,10,w/2,h*2,this); //瘦高图
}
}
