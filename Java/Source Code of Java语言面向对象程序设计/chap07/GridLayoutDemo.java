import java.awt.*;
public class GridLayoutDemo extends Frame{
  private Panel panel;
  private Label label;
  private String[] names={"7","8","9","+","4","5","6","-","1","2","3","*","0",".","=","/"};
  private Button[] buttons=new Button[16];
  public GridLayoutDemo(String title){
    super(title);
    label=new Label();
    panel=new Panel(); 
    panel.setLayout(new GridLayout(4,4));
    add(label,BorderLayout.NORTH);
    add(panel,BorderLayout.CENTER);
   
    for(int i=0;i<buttons.length;i++){
      buttons[i]=new Button(names[i]);
      panel.add(buttons[i]);
    }
    pack();
    setVisible(true);
  }
  public static void main(String[] args){
    new GridLayoutDemo("GridLayoutDemo");
  }
}