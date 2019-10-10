import java.awt.*;
public class GridBagLayoutDemo extends Frame {
  private GridBagLayout gridbag = new GridBagLayout();
  private GridBagConstraints c = new GridBagConstraints();
  private Button[] buttons=new Button[7];
  public GridBagLayoutDemo(String title) {
    super(title);
    setLayout(gridbag);

    for(int i=0;i<buttons.length;i++)
      buttons[i]=new Button("button"+i);

    c.fill=GridBagConstraints.BOTH;
    addComponent(buttons[0],0,0,1,3);

    c.fill=GridBagConstraints.HORIZONTAL;
    addComponent(buttons[1], 0,1,2,1);

    addComponent(buttons[2], 2,1,2,1);

    c.weightx=1000;  //设定水平方向的重量
    c.weighty=1;  //设定垂直方向的重量
    c.fill=GridBagConstraints.BOTH;
    addComponent(buttons[3], 1,1,1,1);

    c.weightx=0;
    c.weighty=0;
    addComponent(buttons[4], 1,2,1,1);

    addComponent(buttons[5], 3,0,2,1);
    addComponent(buttons[6], 3,2,1,1);
    pack();
    setVisible(true);
  }
  protected void addComponent(Component component,
                            int row,int column,
                            int width,int height) {
    c.gridx=column;
    c.gridy=row;
    c.gridwidth=width;
    c.gridheight=height;
    gridbag.setConstraints(component, c);
    add(component);
  }
public static void main(String args[]) {
     new GridBagLayoutDemo ("Hello");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
