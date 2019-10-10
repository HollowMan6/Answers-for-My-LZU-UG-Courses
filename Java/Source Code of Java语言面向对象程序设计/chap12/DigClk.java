/* <applet code="DigClk.class" width=200 height=100></applet> */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Date;
public class DigClk extends java.applet.Applet implements Runnable
{
Thread timer = null;
Image[] digit_image = new Image[10]; // 数码（0－9）图像数组
Image colon_image, // 冒号图像
frame_image; // 边框图像
int digit_height = 21; // 数码（及冒号）高度
int digit_width = 16; // 数码宽度
int colon_width = 9; // 冒号宽度
int offset = 4; // 边框厚度
int applet_width;
int applet_height;
int[] image_start_x = new int[8]; // 数码或冒号的水平起始位置数组
public void init()
{
for (int i = 0; i < 10; i++){
digit_image[i] = getImage(getCodeBase(), "dcimage/dc" + i + ".gif");
}
colon_image = getImage(getCodeBase(), "dcimage/colon.gif");
frame_image = getImage(getCodeBase(), "dcimage/beijing.gif");
applet_width = (2 * offset) + (6 * digit_width) + (2 * colon_width);//计算applet宽度
applet_height = (2 * offset) + (digit_height); //计算applet高度
image_start_x[0] = offset; // 填充起始位置数组
for (int i = 1; i < 8; i++){ 
if ((i == 3) || (i == 6)) // 下一位置是冒号
image_start_x[i] = image_start_x[i - 1] + colon_width;
else // 下一位置是数码
image_start_x[i] = image_start_x[i - 1] + digit_width;
}
}
public void start()
{
if (timer == null){
timer = new Thread(this);
timer.start();
}
}
public void run()
{
while (timer != null){
try{
timer.sleep(1000); //1秒延时
}catch (InterruptedException e){ }
repaint();
}
}
public void stop()
{
if (timer != null){
timer.stop();
timer = null;
}
}
public void paint(Graphics g)
{
Date now = new Date(); // 获取当前日期和时间的对象
int hour = now.getHours(); // 取小时数
int minute = now.getMinutes(); // 取分钟数
int second = now.getSeconds(); // 取秒钟数
int i = 0; // 水平起始位置数组的索引
g.drawImage(frame_image, 0, 0, this);
g.drawImage(digit_image[hour / 10], image_start_x[i++], offset, this);
g.drawImage(digit_image[hour % 10], image_start_x[i++], offset, this);
g.drawImage(colon_image, image_start_x[i++], offset, this);
g.drawImage(digit_image[minute / 10], image_start_x[i++], offset, this);
g.drawImage(digit_image[minute % 10], image_start_x[i++], offset, this);
g.drawImage(colon_image, image_start_x[i++], offset, this);
g.drawImage(digit_image[second / 10], image_start_x[i++], offset, this);
g.drawImage(digit_image[second % 10], image_start_x[i], offset, this);
}
public void update(Graphics g)
{
paint(g);
}
}
