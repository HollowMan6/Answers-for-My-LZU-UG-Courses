import java.awt.*;
import java.awt.event.*;

class MyPanel extends Panel {
  String msg = "Pressed Key: ";

  public MyPanel() {
    MyKeyAdapter bAction = new MyKeyAdapter();
    TextField tField = new TextField(20);
    add(tField);
    tField.addKeyListener(bAction);
  }

  public void paint(Graphics g) {
    g.setFont(new Font("宋体", Font.BOLD, 24));
    g.drawString(msg, 20, 50);
  }

  private class MyKeyAdapter extends KeyAdapter {
    public void keyPressed(KeyEvent kevent) {
      int keycode = kevent.getKeyCode();
      msg = "Pressed Key: " + keycode + " char: " + (char) keycode;
      repaint();
    }

    public void keyReleased(KeyEvent kevent) {
      setBackground(Color.red);
      repaint();
    }

    public void keyTyped(KeyEvent kevent) {
      if (kevent.getKeyChar() == 'x')
        System.exit(0);
    }
  }
}

public class keytest1 {
  public static void main(String[] args) {
    Frame myframe = new Frame("键盘测试!");
    MyPanel mypanel = new MyPanel();
    myframe.add(mypanel);
    myframe.addWindowListener(new WindowCloser());
    myframe.setSize(400, 300);
    myframe.setVisible(true);
  }
}