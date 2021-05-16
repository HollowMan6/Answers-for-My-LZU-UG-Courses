import java.awt.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;
public class MainFrame extends javax.swing.JFrame{
	public final int WIDTH = 16;
    	public final int HEIGHT = 9;
    	public final int ImageNum = 16;
    	public final File ImagePathName = new File("Image\\");  
    	public String ImageName;
    	public JLabel jLabel;
    	public LianLianKan ImageArray;
    	public int scrX;
    	public int scrY;
    	public int dstX;
    	public int dstY;
    	public int okTimes = 0;  
    	private int state = 0;
    	private int line = 0;
    	private JLabel scrLabel;
    	private JLabel dstLabel;
    	private Point start;
    	private Point first;    
    	private Point second;   
    	private Point finish;
    	private int sleepTime = 100;
    	private String ImagePath;
    	private final int maxTime = 500;
    	private ProgressBarThread progressBarThread;  
    	public MainFrame(){
        	initComponents();
        	initAgain();
    	}
    	public void initAgain(){
        	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        	int x = (screenSize.width - 1000)/2;
        	int y = (screenSize.height - 700)/2;
        	setLocation(x, y);
        	jProgressBar.setMaximum(maxTime);
        	ImagePath = ImagePathName.getAbsolutePath();
        	this.getContentPane().setBackground(Color.WHITE);
        	jPanel.setBackground(Color.WHITE);
        	jButtonStart.setEnabled(false);
        	startGame();
    	}
    	public void startGame(){
        	okTimes = 0;
        	ImageArray = new LianLianKan(WIDTH,HEIGHT,ImageNum);
        	int m=0;
        	int [] record = new int [HEIGHT*WIDTH/2];
        	for(int j=1; j <= HEIGHT;j++){
            		for(int i=1; i <= WIDTH; i++){
                		double k = java.lang.Math.random();
                		int r = (int) (k * ImageNum); 
                		jLabel = new JLabel();
                		if(j <= (HEIGHT/2) || (j == HEIGHT/2 +1 && i <= WIDTH/2)){
                    			ImageName = ImagePath + '\\' + r +".jpg";
                    			record[(j-1) * WIDTH + (i-1)] = r;
                		}else{
                    			r = record[m];
                    			m = m + 2;
                    			if(m >= WIDTH*HEIGHT/2 )
                        		m = 1;
                    			ImageName = ImagePath + '\\' + r +".jpg";
                		}
                		jLabel.setIcon(new ImageIcon(ImageName));
                		jPanel.add(jLabel);
                		ImageArray.setImageArray(i,j,r);
                		jLabel.addMouseListener(new java.awt.event.MouseAdapter(){
                    			public void mousePressed(java.awt.event.MouseEvent evt){
                        			jLabelMousePressed(evt);
                    			}
                		});
            		}
        	}
        	progressBarThread = new ProgressBarThread();
        	progressBarThread.start();
    	}
    	public void reStartGame(){
        	okTimes = 0;
        	int [] record = new int [HEIGHT*WIDTH/2];
        	int m = 0;
        	for(int j=1; j <= HEIGHT;j++){
            		for(int i=1; i <= WIDTH; i++){
                		double k = java.lang.Math.random();
                		int val = (int) (k * ImageNum); 
                		if(j <= (HEIGHT/2) || (j == HEIGHT/2 +1 && i <= WIDTH/2)){
                    			ImageName = ImagePath + '\\' + val +".jpg";
                    			record[(j-1) * WIDTH + (i-1)] = val;
                		}else{
                    			val = record[m];
                    			m = m + 2;
                    			if(m >= WIDTH*HEIGHT/2 )
                        			m = 1;
                    				ImageName = ImagePath + '\\' + val +".jpg";
                		}
                		((JLabel)jPanel.getComponent((j-1)*WIDTH + i-1)).setIcon(new ImageIcon(ImageName));
                		ImageArray.setImageArray(i,j,val);
            		}
        	}
        	progressBarThread = new ProgressBarThread();
        	progressBarThread.start();
        	jButtonArrange.setEnabled(true);
    	}
    	private void jLabelMousePressed(java.awt.event.MouseEvent evt){        
        	int x = evt.getXOnScreen();
        	int y = evt.getYOnScreen();
        	Point p = jPanel.getLocationOnScreen();
        	x = x - p.x;
        	y = y - p.y;      
        	x = x/55 + 1;
        	y = (y + 2 +1)/54 + 1;
        	if(!ImageArray.isNullImage(x,y)){
            		if(state == 0){
                		line = 0;
                		state = 1;
                		scrX = x;
                		scrY = y;
                		scrLabel = (JLabel)evt.getSource();
                		scrLabel.setBorder(new LineBorder(Color.RED));
                		start = getCenterPoint(scrX,scrY);
             		}else if(state == 1){
                		state = 0;
                		dstX = x;
                		dstY = y;
                		dstLabel = (JLabel)evt.getSource();
                		dstLabel.setBorder(new LineBorder(Color.RED));
                		finish = getCenterPoint(dstX,dstY);
            		}
            		if(state == 0){
                		if(ImageArray.ifImageSame(scrX,scrY,dstX,dstY)){                   
                    			if(ImageArray.ifCanOneLineConnect(scrX,scrY,dstX,dstY)){
                        			scrLabel.setBorder(null);
                        			dstLabel.setBorder(null);
                        			progressBarThread.addTime(maxTime/100);
                        			line = 1;
                        			okTimes++;
                        			this.paint(this.getGraphics());
                        			if(okTimes == WIDTH * HEIGHT/2){
                            				showPassDialog();
                            
                        			}
                    			}else if(ImageArray.ifCanTwoLinesConnect(scrX,scrY,dstX,dstY)){
                        			scrLabel.setBorder(null);
                        			dstLabel.setBorder(null);
                        			progressBarThread.addTime(maxTime/100);
                        			line = 2;
                        			first = getCenterPoint(ImageArray.getFirstX(),ImageArray.getFirstY());
                        			okTimes++;
                        			this.paint(this.getGraphics());
                        			if(okTimes == WIDTH * HEIGHT/2){
                            				showPassDialog();
                        			}
                    			}else if(ImageArray.ifCanThreeLinesConnect(scrX,scrY,dstX,dstY)){
                        			scrLabel.setBorder(null);
                        			dstLabel.setBorder(null);
                        			progressBarThread.addTime(maxTime/100);
                        			line = 3;
                        			first = getCenterPoint(ImageArray.getFirstX(),ImageArray.getFirstY());
                        			second = getCenterPoint(ImageArray.getSecondX(),ImageArray.getSecondY());                        
                        			okTimes++;
                        			this.paint(this.getGraphics());
                        			if(okTimes == WIDTH * HEIGHT/2){
                            				showPassDialog();
                        			}
                    			}else{
                        			state = 1;
                        			scrX = dstX;
                        			scrY = dstY;
                        			scrLabel.setBorder(null);
                        			scrLabel = dstLabel;
                        			scrLabel.setBorder(new LineBorder(Color.red));
                        			start = finish;
                    			}
                		}else{                  
                    			state = 1;
                    			scrX = dstX;
                    			scrY = dstY;
                    			scrLabel.setBorder(null);
                    			scrLabel = dstLabel;
                    			scrLabel.setBorder(new LineBorder(Color.red));
                    			start = finish;                 
                		}
            		}
        	}
             
    	}
    	public Point getCenterPoint(int x,int y){
        	Point centerPoint = new Point();
        	centerPoint.x = 25 +(x-1)*55;
        	centerPoint.y = 50 + (y-1)*54;
        	centerPoint.x = centerPoint.x + jPanel.getLocation().x;
        	centerPoint.y = centerPoint.y + jPanel.getLocation().y;
        	return centerPoint;
    	}
    	public void paint(Graphics g){
       		super.paint(g);
       		if(line == 1){
        		g.setColor(Color.red);
            		g.drawLine(start.x,start.y,finish.x,finish.y);
            		try{
                		Thread.sleep(sleepTime);
            		}catch(Exception e){   
                		e.printStackTrace();
           		}
            		scrLabel.setIcon(new ImageIcon(ImagePath + '\\'+ "NULL.jpg"));
            		dstLabel.setIcon(new ImageIcon(ImagePath + '\\'+ "NULL.jpg"));
            		ImageArray.setNULL(scrX,scrY);
            		ImageArray.setNULL(dstX,dstY);
            		line = 0;            
            		super.paint(g);
       		}else if(line == 2){
            		g.setColor(Color.red);
            		g.drawLine(start.x,start.y,first.x,first.y);
            		g.drawLine(first.x,first.y,finish.x,finish.y);
            		try{
                		Thread.sleep(sleepTime);
            		}catch(Exception e){}
            		scrLabel.setIcon(new ImageIcon(ImagePath +'\\'+ "NULL.jpg"));
            		dstLabel.setIcon(new ImageIcon(ImagePath +'\\'+ "NULL.jpg"));
            		ImageArray.setNULL(scrX,scrY);
            		ImageArray.setNULL(dstX,dstY);
            		line = 0;            
            		super.paint(g);
       		}else if(line == 3){
            		g.setColor(Color.red);
            		g.drawLine(start.x,start.y,second.x,second.y);
            		g.drawLine(second.x,second.y,first.x,first.y);
            		g.drawLine(first.x,first.y,finish.x,finish.y);
            		try{
                		Thread.sleep(sleepTime);
            		}catch(Exception e){}
            			scrLabel.setIcon(new ImageIcon(ImagePath +'\\'+ "NULL.jpg"));
            			dstLabel.setIcon(new ImageIcon(ImagePath +'\\'+ "NULL.jpg"));
            			ImageArray.setNULL(scrX,scrY);
            			ImageArray.setNULL(dstX,dstY);
            			line = 0;            
            			super.paint(g);
       		}     
   	}
    	public void showPassDialog(){
            	progressBarThread.end();
            	JOptionPane.showMessageDialog(null, "挑战成功！");
            	jButtonStart.setEnabled(true);
            	jButtonArrange.setEnabled(false);
            	jProgressBar.setValue(maxTime);     
    	} 
class ProgressBarThread extends Thread{
	private int curTime = maxTime;       
        boolean go = true;
        public void minusTime(int n){
            	curTime = curTime - n;
        }
        public void addTime(int n){
            	curTime = curTime + n;
            	if(curTime > maxTime)
                	curTime = maxTime;
        }
        public void end(){
            	go = false;
        }
        public void run(){
            	while(go){
                	if(curTime >= 0){
                    		try {
                        		jProgressBar.setValue(curTime--);
                        		sleep(200);
                    		}catch (InterruptedException ex) {
                        		Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    		}
                		}else{
                    			jProgressBar.setValue(0);
                    			JOptionPane.showMessageDialog(null, "时间耗尽！挑战失败！");
                    			go = false;
                    			showBlankDesktop();                  
                    			jButtonStart.setEnabled(true);
                    			jProgressBar.setValue(maxTime);
                    			jButtonArrange.setEnabled(false);
                		}
            		}
        	}
    	}
    	void showBlankDesktop(){            
        	for(int j=1; j <= HEIGHT;j++){
            		for(int i=1; i <= WIDTH; i++){                
                		ImageName = ImagePath + '\\' + "NULL.jpg";
                		((JLabel)jPanel.getComponent((j-1)*WIDTH + i-1)).setIcon(new ImageIcon(ImageName));
                		((JLabel)jPanel.getComponent((j-1)*WIDTH + i-1)).setBorder(null);
            		}
        	}
    	}
    	private void initComponents(){
        	jPanel = new javax.swing.JPanel();
        	jProgressBar = new javax.swing.JProgressBar();
        	jSeparator1 = new javax.swing.JSeparator();
        	jLabel1 = new javax.swing.JLabel();
        	jButtonArrange = new javax.swing.JButton();
        	jButtonStart = new javax.swing.JButton();
        	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        	setTitle("连连看");
        	setBounds(new java.awt.Rectangle(20, 40, 1000, 700));
        	setResizable(false);
        	jPanel.setLayout(new java.awt.GridLayout(9, 16, 5, 4));
        	jProgressBar.setMaximum(500);
        	jProgressBar.setPreferredSize(new java.awt.Dimension(146, 10));
        	jLabel1.setText("时间：");
        	jButtonArrange.setText("重新排列");
        	jButtonArrange.addActionListener(new java.awt.event.ActionListener(){
            		public void actionPerformed(java.awt.event.ActionEvent evt){
                		jButtonArrangeActionPerformed(evt);
            		}
        	});
        	jButtonStart.setText("开始游戏");
        	jButtonStart.addActionListener(new java.awt.event.ActionListener(){
            		public void actionPerformed(java.awt.event.ActionEvent evt){
                		jButtonStartActionPerformed(evt);
            		}
        	});
        	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        	getContentPane().setLayout(layout);
        		layout.setHorizontalGroup(layout.createParallelGroup
						(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    				.addGroup(layout.createSequentialGroup()
                        			.addGap(47, 47, 47)
                        			.addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE))
                    				.addGroup(layout.createSequentialGroup()
                        			.addGap(48, 48, 48)
                        			.addComponent(jLabel1)
                        			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        			.addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                        			.addGap(26, 26, 26)
                        			.addComponent(jButtonStart)
                        			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        			.addComponent(jButtonArrange))
                    				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        			.addContainerGap()
                        			.addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 955, Short.MAX_VALUE)))
                				.addContainerGap())
        	);
        	layout.setVerticalGroup(
            		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            					.addGroup(layout.createSequentialGroup()
                				.addGap(18, 18, 18)
               	 				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    				.addComponent(jButtonStart)
                    				.addComponent(jButtonArrange)
                    				.addComponent(jLabel1)
                    				.addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                				.addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                				.addGap(45, 45, 45)
                				.addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                				.addContainerGap(60, Short.MAX_VALUE))
        	);
        	pack();
    	}
    	private void jButtonStartActionPerformed(java.awt.event.ActionEvent evt){          
        	reStartGame();
        	jButtonStart.setEnabled(false);
	}
    	private void jButtonArrangeActionPerformed(java.awt.event.ActionEvent evt){
        	ImageArray.reArrange();
        	for(int j=1; j <= HEIGHT;j++){
            		for(int i=1; i <= WIDTH; i++){                
                		int val = ImageArray.getImageArray(i, j);                
                		if( val == -1)
                    			ImageName = ImagePath + '\\' + "NULL.jpg";
                		else 
                    			ImageName = ImagePath + '\\' + val + ".jpg";
                		((JLabel)jPanel.getComponent((j-1)*WIDTH + i-1)).setIcon(new ImageIcon(ImageName));
            		}
        	}
        	progressBarThread.minusTime(maxTime/8);
    	}
    	public static void main(String args[]){
        	java.awt.EventQueue.invokeLater(new Runnable(){
            		public void run(){
                		new MainFrame().setVisible(true);
            		}
        	});   
    	}
    	private javax.swing.JButton jButtonArrange;
    	private javax.swing.JButton jButtonStart;
    	private javax.swing.JLabel jLabel1;
    	private javax.swing.JPanel jPanel;
    	private javax.swing.JProgressBar jProgressBar;
    	private javax.swing.JSeparator jSeparator1;  
}