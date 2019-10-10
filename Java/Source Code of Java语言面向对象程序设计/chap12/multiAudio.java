/* <applet code=multiAudio.class width=300 height=100></applet> */
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.applet.*;
public class multiAudio extends Applet implements ActionListener
{
  String hUr11,hUr12;
  AudioClip audio1,audio2;
  boolean hState1=false;
  boolean hState2=false;
  public void init() 
  {
    resize(300,100);
    hUr11="spacemusic.au";
    hUr12="yesterday.mid";
    try
    { 
      audio1=getAudioClip(new URL(getCodeBase(),hUr11));
      audio2=getAudioClip(new URL(getCodeBase(),hUr12));
    }
    catch(Exception e) {}
    this.setBackground(Color.lightGray);
    Panel p1=new Panel();
    Button myButton1=new Button("播放1");
    myButton1.addActionListener(this);
    p1.add(myButton1);
    Button myButton2=new Button("停止1");
    myButton2.addActionListener(this);
    p1.add(myButton2);
    Button myButton3=new Button("循环1");
    myButton3.addActionListener(this);
    p1.add(myButton3);
    this.add(p1);
    Panel p2=new Panel();
    Button myButton4=new Button("播放2");
    myButton4.addActionListener(this);
    p2.add(myButton4);
    Button myButton5=new Button("停止2");
    myButton5.addActionListener(this);
    p2.add(myButton5);
    Button myButton6=new Button("循环2");;
    myButton6.addActionListener(this);
    p2.add(myButton6);
    this.add(p2);
  }
  public void actionPerformed(ActionEvent e)
  {
    if(e.getActionCommand().equals("播放1"))
    {
      if(hState1==false)
      {
        audio1.play();
        hState1=true;
      }
  }
  else
  {
    if(e.getActionCommand().equals("播放2"))
    {
      if(hState2==false)
      {
        audio2.play();
        hState2=true;
      }
    }
    else
    {
      if(e.getActionCommand().equals("停止1"))
      {
        if(hState1==true)
        {
          audio1.stop();
          hState1=false;
        }
      }
      else
      {
        if(e.getActionCommand().equals("停止2"))
        {
          if(hState2==true)
          {
            audio2.stop();
            hState2=false;
          }
        }
        else
        {
          if(e.getActionCommand().equals("循环1"))
             audio1.loop();
          else audio2.loop();
        }
      }
    }
  }
 }
}
