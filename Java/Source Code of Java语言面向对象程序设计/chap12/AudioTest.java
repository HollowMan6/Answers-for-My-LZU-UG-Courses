/* <applet code="AudioTest.class" width=300 height=100></applet>*/
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.applet.*;
public class AudioTest extends Applet implements ActionListener
{
  String hurl;
  AudioClip audio;
  boolean hState=false;   //声音播放状态
  public void init()   //初始化应用程序
  {
    resize(200,60);
    if(hurl==null)
    {
      hurl="spacemusic.au";
    }
    try
    {
      audio=getAudioClip(getDocumentBase(),hurl);
    }
    catch(Exception e) {}
    this.setBackground(Color.lightGray);
    Button myButtonl=new Button("播放");
    myButtonl.addActionListener(this);
    this.add(myButtonl);
    Button myButton2=new Button("停止");
    myButton2.addActionListener(this);
    this.add(myButton2);
    Button myButton3=new Button("循环");
    myButton3.addActionListener(this);
    this.add(myButton3);
  }
  public void actionPerformed(ActionEvent e)
  {
    if(e.getActionCommand().equals("播放"))
    {
      if(hState==false) //如果播放状态为false则处理
      {
        audio.play();
        hState=true;
      }
    }
    else
    {
      if(e.getActionCommand().equals("停止"))
      {
        if(hState==true)
        {
          audio.stop();
          hState=false;
        }
      }
      else
      {
        if(e.getActionCommand().equals("循环"))
        {
          audio.loop();
          hState=true;                           
        }
      }
    }
  }
}
