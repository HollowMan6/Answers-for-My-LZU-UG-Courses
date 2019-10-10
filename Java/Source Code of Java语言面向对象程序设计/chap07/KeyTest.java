import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class KeyTest extends JPanel {
  public KeyTest() {
    JTextField tField = new JTextField(20);
    add(tField);
    MyKeyAdapter bAction = new MyKeyAdapter();
    tField.addKeyListener(bAction);
  }

  private class MyKeyAdapter extends KeyAdapter {
    public void keyPressed(KeyEvent kevent) {
      System.out.println("you pressed the key:" + kevent.getKeyText(kevent.getKeyCode()));
      setBackground(Color.blue);
      repaint();
    }

    public void keyReleased(KeyEvent kevent) {
      System.out.println("code:" + kevent.getKeyCode());
      setBackground(Color.red);
      repaint();
    }

    public void keyTyped(KeyEvent kevent) {
      if (kevent.getKeyChar() == 'x')
        System.exit(0);
    }
  }

  public static void main(String[] args) {
    JFrame myapp = new JFrame("键盘测试!");
    KeyTest mypanel = new KeyTest();
    myapp.add(mypanel);
    myapp.setSize(300, 300);
    myapp.setVisible(true);
    myapp.addWindowListener(new WindowDestroyer());
  }
}