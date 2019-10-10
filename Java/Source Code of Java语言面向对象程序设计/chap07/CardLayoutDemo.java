import java.awt.*;
import java.awt.event.*;
public class CardLayoutDemo extends Frame{
  private final String names[]={"red","green","blue"};
  private final Color colors[]={Color.RED,Color.GREEN,Color.BLUE};
  private Button[] buttons=new Button[3];
  private Panel northPanel=new Panel();
  private Panel centerPanel=new Panel();
  private Panel[] cardPanels=new Panel[3];
  private GridLayout gridLayout=new GridLayout(1,3);
  private CardLayout cardLayout=new CardLayout();
  ActionListener listener=new ActionListener(){
     public void actionPerformed(ActionEvent event){
       Button button=(Button)event.getSource();
       cardLayout.show(centerPanel,button.getLabel());
     }
  };
  public CardLayoutDemo(String title){
    super(title);
    northPanel.setLayout(gridLayout);
    centerPanel.setLayout(cardLayout);
    for(int i=0;i<buttons.length;i++){
       buttons[i]=new Button(names[i]);
       buttons[i].addActionListener(listener);
       northPanel.add(buttons[i]);
       cardPanels[i]=new Panel();
       cardPanels[i].setBackground(colors[i]);
       centerPanel.add(cardPanels[i],names[i]);  //向centerPanel加入CardPanel
    }
    add(northPanel,BorderLayout.NORTH);
    add(centerPanel,BorderLayout.CENTER);
    setSize(300,300);
    setVisible(true);
  }
  public static void main(String[] args){
     new CardLayoutDemo("CardLayoutDemo");
  }
}