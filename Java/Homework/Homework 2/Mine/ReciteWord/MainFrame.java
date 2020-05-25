import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class MainFrame {
	private Word word;
	private JFrame f;
	private JMenuBar mMenBar;
	private JMenu mMenuSelect, mMenuHelp, mMenuReview;
	private JMenuItem mItemReviewW, mItemReviewM, mItemAbout, mItemHelp, mItemContinue, mItemRefresh, mItemWord;
	private JPanel topPanel, bottomPanel, middlePanel;
	private JButton judgeAns, skip, mark;
	private JLabel en, cn, pgwd, pgwr, pg, pgmk, tm, md;
	private DefaultListModel<String> listModel;
	private JList<String> sourceList;
	private JLabel sourceLabel, nLabel;
	private JPanel panelContainer;
	private JTextField tf1;
	private JProgressBar jProgressBar;
	private DefaultListModel<String> targetListModel;
	private int count, countm;
	private ProgressBarThread progressBarThread;

	class ProgressBarThread extends Thread {
		private int curTime = 15;
		boolean go = true;

		public void end() {
			go = false;
		}

		public void run() {
			judgeAns.setEnabled(false);
			nLabel.setVisible(false);
			tf1.setEnabled(false);
			jProgressBar.setVisible(true);
			tm.setVisible(true);
			while (go) {
				if (curTime >= 0) {
					try {
						tm.setText("时间剩余：" + curTime + " 秒");
						jProgressBar.setValue(curTime--);
						sleep(1000);
					} catch (InterruptedException ex) {
						Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
					}
				} else {
					judgeAns.setEnabled(true);
					nLabel.setVisible(true);
					tf1.setEnabled(true);
					jProgressBar.setValue(0);
					en.setVisible(false);
					jProgressBar.setVisible(false);
					tm.setVisible(false);
					go = false;
					jProgressBar.setValue(15);
				}
			}
		}
	}

	public MainFrame() throws IOException {
		count = 0;
		countm = 0;
		word = new Word();
		panelContainer = new JPanel();
		topPanel = new JPanel();
		listModel = new DefaultListModel<String>();
		sourceLabel = new JLabel("已背诵单词：");
		judgeAns = new JButton("提交");
		skip = new JButton("跳过");
		mark = new JButton("收藏");
		cn = new JLabel("中文");
		en = new JLabel("英文");
		nLabel = new JLabel("请输入答案：");
		md = new JLabel("模式：记忆新词");
		if (word.getwordTotalLine() == 0)
			pg = new JLabel("进度 ：0%");
		else
			pg = new JLabel("进度 ：" + 100.0 * word.getwordLine() / word.getwordTotalLine() + "%");
		pgwd = new JLabel("已背单词：" + word.getwordLine() + "/" + word.getwordTotalLine());
		pgwr = new JLabel("已背错词：" + word.getwrongLine() + "/" + word.getwrongTotalLine());
		pgmk = new JLabel("已背收藏词：" + word.getmarkLine() + "/" + word.getmarkTotalLine());
		tm = new JLabel("时间剩余：15 秒");
		f = new JFrame("Hollow 智能背单词软件 V1.0");
		tf1 = new JTextField();
		tf1.setHorizontalAlignment(JTextField.CENTER);
		en.setFont(new Font(null, Font.PLAIN, 32));
		cn.setFont(new Font(null, Font.PLAIN, 16));
		tm.setFont(new Font(null, Font.PLAIN, 16));
		md.setFont(new Font(null, Font.PLAIN, 16));
		jProgressBar = new JProgressBar();
		jProgressBar.setMaximum(15);
		jProgressBar.setPreferredSize(new java.awt.Dimension(146, 10));
		progressBarThread = new ProgressBarThread();
		mMenBar = new JMenuBar();
		mMenuSelect = new JMenu("选项");
		mMenuHelp = new JMenu("帮助");
		mMenuReview = new JMenu("复习");
		mItemReviewW = new JMenuItem("背错的单词");
		mItemReviewM = new JMenuItem("收藏的单词");
		mItemAbout = new JMenuItem("关于");
		mItemHelp = new JMenuItem("使用说明");
		mItemContinue = new JMenuItem("继续上次");
		mItemRefresh = new JMenuItem("重新开始");
		mItemWord = new JMenuItem("记忆新词(默认)");
		targetListModel = new DefaultListModel<String>();
	}

	public void run() {
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.add(md);
		topPanel.add(pg);
		topPanel.add(pgwd);
		topPanel.add(pgwr);
		topPanel.add(pgmk);
		topPanel.add(tm);
		middlePanel = new JPanel();
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
		sourceLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		sourceLabel.setBorder(BorderFactory.createEmptyBorder(4, 5, 0, 5));
		sourceList = new JList<String>(listModel);
		sourceList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		sourceList.setVisibleRowCount(5);
		JScrollPane sourceListScroller = new JScrollPane(sourceList);
		sourceListScroller.setPreferredSize(new Dimension(120, 80));
		sourceListScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sourceListScroller.setAlignmentY(Component.TOP_ALIGNMENT);
		JPanel sourceListPanel = new JPanel();
		sourceListPanel.setLayout(new BoxLayout(sourceListPanel, BoxLayout.X_AXIS));
		sourceListPanel.add(sourceLabel);
		sourceListPanel.add(sourceListScroller);
		sourceListPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		sourceListPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
		middlePanel.add(sourceListPanel);
		progressBarThread.start();
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(Box.createRigidArea(new Dimension(15, 15)));
		buttonPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 5, 15, 5));
		middlePanel.add(buttonPanel);

		JLabel targetLabel = new JLabel("错误及收藏单词：");
		targetLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		targetLabel.setBorder(BorderFactory.createEmptyBorder(4, 5, 0, 5));
		JList<String> targetList = new JList<String>(targetListModel);
		targetList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		targetList.setVisibleRowCount(5);
		JScrollPane targetListScroller = new JScrollPane(targetList);
		targetListScroller.setPreferredSize(new Dimension(120, 80));
		targetListScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		targetListScroller.setAlignmentY(Component.TOP_ALIGNMENT);
		JPanel targetListPanel = new JPanel();
		targetListPanel.setLayout(new BoxLayout(targetListPanel, BoxLayout.X_AXIS));
		targetListPanel.add(targetLabel);
		targetListPanel.add(targetListScroller);
		targetListPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		targetListPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
		middlePanel.add(targetListPanel);
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
		bottomPanel.add(jProgressBar);

		bottomPanel.add(en);
		bottomPanel.add(Box.createVerticalStrut(10));
		bottomPanel.add(cn);
		bottomPanel.add(Box.createVerticalStrut(10));
		bottomPanel.add(nLabel);
		bottomPanel.add(Box.createVerticalStrut(10));
		bottomPanel.add(tf1);
		bottomPanel.add(Box.createVerticalStrut(10));
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(skip);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(judgeAns);

		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(mark);
		bottomPanel.add(Box.createVerticalStrut(10));
		bottomPanel.add(buttonPanel);
		bottomPanel.add(Box.createVerticalStrut(10));
		panelContainer.setLayout(new GridBagLayout());

		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridx = 0;
		c1.gridy = 0;
		c1.weightx = 1.0;
		c1.weighty = 1.0;

		c1.fill = GridBagConstraints.BOTH;
		panelContainer.add(topPanel, c1);

		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 1;
		c2.weightx = 1.0;
		c2.weighty = 0;
		c2.fill = GridBagConstraints.HORIZONTAL;
		panelContainer.add(bottomPanel, c2);

		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 0;
		c3.gridy = 2;
		c3.weightx = 1.0;
		c3.weighty = 0;
		c3.fill = GridBagConstraints.HORIZONTAL;
		panelContainer.add(middlePanel, c3);
		mMenuReview.add(mItemReviewW);
		mMenuReview.add(mItemReviewM);
		mMenuSelect.add(mItemWord);
		mMenuSelect.add(mMenuReview);
		mMenuSelect.add(mItemContinue);
		mMenuSelect.add(mItemRefresh);
		mMenuHelp.add(mItemHelp);
		mMenuHelp.add(mItemAbout);
		mMenBar.add(mMenuSelect);
		mMenBar.add(mMenuHelp);
		// 监听各个菜单项的动作并调用相应函数
		mItemAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"作者：\n               Hollow Man\n联系邮箱：\n      hollowman186@qq.com",
						"关于", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mItemHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "本程序帮助你记忆单词。\n词库存放在软件目录下word.txt文件中\n你拥有15秒钟记忆单词的时间，然后你必须拼写出单词！",
						"使用说明", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mItemReviewW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (md.getText().equals("模式：复习错词"))
					JOptionPane.showMessageDialog(null, "当前已在该模式下！", "提示", JOptionPane.INFORMATION_MESSAGE);
				else {
					if (word.readWord("wrong")) {
						countm = 0;
						count = 0;
						en.setText(word.getEnWord());
						cn.setText(word.getCnTrans());
						progressBarThread.end();
						progressBarThread = new ProgressBarThread();
						progressBarThread.start();
						md.setText("模式：复习错词");
						JOptionPane.showMessageDialog(null, "成功切换至复习错词模式！", "成功", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "你已经背完了所有错词，重新开始吧！", "没有单词了", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		mItemWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (md.getText().equals("模式：记忆新词"))
					JOptionPane.showMessageDialog(null, "当前已在该模式下！", "提示", JOptionPane.INFORMATION_MESSAGE);
				else {

					if (word.readWord("word")) {
						countm = 0;
						count = 0;
						en.setText(word.getEnWord());
						cn.setText(word.getCnTrans());
						progressBarThread.end();
						progressBarThread = new ProgressBarThread();
						progressBarThread.start();
						md.setText("模式：记忆新词");
						JOptionPane.showMessageDialog(null, "成功切换至记忆新词模式！", "成功", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "你已经背完了所有新词，重新开始吧！", "没有单词了", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		mItemReviewM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (md.getText().equals("模式：复习收藏词"))
					JOptionPane.showMessageDialog(null, "当前已在该模式下！", "提示", JOptionPane.INFORMATION_MESSAGE);
				else {
					if (word.readWord("mark")) {
						countm = 0;
						count = 0;
						en.setText(word.getEnWord());
						cn.setText(word.getCnTrans());
						progressBarThread.end();
						progressBarThread = new ProgressBarThread();
						progressBarThread.start();
						md.setText("模式：复习收藏词");
						JOptionPane.showMessageDialog(null, "成功切换至复习收藏词模式！", "成功", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "你已经背完了所有收藏词，重新开始吧！", "没有单词了", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		mItemRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (md.getText().equals("模式：复习收藏词")) {
					if (word.setPstLine(word.getwordLine(), word.getwrongLine(), 0)) {
						progressBarThread.end();
						try {
							newWord("mark");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "文件读取错误！", "错误", JOptionPane.ERROR_MESSAGE);
						}
						JOptionPane.showMessageDialog(null, "成功！", "重新开始", JOptionPane.INFORMATION_MESSAGE);
					} else
						JOptionPane.showMessageDialog(null, "失败！", "重新开始", JOptionPane.ERROR_MESSAGE);
				} else if (md.getText().equals("模式：复习错词")) {
					if (word.setPstLine(word.getwordLine(), 0, word.getmarkLine())) {
						progressBarThread.end();
						try {
							newWord("wrong");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "文件读取错误！", "错误", JOptionPane.ERROR_MESSAGE);
						}
						JOptionPane.showMessageDialog(null, "成功！", "重新开始", JOptionPane.INFORMATION_MESSAGE);
					} else
						JOptionPane.showMessageDialog(null, "失败！", "重新开始", JOptionPane.ERROR_MESSAGE);
				} else if (md.getText().equals("模式：记忆新词")) {
					if (word.setPstLine(0, word.getwrongLine(), word.getmarkLine())) {
						progressBarThread.end();
						try {
							newWord("word");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "文件读取错误！", "错误", JOptionPane.ERROR_MESSAGE);
						}
						JOptionPane.showMessageDialog(null, "成功！", "重新开始", JOptionPane.INFORMATION_MESSAGE);
					} else
						JOptionPane.showMessageDialog(null, "失败！", "重新开始", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mItemContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (word.setPstLine(word.readRecord())) {
						progressBarThread.end();
						if (md.getText().equals("模式：复习收藏词"))
							newWord("mark");
						else if (md.getText().equals("模式：复习错词"))
							newWord("wrong");
						else if (md.getText().equals("模式：记忆新词"))
							newWord("word");
						JOptionPane.showMessageDialog(null, "成功！", "继续上次", JOptionPane.INFORMATION_MESSAGE);
					} else
						JOptionPane.showMessageDialog(null, "失败！未知原因！", "继续上次", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e2) {
					JOptionPane.showMessageDialog(null, "失败！历史文件不存在！", "继续上次", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		judgeAns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					judgeWord();
				} catch (HeadlessException e1) {
					JOptionPane.showMessageDialog(null, "设备不支持！", "错误", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "文件写入错误！", "错误", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		skip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				progressBarThread.end();
				progressBarThread = new ProgressBarThread();
				progressBarThread.start();
				judgeAns.setEnabled(true);
				nLabel.setVisible(true);
				tf1.setEnabled(true);
				en.setVisible(true);
				listModel.addElement(word.getEnWord());
				if (md.getText().equals("模式：复习收藏词"))
					try {
						newWord("mark");
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "文件读取错误！", "错误", JOptionPane.ERROR_MESSAGE);
					}
				else if (md.getText().equals("模式：复习错词"))
					try {
						newWord("wrong");
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "文件读取错误！", "错误", JOptionPane.ERROR_MESSAGE);
					}
				else if (md.getText().equals("模式：记忆新词"))
					try {
						newWord("word");
					} catch (IOException e1) {
					}

			}
		});
		mark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					pgmk.setText("已背收藏词：" + word.getmarkLine() + "/" + word.getmarkTotalLine());
				} catch (IOException e2) {
					JOptionPane.showMessageDialog(null, "文件读取错误！", "错误", JOptionPane.ERROR_MESSAGE);
				}
				if (countm == 0) {
					try {
						word.mark();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "文件写入错误！", "错误", JOptionPane.ERROR_MESSAGE);
					}
					targetListModel.addElement(word.getEnWord());
					countm++;
				}
			}
		});
		f.setSize(600, 440);
		f.setContentPane(panelContainer);
		f.setLocationRelativeTo(null);
		f.setJMenuBar(mMenBar);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					word.saveRecord();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "保存记录失败！", "退出", JOptionPane.ERROR_MESSAGE);
				}
				super.windowClosing(e);
			}
		});
		File file = new File("word.txt");
		if (!file.exists()) {
			progressBarThread.end();
			JOptionPane.showMessageDialog(null, "请在软件目录下放置word.txt文件！", "词库不存在", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		if (word.readWord("word")) {
			en.setText(word.getEnWord());
			cn.setText(word.getCnTrans());
		} else {
			progressBarThread.end();
			JOptionPane.showMessageDialog(null, "你已经背完了所有单词，重新开始或者更新词库吧！", "没有单词了", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void newWord(String choice) throws IOException {
		pg.setText("进度 ：" + 100.0 * word.getwordLine() / word.getwordTotalLine() + "%");
		pgwd.setText("已背单词：" + word.getwordLine() + "/" + word.getwordTotalLine());
		pgwr.setText("已背错词：" + word.getwrongLine() + "/" + word.getwrongTotalLine());
		pgmk.setText("已背收藏词：" + word.getmarkLine() + "/" + word.getmarkTotalLine());
		if (word.readWord(choice)) {
			countm = 0;
			count = 0;
			en.setText(word.getEnWord());
			cn.setText(word.getCnTrans());
			progressBarThread.end();
			progressBarThread = new ProgressBarThread();
			progressBarThread.start();
		} else {
			JOptionPane.showMessageDialog(null, "你已经背完了所有单词，重新开始或者更新词库吧！", "没有单词了", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void judgeWord() throws HeadlessException, IOException {
		pgwr.setText("已背错词：" + word.getwrongLine() + "/" + word.getwrongTotalLine());
		boolean r = false;
		if (count == 0)
			r = true;
		if (word.judge(tf1.getText(), r)) {
			tf1.setText("");
			judgeAns.setEnabled(true);
			nLabel.setVisible(true);
			tf1.setEnabled(true);
			en.setVisible(true);
			listModel.addElement(word.getEnWord());
			if (md.getText().equals("模式：复习收藏词"))
				newWord("mark");
			else if (md.getText().equals("模式：复习错词"))
				newWord("wrong");
			else if (md.getText().equals("模式：记忆新词"))
				newWord("word");
			count = 0;
		} else {
			if (count == 0)
				targetListModel.addElement(word.getEnWord());
			count++;
			JOptionPane.showMessageDialog(null, "请重试！", "拼写错误", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static void main(String[] args) throws IOException {
		MainFrame window = new MainFrame();
		window.run();
	}
}
