import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class MyExcel {
	boolean flag=false;   //这个flag用于表征现在程序运行时你看到的这个表格有没有被保存在磁盘上过，如果没有，则为false，显然这里应该初始化为false
	String path=null;// 这个path用来存放现在程序运行时你看到的这个表格被存放的路径，如果flag是false（这个表格还没有保存在磁盘上过），那么显然path应该初始化为null
	JFrame frame;
	Container con;
	JTabbedPane tabbedpane = new JTabbedPane();
	int number = 1;       //这个number用来计数页表的数量
	int row,col;
	int sum=0,avg=0,count=0;
	JTable table;
	JMenuBar mb;
	JMenu fileMenu, filenew, editMenu, color,formatMenu, helpMenu, aboutMenu, calculateMenu; // 这是所有的菜单组件
	JMenuItem newsheet, newfile, open, close, save, saveas, quit; // "文件"菜单下的菜单项
	JMenuItem copy, cut, paste, search, forecolor, backcolor,font; // "编辑"菜单下的菜单项
	JMenuItem rename; // "格式"菜单下的菜单项
	JMenuItem getsum,getavg;
	JMenuItem designer, annotation; // "帮助-关于"菜单下的菜单项
	FileDialog filedialog; // 用于选择文件的对话框
	JDialog dialog;// 用于显示提示信息的对话框
	Cell[][] cell = new Cell[40][20];
	Cell temp=null;//这个temp是用来临时存放复制或者剪切下来的单元格里头的内容的,相当于剪贴板
	//String[] ColNames=new String[40];
	MyActionListener myActionListener = new MyActionListener();
	Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	int screenWidth = (int) screensize.getWidth();
	int screenHeight = (int) screensize.getHeight(); // 这三句是为了得到屏幕的尺寸以使得初始弹出的窗口都位于屏幕中央
	public MyExcel() {
		/*for(int i=0;i<20;i++){
			ColNames[i]=String.valueOf(i);
		}*/
		frame = new JFrame("My Excel");
		con = frame.getContentPane();
		table = new JTable(40,20);		
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
		tabbedpane.add("sheet" + number, table);
		number++; // 这两句用于添加表页并给表页命名
		JScrollPane scrollpane = new JScrollPane(tabbedpane);
		con.add(scrollpane);
		mb = new JMenuBar();
		frame.setJMenuBar(mb); // 在面板上添加菜单栏
		/****************************************************/
		fileMenu = new JMenu("文件");
		filenew = new JMenu("新建");
		newsheet = new JMenuItem("页");
		newfile = new JMenuItem("文件");
		open = new JMenuItem("打开(o)");
		open.setMnemonic('o');
		close = new JMenuItem("关闭");
		save = new JMenuItem("保存(s)");
		save.setMnemonic('s');
		saveas = new JMenuItem("另存为");
		quit = new JMenuItem("退出");
		save.addActionListener(myActionListener);
		saveas.addActionListener(myActionListener);
		newsheet.addActionListener(myActionListener);
		newfile.addActionListener(myActionListener);
		open.addActionListener(myActionListener);
		close.addActionListener(myActionListener);
		quit.addActionListener(myActionListener);
		fileMenu.add(filenew);
		filenew.add(newsheet);
		filenew.add(newfile);
		fileMenu.add(open);
		fileMenu.add(close);
		fileMenu.add(save);
		fileMenu.add(saveas);
		fileMenu.add(quit);
		fileMenu.addSeparator(); // 这一部分用来初始化"文件"菜单里的所有菜单项及子菜单
		/****************************************************/
		editMenu = new JMenu("编辑");
		color=new JMenu("颜色");
		copy = new JMenuItem("复制(c)");
		copy.setMnemonic('c');
		cut = new JMenuItem("剪切(x)");
		cut.setMnemonic('x');
		paste = new JMenuItem("粘贴(v)");
		paste.setMnemonic('v');
		search = new JMenuItem("查找");
		forecolor = new JMenuItem("前景色");
		backcolor=new JMenuItem("背景色");
		font = new JMenuItem("字体");
		copy.addActionListener(myActionListener);
		cut.addActionListener(myActionListener);
		paste.addActionListener(myActionListener);
		search.addActionListener(myActionListener);
		forecolor.addActionListener(myActionListener);
		backcolor.addActionListener(myActionListener);
		font.addActionListener(myActionListener);
		editMenu.add(copy);
		editMenu.add(cut);
		editMenu.add(paste);
		editMenu.add(search);
		editMenu.add(color);
		color.add(forecolor);
		color.add(backcolor);
		editMenu.add(font);// 这一部分用来初始化"编辑"菜单里的所有菜单项
		/****************************************************/
		formatMenu = new JMenu("格式"); // 这一部分用来初始化"格式"菜单里的所有菜单项
		rename = new JMenuItem("表页重命名");
		rename.addActionListener(myActionListener);
		formatMenu.add(rename);
		/****************************************************/
		calculateMenu=new JMenu("计算");
		getsum=new JMenuItem("求和");
		getavg=new JMenuItem("求平均");
		getsum.addActionListener(myActionListener);
		getavg.addActionListener(myActionListener);
		calculateMenu.add(getsum);
		calculateMenu.add(getavg);
		/****************************************************/
		helpMenu = new JMenu("帮助");
		aboutMenu = new JMenu("关于");
		designer = new JMenuItem("作者");
		annotation = new JMenuItem("本程序的一些说明");
		designer.addActionListener(myActionListener);
		annotation.addActionListener(myActionListener);
		aboutMenu.add(designer);
		aboutMenu.add(annotation);
		helpMenu.add(aboutMenu);
		mb.add(fileMenu);
		mb.add(editMenu);
		mb.add(formatMenu);
		mb.add(calculateMenu);
		mb.add(helpMenu); // 这一部分用来初始化"帮助"菜单里的所有菜单项及子菜单
		/****************************************************/
		for (int i = 0; i < 40; i++)
			for (int j = 0; j < 20; j++){
				cell[i][j]=new Cell();
			}
		
		frame.setBounds(screenWidth / 2 - 1028 / 2, screenHeight / 2 - 526 / 2, 1028, 526);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void NewSheet() { // 新建一个表页
		JTable newtable = new JTable(100, 100);
		tabbedpane.add("sheet" + number, newtable);
		number++;
	}

	public void NewFile() { // 新建一个文件
		filedialog = new FileDialog(frame, "新建文件", FileDialog.SAVE);
		filedialog.setBounds(screenWidth / 2 - 200 / 2, screenHeight / 2 - 100 / 2, 200, 100);
		filedialog.setVisible(true);
		try {
			File f = new File(filedialog.getDirectory(), filedialog.getFile());
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream objectout = new ObjectOutputStream(fos);
			objectout.writeObject(cell);
			objectout.flush();
			objectout.close();
			fos.close();
		} catch (Exception ecp) {
			System.out.println("文件新建错误！");
			ecp.printStackTrace();
		}
		
	}

	public void Save() { 
		for (int i = 0; i < 40; i++)
			for (int j = 0; j < 20; j++) {
				{
					cell[i][j].setS((String) table.getValueAt(i, j));
				}
			}
		if (flag) { // 表格之前被保存过，只许再重写一次就好了
			try {
				File f = new File(path);
				FileOutputStream fos = new FileOutputStream(f.toString());
				ObjectOutputStream objectout = new ObjectOutputStream(fos);
				objectout.writeObject(cell);
				objectout.flush();
				objectout.close();
				fos.close();
			} catch (Exception ecp) {
				System.out.println("保存错误！");
			}
		} else {
			filedialog = new FileDialog(frame, "保存", FileDialog.SAVE);
			filedialog.setBounds(screenWidth / 2 - 200 / 2, screenHeight / 2 - 100 / 2, 200, 100);
			filedialog.setVisible(true);
			try {
				File f = new File(filedialog.getDirectory(), filedialog.getFile());
				FileOutputStream fos = new FileOutputStream(f.toString());
				ObjectOutputStream objectout = new ObjectOutputStream(fos);
				objectout.writeObject(cell);
				objectout.flush();
				objectout.close();
				fos.close();
			} catch (Exception ecp) {
				System.out.println("首次保存错误！");
			}
		}
		dialog = new JDialog(frame, "保存信息", false);
		dialog.getContentPane().add(new JLabel("保存成功！", JLabel.CENTER));
		dialog.setBounds(screenWidth / 2 - 200 / 2, screenHeight / 2 - 100 / 2, 200, 100);
		dialog.setVisible(true);
	}

	public void SaveAs() { // 另存为
		for (int i = 0; i < 40; i++)
			for (int j = 0; j < 20; j++) {
				cell[i][j].setS((String) table.getValueAt(i, j));

			}
		filedialog = new FileDialog(frame, "文件另存为", FileDialog.SAVE);
		filedialog.setBounds(screenWidth / 2 - 200 / 2, screenHeight / 2 - 100 / 2, 200, 100);
		filedialog.setVisible(true);
		try {
			File f = new File(filedialog.getDirectory(), filedialog.getFile());
			FileOutputStream fos = new FileOutputStream(f.toString());
			ObjectOutputStream objectout = new ObjectOutputStream(fos);
			objectout.writeObject(cell);
			objectout.flush();
			objectout.close();
			fos.close();
		} catch (Exception ecp) {
			System.out.println("另存为错误！");
		}
	}

	public void Open() { // 打开
		filedialog = new FileDialog(frame, "打开文件", FileDialog.LOAD);
		FilenameFilter ff = new FilenameFilter() {     //就是这个过滤器！他不起作用。。。
			public boolean accept(File dir, String name) {
				if (name.endsWith("yqc")) {
					return true;
				}
				return false;
			}
		};
		filedialog.setFilenameFilter(ff);
		filedialog.setBounds(screenWidth / 2 - 200 / 2, screenHeight / 2 - 100 / 2, 200, 100);
		filedialog.setVisible(true);
		try {
			File f = new File(filedialog.getDirectory(), filedialog.getFile());
			path=filedialog.getDirectory()+filedialog.getFile();    //文件选中之后把每一次选中文件的路径给记录下来
			flag=true;//只要程序运行起来，打开一次，flag就都是true了
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream objectin = new ObjectInputStream(fis);
			for (int i = 0; i < 40; i++)
				for (int j = 0; j < 20; j++) {
					table.setValueAt(null, i, j);
				}
			cell = (Cell[][]) objectin.readObject();
			table.setFont(cell[0][0].getFont());
			table.setDefaultRenderer(Object.class, new MyRenderer(cell)); // 在这儿设置了渲染器
			for (int i = 0; i < 40; i++)
				for (int j = 0; j < 20; j++) {
					table.setValueAt(cell[i][j].getS(), i, j);
				}
			fis.close();
		} catch (EOFException e) {
			System.out.println("必须打开一个.yqc文件！");
		} catch (Exception ecp) {
			System.out.println("打开错误！");
			ecp.printStackTrace();
		}
	}
	public void Close() { // 关闭选中的表页
		tabbedpane.remove(tabbedpane.getSelectedComponent());
	}
	public void Search(){
		dialog = new JDialog(frame, "查找", false);
		dialog.setLayout(new FlowLayout());
		dialog.getContentPane().add(new JLabel("目标："));
		JTextField tf = new JTextField(10);
		dialog.getContentPane().add(tf);
		JButton yes = new JButton("确定");
		JButton cancel = new JButton("取消");
		dialog.add(yes);
		dialog.add(cancel);
		dialog.setBounds(screenWidth / 2 - 200 / 2, screenHeight / 2 - 100 / 2, 200, 100);
		dialog.setVisible(true);
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String goal=tf.getText();
				JTextArea showresult=new JTextArea();
				showresult.setLineWrap(true);
				int count=0;
				dialog.setVisible(false);
				dialog = new JDialog(frame, "查找结果", false);
				StringBuffer result=new StringBuffer();
				for(int i=0;i<40;i++)
					for(int j=0;j<20;j++){
						if((cell[i][j].getS()!=null)&&cell[i][j].getS().equals(goal)){
							if(count%5==0){
								result.append("\n");
							}	
							result.append(" ("+i+","+j+")");	
							count++;
						}
					}
				showresult.setEditable(false);
				if(count==0){
					showresult.setText("\n\n     抱歉！未找到查询结果！");
				}	
				else
					showresult.setText(result.toString());
				dialog.getContentPane().add(showresult);
				dialog.setBounds(screenWidth / 2 - 200 / 2, screenHeight / 2 - 200 / 2, 200, 200);
				dialog.setVisible(true);
			}
		});
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
		});
	}
	public void Copy(){
		temp=new Cell();
		temp=cell[table.getSelectedRow()][table.getSelectedColumn()];
	}
	public void Cut(){
		temp=new Cell();
		temp=cell[table.getSelectedRow()][table.getSelectedColumn()];
		cell[table.getSelectedRow()][table.getSelectedColumn()]=new Cell();//被剪切的单元格要置空了
		table.setValueAt("", table.getSelectedRow(), table.getSelectedColumn());
	}
	public void Paste(){
		if(temp==null){
			dialog = new JDialog(frame, "提示信息", false);
			dialog.getContentPane().add(new JLabel("剪贴板内容为空！", JLabel.CENTER));
			dialog.setBounds(screenWidth / 2 - 200 / 2, screenHeight / 2 - 100 / 2, 200, 100);
			dialog.setVisible(true);
		}
		else{
			cell[table.getSelectedRow()][table.getSelectedColumn()]=temp;
			table.setDefaultRenderer(Object.class, new MyRenderer(cell));
			table.setValueAt(temp.getS(), table.getSelectedRow(), table.getSelectedColumn());
			
		}
	}
	public void Rename() { // 对选中表页重命名
		dialog = new JDialog(frame, "重命名", false);
		dialog.setLayout(new FlowLayout());
		dialog.getContentPane().add(new JLabel("名称："));
		JTextField name = new JTextField(10);
		dialog.getContentPane().add(name);
		JButton yes = new JButton("确定");
		JButton cancel = new JButton("取消");
		dialog.add(yes);
		dialog.add(cancel);
		dialog.setBounds(screenWidth / 2 - 200 / 2, screenHeight / 2 - 100 / 2, 200, 100);
		dialog.setVisible(true);
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedpane.setTitleAt(tabbedpane.getSelectedIndex(), name.getText());
				dialog.setVisible(false);
			}
		});
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
		});
	}

	public void ForeColorChange() {   //用来改变单元格背景色
		JColorChooser cc = new JColorChooser();
		JButton yes=new JButton("确定");
		dialog = new JDialog(frame, "保存信息", false);
		dialog.setLayout(new FlowLayout());
		dialog.getContentPane().add(cc);
		dialog.getContentPane().add(yes);    //上面几句只是为了布局那个弹出来的颜色选择框而已
		yes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				table.setDefaultRenderer(Object.class,new MyRenderer(cell));    
				cell[table.getSelectedRow()][table.getSelectedColumn()].setFColor(cc.getColor());//这句就是为了把相应选中的单元格所对应的那个Cell实例里头的颜色给设置了
				dialog.setVisible(false);
			}
		});
		dialog.setBounds(screenWidth / 2 - 700 / 2, screenHeight / 2 - 400 / 2, 700, 400);
		dialog.setVisible(true);
		
	}
	public void BackColorChange() {   //用来改变单元格背景色
		JColorChooser cc = new JColorChooser();
		JButton yes=new JButton("确定");
		dialog = new JDialog(frame, "保存信息", false);
		dialog.setLayout(new FlowLayout());
		dialog.getContentPane().add(cc);
		dialog.getContentPane().add(yes);    //上面几句只是为了布局那个弹出来的颜色选择框而已
		yes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				table.setDefaultRenderer(Object.class,new MyRenderer(cell));    
				cell[table.getSelectedRow()][table.getSelectedColumn()].setBColor(cc.getColor());//这句就是为了把相应选中的单元格所对应的那个Cell实例里头的颜色给设置了
				dialog.setVisible(false);
			}
		});
		dialog.setBounds(screenWidth / 2 - 700 / 2, screenHeight / 2 - 400 / 2, 700, 400);
		dialog.setVisible(true);
		
	}

	public void FontChange() {
		FontChooser fontchooser = new FontChooser();
		dialog = new JDialog(frame, "字体选择", false);
		dialog.getContentPane().add(fontchooser);
		JButton yes = new JButton("确定");
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Font myFont = new Font(fontchooser.getZT(), fontchooser.getZX(), fontchooser.getZH());// 用选择得到的字体字形和字号得到了一个Font
				System.out.println("Font:" + myFont.toString());
				dialog.setVisible(false);
				for (int i = 0; i < 40; i++)
					for (int j = 0; j < 20; j++)
						cell[i][j].setFont(myFont);
				table.setFont(cell[0][0].getFont());
				System.out.println(cell[0][0].getFont());
				// table.repaint();
			}

		});
		yes.setBounds(250, 300, 80, 20);
		fontchooser.add(yes);
		dialog.setResizable(false);
		dialog.setBounds(screenWidth / 2 - 375 / 2, screenHeight / 2 - 400 / 2, 375, 400);
		dialog.setVisible(true);
	}
	public void Sum(){
		dialog=new JDialog(frame,"求和",false);
		dialog.setLayout(new FlowLayout());
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JTextField t1=new JTextField(5);
		JTextField t2=new JTextField(5);
		p1.add(new JLabel("行号"));
		p1.add(t1);
		p1.add(new JLabel("列号"));
		p1.add(t2);
		dialog.getContentPane().add(p1);
		JButton next=new JButton("下一个");
		JButton cancel=new JButton("取消");
		JButton done=new JButton("完成");
		p2.add(next);
		p2.add(cancel);
		p2.add(done);
		dialog.getContentPane().add(p2);
		dialog.setBounds(screenWidth / 2 - 250 / 2, screenHeight / 2 - 130 / 2, 250, 130);
		dialog.setVisible(true);
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				row=Integer.parseInt(t1.getText());
				col=Integer.parseInt(t2.getText());
				if(cell[row][col].getS()!=null){
					sum=sum+Integer.parseInt(cell[row][col].getS());
				}
				t1.setText("");
				t2.setText("");
				System.out.println("sum="+sum);
			}
		});
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dialog.setVisible(false);
			}
		});
		done.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				row=Integer.parseInt(t1.getText());
				col=Integer.parseInt(t2.getText());
				if(cell[row][col].getS()!=null){
					sum=sum+Integer.parseInt(cell[row][col].getS());
				}
				cell[table.getSelectedRow()][table.getSelectedColumn()].setS(String.valueOf(sum));
				table.setValueAt(cell[table.getSelectedRow()][table.getSelectedColumn()].getS(), table.getSelectedRow(), table.getSelectedColumn());
				sum=0;
				dialog.setVisible(false);
			}
		});
	}
	public void Avg(){
		dialog=new JDialog(frame,"求和",false);
		dialog.setLayout(new FlowLayout());
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JTextField t1=new JTextField(5);
		JTextField t2=new JTextField(5);
		p1.add(new JLabel("行号"));
		p1.add(t1);
		p1.add(new JLabel("列号"));
		p1.add(t2);
		dialog.getContentPane().add(p1);
		JButton next=new JButton("下一个");
		JButton cancel=new JButton("取消");
		JButton done=new JButton("完成");
		p2.add(next);
		p2.add(cancel);
		p2.add(done);
		dialog.getContentPane().add(p2);
		dialog.setBounds(screenWidth / 2 - 250 / 2, screenHeight / 2 - 130 / 2, 250, 130);
		dialog.setVisible(true);
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				row=Integer.parseInt(t1.getText());
				col=Integer.parseInt(t2.getText());
				if(cell[row][col].getS()!=null){
					sum=sum+Integer.parseInt(cell[row][col].getS());
					count++;
				}
				avg=sum/count;
				t1.setText("");
				t2.setText("");
				//System.out.println("sum="+sum+" avg="+avg);
			}
		});
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dialog.setVisible(false);
			}
		});
		done.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				row=Integer.parseInt(t1.getText());
				col=Integer.parseInt(t2.getText());
				if(cell[row][col]!=null){
					sum=sum+Integer.parseInt(cell[row][col].getS());
					count++;
				}
				avg=sum/count;
				cell[table.getSelectedRow()][table.getSelectedColumn()].setS(String.valueOf(avg));
				table.setValueAt(cell[table.getSelectedRow()][table.getSelectedColumn()].getS(), table.getSelectedRow(), table.getSelectedColumn());
				sum=0;
				dialog.setVisible(false);
			}
		});
	}
	public void getDesigner() {
		dialog = new JDialog(frame, "设计者信息", false);
		dialog.setLayout(new GridLayout(3, 1));
		dialog.getContentPane().add(new JLabel("设计者：于清晨"));
		dialog.getContentPane().add(new JLabel("学号：320130938450"));
		dialog.getContentPane().add(new JLabel("班级：13级信息安全"));
		dialog.setBounds(screenWidth / 2 - 200 / 2, screenHeight / 2 - 100 / 2, 200, 100);
		dialog.setVisible(true);
	}
	public void getAnnotation(){
		dialog = new JDialog(frame, "一些说明", false);
		dialog.getContentPane().add(new JLabel("详见文件夹中附录的记事本文档~"));
		dialog.setBounds(screenWidth / 2 - 220 / 2, screenHeight / 2 - 100 / 2, 220, 100);
		dialog.setVisible(true);
	}

	private class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == newsheet) {
				NewSheet();
			}
			if (ae.getSource() == newfile) {
				NewFile();
			}
			if (ae.getSource() == save) {
				Save();
			}
			if (ae.getSource() == saveas) {
				SaveAs();
			}
			if (ae.getSource() == open) {
				Open();
			}
			if (ae.getSource() == close) {
				Close();
			}
			if (ae.getSource() == quit) {
				System.exit(0);
			}
			if(ae.getSource()==copy){
				Copy();
			}
			if(ae.getSource()==cut){
				Cut();
			}
			if(ae.getSource()==paste){
				Paste();
			}
			if (ae.getSource() == rename) {
				Rename();
			}
			if(ae.getSource()==search){
				Search();
			}
			if (ae.getSource() == forecolor) {
				ForeColorChange();
			}
			if (ae.getSource() == backcolor) {
				BackColorChange();
			}
			if(ae.getSource()==font){
				FontChange();
			}
			if(ae.getSource()==getsum){
				Sum();
			}
			if(ae.getSource()==getavg){
				Avg();
			}
			if (ae.getSource() == designer) {
				getDesigner();
			}
			if(ae.getSource()==annotation){
				getAnnotation();
			}
		}
	}

}
class Cell implements Serializable{   //我们老师说要分层抽象 所以这个封装每一个单元格的类里头的内容很单纯，就一个字符串内容，一个颜色，和一个字体而已，剩下的就是get和set方法了
	private String s;
	private Color fcolor,bcolor;
	private Font font;
	public Cell(){
		s=null;
		fcolor=null;
		font=null;
	}
	public void setS(String s){
		this.s=s;
	}
	public void setFColor(Color fcolor){
		this.fcolor=fcolor;
	}
	public void setBColor(Color bcolor){
		this.bcolor=bcolor;
	}
	public void setFont(Font font){
		this.font=font;
	}
	public String getS(){
		return s;
	}
	public Color getFColor(){
		return fcolor;
	}
	public Color getBColor(){
		return bcolor;
	}
	public Font getFont(){
		return font;
	}
	public String toString(){
		return s+" ForeColor:"+fcolor+" BackColor:"+bcolor+" Font:"+font;
	}
}
class MyRenderer implements TableCellRenderer {//渲染器
	Cell[][] cell;
	public MyRenderer(Cell[][] cell){
		this.cell=cell; //通过构造方法传过来一个Cell二维对象数组，Cell里头不是封装color了嘛，就依照那个color来渲染每个单元格就好了
	}
	public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) {
		Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,column);
		Color foreground = cell[row][column].getFColor();
		Color background = cell[row][column].getBColor();
		renderer.setForeground(foreground);
        renderer.setBackground(background);
		return renderer;
	}
}

class FontChooser extends JPanel {
	private static ClassLoader loader; // 类的加载器
	private JList style; // 选择字体
	private JList font; // 选择字型
	private JList size; // 选择字号
	private String zt = "Fixedsys"; // 用于动态保存字体的字符串
	private int zx = Font.PLAIN; // 用于动态保存字形
	private int zh = 10; // 用于动态保存字号
	//private Font myFont = new Font(zt,zx,zh); // 经过一次选择（按下确定按钮之后）生成的Font，相当于此次对话框得到的结果
	private JScrollPane spzt; // 字体
	private JScrollPane spzx; // 字型
	private JScrollPane spzh; // 字号
	private JLabel tfzt; // 字体
	private JLabel tfzx; // 字型
	private JLabel tfzh; // 字号
	private JTextField tfsr; // 内容
	private JLabel Lzt; // 字体
	private JLabel Lzx; // 字型
	private JLabel Lzh; // 字号
	private JLabel Lcs; // 显示文字内容
	private JPanel Psl; // 展示区
	private JButton Bsr; // 输入
	//private JButton yes = new JButton("确定"); // 确定按钮
	Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	int screenWidth = (int) screensize.getWidth();
	int screenHeight = (int) screensize.getHeight();
	public FontChooser() {
		setLayout(null);
		setPreferredSize(new Dimension(370, 375));
		initComponent();	
	}

	private void initComponent() {
		// 初始化资源
		loader = this.getClass().getClassLoader();
		// 字体选择
		/*************************************************************************************************/
		Lzt = new JLabel("字体(F):");
		Lzt.setBounds(20, 15, 80, 17);
		this.add(Lzt);
		tfzt = new JLabel("Fixedsys");
		tfzt.setOpaque(true);
		tfzt.setBackground(Color.WHITE);
		tfzt.setBounds(20, 32, 150, 18);
		this.add(tfzt);
		style = new JList(getFontStyle());
		style.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				tfzt.setText(style.getSelectedValue().toString());
				zt = style.getSelectedValue().toString();// 把选择好的字体动态保存下来
				Lcs.setFont(new Font(tfzt.getText(), getCharFont(tfzx.getText()), Integer.parseInt(tfzh.getText())));// 设置展示框里头标签的字体，字形，字号
			}
		});
		spzt = new JScrollPane(style);
		spzt.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spzt.setBounds(20, 50, 150, 140);
		this.add(spzt);
		/*************************************************************************************************/
		// 字型选择
		Lzx = new JLabel("字型(Y):");
		Lzx.setBounds(180, 15, 80, 17);
		this.add(Lzx);
		tfzx = new JLabel("常规");
		tfzx.setOpaque(true);
		tfzx.setBackground(Color.WHITE);
		tfzx.setBounds(180, 32, 110, 18);
		this.add(tfzx);
		font = new JList(getCharFont());
		font.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				tfzx.setText(font.getSelectedValue().toString());
				zx = getCharFont(tfzx.getText());// 把选择好的字形动态保存下来
				Lcs.setFont(new Font(tfzt.getText(), getCharFont(tfzx.getText()), Integer.parseInt(tfzh.getText())));
			}
		});
		spzx = new JScrollPane(font);
		spzx.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spzx.setBounds(180, 50, 110, 140);
		this.add(spzx);
		/*************************************************************************************************/
		// 字号选择
		Lzh = new JLabel("字号(S):");
		Lzh.setBounds(300, 15, 50, 17);
		this.add(Lzh);
		tfzh = new JLabel("12");
		tfzh.setOpaque(true);
		tfzh.setBackground(Color.WHITE);
		tfzh.setBounds(300, 32, 50, 18);
		this.add(tfzh);
		size = new JList(getCharSize());
		size.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				tfzh.setText(size.getSelectedValue().toString());
				zh = Integer.parseInt(tfzh.getText());// 把选择好的字号动态保存下来
				Lcs.setFont(new Font(tfzt.getText(), getCharFont(tfzx.getText()), Integer.parseInt(tfzh.getText())));
			}
		});
		spzh = new JScrollPane(size);
		spzh.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spzh.setBounds(300, 50, 50, 140);
		this.add(spzh);
		/*************************************************************************************************/
		// 展示文本
		Psl = new JPanel();
		Psl.setLayout(null);
		Psl.setBorder(BorderFactory.createTitledBorder("示例"));
		Psl.setBounds(20, 210, 170, 120);
		this.add(Psl);
		Lcs = new JLabel("<html>测试");
		Lcs.setHorizontalAlignment(SwingConstants.CENTER);
		Lcs.setBounds(5, 15, 160, 90);
		Psl.add(Lcs);
		// 自己输入内容
		tfsr = new JTextField();
		tfsr.setBounds(21, 330, 90, 20);
		this.add(tfsr);
		Bsr = new JButton("输入");
		Bsr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfsr.getText().equals("")) {
					return;
				}
				Lcs.setText("<html>" + tfsr.getText()); // 可能在这里我们不欢迎输
														// 入像<html>这样的字符串
			}
		});
		Bsr.setBounds(110, 330, 80, 20);
		this.add(Bsr);
	}
	/*************************************************************************************************/
	public String[] getFontStyle() {
		Font[] systemFont = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		String[] systemFontString = new String[systemFont.length];
		for (int i = 0; i < systemFontString.length; i++) {
			systemFontString[i] = systemFont[i].getName();
			// systemFontString[i] = systemFont[i].getFontName();
		}

		return systemFontString;
	}

	public String[] getCharFont() {

		String[] font = { "常规", "粗体", "斜体", "粗斜体" };
		return font;
	}

	public int getCharFont(String str) {

		if (str.equals("常规")) {
			return Font.PLAIN;
		} else if (str.equals("粗体")) {
			return Font.BOLD;
		} else if (str.equals("斜体")) {
			return Font.ITALIC;
		} else {
			return Font.ITALIC + Font.BOLD;
		}
	}

	/*************************************************************************************************/

	public String[] getCharSize() {

		String[] size = new String[70];
		for (int i = 0; i < size.length; i++) {
			size[i] = Integer.toString(i + 8);
		}
		return size;
	}
	/*************************************************************************************************/
	public String getZT(){
		return zt;
	}
	public int getZX(){
		return zx;
	}
	public int getZH(){
		return zh;
	}
}
public class MyExcelTest {

	public static void main(String[] args) {
		new MyExcel();

	}

}
