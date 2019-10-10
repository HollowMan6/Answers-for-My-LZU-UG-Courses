import java.awt.*;
import java.awt.event.*;
public class Calculator extends Frame{
    private Panel panel;
    private Label label;
    private Label label1;
    private String[] names={"7","8","9","+","4","5","6","-","1","2","3","*","0",".","=","/"};
    private Button[] buttons=new Button[16];
    MyListener mylistener=new MyListener();
    double ans=0;
    char sign='\0';
    String num="";
    int flag=1;//±ê??

    public Calculator(String title){
	super(title);
	label=new Label("0",2);
	label1=new Label("",2);
	panel=new Panel();
	panel.setLayout(new GridLayout(4,4));
	add(label1,BorderLayout.NORTH);
	add(label,BorderLayout.CENTER);
	add(panel,BorderLayout.SOUTH);
	
	for (int i=0; i<buttons.length; i++){
	    buttons[i]=new Button(names[i]);
	    buttons[i].addActionListener(mylistener);
	    panel.add(buttons[i]);
	}
	pack();
	setVisible(true);
	addWindowListener(new WindowDestroyer());
    }

    public double calculate(double ans,char sign,String num){
	if (sign=='\0' && num=="") return ans;
	if (sign=='\0' && num!=""){
	    ans=Double.parseDouble(num);
	    return ans;
	}else{
	    switch(sign){
		case '+': ans+=Double.parseDouble(num); break;
		case '-': ans-=Double.parseDouble(num); break;
		case '*': ans*=Double.parseDouble(num); break;
		case '/': if (Double.parseDouble(num)!=0.0) ans/=Double.parseDouble(num); 
			  else label.setText("ERROR"); break;
	    }
	}
	return ans;
    }

    class MyListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
	    for (int i=0; i<16; i++){
		if (e.getSource()==buttons[i]){
		    switch (i){
			case 3: ans=calculate(ans,sign,num);
				label1.setText(label1.getText()+label.getText()+"+"); 
				label.setText(""+ans);  flag=1;
				sign='+'; num=""; break;

			case 7: ans=calculate(ans,sign,num);
				label1.setText(label1.getText()+label.getText()+"-"); 
				label.setText(""+ans);  flag=1;
				sign='-'; num=""; break;

			case 11: ans=calculate(ans,sign,num);
				 label1.setText(label1.getText()+label.getText()+"*");
				 label.setText(""+ans);  flag=1;
				 sign='*'; num=""; break;

			case 12: if (flag==1){
				     label.setText("0");
				     flag=0;
				 }else{
				     label.setText(label.getText()+"0");
				 }
				 //label1.setText(label1.getText()+"0");
				 num+="0"; break;

			case 13: label.setText(label.getText()+"."); 
				 //label1.setText(label1.getText()+"+"); 
				 num+="."; break;

			case 14: ans=calculate(ans,sign,num); label.setText(""+ans); 
				 label1.setText(""); num=""; sign='\0'; flag=1; break;//?aê?????
				 //ans=0;

			case 15: ans=calculate(ans,sign,num);
				 label1.setText(label1.getText()+label.getText()+"/"); 
				 label.setText(""+ans);  flag=1;
				 sign='/'; num=""; break;

			default: if (flag==1) {
				     label.setText(""+((2-i/4)*3+i%4+1));
				     flag=0;
				 }else{
				     label.setText(label.getText()+""+((2-i/4)*3+i%4+1));
				 }
				 //label1.setText(label1.getText()+""+((2-i/4)*3+i%4+1));
				 num+=""+((2-i/4)*3+i%4+1); break;
		    }
		}
	    }
	}
    }

    public static void main(String[] args){
	Calculator calc=new Calculator("?????÷");
    }
}